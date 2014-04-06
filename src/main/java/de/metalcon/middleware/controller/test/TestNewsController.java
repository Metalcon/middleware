package de.metalcon.middleware.controller.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class TestNewsController {

    private static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private BeanFactory beanFactory;

    public ModelAndView handle() {
        return new ModelAndView("news");
    }

    public ModelAndView listNews(
            @PathVariable("userId") String userId,
            @PathVariable("posterId") String posterId,
            @PathVariable("ownUpdates") Boolean ownUpdates)
            throws JsonParseException, JsonMappingException, IOException {
        //        RequestTransaction tx = beanFactory.getBean(RequestTransaction.class);
        //        tx.request(new JsonRequest(
        //                "http://localhost:8080/Graphity-Server-0.1/read?" + "user_id="
        //                        + userId + "&poster_id=" + posterId + "&num_items=10"
        //                        + "&own_updates=" + (ownUpdates ? "1" : "0")));

        List<Map<String, Object>> modelNews =
                new LinkedList<Map<String, Object>>();

        String answer =
                "{\"items\":[{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"hey\",\"id\":\"1394529863852\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2014-03-11T10:24:24Z\"},{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"asdf\",\"id\":\"1387393615465\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2013-12-18T20:06:55Z\"},{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"nice\",\"id\":\"1386331367991\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2013-12-06T13:02:48Z\"},{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"auf gehts. 2010 fi.... auch im stehen.\",\"id\":\"1386331359345\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2013-12-06T13:02:39Z\"},{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"entry !!1386330847185\",\"id\":\"1386330847185\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2013-12-06T12:54:07Z\"},{\"verb\":\"read\",\"actor\":{\"objectType\":\"person\",\"id\":\"3\",\"displayName\":\"anotherUser\"},\"object\":{\"message\":\"entry !!1386330828215\",\"id\":\"1386330828215\",\"type\":\"Plain\",\"objectType\":\"article\"},\"published\":\"2013-12-06T12:53:48Z\"}]}";//(String) tx.recieve();
        JsonNode root = mapper.readValue(answer, JsonNode.class);
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
        model.addAttribute("userId", userId);
        model.addAttribute("posterId", posterId);
        model.addAttribute("ownUpdates", ownUpdates);
        model.addAttribute("news", modelNews);
        return new ModelAndView("test/news", model);
    }

    public String postNews(
            @PathVariable("userId") String userId,
            @PathVariable("posterId") String posterId,
            @PathVariable("ownUpdates") Boolean ownUpdates,
            @RequestParam("formMessage") String formMessage) throws IOException {
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

}
