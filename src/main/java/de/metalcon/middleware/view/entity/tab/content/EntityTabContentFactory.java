package de.metalcon.middleware.view.entity.tab.content;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.content.impl.AboutTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.BandsTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.EventsTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.NewsTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.PhotosTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.RecommendationsTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.RecordsTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.ReviewsTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.TracksTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.UsersTabContent;
import de.metalcon.middleware.view.entity.tab.content.impl.VenuesTabContent;

@Configuration
public class EntityTabContentFactory {

    public EntityTabContent createTabContent(EntityTabType entityTabType) {
        switch (entityTabType) {
        // @formatter:off
            case ABOUT:           return createAboutTabContent();
            case BANDS:           return createBandsTabContent();
            case EVENTS:          return createEventsTabContent();
            case NEWS:            return createNewsTabContent();
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
    @Bean
    @Scope("prototype")
    public AboutTabContent createAboutTabContent() {
        return new AboutTabContent();
    }

    @Bean
    @Scope("prototype")
    public BandsTabContent createBandsTabContent() {
        return new BandsTabContent();
    }

    @Bean
    @Scope("prototype")
    public EventsTabContent createEventsTabContent() {
        return new EventsTabContent();
    }

    @Bean
    @Scope("prototype")
    public NewsTabContent createNewsTabContent() {
        return new NewsTabContent();
    }

    @Bean
    @Scope("prototype")
    public PhotosTabContent createPhotosTabContent() {
        return new PhotosTabContent();
    }

    @Bean
    @Scope("prototype")
    public RecommendationsTabContent createRecomendationsTabContent() {
        return new RecommendationsTabContent();
    }

    @Bean
    @Scope("prototype")
    public RecordsTabContent createRecordsTabContent() {
        return new RecordsTabContent();
    }

    @Bean
    @Scope("prototype")
    public ReviewsTabContent createReviewsTabContent() {
        return new ReviewsTabContent();
    }

    @Bean
    @Scope("prototype")
    public TracksTabContent createTracksTabContent() {
        return new TracksTabContent();
    }

    @Bean
    @Scope("prototype")
    public UsersTabContent createUsersTabContent() {
        return new UsersTabContent();
    }

    @Bean
    @Scope("prototype")
    public VenuesTabContent createVenuesTabContent() {
        return new VenuesTabContent();
    }

}
