package de.metalcon.middleware.controller.entity;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.hh.request_dispatcher.Callback;
import net.hh.request_dispatcher.Dispatcher;
import net.hh.request_dispatcher.RequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import de.metalcon.domain.Muid;
import de.metalcon.middleware.controller.BaseController;
import de.metalcon.middleware.controller.LikeController;
import de.metalcon.middleware.controller.LikeData;
import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.AboutTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.BandsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.EventsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.NewsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.PhotosTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecommendationsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.RecordsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.ReviewsTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.TracksTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.UsersTabGenerator;
import de.metalcon.middleware.controller.entity.generator.impl.VenuesTabGenerator;
import de.metalcon.middleware.controller.entity.tab.EntityTabController;
import de.metalcon.middleware.controller.entity.tab.impl.AboutTabController;
import de.metalcon.middleware.controller.entity.tab.impl.BandsTabController;
import de.metalcon.middleware.controller.entity.tab.impl.EventsTabController;
import de.metalcon.middleware.controller.entity.tab.impl.NewsTabController;
import de.metalcon.middleware.controller.entity.tab.impl.PhotosTabController;
import de.metalcon.middleware.controller.entity.tab.impl.RecommendationsTabController;
import de.metalcon.middleware.controller.entity.tab.impl.RecordsTabController;
import de.metalcon.middleware.controller.entity.tab.impl.ReviewsTabController;
import de.metalcon.middleware.controller.entity.tab.impl.TracksTabController;
import de.metalcon.middleware.controller.entity.tab.impl.UsersTabController;
import de.metalcon.middleware.controller.entity.tab.impl.VenuesTabController;
import de.metalcon.middleware.core.MetalconPjaxr;
import de.metalcon.middleware.core.SddOutputGenerator;
import de.metalcon.middleware.core.UserLogin;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.exception.RedirectException;
import de.metalcon.middleware.sdd.SddOutput;
import de.metalcon.middleware.view.entity.EntityView;
import de.metalcon.middleware.view.entity.EntityViewFactory;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;
import de.metalcon.middleware.view.entity.tab.preview.EntityTabPreview;
import de.metalcon.sdd.api.requests.SddReadRequest;
import de.metalcon.sdd.api.responses.SddResponse;
import de.metalcon.sdd.api.responses.SddSucessfulReadResponse;
import de.metalcon.urlmappingserver.api.requests.ResolveMuidRequest;
import de.metalcon.urlmappingserver.api.requests.ResolveUrlRequest;
import de.metalcon.urlmappingserver.api.responses.MuidResolvedResponse;
import de.metalcon.urlmappingserver.api.responses.UrlResolvedResponse;

/**
 * basic controller for entity requests
 */
public abstract class EntityController<EntityViewType extends EntityView >
        extends BaseController {

    public static class Data extends BaseController.Data {

        private Muid muid;

        private EntityType entityType;

        private EntityTabType entityTabType;

        private EntityTabController entityTabController;

        /**
         * Null until page Callback is executed.
         */
        private SddOutput page;

        private Callback<SddResponse> pageCallback;

        public Muid getMuid() {
            return muid;
        }

        public void setMuid(Muid muid) {
            this.muid = muid;
        }

        public EntityType getEntityType() {
            return entityType;
        }

        public void setEntityType(EntityType entityType) {
            this.entityType = entityType;
        }

        public EntityTabType getEntityTabType() {
            return entityTabType;
        }

        public void setEntityTabType(EntityTabType entityTabType) {
            this.entityTabType = entityTabType;
        }

        public EntityTabController getEntityTabController() {
            return entityTabController;
        }

        public void setEntityTabController(
                EntityTabController entityTabController) {
            this.entityTabController = entityTabController;
        }

        public SddOutput getPage() {
            return page;
        }

        public void setPage(SddOutput page) {
            this.page = page;
        }

        public Callback<SddResponse> getPageCallback() {
            return pageCallback;
        }

        public void setPageCallback(Callback<SddResponse> pageCallback) {
            this.pageCallback = pageCallback;
        }

    }

    @Autowired
    protected EntityViewFactory entityViewFactory;

    private final EntityType entityType;

    private final Class<? extends EntityView> entityViewClass;

    private final Map<EntityTabType, EntityTabGenerator<?, ?>> entityTabsGenerators;

    public EntityController(
            EntityType entityType,
            Class<? extends EntityView> entityViewClass) {
        this.entityType = entityType;
        this.entityViewClass = entityViewClass;
        entityTabsGenerators =
                new HashMap<EntityTabType, EntityTabGenerator<?, ?>>();
    }

    @PostConstruct
    public void init() {
        fillEntityTabGenerators();
    }

    /**
     * @return type of the requested entity
     */
    public final EntityType getEntityType() {
        return entityType;
    }

    private final EntityViewType createView() {
        @SuppressWarnings("unchecked")
        EntityViewType view =
                (EntityViewType) entityViewFactory.createView(entityViewClass);
        return view;
    }

    public final EntityViewType mappingEmptyTabGet(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable Map<String, String> pathVars,
            @AuthenticationPrincipal UserLogin userLogin)
            throws RedirectException, NoSuchRequestHandlingMethodException,
            URISyntaxException {
        String requestUri = httpServletRequest.getRequestURI();

        int fileExtensionPos = requestUri.lastIndexOf(".");
        String fileExtension, redirectPath;
        if (fileExtensionPos != -1) {
            fileExtension = requestUri.substring(fileExtensionPos);
            redirectPath = requestUri.substring(0, fileExtensionPos);
        } else {
            fileExtension = "";
            redirectPath = requestUri;
        }

        if (!redirectPath.endsWith("/")) {
            redirectPath += "/";
        }
        redirectPath += "news" + fileExtension;

        throw new RedirectException(redirectPath);
    }

    public final EntityViewType mappingTabGet(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable Map<String, String> pathVars,
            @AuthenticationPrincipal UserLogin userLogin)
            throws RedirectException, NoSuchRequestHandlingMethodException,
            RequestException {
        final Data data = new Data();
        data.setHttpServletRequest(httpServletRequest);
        data.setHttpServletResponse(httpServletResponse);
        data.setPathVars(pathVars);
        data.setUserLogin(userLogin);
        data.setEntityType(getEntityType());

        EntityTabType entityTabType =
                getEntityTabTypeFromString(pathVars.get("pathTab"));
        data.setEntityTabType(entityTabType);

        if (data.getEntityTabType() == null) {
            throw new NoSuchRequestHandlingMethodException(
                    data.getHttpServletRequest());
        }

        final EntityViewType view = createView();
        data.setView(view);

        beforeRequest(data);

        data.setEntityTabController(getEntityTabController(data
                .getEntityTabType()));

        // ATTENTION BLOCKING CALL (100 ms)
        Muid muid = getMuidOr404(data);
        data.setMuid(muid);
        view.setMuid(muid);

        Dispatcher dispatcher = dispatcherFactory.dispatcher();

        LikeData likeData = LikeController.getLikeCounts(dispatcher, muid);

        dispatcher.execute(new ResolveMuidRequest(muid),
                new Callback<MuidResolvedResponse>() {

                    @Override
                    public void onSuccess(MuidResolvedResponse reply) {

                        view.setUrlPath("/" + getPathPrefix(getEntityType())
                                + "/" + reply.getUrl());
                    }

                });

        String pjaxrNamespace =
                METALCON_NAMESPACE + "." + getEntityType().toString() + "."
                        + muid + "."
                        + data.getEntityTabType().toString().toLowerCase();
        MetalconPjaxr pjaxr =
                new MetalconPjaxr(data.getHttpServletRequest(), pjaxrNamespace);
        view.setPjaxrMatching(pjaxr.getMatchingCount());
        view.setPjaxrNamespace(pjaxrNamespace);

        if (pjaxr.isPjaxrInnerContent()) {
            data.setPageCallback(new Callback<SddResponse>() {

                @Override
                public void onSuccess(SddResponse response) {
                    if (response instanceof SddSucessfulReadResponse) {
                        data.setPage(SddOutputGenerator.get(
                                (SddSucessfulReadResponse) response,
                                data.getMuid(), "page"));
                    } else {
                        System.out.println("read failed");
                    }
                }

            });

            SddReadRequest read = new SddReadRequest();
            read.read(muid, "page");
            dispatcher.execute(read, data.getPageCallback());

            if (pjaxr.isPjaxrContent()) {
                // create tab previews if content
                Map<EntityTabType, EntityTabPreview> entityTabPreviews =
                        new HashMap<EntityTabType, EntityTabPreview>();
                for (Map.Entry<EntityTabType, EntityTabGenerator<?, ?>> entry : entityTabsGenerators
                        .entrySet()) {
                    EntityTabType entityTabPreviewType = entry.getKey();
                    EntityTabGenerator<?, ?> entityTabPreviewGenerator =
                            entry.getValue();
                    if (entityTabPreviewGenerator != null) {
                        EntityTabPreview entityTabPreview =
                                entityTabPreviewGenerator
                                        .generateTabPreview(data);
                        entityTabPreviews.put(entityTabPreviewType,
                                entityTabPreview);
                    }
                }
                view.setEntityTabPreviews(entityTabPreviews);
            }

            // create empty tab content and fill it with data from entity
            EntityTabGenerator<?, ?> entityTabGenerator =
                    getEntityTabGenerator(data.getEntityTabType());

            EntityTabContent entityTabContent =
                    entityTabGenerator.generateTabContent(data);
            view.setEntityTabContent(entityTabContent);
        }

        data.getEntityTabController().handleGet(data, this);

        afterRequest(data);
        view.setEntityName(data.getPage().getName());
        view.setNumLikeUp(likeData.getUpVoteNum());
        view.setNumLikeDown(likeData.getDownVoteNum());

        return view;
    }

    /**
     * reverse mapping of routes.conf. every entity type is resolved to a prefix
     * in order to be able to build a url
     * 
     * @param entityType
     * @return
     */
    protected String getPathPrefix(EntityType entityType) {
        //@formatter:off
        if (entityType.equals(EntityType.BAND)) {
            return "music";
        }
        if (entityType.equals(EntityType.RECORD)) {
            return "music";
        }
        if (entityType.equals(EntityType.TRACK)) {
            return "music";
        }
        if (entityType.equals(EntityType.CITY)) {
            return "city";
        }
        if (entityType.equals(EntityType.VENUE)) {
            return "venue";
        }
        if (entityType.equals(EntityType.EVENT)) {
            return "event";
        }
        if (entityType.equals(EntityType.TOUR)) {
            return "tour";
        }
        if (entityType.equals(EntityType.GENRE)) {
            return "genre";
        }
        if (entityType.equals(EntityType.INSTRUMENT)) {
            return "instrument";
        }
        if (entityType.equals(EntityType.USER))
         {
            return "user";
            //@formatter:on
        }

        return null;
    }

    private EntityTabType getEntityTabTypeFromString(String tabString) {
        switch (tabString) {
        //@formatter:off
            case "about":           return EntityTabType.ABOUT;
            case "bands":           return EntityTabType.BANDS;
            case "events":          return EntityTabType.EVENTS;
            case "news":            return EntityTabType.NEWS;
            case "photos":          return EntityTabType.PHOTOS;
            case "recommendations": return EntityTabType.RECOMMENDATIONS;
            case "records":         return EntityTabType.RECORDS;
            case "reviews":         return EntityTabType.REVIEWS;
            case "tracks":          return EntityTabType.TRACKS;
            case "users":           return EntityTabType.USERS;
            case "venues":          return EntityTabType.VENUES;
            //@formatter:on
            default:
                return null;
        }
    }

    /**
     * Checks whether the current EntityType has the requested tab and 404s
     * if not. Returns the entity object.
     * 
     * @param entityTabType
     *            Type of request tab.
     * @return The entities MUID.
     * @throws RedirectException
     *             TODO
     * @throws NoSuchRequestHandlingMethodException
     *             If entity type doesn't have requested tab type or MUID
     *             couldn't be resolved.
     */
    public Muid getMuidOr404(Data data) throws RedirectException,
            NoSuchRequestHandlingMethodException {
        Dispatcher dispatcher = dispatcherFactory.dispatcher();

        Muid muid = null;
        try {
            ResolveUrlRequest request =
                    new ResolveUrlRequest(data.getPathVars(),
                            EntityType.toUidType(getEntityType()));
            UrlResolvedResponse response =
                    (UrlResolvedResponse) dispatcher.executeSync(request, 100);
            muid = response.getMuid();
        } catch (net.hh.request_dispatcher.RequestException | TimeoutException
                | ClassCastException e) {
            // muid == null will throw 404
        }

        if (entityTabsGenerators.get(data.getEntityTabType()) == null
                || muid == null) {
            throw new NoSuchRequestHandlingMethodException(
                    data.getHttpServletRequest());
        }

        return muid;
    }

    //// TAB CONTROLLERS ///////////////////////////////////////////////////////

    @Autowired
    private AboutTabController aboutTabController;

    @Autowired
    private BandsTabController bandsTabController;

    @Autowired
    private EventsTabController eventsTabController;

    @Autowired
    private NewsTabController newsTabController;

    @Autowired
    private PhotosTabController photosTabController;

    @Autowired
    private RecommendationsTabController recommendationsTabController;

    @Autowired
    private RecordsTabController recordsTabController;

    @Autowired
    private ReviewsTabController reviewsTabController;

    @Autowired
    private TracksTabController tracksTabController;

    @Autowired
    private UsersTabController usersTabController;

    @Autowired
    private VenuesTabController venuesTabController;

    protected final EntityTabController getEntityTabController(
            EntityTabType entityTabType) {
        switch (entityTabType) {
        // @formatter:off
            case ABOUT:           return aboutTabController;
            case BANDS:           return bandsTabController;
            case EVENTS:          return eventsTabController;
            case NEWS:            return newsTabController;
            case PHOTOS:          return photosTabController;
            case RECOMMENDATIONS: return recommendationsTabController;
            case RECORDS:         return recordsTabController;
            case REVIEWS:         return reviewsTabController;
            case TRACKS:          return tracksTabController;
            case USERS:           return usersTabController;
            case VENUES:          return venuesTabController;
            // @formatter:on
            default:
                throw new IllegalStateException("Unimplemented EntityTabType:"
                        + entityTabType.toString() + ".");
        }
    }

    //// TAB GENERATORS ////////////////////////////////////////////////////////

    // START: Methods to overwrite.

    protected AboutTabGenerator getAboutTabGenerator() {
        return null;
    }

    protected BandsTabGenerator getBandsTabGenerator() {
        return null;
    }

    protected EventsTabGenerator getEventsTabGenerator() {
        return null;
    }

    protected NewsTabGenerator getNewsTabGenerator() {
        return null;
    }

    protected PhotosTabGenerator getPhotosTabGenerator() {
        return null;
    }

    protected RecommendationsTabGenerator getRecommendationsTabGenerator() {
        return null;
    }

    protected RecordsTabGenerator getRecordsTabGenerator() {
        return null;
    }

    protected ReviewsTabGenerator getReviewsTabGenerator() {
        return null;
    }

    protected TracksTabGenerator getTracksTabGenerator() {
        return null;
    }

    protected UsersTabGenerator getUsersTabGenerator() {
        return null;
    }

    protected VenuesTabGenerator getVenuesTabGenerator() {
        return null;
    }

    // END: Methods to overwrite.

    /**
     * fill tab generator map with all generators implemented
     */
    private final void fillEntityTabGenerators() {
        // @formatter:off
        entityTabsGenerators.put(EntityTabType.ABOUT,           getAboutTabGenerator());
        entityTabsGenerators.put(EntityTabType.BANDS,           getBandsTabGenerator());
        entityTabsGenerators.put(EntityTabType.EVENTS,          getEventsTabGenerator());
        entityTabsGenerators.put(EntityTabType.NEWS,            getNewsTabGenerator());
        entityTabsGenerators.put(EntityTabType.PHOTOS,          getPhotosTabGenerator());
        entityTabsGenerators.put(EntityTabType.RECOMMENDATIONS, getRecommendationsTabGenerator());
        entityTabsGenerators.put(EntityTabType.RECORDS,         getRecordsTabGenerator());
        entityTabsGenerators.put(EntityTabType.REVIEWS,         getReviewsTabGenerator());
        entityTabsGenerators.put(EntityTabType.TRACKS,          getTracksTabGenerator());
        entityTabsGenerators.put(EntityTabType.USERS,           getUsersTabGenerator());
        entityTabsGenerators.put(EntityTabType.VENUES,          getVenuesTabGenerator());
        // @formatter:on
    }

    protected final EntityTabGenerator<?, ?> getEntityTabGenerator(
            EntityTabType entityTabType) {
        return entityTabsGenerators.get(entityTabType);
    }

}
