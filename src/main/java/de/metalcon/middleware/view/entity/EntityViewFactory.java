package de.metalcon.middleware.view.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.view.entity.impl.BandView;
import de.metalcon.middleware.view.entity.impl.CityView;
import de.metalcon.middleware.view.entity.impl.EventView;
import de.metalcon.middleware.view.entity.impl.GenreView;
import de.metalcon.middleware.view.entity.impl.InstrumentView;
import de.metalcon.middleware.view.entity.impl.RecordView;
import de.metalcon.middleware.view.entity.impl.TourView;
import de.metalcon.middleware.view.entity.impl.TrackView;
import de.metalcon.middleware.view.entity.impl.UserView;
import de.metalcon.middleware.view.entity.impl.VenueView;

@Configuration
public class EntityViewFactory {

    public EntityView createView(EntityType entityType) {
        switch (entityType) {
        // @formatter:off
            case BAND:       return createBandView();
            case CITY:       return createCityView();
            case EVENT:      return createEventView();
            case GENRE:      return createGenreView();
            case INSTRUMENT: return createInstrumentView();
            case RECORD:     return createRecordView();
            case TOUR:       return createTourView();
            case TRACK:      return createTrackView();
            case USER:       return createUserView();
            case VENUE:      return createVenueView();
            // @formatter:on

            default:
                throw new IllegalStateException("Unimplented EntityType: "
                        + entityType.toString() + ".");
        }
    }

    /**
     * @return empty band view
     */
    @Bean
    @Scope("prototype")
    public BandView createBandView() {
        return new BandView();
    }

    @Bean
    @Scope("prototype")
    public CityView createCityView() {
        return new CityView();
    }

    @Bean
    @Scope("prototype")
    public EventView createEventView() {
        return new EventView();
    }

    @Bean
    @Scope("prototype")
    public GenreView createGenreView() {
        return new GenreView();
    }

    @Bean
    @Scope("prototype")
    public InstrumentView createInstrumentView() {
        return new InstrumentView();
    }

    @Bean
    @Scope("prototype")
    public RecordView createRecordView() {
        return new RecordView();
    }

    @Bean
    @Scope("prototype")
    public TourView createTourView() {
        return new TourView();
    }

    @Bean
    @Scope("prototype")
    public TrackView createTrackView() {
        return new TrackView();
    }

    @Bean
    @Scope("prototype")
    public UserView createUserView() {
        return new UserView();
    }

    @Bean
    @Scope("prototype")
    public VenueView createVenueView() {
        return new VenueView();
    }

}
