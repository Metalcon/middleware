package de.metalcon.middleware.backend.newsserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.hh.RequestDispatcher.Dispatcher;
import net.hh.RequestDispatcher.Service.ZmqService;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

// import api.newsfeed.NewsFeed;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.metalcon.middleware.domain.Muid;

@Component
public class NewsServer /* implements NewsFeed */{

    private static ObjectMapper mapper = new ObjectMapper();

    // TODO: not thread save! need to use spring annotation for ThreadBeans
    //private static Dispatcher dispatcher = new Dispatcher();

    @Autowired
    private BeanFactory beanFactory;

    public NewsServer() {
        // TODO: initialization. Replace with the real graphity service.
        //        dispatcher.registerServiceProvider("graphity", new ZmqService(
        //                "tcp://127.0.0.1:60125"));
        //dispatcher.setDefaultTimeout(100);

    }

    private Map<String, Object> fetchNewsFromServer(
            Muid user_id,
            Muid poster_id,
            Boolean ownUpdates) {

        final List<String> response = new ArrayList<String>();
        List<Map<String, Object>> modelNews =
                new LinkedList<Map<String, Object>>();

        //        dispatcher.execute("graphity", new TestRequest(
        //                "fetch newsfeed. this string currently has no meaning"),
        //                new Callback<TestReply>(new TestReply()) {
        //
        //                    @Override
        //                    public void onTimeOut(String errorMessage) {
        //                        response.add("{\"items\":[{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"hey\",\"id\":\"1394529863852\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2014-03-11T10:24:24Z\"},{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"asdf\",\"id\":\"1387393615465\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2013-12-18T20:06:55Z\"},{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"nice\",\"id\":\"1386331367991\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2013-12-06T13:02:48Z\"},{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"auf gehts. 2010 fi.... auch im stehen.\",\"id\":\"1386331359345\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2013-12-06T13:02:39Z\"},{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"entry !!1386330847185\",\"id\":\"1386330847185\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2013-12-06T12:54:07Z\"},{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"entry !!1386330828215\",\"id\":\"1386330828215\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2013-12-06T12:53:48Z\"}]}");
        //                    }
        //
        //                    @Override
        //                    public void onSuccess(final TestReply secondReply) {
        //                        response.add("{\"items\":[{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"hey\",\"id\":\"1394529863852\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2014-03-11T10:24:24Z\"},{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"asdf\",\"id\":\"1387393615465\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2013-12-18T20:06:55Z\"},{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"nice\",\"id\":\"1386331367991\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2013-12-06T13:02:48Z\"},{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"auf gehts. 2010 fi.... auch im stehen.\",\"id\":\"1386331359345\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2013-12-06T13:02:39Z\"},{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"entry !!1386330847185\",\"id\":\"1386330847185\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2013-12-06T12:54:07Z\"},{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"entry !!1386330828215\",\"id\":\"1386330828215\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2013-12-06T12:53:48Z\"}]}");
        //
        //                    }
        //                });
        //
        //        // FIXME: Here is the blocking. Put to another position. Not clear how to collect the processed answers. Since this should be in the metalcon controller. But on the other side in the callbacks one should be able to use outside data. Will all of this be achieved with DTO's ? 
        //        dispatcher.gatherResults(100);
        //

        response.add("{\"items\":[{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"hey\",\"id\":\"1394529863852\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2014-03-11T10:24:24Z\"},{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"asdf\",\"id\":\"1387393615465\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2013-12-18T20:06:55Z\"},{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"nice\",\"id\":\"1386331367991\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2013-12-06T13:02:48Z\"},{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"auf gehts. 2010 fi.... auch im stehen.\",\"id\":\"1386331359345\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2013-12-06T13:02:39Z\"},{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"entry !!1386330847185\",\"id\":\"1386330847185\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2013-12-06T12:54:07Z\"},{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"entry !!1386330828215\",\"id\":\"1386330828215\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2013-12-06T12:53:48Z\"}]}");

        String answer = response.get(0);

        JsonNode root = null;
        try {
            root = mapper.readValue(answer, JsonNode.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (JsonNode item : root.path("items")) {
            JsonNode verb = item.get("verb");
            JsonNode actor = item.get("actor");
            JsonNode object = item.get("object");
            JsonNode published = item.get("published");

            Map<String, String> modelActor = new HashMap<String, String>();
            modelActor.put("id", actor.get("id").textValue());
            modelActor.put("objectType", actor.get("objectType").textValue());
            modelActor.put("displayName", actor.get("displayName").textValue());

            Map<String, String> modelObject = new HashMap<String, String>();
            modelObject.put("id", object.get("id").textValue());
            modelObject.put("objectType", object.get("objectType").textValue());
            modelObject.put("message", object.get("message").textValue());
            modelObject.put("type", object.get("type").textValue());

            Map<String, Object> modelItem = new HashMap<String, Object>();
            modelItem.put("verb", verb.textValue());
            modelItem.put("actor", modelActor);
            modelItem.put("object", modelObject);
            modelItem.put("published", published.textValue());
            modelNews.add(modelItem);
        }

        ModelMap model = new ModelMap();
        model.addAttribute("userId", user_id);
        model.addAttribute("posterId", poster_id);
        model.addAttribute("ownUpdates", ownUpdates);
        model.addAttribute("news", modelNews);
        return model;
    }

    public Map<String, Object> getNews(
            Muid user_id,
            Muid poster_id,
            Boolean ownUpdates) {
        return fetchNewsFromServer(user_id, poster_id, ownUpdates);
    }

    public String postNews(
            String userId,
            String posterId,
            Boolean ownUpdates,
            String formMessage) throws IOException {
        MultipartEntity entity = new MultipartEntity();
        entity.addPart("user_id", new StringBody(userId));
        entity.addPart("status_update_id",
                new StringBody(String.valueOf(System.currentTimeMillis())));
        entity.addPart("status_update_type", new StringBody("Plain"));
        entity.addPart("message", new StringBody(formMessage));
        entity.addPart("type", new StringBody("status_update"));

        String url = "http://localhost:8080/Graphity-Server-0.1/create";
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);
        new DefaultHttpClient().execute(httpPost);

        return "redirect:/test/news/" + userId + "/" + posterId + "/"
                + ownUpdates.toString();
    }

    public void createUser(
            String userId,
            String displayName,
            String profilePicturePath) {
        // TODO Auto-generated method stub

    }

    public void createStatusUpdate(
            long timestamp,
            String user,
            Map<String, Object> content) {
        // TODO Auto-generated method stub

    }

    public void createFriendship(String followingId, String followedId) {
        // TODO Auto-generated method stub

    }

    public Map<String, Object> readStatusUpdates(
            String posterId,
            String userId,
            int numItems,
            boolean ownUpdates) {
        // TODO Auto-generated method stub
        return null;
    }

    public void deleteUser(String userId) {
        // TODO Auto-generated method stub

    }

    public boolean removeFriendship(String followingId, String followedId) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean deleteStatusUpdate(String user, String statusUpdateId) {
        // TODO Auto-generated method stub
        return false;
    }
}
