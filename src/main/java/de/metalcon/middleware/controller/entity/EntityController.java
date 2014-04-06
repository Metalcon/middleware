package de.metalcon.middleware.controller.entity;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import de.metalcon.domain.Muid;
import de.metalcon.middleware.controller.MetalconController;
import de.metalcon.middleware.controller.Request;
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
import de.metalcon.middleware.core.EntityUrlMapppingManager;
import de.metalcon.middleware.core.MetalconPjaxr;
import de.metalcon.middleware.core.UserLogin;
import de.metalcon.middleware.domain.entity.Entity;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.exception.RedirectException;
import de.metalcon.middleware.view.entity.EntityView;
import de.metalcon.middleware.view.entity.EntityViewFactory;
import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.EntityTabContent;
import de.metalcon.middleware.view.entity.tab.preview.EntityTabPreview;

/**
 * basic controller for entity requests
 */
public abstract class EntityController<EntityViewType extends EntityView >
        extends MetalconController {

    @Autowired
    protected EntityViewFactory entityViewFactory;

    @Autowired
    protected EntityUrlMapppingManager entityUrlMappingManager;

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
    protected void handleGet(Request request, EntityTabType entityTabType)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        EntityTabController entityTabController =
                getEntityTabController(entityTabType);

        Muid muid =
                getMuidAndCheck404(entityTabType, request.getPathVars(),
                        request.getHttpServletRequest());

        String pjaxrNamespace =
                getMetalconNamespace() + "." + getEntityType().toString() + "."
                        + muid + "." + entityTabType.toString().toLowerCase();
        MetalconPjaxr pjaxr =
                new MetalconPjaxr(request.getHttpServletRequest(),
                        pjaxrNamespace);

        Entity entity = entityManager.getEntity(muid, getEntityType());

        @SuppressWarnings("unchecked")
        EntityViewType view = (EntityViewType) request.getView();
        view.setMuid(entity.getMuid());

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
                                    .generateTabPreview(entity);
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
                    entityTabGenerator.generateTabContent(entity);
            view.setEntityTabContent(entityTabContent);
        }
        view.setPjaxrMatching(pjaxr.getMatchingCount());
        view.setPjaxrNamespace(pjaxrNamespace);

        entityTabController.handleGet(request, this);
        super.handleGet(request);
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
        // resolve MUID to entity (data model object)
        Muid muid = entityUrlMappingManager.getMuid(getEntityType(), pathVars);

        if (entityTabsGenerators.get(entityTabType) == null || muid == null) {
            throw new NoSuchRequestHandlingMethodException(httpServletRequest);
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

    //// MAPPINGS //////////////////////////////////////////////////////////////

    public final EntityViewType mappingEmptyTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException,
            URISyntaxException {
        String requestUri = request.getRequestURI();

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

    /**
     * handle about tab requests
     * 
     * @param request
     *            servlet request
     * @param pathVars
     *            request path
     * @return filled about tab view
     * @throws RedirectException
     *             TODO
     * @throws NoSuchRequestHandlingMethodException
     *             if no about tab or entity invalid
     */
    public final EntityViewType mappingAboutTabGet(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable Map<String, String> pathVars,
            @AuthenticationPrincipal UserLogin userLogin)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        EntityViewType view = createView();
        Request request =
                createRequest(httpServletRequest, httpServletResponse,
                        pathVars, view, userLogin);
        handleGet(request, EntityTabType.ABOUT);
        return view;
    }

    public final EntityViewType mappingBandsTabGet(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable Map<String, String> pathVars,
            @AuthenticationPrincipal UserLogin userLogin)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        EntityViewType view = createView();
        Request request =
                createRequest(httpServletRequest, httpServletResponse,
                        pathVars, view, userLogin);
        handleGet(request, EntityTabType.BANDS);
        return view;
    }

    public final EntityViewType mappingEventsTabGet(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable Map<String, String> pathVars,
            @AuthenticationPrincipal UserLogin userLogin)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        EntityViewType view = createView();
        Request request =
                createRequest(httpServletRequest, httpServletResponse,
                        pathVars, view, userLogin);
        handleGet(request, EntityTabType.EVENTS);
        return view;
    }

    public final EntityViewType mappingNewsTabGet(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable Map<String, String> pathVars,
            @AuthenticationPrincipal UserLogin userLogin)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        EntityViewType view = createView();
        Request request =
                createRequest(httpServletRequest, httpServletResponse,
                        pathVars, view, userLogin);
        handleGet(request, EntityTabType.NEWS);
        return view;
    }

    public final EntityViewType mappingNewsTabPost(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable Map<String, String> pathVars,
            @AuthenticationPrincipal UserLogin userLogin)
            throws RedirectException, NoSuchRequestHandlingMethodException,
            IOException {
        EntityViewType view = createView();
        Request request =
                createRequest(httpServletRequest, httpServletResponse,
                        pathVars, view, userLogin);
        // TODO
        newsTabController.createNewsItem(request, this);
        return view;
    }

    public final EntityViewType mappingPhotosTabGet(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable Map<String, String> pathVars,
            @AuthenticationPrincipal UserLogin userLogin)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        EntityViewType view = createView();
        Request request =
                createRequest(httpServletRequest, httpServletResponse,
                        pathVars, view, userLogin);
        handleGet(request, EntityTabType.PHOTOS);
        return view;
    }

    public final EntityViewType mappingRecommendationsTabGet(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable Map<String, String> pathVars,
            @AuthenticationPrincipal UserLogin userLogin)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        EntityViewType view = createView();
        Request request =
                createRequest(httpServletRequest, httpServletResponse,
                        pathVars, view, userLogin);
        handleGet(request, EntityTabType.RECOMMENDATIONS);
        return view;
    }

    public final EntityViewType mappingRecordsTabGet(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable Map<String, String> pathVars,
            @AuthenticationPrincipal UserLogin userLogin)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        EntityViewType view = createView();
        Request request =
                createRequest(httpServletRequest, httpServletResponse,
                        pathVars, view, userLogin);
        handleGet(request, EntityTabType.ABOUT);
        return view;
    }

    public final EntityViewType mappingReviewTabGet(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable Map<String, String> pathVars,
            @AuthenticationPrincipal UserLogin userLogin)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        EntityViewType view = createView();
        Request request =
                createRequest(httpServletRequest, httpServletResponse,
                        pathVars, view, userLogin);
        handleGet(request, EntityTabType.REVIEWS);
        return view;
    }

    public final EntityViewType mappingTracksTabGet(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable Map<String, String> pathVars,
            @AuthenticationPrincipal UserLogin userLogin)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        EntityViewType view = createView();
        Request request =
                createRequest(httpServletRequest, httpServletResponse,
                        pathVars, view, userLogin);
        handleGet(request, EntityTabType.TRACKS);
        return view;
    }

    public final EntityViewType mappingUsersTabGet(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable Map<String, String> pathVars,
            @AuthenticationPrincipal UserLogin userLogin)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        EntityViewType view = createView();
        Request request =
                createRequest(httpServletRequest, httpServletResponse,
                        pathVars, view, userLogin);
        handleGet(request, EntityTabType.USERS);
        return view;
    }

    public final EntityViewType mappingVenuesTabGet(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @PathVariable Map<String, String> pathVars,
            @AuthenticationPrincipal UserLogin userLogin)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        EntityViewType view = createView();
        Request request =
                createRequest(httpServletRequest, httpServletResponse,
                        pathVars, view, userLogin);
        handleGet(request, EntityTabType.VENUES);
        return view;
    }

}
