package de.metalcon.middleware.controller.entity;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import de.iekadou.spring_pjaxr.Pjaxr;
import de.metalcon.middleware.controller.MetalconController;
import de.metalcon.middleware.controller.UrlMappings;
import de.metalcon.middleware.controller.entity.generating.AboutTabGenerating;
import de.metalcon.middleware.controller.entity.generating.BandsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.EventsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.NewsfeedTabGenerating;
import de.metalcon.middleware.controller.entity.generating.PhotosTabGenerating;
import de.metalcon.middleware.controller.entity.generating.RecommendationsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.RecordsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.ReviewsTabGenerating;
import de.metalcon.middleware.controller.entity.generating.TracksTabGenerating;
import de.metalcon.middleware.controller.entity.generating.UsersTabGenerating;
import de.metalcon.middleware.controller.entity.generating.VenuesTabGenerating;
import de.metalcon.middleware.controller.entity.generator.EntityTabGenerator;
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
import de.metalcon.middleware.view.entity.tab.content.EntityTabContentFactory;
import de.metalcon.middleware.view.entity.tab.preview.EntityTabPreview;
import de.metalcon.middleware.view.entity.tab.preview.EntityTabPreviewFactory;

/**
 * basic controller for entity requests
 */

public abstract class EntityController extends MetalconController {

    @Autowired
    private EntityViewFactory entityViewFactory;

    @Autowired
    private EntityTabContentFactory entityTabContentFactory;

    @Autowired
    private EntityTabPreviewFactory entityTabPreviewFactory;

    @Autowired
    protected EntityUrlMapppingManager entityUrlMappingManager;

    @Autowired
    private EntityManager entityManager;

    private Map<EntityTabType, EntityTabGenerator> entityTabsGenerators;

    public EntityController() {
        entityTabsGenerators = new HashMap<EntityTabType, EntityTabGenerator>();
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
        if (this instanceof NewsfeedTabGenerating)        { entityTabsGenerators.put(EntityTabType.NEWSFEED,        ((NewsfeedTabGenerating)        this).getNewsfeedTabGenerator());        }
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
    public abstract EntityType getEntityType();

    /**
     * @return default tab for this entity
     */
    protected EntityTabType getDefaultTab() {
        return EntityTabType.NEWSFEED;
    }

    /**
     * Checks whether the current EntityType has the requested tab and 404s
     * if not. Returns the entity object.
     * 
     * @param entityTabType
     *            Type of request tab.
     * @param request
     *            Servlet request.
     * @param pathVars
     *            Variables from request path.
     * @return The entities MUID.
     * @throws RedirectException
     *             TODO
     * @throws NoSuchRequestHandlingMethodException
     *             If entity type doesn't have requested tab type or MUID
     *             couldn't be resolved.
     */
    private Muid getMuidAndCheck404(
            EntityTabType entityTabType,
            HttpServletRequest request,
            Map<String, String> pathVars) throws RedirectException,
            NoSuchRequestHandlingMethodException {
        // resolve MUID to entity (data model object)
        Muid muid = entityUrlMappingManager.getMuid(getEntityType(), pathVars);

        if (!entityTabsGenerators.containsKey(entityTabType) || muid == null) {
            throw new NoSuchRequestHandlingMethodException(request);
        }

        return muid;
    }

    /**
     * create and fill view object
     * 
     * @param entityTabType
     *            Type of requested tab.
     * @param request
     *            Servlet request.
     * @param pathVars
     *            Variables from request path.
     * @return filled view object
     * @throws RedirectException
     *             TODO
     * @throws NoSuchRequestHandlingMethodException
     *             If entity type doesn't have requested tab type or MUID
     *             couldn't be resolved.
     */
    private final EntityView handleTab(
            EntityTabType entityTabType,
            HttpServletRequest request,
            Map<String, String> pathVars) throws RedirectException,
            NoSuchRequestHandlingMethodException {
        if (entityTabType == EntityTabType.EMPTY) {
            entityTabType = getDefaultTab();
        }

        String pjaxrNamespace =
                getMetalconNamespace() + "." + getEntityType().toString() + "."
                        + "Metallica" + "."
                        + entityTabType.toString().toLowerCase();

        Pjaxr pjaxrObj = new Pjaxr(request, pjaxrNamespace);

        Muid muid = getMuidAndCheck404(entityTabType, request, pathVars);

        Entity entity = entityManager.getEntity(muid, getEntityType());

        // create view object
        EntityView entityView = entityViewFactory.createView(getEntityType());
        entityView.setMuid(entity.getMuid());

        // create empty tab content and fill it with data from entity
        EntityTabContent entityTabContent =
                entityTabContentFactory.createTabContent(entityTabType);
        entityTabsGenerators.get(entityTabType).generateTabContent(
                entityTabContent, entity);
        entityView.setEntityTabContent(entityTabContent);

        if (pjaxrObj.getMatchingCount() < 2) {
            // create tab previews
            Map<EntityTabType, EntityTabPreview> entityTabPreviews =
                    new HashMap<EntityTabType, EntityTabPreview>();
            for (Map.Entry<EntityTabType, EntityTabGenerator> entry : entityTabsGenerators
                    .entrySet()) {
                EntityTabType entityTabPreviewType = entry.getKey();
                EntityTabGenerator entityTabPreviewGenerator = entry.getValue();

                // create empty tab preview and fill it with data from entity
                EntityTabPreview entityTabPreview =
                        entityTabPreviewFactory
                                .createTabPreview(entityTabPreviewType);
                entityTabPreviewGenerator.generateTabPreview(entityTabPreview,
                        entity);
                entityTabPreviews.put(entityTabPreviewType, entityTabPreview);
            }
            entityView.setEntityTabPreviews(entityTabPreviews);
        }
        entityView.setPjaxrMatching(pjaxrObj.getMatchingCount());
        entityView.setPjaxrNamespace(pjaxrObj.getCurrentNamespace());

        return entityView;
    }

    @RequestMapping({
        "", "/"
    })
    public final EntityView mappingEmptyTab(
            HttpServletRequest request,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        return handleTab(EntityTabType.EMPTY, request, pathVars);
    }

    // TAB REQUEST HANDLING

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
    @RequestMapping(UrlMappings.ABOUT_TAB_MAPPING)
    public final EntityView mappingAboutTab(
            HttpServletRequest request,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        return handleTab(EntityTabType.ABOUT, request, pathVars);
    }

    @RequestMapping(UrlMappings.BANDS_TAB_MAPPING)
    public final EntityView mappingBandsTab(
            HttpServletRequest request,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        return handleTab(EntityTabType.BANDS, request, pathVars);
    }

    @RequestMapping(UrlMappings.EVENTS_TAB_MAPPING)
    public final EntityView mappingEventsTab(
            HttpServletRequest request,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        return handleTab(EntityTabType.EVENTS, request, pathVars);
    }

    @RequestMapping(UrlMappings.NEWSFEED_TAB_MAPPING)
    public final EntityView mappingNewsfeedTab(
            HttpServletRequest request,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        return handleTab(EntityTabType.NEWSFEED, request, pathVars);
    }

    @RequestMapping(UrlMappings.PHOTOS_TAB_MAPPING)
    public final EntityView mappingPhotosTab(
            HttpServletRequest request,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        return handleTab(EntityTabType.PHOTOS, request, pathVars);
    }

    @RequestMapping(UrlMappings.RECOMMENDATIONS_TAB_MAPPING)
    public final EntityView mappingRecommendationsTab(
            HttpServletRequest request,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        return handleTab(EntityTabType.RECOMMENDATIONS, request, pathVars);
    }

    @RequestMapping(UrlMappings.RECORDS_TAB_MAPPING)
    public final EntityView mappingRecordsTab(
            HttpServletRequest request,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        return handleTab(EntityTabType.RECORDS, request, pathVars);
    }

    @RequestMapping(UrlMappings.REVIEWS_TAB_MAPPING)
    public final EntityView mappingReviewsTab(
            HttpServletRequest request,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        return handleTab(EntityTabType.REVIEWS, request, pathVars);
    }

    @RequestMapping(UrlMappings.TRACKS_TAB_MAPPING)
    public final EntityView mappingTracksTab(
            HttpServletRequest request,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        return handleTab(EntityTabType.TRACKS, request, pathVars);
    }

    @RequestMapping(UrlMappings.USERS_TAB_MAPPING)
    public final EntityView mappingUsersTab(
            HttpServletRequest request,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        return handleTab(EntityTabType.USERS, request, pathVars);
    }

    @RequestMapping(UrlMappings.VENUES_TAB_MAPPING)
    public final EntityView mappingVenuesTab(
            HttpServletRequest request,
            @PathVariable Map<String, String> pathVars)
            throws RedirectException, NoSuchRequestHandlingMethodException {
        return handleTab(EntityTabType.VENUES, request, pathVars);
    }

}
