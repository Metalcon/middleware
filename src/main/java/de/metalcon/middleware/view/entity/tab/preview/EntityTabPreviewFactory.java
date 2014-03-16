package de.metalcon.middleware.view.entity.tab.preview;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import de.metalcon.middleware.view.entity.tab.EntityTabType;
import de.metalcon.middleware.view.entity.tab.preview.impl.AboutTabPreview;
import de.metalcon.middleware.view.entity.tab.preview.impl.BandsTabPreview;
import de.metalcon.middleware.view.entity.tab.preview.impl.EventsTabPreview;
import de.metalcon.middleware.view.entity.tab.preview.impl.NewsTabPreview;
import de.metalcon.middleware.view.entity.tab.preview.impl.PhotosTabPreview;
import de.metalcon.middleware.view.entity.tab.preview.impl.RecommendationsTabPreview;
import de.metalcon.middleware.view.entity.tab.preview.impl.RecordsTabPreview;
import de.metalcon.middleware.view.entity.tab.preview.impl.ReviewsTabPreview;
import de.metalcon.middleware.view.entity.tab.preview.impl.TracksTabPreview;
import de.metalcon.middleware.view.entity.tab.preview.impl.UsersTabPreview;
import de.metalcon.middleware.view.entity.tab.preview.impl.VenuesTabPreview;

@Configuration
public class EntityTabPreviewFactory {

    public EntityTabPreview createTabPreview(EntityTabType entityTabType) {
        switch (entityTabType) {
        // @formatter:off
            case ABOUT:           return createAboutTabPreview();
            case BANDS:           return createBandsTabPreview();
            case EVENTS:          return createEventsTabPreview();
            case NEWS:        return createNewsTabPreview();
            case PHOTOS:          return createPhotosTabPreview();
            case RECOMMENDATIONS: return createRecomendationsTabPreview();
            case RECORDS:         return createRecordsTabPreview();
            case REVIEWS:         return createReviewsTabPreview();
            case TRACKS:          return createTracksTabPreview();
            case USERS:           return createUsersTabPreview();
            case VENUES:          return createVenuesTabPreview();
            // @formatter:on

            default:
                throw new IllegalStateException("Unimplemented EntityTabType:"
                        + entityTabType.toString() + ".");
        }
    }

    /**
     * @return empty about tab preview
     */
    @Bean
    @Scope("prototype")
    public AboutTabPreview createAboutTabPreview() {
        return new AboutTabPreview();
    }

    @Bean
    @Scope("prototype")
    public BandsTabPreview createBandsTabPreview() {
        return new BandsTabPreview();
    }

    @Bean
    @Scope("prototype")
    public EventsTabPreview createEventsTabPreview() {
        return new EventsTabPreview();
    }

    @Bean
    @Scope("prototype")
    public NewsTabPreview createNewsTabPreview() {
        return new NewsTabPreview();
    }

    @Bean
    @Scope("prototype")
    public PhotosTabPreview createPhotosTabPreview() {
        return new PhotosTabPreview();
    }

    @Bean
    @Scope("prototype")
    public RecommendationsTabPreview createRecomendationsTabPreview() {
        return new RecommendationsTabPreview();
    }

    @Bean
    @Scope("prototype")
    public RecordsTabPreview createRecordsTabPreview() {
        return new RecordsTabPreview();
    }

    @Bean
    @Scope("prototype")
    public ReviewsTabPreview createReviewsTabPreview() {
        return new ReviewsTabPreview();
    }

    @Bean
    @Scope("prototype")
    public TracksTabPreview createTracksTabPreview() {
        return new TracksTabPreview();
    }

    @Bean
    @Scope("prototype")
    public UsersTabPreview createUsersTabPreview() {
        return new UsersTabPreview();
    }

    @Bean
    @Scope("prototype")
    public VenuesTabPreview createVenuesTabPreview() {
        return new VenuesTabPreview();
    }

}
