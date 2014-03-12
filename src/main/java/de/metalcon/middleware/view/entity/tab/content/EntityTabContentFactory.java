package de.metalcon.middleware.view.entity.tab.content;

import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.impl.AboutTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.BandsTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.EventsTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.NewsfeedTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.PhotosTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.RecommendationsTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.RecordsTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.ReviewsTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.TracksTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.UsersTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.VenuesTabContent;

/**
 * Together with EntityTabContentConfig, this file is used to provide access to
 * prototype access to EntityTabContents inside singleton beans. See:
 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/
 * beans.html#beans-java-method-injection
 */
public abstract class EntityTabContentFactory {

    public EntityTabContent createTabContent(EntityTabType entityTabType) {
        switch (entityTabType) {
        // @formatter:off
            case ABOUT:           return createAboutTabContent();
            case BANDS:           return createBandsTabContent();
            case EVENTS:          return createEventsTabContent();
            case NEWSFEED:        return createNewsfeedTabContent();
            case PHOTOS:          return createPhotosTabContent();
            case RECOMMENDATIONS: return createRecomendationsTabContent();
            case RECORDS:         return createRecordsTabContent();
            case REVIEWS:         return createReviewsTabContent();
            case TRACKS:          return createTracksTabContent();
            case USERS:           return createUsersTabContent();
            case VENUES:          return createVenuesTabContent();
            // @formatter:on

            default:
                throw new IllegalStateException("Unimplemented EntityTabType:"
                        + entityTabType.toString() + ".");
        }
    }

    /**
     * @return empty about tab content container
     */
    public abstract AboutTabContent createAboutTabContent();

    public abstract BandsTabContent createBandsTabContent();

    public abstract EventsTabContent createEventsTabContent();

    public abstract NewsfeedTabContent createNewsfeedTabContent();

    public abstract PhotosTabContent createPhotosTabContent();

    public abstract RecommendationsTabContent createRecomendationsTabContent();

    public abstract RecordsTabContent createRecordsTabContent();

    public abstract ReviewsTabContent createReviewsTabContent();

    public abstract TracksTabContent createTracksTabContent();

    public abstract UsersTabContent createUsersTabContent();

    public abstract VenuesTabContent createVenuesTabContent();

}
