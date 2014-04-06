package de.metalcon.middleware.controller.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.hh.request_dispatcher.Callback;
import net.hh.request_dispatcher.Dispatcher;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.metalcon.domain.Muid;
import de.metalcon.middleware.core.DispatcherFactory;
import de.metalcon.musicstreamingserver.api.requests.create.MusicStreamingCreateRequest;
import de.metalcon.musicstreamingserver.api.responses.create.MusicStreamingCreateResponse;

@Controller
public class TestUploadMusicController {

    @SuppressWarnings("unused")
    private static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private DispatcherFactory dispatcherFactory;

    //    // TODO: not thread save! need to use spring annotation for ThreadBeans
    //    private static Dispatcher dispatcher = new Dispatcher();

    public ModelAndView handle() {
        return new ModelAndView("music");
    }

    public TestUploadMusicController() {
    }

    public ModelAndView showForm() {

        ModelMap model = new ModelMap();
        model.addAttribute("userId", "testUserId");
        return new ModelAndView("test/music", model);
    }

    public String postMp3(@RequestParam("file") MultipartFile file)
            throws IOException {
        // TODO: do magic call request dispatcher

        final List<String> response = new ArrayList<String>();
        @SuppressWarnings("unused")
        List<Map<String, Object>> modelNews =
                new LinkedList<Map<String, Object>>();

        byte[] musicFile = file.getBytes();

        System.out.println(musicFile.length);

        Dispatcher dispatcher = dispatcherFactory.getDispatcher();

        dispatcher.execute(new MusicStreamingCreateRequest(Muid.createFromID(
                (long) (Math.random() * 1000000)), musicFile, "{}"),
                new Callback<MusicStreamingCreateResponse>() {

                    @Override
                    public void onSuccess(MusicStreamingCreateResponse arg0) {
                        System.out.println("received response!");
                        response.add(arg0.getResponseString());
                    }
                });

        // FIXME: Here is the blocking. Put to another position. Not clear how to collect the processed answers. Since this should be in the metalcon controller. But on the other side in the callbacks one should be able to use outside data. Will all of this be achieved with DTO's ? 
        dispatcher.gatherResults();

        String answer = response.get(0);
        System.out.println(answer);

        return "redirect:/test/music/";
    }
}
