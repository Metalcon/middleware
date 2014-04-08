package de.metalcon.middleware.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import net.hh.request_dispatcher.Callback;
import net.hh.request_dispatcher.Dispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.metalcon.api.responses.Response;
import de.metalcon.api.responses.errors.UsageErrorResponse;
import de.metalcon.domain.Muid;
import de.metalcon.domain.UidType;
import de.metalcon.exceptions.ServiceOverloadedException;
import de.metalcon.middleware.core.DispatcherFactory;
import de.metalcon.middleware.core.EntityManager;
import de.metalcon.middleware.core.EntityUrlMapppingManager;
import de.metalcon.middleware.domain.entity.EntityType;
import de.metalcon.middleware.domain.entity.impl.Band;
import de.metalcon.sdd.api.requests.SddWriteRequest;

@Component
public class SddInitializer {

    @Autowired
    private DispatcherFactory dispatcherFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EntityUrlMapppingManager entityUrlMappingManager;

    @PostConstruct
    private void init() throws NumberFormatException, IOException,
            ServiceOverloadedException {
        Dispatcher dispatcher = dispatcherFactory.dispatcher();

        SddWriteRequest request = new SddWriteRequest();

        Map<String, String[]> bands = new HashMap<String, String[]>();

        Map<String, Muid> bandIndex = new HashMap<String, Muid>();

        BufferedReader br =
                new BufferedReader(
                        new FileReader(
                                new File(
                                        "/media/mssd/datasets/metalcon/Band_Freebase_matched.csv")));
        String line = "";
        Muid FINNTROLL = null;
        while ((line = br.readLine()) != null) {
            Muid tmp = Muid.create(EntityType.toUidType(EntityType.BAND));
            String[] values = line.split("\t");
            bandIndex.put(values[0], tmp);
            String name = values[1];
            System.out.println(tmp + " " + name);
            String freebaseId = values[2];
            Map<String, String> properties = new HashMap<String, String>();
            properties.put("freebaseId", freebaseId);
            if (freebaseId.equals("/m/02gpxc")) { //FINNTROLL
                FINNTROLL = tmp;
            }
            properties.put("name", name);
            properties.put("oldId", values[0]);
            request.setProperties(tmp, properties);

            Band tmpBand = new Band(tmp, values[1]);
            tmpBand.setName(values[1]);
            tmpBand.setFreeBaseId(values[2]);
            entityManager.putEntity(tmpBand);
            entityUrlMappingManager.registerMuid(tmp);

        }

        dispatcher.execute(request, new Callback<Response>() {

            @Override
            public void onSuccess(Response arg0) {
                System.out.println("yeah: " + arg0.getClass());
                if (arg0 instanceof UsageErrorResponse) {
                    System.out.println(arg0.getStatusMessage());
                    System.out.println(((UsageErrorResponse) arg0)
                            .getErrorMessage());
                    System.out.println(((UsageErrorResponse) arg0)
                            .getSolution());
                }
            }
        });

        dispatcher.gatherResults();

        br =
                new BufferedReader(
                        new FileReader(
                                new File(
                                        "/media/mssd/datasets/metalcon/BandBandNewSimilar.csv")));

        line = "";
        request = new SddWriteRequest();
        while ((line = br.readLine()) != null) {
            String[] values = line.split("\t");
            Float score = Float.parseFloat(values[3]);
            Muid toid = bandIndex.get(values[2]);
            if (toid == null) {
                //            System.out.println("fail: " + toid);
                continue;
            }
            List<Muid> toIds = new LinkedList<Muid>();
            toIds.add(toid);

            Muid from = bandIndex.get(values[1]);
            if (from == null) {
                //             System.out.println("fail from: " + from);
                continue;
            }

            request.addRelations(from, "recBands", toIds);

        }

        dispatcher.execute(request, new Callback<Response>() {

            @Override
            public void onSuccess(Response arg0) {
                System.out.println("yeah: " + arg0.getClass());
                if (arg0 instanceof UsageErrorResponse) {
                    System.out.println(arg0.getStatusMessage());
                    System.out.println(((UsageErrorResponse) arg0)
                            .getErrorMessage());
                    System.out.println(((UsageErrorResponse) arg0)
                            .getSolution());
                }
            }
        });

        dispatcher.gatherResults();

        br =
                new BufferedReader(new FileReader(new File(
                        "/media/mssd/datasets/metalcon/TrackSample.csv")));

        //3444  Amon Amarth Twilight Of The Thunder God Twilight of the Thunder God 1623    446
        //oid   band    record  song    fanCount    ownerCount  youtubeId
        //0     1       2       3       4           5           6
        line = "";
        System.out.println("import tracks");
        request = new SddWriteRequest();
        HashMap<String, Muid> recordIndex = new HashMap<String, Muid>();
        HashMap<String, Muid> trackIndex = new HashMap<String, Muid>();

        HashMap<Muid, List<Muid>> bandRecords = new HashMap<Muid, List<Muid>>();
        HashMap<Muid, List<Muid>> recordTracks =
                new HashMap<Muid, List<Muid>>();

        int cnt = 1;
        while ((line = br.readLine()) != null) {
            String[] values = line.split("\t");
            if (values.length != 7) {
                continue;
            }
            Muid bandMuid = bandIndex.get(values[0]);
            if (bandMuid == null) {
                continue;
            }
            //            if (!bandMuid.equals(FINNTROLL)) {
            //                continue;
            //            }
            cnt++;
            if (cnt % 20000 == 0) {
                try {
                    System.out.println("sleep to ..." + cnt);
                    Thread.sleep(999);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            Muid recordMuid = recordIndex.get(bandMuid.toString() + values[2]);
            if (recordMuid == null) {
                recordMuid = Muid.create(UidType.RECORD);
                recordIndex.put(bandMuid.toString() + values[2], recordMuid);
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", values[2]);
                request.setProperties(recordMuid, map);
                List<Muid> toIds = bandRecords.get(bandMuid);
                if (toIds == null) {
                    toIds = new LinkedList<Muid>();
                }
                toIds.add(recordMuid);
                bandRecords.put(bandMuid, toIds);
                //TODO: doesn't seem to work                request.setRelation(bandMuid, "myRecords", recordMuid);
            }
            Muid trackMuid = trackIndex.get(recordMuid.toString() + values[3]);
            if (trackMuid == null) {
                trackMuid = Muid.create(UidType.TRACK);
                trackIndex.put(recordMuid.toString() + values[3], trackMuid);
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", values[3]);

                //                if (bandMuid.equals(FINNTROLL)) {
                //                    YoutubeApiClient youtubeApiClient = new YoutubeApiClient();
                //                    List<YoutubeMetaData> result =
                //                            youtubeApiClient.youtubeSongSearch(1, values[3],
                //                                    "/m/02gpxc");
                //                    if (result.size() > 0) {
                //                        map.put("youtubeId", result.get(0).getYoutubeID());
                //                    }
                //                }
                map.put("youtubeId", values[6]);
                request.setProperties(trackMuid, map);
                List<Muid> toIds = recordTracks.get(recordMuid);
                if (toIds == null) {
                    toIds = new LinkedList<Muid>();
                }
                toIds.add(trackMuid);
                recordTracks.put(recordMuid, toIds);
                //request.setRelation(recordMuid, "myTracks", trackMuid);
            }
        }
        System.out.println("adding band record relations: "
                + bandRecords.size());
        for (Muid bandMuid : bandRecords.keySet()) {
            request.addRelations(bandMuid, "myRecords",
                    bandRecords.get(bandMuid));
        }

        System.out.println("adding track record relations: "
                + recordTracks.size());
        for (Muid recordMuid : recordTracks.keySet()) {
            request.addRelations(recordMuid, "myTracks",
                    recordTracks.get(recordMuid));
        }

        dispatcher.execute(request, new Callback<Response>() {

            @Override
            public void onSuccess(Response arg0) {
                System.out.println("yeah: " + arg0.getClass());
                if (arg0 instanceof UsageErrorResponse) {
                    System.out.println(arg0.getStatusMessage());
                    System.out.println(((UsageErrorResponse) arg0)
                            .getErrorMessage());
                    System.out.println(((UsageErrorResponse) arg0)
                            .getSolution());
                }
            }
        });

        dispatcher.gatherResults();

    }
}
