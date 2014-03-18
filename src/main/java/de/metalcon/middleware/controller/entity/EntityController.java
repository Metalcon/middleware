package de.metalcon.middleware.controller.entity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import de.iekadou.spring_pjaxr.Pjaxr;
import de.metalcon.middleware.controller.MetalconController;
import de.metalcon.middleware.controller.RequestParameters;
import de.metalcon.middleware.controller.entity.generating.impl.AboutTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.BandsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.EventsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.NewsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.PhotosTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.RecommendationsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.RecordsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.ReviewsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.TracksTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.UsersTabGenerating;
import de.metalcon.middleware.controller.entity.generating.impl.VenuesTabGenerating;
import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
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
        if (this instanceof AboutTabGenerating)           { entityTabsGenerators.put(EntityTabType.ABOUT,           ((AboutTabGenerating)           this).getAboutTabGenerator());           }
        if (this instanceof BandsTabGenerating)           { entityTabsGenerators.put(EntityTabType.BANDS,           ((BandsTabGenerating)           this).getBandsTabGenerator());           }
        if (this instanceof EventsTabGenerating)          { entityTabsGenerators.put(EntityTabType.EVENTS,          ((EventsTabGenerating)          this).getEventsTabGenerator());          }
        if (this instanceof NewsTabGenerating)            { entityTabsGenerators.put(EntityTabType.NEWS,            ((NewsTabGenerating)            this).getNewsTabGenerator());            }
        if (this instanceof PhotosTabGenerating)          { entityTabsGenerators.put(EntityTabType.PHOTOS,          ((PhotosTabGenerating)          this).getPhotosTabGenerator());          }
        if (this instanceof RecommendationsTabGenerating) { entityTabsGenerators.put(EntityTabType.RECOMMENDATIONS, ((RecommendationsTabGenerating) this).getRecommendationsTabGenerator()); }
        if (this instanceof RecordsTabGenerating)         { entityTabsGenerators.put(EntityTabType.RECORDS,         ((RecordsTabGenerating)         this).getRecordsTabGenerator());         }
        if (this instanceof ReviewsTabGenerating)         { entityTabsGenerators.put(EntityTabType.REVIEWS,         ((ReviewsTabGenerating)         this).getReviewsTabGenerator());         }
        if (this instanceof TracksTabGenerating)          { entityTabsGenerators.put(EntityTabType.TRACKS,          ((TracksTabGenerating)          this).getTracksTabGenerator());          }
        if (this instanceof UsersTabGenerating)           { entityTabsGenerators.put(EntityTabType.USERS,           ((UsersTabGenerating)           this).getUsersTabGenerator());           }
        if (this instanceof VenuesTabGenerating)          { entityTabsGenerators.put(EntityTabType.VENUES,          ((VenuesTabGenerating)          this).getVenuesTabGenerator());          }
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

        if (!entityTabsGenerators.containsKey(entityTabType) || muid == null) {
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
    @SuppressWarnings("fallthrough")
    protected EntityViewType handleGet(
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

        Pjaxr pjaxrObj = new Pjaxr(params.getRequest(), pjaxrNamespace);

        Entity entity = entityManager.getEntity(muid, getEntityType());

        view.setMuid(entity.getMuid());

        switch (pjaxrObj.getMatchingCount()) {
            case Pjaxr.NEEDED_IN_SITE:
            case Pjaxr.NEEDED_IN_PAGE:
            case Pjaxr.NEEDED_IN_CONTENT:
                // create tab previews if content
                Map<EntityTabType, EntityTabPreview> entityTabPreviews =
                        new HashMap<EntityTabType, EntityTabPreview>();
                for (Map.Entry<EntityTabType, EntityTabGenerator<?, ?>> entry : entityTabsGenerators
                        .entrySet()) {
                    EntityTabType entityTabPreviewType = entry.getKey();
                    EntityTabGenerator<?, ?> entityTabPreviewGenerator =
                            entry.getValue();

                    EntityTabPreview entityTabPreview =
                            entityTabPreviewGenerator
                                    .generateTabPreview(entity);
                    entityTabPreviews.put(entityTabPreviewType,
                            entityTabPreview);
                }
                view.setEntityTabPreviews(entityTabPreviews);

            case Pjaxr.NEEDED_IN_INNER_CONTENT:
                // create empty tab content and fill it with data from entity
                EntityTabGenerator<?, ?> entityTabGenerator =
                        getEntityTabGenerator(entityTabType);

                EntityTabContent entityTabContent =
                        entityTabGenerator.generateTabContent(entity);
                view.setEntityTabContent(entityTabContent);

            default:
                break;
        }
        view.setPjaxrMatching(pjaxrObj.getMatchingCount());
        view.setPjaxrNamespace(pjaxrNamespace);

        @SuppressWarnings("unchecked")
        EntityViewType viewAfterTabController =
                (EntityViewType) entityTabController.handleGet(view, params,
                        this);
        view = viewAfterTabController;

        @SuppressWarnings("unchecked")
        EntityViewType viewAfterSuper =
                (EntityViewType) super.handleRequest(view, params);
        view = viewAfterSuper;

        return view;
    }

    // TAB REQUEST HANDLING

    public final EntityViewType mappingEmptyTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        return handleGet(createView(), params, EntityTabType.NEWS);
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
        return handleGet(createView(), params, EntityTabType.ABOUT);
    }

    public final EntityViewType mappingBandsTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        return handleGet(createView(), params, EntityTabType.BANDS);
    }

    public final EntityViewType mappingEventsTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        return handleGet(createView(), params, EntityTabType.EVENTS);
    }

    public final EntityViewType mappingNewsTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        return handleGet(createView(), params, EntityTabType.NEWS);
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
        @SuppressWarnings("unchecked")
        EntityViewType view =
                (EntityViewType) newsTabController.createNewsItem(params, this);
        return view;
    }

    public final EntityViewType mappingPhotosTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        return handleGet(createView(), params, EntityTabType.PHOTOS);
    }

    public final EntityViewType mappingRecommendationsTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        return handleGet(createView(), params, EntityTabType.RECOMMENDATIONS);
    }

    public final EntityViewType mappingRecordsTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        return handleGet(createView(), params, EntityTabType.RECORDS);
    }

    public final EntityViewType mappingReviewsTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        return handleGet(createView(), params, EntityTabType.REVIEWS);
    }

    public final EntityViewType mappingTracksTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        return handleGet(createView(), params, EntityTabType.TRACKS);
    }

    public final EntityViewType mappingUsersTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        return handleGet(createView(), params, EntityTabType.USERS);
    }

    public final EntityViewType mappingVenuesTabGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        RequestParameters params =
                new RequestParameters(request, response, pathVars);
        return handleGet(createView(), params, EntityTabType.VENUES);
    }

}
