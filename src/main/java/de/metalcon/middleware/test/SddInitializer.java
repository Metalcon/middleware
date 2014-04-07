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

        Map<String, Muid> index = new HashMap<String, Muid>();

        BufferedReader br =
                new BufferedReader(
                        new FileReader(
                                new File(
                                        "/media/mssd/datasets/metalcon/Band_Freebase_matched.csv")));
        String line = "";
        while ((line = br.readLine()) != null) {
            Muid tmp = Muid.create(EntityType.toUidType(EntityType.BAND));
            String[] values = line.split("\t");
            index.put(values[0], tmp);
            String name = values[1];
            System.out.println(tmp + " " + name);
            String freebaseId = values[2];
            Map<String, String> properties = new HashMap<String, String>();
            properties.put("freebaseId", freebaseId);
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
            Muid toid = index.get(values[2]);
            if (toid == null) {
                //            System.out.println("fail: " + toid);
                continue;
            }
            List<Muid> toIds = new LinkedList<Muid>();
            toIds.add(toid);

            Muid from = index.get(values[1]);
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

    }

}
