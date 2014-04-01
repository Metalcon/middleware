package de.metalcon.middleware.controller.entity;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import de.metalcon.middleware.controller.MetalconController;
import de.metalcon.middleware.controller.RequestParameters;
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
import de.metalcon.middleware.domain.Muid;
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
     * fill tab generator map with all generators implemented
     */
    private void fillEntityTabGenerators() {
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

    /**
     * @return type of the requested entity
     */
    public final EntityType getEntityType() {
        return entityType;
    }

    protected final EntityTabGenerator<?, ?> getEntityTabGenerator(
            EntityTabType entityTabType) {
        return entityTabsGenerators.get(entityTabType);
    }

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

    protected final EntityViewType createView() {
        @SuppressWarnings("unchecked")
        EntityViewType view =
                (EntityViewType) entityViewFactory.createView(entityViewClass);
        return view;
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
            RequestParameters params) throws RedirectException,
            NoSuchRequestHandlingMethodException {
        // resolve MUID to entity (data model object)
        Muid muid =
                entityUrlMappingManager.getMuid(getEntityType(),
                        params.getPathVars());

        if (entityTabsGenerators.get(entityTabType) == null || muid == null) {
            throw new NoSuchRequestHandlingMethodException(params.getRequest());
        }

        return muid;
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
    protected void handleGet(
            EntityViewType view,
            RequestParameters params,
            EntityTabType entityTabType) throws RedirectException,
            NoSuchRequestHandlingMethodException {
        EntityTabController entityTabController =
                getEntityTabController(entityTabType);

        Muid muid = getMuidAndCheck404(entityTabType, params);

        String pjaxrNamespace =
                getMetalconNamespace() + "." + getEntityType().toString() + "."
                        + muid + "." + entityTabType.toString().toLowerCase();

        MetalconPjaxr pjaxrObj =
                new MetalconPjaxr(params.getRequest(), pjaxrNamespace);

        Entity entity = entityManager.getEntity(muid, getEntityType());

        view.setMuid(entity.getMuid());

        if (pjaxrObj.pjaxr_content) {
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
        if (pjaxrObj.pjaxr_inner_content) {
            // create empty tab content and fill it with data from entity
            EntityTabGenerator<?, ?> entityTabGenerator =
                    getEntityTabGenerator(entityTabType);

            EntityTabContent entityTabContent =
                    entityTabGenerator.generateTabContent(entity);
            view.setEntityTabContent(entityTabContent);
        }
        view.setPjaxrMatching(pjaxrObj.getMatchingCount());
        view.setPjaxrNamespace(pjaxrNamespace);

        entityTabController.handleGet(view, params, this);
        super.handleGet(view, params);
    }

    // =========================================================================
    // TAB GENERATORS

    public AboutTabGenerator getAboutTabGenerator() {
        return null;
    }

    public BandsTabGenerator getBandsTabGenerator() {
        return null;
    }

    public EventsTabGenerator getEventsTabGenerator() {
        return null;
    }

    public NewsTabGenerator getNewsTabGenerator() {
        return null;
    }

    public PhotosTabGenerator getPhotosTabGenerator() {
        return null;
    }

    public RecommendationsTabGenerator getRecommendationsTabGenerator() {
        return null;
    }

    public RecordsTabGenerator getRecordsTabGenerator() {
        return null;
    }

    public ReviewsTabGenerator getReviewsTabGenerator() {
        return null;
    }

    public TracksTabGenerator getTracksTabGenerator() {
        return null;
    }

    public UsersTabGenerator getUsersTabGenerator() {
        return null;
    }

    public VenuesTabGenerator getVenuesTabGenerator() {
        return null;
    }

    // =========================================================================
    // TAB REQUEST HANDLING

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
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        EntityViewType view = createView();
        handleGet(view, params, EntityTabType.ABOUT);
        return view;
    }

    public final EntityViewType mappingBandsTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        EntityViewType view = createView();
        handleGet(view, params, EntityTabType.BANDS);
        return view;
    }

    public final EntityViewType mappingEventsTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        EntityViewType view = createView();
        handleGet(view, params, EntityTabType.EVENTS);
        return view;
    }

    public final EntityViewType mappingNewsTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        EntityViewType view = createView();
        handleGet(view, params, EntityTabType.NEWS);
        return view;
    }

    public final EntityViewType mappingNewsTabPost(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException,
            IOException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        // TODO
        EntityViewType view = createView();
        newsTabController.createNewsItem(params, this);
        return view;
    }

    public final EntityViewType mappingPhotosTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        EntityViewType view = createView();
        handleGet(view, params, EntityTabType.PHOTOS);
        return view;
    }

    public final EntityViewType mappingRecommendationsTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        EntityViewType view = createView();
        handleGet(view, params, EntityTabType.RECOMMENDATIONS);
        return view;
    }

    public final EntityViewType mappingRecordsTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        EntityViewType view = createView();
        handleGet(view, params, EntityTabType.RECORDS);
        return view;
    }

    public final EntityViewType mappingReviewsTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        EntityViewType view = createView();
        handleGet(view, params, EntityTabType.REVIEWS);
        return view;
    }

    public final EntityViewType mappingTracksTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        EntityViewType view = createView();
        handleGet(view, params, EntityTabType.TRACKS);
        return view;
    }

    public final EntityViewType mappingUsersTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        EntityViewType view = createView();
        handleGet(view, params, EntityTabType.USERS);
        return view;
    }

    public final EntityViewType mappingVenuesTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        EntityViewType view = createView();
        handleGet(view, params, EntityTabType.VENUES);
        return view;
    }

}
