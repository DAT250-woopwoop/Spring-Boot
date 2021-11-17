package no.hvl.dat250.feedApp.dweet;

import no.hvl.dat250.feedApp.dto.*;
import no.hvl.dat250.feedApp.entity.*;
import no.hvl.dat250.feedApp.rabbit.*;
import no.hvl.dat250.feedApp.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.scheduling.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;

@Service
public class DweetService {

    @Autowired
    PollService pollService;

    @Autowired
    TaskScheduler taskScheduler;

    @Autowired
    RabbitMQSender rabbitMQSender;

    Mapper mapper = new Mapper();

    public void send(Poll poll, boolean open) {
        Date delay;
        String status;

        if (open) {
            status = "open";
            delay = poll.getStartTime();
            send(poll, false);
        } else {
            status = "closed";
            delay = poll.getEndTime();
        }
        taskScheduler.schedule(() -> {
            try {
                sendPost(poll, status);
                if (status == "close"){
                    rabbitMQSender.send(mapper.toDTO(pollService.find(poll.getId())));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, delay);

    }
    private void sendPost(Poll poll, String status) throws IOException {
        URL url = new URL("https://dweet.io/dweet/for/dat250woop-woop");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);

        byte[] out = ("{\"question\":\"" + poll.getPollName() + "\",\"status\":\"" + status + "\",\"id\":\"" + poll.getId() + "\"}").getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        http.connect();
        try (OutputStream os = http.getOutputStream()) {
            os.write(out);
        }
    }



}
