package de.metalcon.middleware.test;

import java.util.Calendar;

import javax.annotation.PostConstruct;

import net.hh.request_dispatcher.Callback;
import net.hh.request_dispatcher.Dispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.metalcon.api.responses.Response;
import de.metalcon.api.responses.errors.ErrorResponse;
import de.metalcon.domain.Muid;
import de.metalcon.exceptions.ServiceOverloadedException;
import de.metalcon.middleware.core.DispatcherFactory;
import de.metalcon.middleware.core.EntityManager;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.domain.entity.impl.Band;
import de.metalcon.middleware.domain.entity.impl.City;
import de.metalcon.middleware.domain.entity.impl.Event;
import de.metalcon.middleware.domain.entity.impl.Genre;
import de.metalcon.middleware.domain.entity.impl.Instrument;
import de.metalcon.middleware.domain.entity.impl.Record;
import de.metalcon.middleware.domain.entity.impl.Tour;
import de.metalcon.middleware.domain.entity.impl.Track;
import de.metalcon.middleware.domain.entity.impl.User;
import de.metalcon.middleware.domain.entity.impl.Venue;
import de.metalcon.urlmappingserver.api.requests.UrlMappingRegistrationRequest;

@Component
public class TestData {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DispatcherFactory dispatcherFactory;

    public Muid heidenfestMuid;

    public Muid guitarMuid;

    public Muid blackMetalMuid;

    public Muid koblenzMuid;

    public Muid wackenMuid;

    public Muid druckkammerMuid;

    public Muid ahtiMuid;

    public Muid victorySongsMuid;

    public Muid ensiferum2Muid;

    public Muid ensiferumMuid;

    public Muid jamesHetfieldMuid;

    @PostConstruct
    private void init() throws ServiceOverloadedException {
        jamesHetfieldMuid = Muid.create(EntityType.toUidType(EntityType.USER));
        ensiferumMuid = Muid.create(EntityType.toUidType(EntityType.BAND));
        ensiferum2Muid = Muid.create(EntityType.toUidType(EntityType.BAND));
        victorySongsMuid = Muid.create(EntityType.toUidType(EntityType.RECORD));
        ahtiMuid = Muid.create(EntityType.toUidType(EntityType.TRACK));
        druckkammerMuid = Muid.create(EntityType.toUidType(EntityType.VENUE));
        wackenMuid = Muid.create(EntityType.toUidType(EntityType.EVENT));
        koblenzMuid = Muid.create(EntityType.toUidType(EntityType.CITY));
        blackMetalMuid = Muid.create(EntityType.toUidType(EntityType.GENRE));
        guitarMuid = Muid.create(EntityType.toUidType(EntityType.INSTRUMENT));
        heidenfestMuid = Muid.create(EntityType.toUidType(EntityType.TOUR));

        User jamesHetfield = new User(jamesHetfieldMuid, "James", "Hetfield");
        Band ensiferum = new Band(ensiferumMuid, "Ensiferum");
        Band ensiferum2 = new Band(ensiferum2Muid, "Ensiferum");
        Record victorySongs = new Record(victorySongsMuid, "Victory Songs");
        Track ahti = new Track(ahtiMuid, "Ahti");
        Venue druckkammer = new Venue(druckkammerMuid, "Druckluftkammer");
        Event wacken = new Event(wackenMuid, "Wacken");
        City koblenz = new City(koblenzMuid, "Koblenz");
        Genre blackMetal = new Genre(blackMetalMuid, "Black Metal");
        Instrument guitar = new Instrument(guitarMuid, "Guitar");
        Tour heidenfest = new Tour(heidenfestMuid, "Heidenfest");

        entityManager.putEntity(jamesHetfield);
        entityManager.putEntity(ensiferum);
        entityManager.putEntity(ensiferum2);
        entityManager.putEntity(victorySongs);
        entityManager.putEntity(ahti);
        entityManager.putEntity(druckkammer);
        entityManager.putEntity(wacken);
        entityManager.putEntity(koblenz);
        entityManager.putEntity(blackMetal);
        entityManager.putEntity(guitar);
        entityManager.putEntity(heidenfest);

        victorySongs.setBand(ensiferumMuid);
        victorySongs.setReleaseYear(2007);
        ahti.setBand(ensiferumMuid);
        ahti.setRecord(victorySongsMuid);
        ahti.setTrackNumber(4);
        druckkammer.setCity(koblenzMuid);
        wacken.setCity(koblenzMuid);
        wacken.setVenue(druckkammerMuid);
        Calendar cal = Calendar.getInstance();
        cal.set(2014, 7, 31);
        wacken.setDate(cal.getTime());

        Dispatcher dispatcher = dispatcherFactory.dispatcher();

        UrlMappingRegistrationRequest[] reqs =
                new UrlMappingRegistrationRequest[] {
                    new UrlMappingRegistrationRequest(
                            jamesHetfield.getUrlData()),
                    new UrlMappingRegistrationRequest(ensiferum.getUrlData()),
                    new UrlMappingRegistrationRequest(ensiferum2.getUrlData()),
                    new UrlMappingRegistrationRequest(victorySongs.getUrlData()),
                    new UrlMappingRegistrationRequest(ahti.getUrlData()),
                    new UrlMappingRegistrationRequest(druckkammer.getUrlData()),
                    new UrlMappingRegistrationRequest(wacken.getUrlData()),
                    new UrlMappingRegistrationRequest(koblenz.getUrlData()),
                    new UrlMappingRegistrationRequest(blackMetal.getUrlData()),
                    new UrlMappingRegistrationRequest(guitar.getUrlData()),
                    new UrlMappingRegistrationRequest(heidenfest.getUrlData())
                };

        for (UrlMappingRegistrationRequest req : reqs) {
            dispatcher.execute(req, new Callback<Response>() {

                @Override
                public void onSuccess(Response response) {
                    if (response instanceof ErrorResponse) {
                        System.out.println(response.getStatusMessage());
                        System.out.println(((ErrorResponse) response)
                                .getErrorMessage());
                        System.out.println(((ErrorResponse) response)
                                .getSolution());
                    }
                }

            });
        }

        dispatcher.gatherResults();
    }

}
