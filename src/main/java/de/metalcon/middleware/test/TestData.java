package de.metalcon.middleware.test;

import java.util.Calendar;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.metalcon.domain.Muid;
import de.metalcon.exceptions.ServiceOverloadedException;
import de.metalcon.middleware.core.EntityManager;
import de.metalcon.middleware.core.EntityUrlMapppingManager;
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

@Component
public class TestData {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private EntityUrlMapppingManager entityUrlMappingManager;

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

		entityUrlMappingManager.registerMuid(jamesHetfieldMuid);
		entityUrlMappingManager.registerMuid(ensiferumMuid);
		entityUrlMappingManager.registerMuid(ensiferum2Muid);
		entityUrlMappingManager.registerMuid(victorySongsMuid);
		entityUrlMappingManager.registerMuid(ahtiMuid);
		entityUrlMappingManager.registerMuid(druckkammerMuid);
		entityUrlMappingManager.registerMuid(wackenMuid);
		entityUrlMappingManager.registerMuid(koblenzMuid);
		entityUrlMappingManager.registerMuid(blackMetalMuid);
		entityUrlMappingManager.registerMuid(guitarMuid);
		entityUrlMappingManager.registerMuid(heidenfestMuid);
	}

}
