package de.metalcon.middleware.controller.entity.impl.band;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.hh.request_dispatcher.Callback;
import net.hh.request_dispatcher.Dispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.metalcon.api.responses.Response;
import de.metalcon.domain.Muid;
import de.metalcon.middleware.controller.entity.generator.impl.RecommendationsTabGenerator;
import de.metalcon.middleware.core.DispatcherFactory;
import de.metalcon.middleware.core.EntityUrlMapppingManager;
import de.metalcon.middleware.domain.entity.Entity;
import de.metalcon.middleware.view.entity.tab.content.impl.RecommendationsTabContent;
import de.metalcon.sdd.api.requests.SddReadRequest;
import de.metalcon.sdd.api.responses.SddSucessfulReadResponse;

@Component
public class BandRecommendationsTabGenerator extends
        RecommendationsTabGenerator {

    @Autowired
    private DispatcherFactory dispatcherFactory;

    @Autowired
    private EntityUrlMapppingManager entityurlMappingManger;

    @Override
    public RecommendationsTabContent generateTabContent(final Entity entity) {
        final RecommendationsTabContent tabContent =
                super.generateTabContent(entity);
        Dispatcher dispatcher = dispatcherFactory.dispatcher();

        SddReadRequest req = new SddReadRequest();
        req.read(entity.getMuid(), "detailed");

        dispatcher.execute(req, new Callback<Response>() {

            @Override
            public void onSuccess(Response arg0) {
                if (arg0 instanceof SddSucessfulReadResponse) {
                    SddSucessfulReadResponse tmp =
                            (SddSucessfulReadResponse) arg0;
                    String s = tmp.get(entity.getMuid(), "detailed");
                    System.out.println(s);
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        JsonNode root = mapper.readValue(s, JsonNode.class);
                        for (JsonNode node : root.path("recBands")) {
                            String name = node.get("name").textValue();
                            //                            System.out.println(name);
                            Muid band =
                                    Muid.createFromID(node.get("id").asLong());
                            String mapping =
                                    "/music/"
                                            + EntityUrlMapppingManager
                                                    .toUrlText(name);
                            //                            System.out.println(band);
                            //                            System.out.println(mapping);
                            tabContent.addRecommendedBand(new String[] {
                                mapping, name
                            });
                            //                           System.out.println(band);
                        }

                        Map<String, List<String[]>> records =
                                new HashMap<String, List<String[]>>();
                        for (JsonNode node : root.path("myRecords")) {
                            String name = node.get("name").textValue();
                            System.out.println(name);
                            List<String[]> tracks = new LinkedList<String[]>();
                            for (JsonNode n : node.path("myTracks")) {
                                if (n.get("youtubeId").textValue()
                                        .equals("null")) {
                                    continue;
                                }
                                System.out.println("\t"

                                + n.get("name").textValue());
                                System.out.println("\t"
                                        + n.get("youtubeId").textValue());

                                tracks.add(new String[] {
                                    n.get("name").textValue(),
                                    n.get("youtubeId").textValue()
                                });
                            }
                            records.put(name, tracks);
                        }
                        tabContent.addRecords(records);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //                    System.out.println(s);
                } else {
                    System.out.println("fehler");
                }
            }
        });

        //        List<String> records = new ArrayList<String>(10);
        //        for (int i = 0; i < 10; i++) {
        //            String video =
        //                    "https://www.youtube.com/watch?v="
        //                            + result.get(i).getYoutubeID();
        //            records.add(video);
        //        tabContent.setMyRecords(records);
        return tabContent;
    }
}
