package de.metalcon.middleware.controller.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.hh.RequestDispatcher.Callback;
import net.hh.RequestDispatcher.Dispatcher;
import net.hh.RequestDispatcher.Service.ZmqService;
import net.hh.RequestDispatcher.TransferClasses.Request;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.metalcon.domain.Muid;
import de.metalcon.musicstreamingserver.api.requests.MusicStreamingCreateRequest;
import de.metalcon.musicstreamingserver.api.requests.registration.CreateRequestData;
import de.metalcon.musicstreamingserver.api.responses.MusicStreamingCreateResponse;

@Controller
public class TestUploadMusicController {

    private static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private BeanFactory beanFactory;

    // TODO: not thread save! need to use spring annotation for ThreadBeans
    private static Dispatcher dispatcher = new Dispatcher();

    public ModelAndView handle() {
        return new ModelAndView("music");
    }

    public TestUploadMusicController() {
        dispatcher.registerServiceProvider("music", new ZmqService(
                "tcp://127.0.0.1:6666"));
    }

    public ModelAndView showForm() {

        ModelMap model = new ModelMap();
        model.addAttribute("userId", "testUserId");
        return new ModelAndView("test/music", model);
    }

    public String postMp3(@RequestParam("formMessage") String formMessage)
            throws IOException {
        // TODO: do magic call request dispatcher

        final List<String> response = new ArrayList<String>();
        List<Map<String, Object>> modelNews =
                new LinkedList<Map<String, Object>>();

        File mp3 = new File("/etc/musicStorageServer/test.mp3");
        FileInputStream fis = new FileInputStream(mp3);
        byte[] musicFile = IOUtils.toByteArray(fis);

        CreateRequestData createRequestData =
                new CreateRequestData(
                        new Muid((long) (Math.random() * 1000000)), musicFile,
                        "");
        dispatcher.execute("music", (Request) new MusicStreamingCreateRequest(
                createRequestData), new Callback<MusicStreamingCreateResponse>(
                new MusicStreamingCreateResponse(formMessage)) {

            @Override
            public void onTimeOut(String errorMessage) {
                response.add("timout!!!" + errorMessage);
            }

            @Override
            public void
                onSuccess(final MusicStreamingCreateResponse secondReply) {
                response.add("success" + secondReply.toString());

            }
        });

        // FIXME: Here is the blocking. Put to another position. Not clear how to collect the processed answers. Since this should be in the metalcon controller. But on the other side in the callbacks one should be able to use outside data. Will all of this be achieved with DTO's ? 
        dispatcher.gatherResults(100);

        String answer = response.get(0);
        System.out.println(answer);

        return "redirect:/test/music/";
    }
}
