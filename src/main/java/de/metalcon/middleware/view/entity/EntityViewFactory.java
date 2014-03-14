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

    public EntityView createView(Class<? extends EntityView> entityViewClass) {
        // @formatter:off
             if (entityViewClass.equals(BandView.class))       { return createBandView();       }
        else if (entityViewClass.equals(CityView.class))       { return createCityView();       }
        else if (entityViewClass.equals(EventView.class))      { return createEventView();      }
        else if (entityViewClass.equals(GenreView.class))      { return createGenreView();      }
        else if (entityViewClass.equals(InstrumentView.class)) { return createInstrumentView(); }
        else if (entityViewClass.equals(RecordView.class))     { return createRecordView();     }
        else if (entityViewClass.equals(TourView.class))       { return createTourView();       }
        else if (entityViewClass.equals(TrackView.class))      { return createTrackView();      }
        else if (entityViewClass.equals(UserView.class))       { return createUserView();       }
        else if (entityViewClass.equals(VenueView.class))      { return createVenueView();      }
        // @formatter:on
        else {
            throw new IllegalStateException("Unimpelemented EntityViewClass: "
                    + entityViewClass.toString() + ".");
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
