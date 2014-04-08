package de.metalcon.middleware.controller.entity;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.hh.request_dispatcher.Callback;
import net.hh.request_dispatcher.Dispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import de.metalcon.api.responses.Response;
import de.metalcon.domain.Muid;
import de.metalcon.middleware.controller.BaseController;
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
import de.metalcon.middleware.core.EntityManager;
import de.metalcon.middleware.core.MetalconPjaxr;
import de.metalcon.middleware.core.UserLogin;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.exception.RedirectException;
import de.metalcon.middleware.view.entity.EntityView;
import de.metalcon.middleware.view.entity.EntityViewFactory;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;
import de.metalcon.middleware.view.entity.tab.preview.EntityTabPreview;
import de.metalcon.urlmappingserver.api.requests.UrlMappingResolveRequest;
import de.metalcon.urlmappingserver.api.responses.MuidResolvedResponse;

/**
 * basic controller for entity requests
 */
public abstract class EntityController<EntityViewType extends EntityView >
        extends BaseController {

    public static class Data extends BaseController.Data {

        private Muid muid;

        public Muid getMuid() {
            return muid;
        }

        public void setMuid(Muid muid) {
            this.muid = muid;
        }

    }

    @Autowired
    protected EntityViewFactory entityViewFactory;

    @Autowired
    private EntityManager entityManager;

    private EntityType entityType;

    private Class<? extends EntityView> entityViewClass;

    private Map<EntityTabType, EntityTabGenerator<?, ?>> entityTabsGenerators;

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

    protected final EntityViewType createView() {
        @SuppressWarnings("unchecked")
        EntityViewType view =
                (EntityViewType) entityViewFactory.createView(entityViewClass);
        return view;
    }

    /**
     * create and fill view object
     * 
     * @param entityTabType
     *            Type of requested tab.
     * @return filled view object
     * @throws RedirectException
     *             TODO
     * @throws NoSuchRequestHandlingMethodException
     *             If entity type doesn't have requested tab type or MUID
     *             couldn't be resolved.
     */
    protected void handleGet(Data data, EntityTabType entityTabType)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        super.handleGet(data);

        EntityTabController entityTabController =
                getEntityTabController(entityTabType);

        Muid muid =
                getMuidAndCheck404(entityTabType, data.getPathVars(),
                        data.getHttpServletRequest());

        @SuppressWarnings("unchecked")
        EntityViewType view = (EntityViewType) data.getView();
        view.setMuid(muid);

        String pjaxrNamespace =
                getMetalconNamespace() + "." + getEntityType().toString() + "."
                        + muid + "." + entityTabType.toString().toLowerCase();
        MetalconPjaxr pjaxr =
                new MetalconPjaxr(data.getHttpServletRequest(), pjaxrNamespace);

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
                            entityTabPreviewGenerator.generateTabPreview(muid);
                    entityTabPreviews.put(entityTabPreviewType,
                            entityTabPreview);
                }
            }
            view.setEntityTabPreviews(entityTabPreviews);
        }
        if (pjaxr.isPjaxrInnerContent()) {
            // create empty tab content and fill it with data from entity
            EntityTabGenerator<?, ?> entityTabGenerator =
                    getEntityTabGenerator(entityTabType);

            EntityTabContent entityTabContent =
                    entityTabGenerator.generateTabContent(muid);
            view.setEntityTabContent(entityTabContent);
        }
        view.setPjaxrMatching(pjaxr.getMatchingCount());
        view.setPjaxrNamespace(pjaxrNamespace);

        entityTabController.handleGet(data, this);
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
    public Muid getMuidAndCheck404(
            EntityTabType entityTabType,
            Map<String, String> pathVars,
            HttpServletRequest httpServletRequest) throws RedirectException,
            NoSuchRequestHandlingMethodException {
        final Muid[] muidResponse = new Muid[1];

        Dispatcher dispatcher = dispatcherFactory.dispatcher();
        dispatcher.execute(
                new UrlMappingResolveRequest(pathVars, EntityType
                        .toUidType(getEntityType())), new Callback<Response>() {

                    @Override
                    public void onSuccess(Response response) {
                        if (response instanceof MuidResolvedResponse) {
                            muidResponse[0] =
                                    ((MuidResolvedResponse) response).getMuid();
                        } else {
                            muidResponse[0] = null;
                        }
                    }

                });
        dispatcher.gatherResults(50);

        Muid muid = muidResponse[0];

        if (entityTabsGenerators.get(entityTabType) == null || muid == null) {
            throw new NoSuchRequestHandlingMethodException(httpServletRequest);
        }

        return muid;
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
            throws RedirectException, NoSuchRequestHandlingMethodException {
        Data data = new Data();
        data.setHttpServletRequest(httpServletRequest);
        data.setHttpServletResponse(httpServletResponse);
        data.setPathVars(pathVars);
        data.setUserLogin(userLogin);

        EntityTabType entityTabType =
                getEntityTabTypeFromString(pathVars.get("pathTab"));

        if (entityTabType == null) {
            throw new NoSuchRequestHandlingMethodException(
                    data.getHttpServletRequest());
        }

        EntityViewType view = createView();
        data.setView(view);

        handleGet(data, entityTabType);

        return view;
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
            //@formmater:on
            default:
                return null;
        }
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

    // methods to be overwritten:

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

}
