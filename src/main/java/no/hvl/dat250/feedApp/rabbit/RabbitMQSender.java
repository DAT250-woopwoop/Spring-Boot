package no.hvl.dat250.feedApp.rabbit;

import no.hvl.dat250.feedApp.dto.*;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("logs")
    private String logs;

    @Value("routing")
    private String routingkey;

    public void send(PollDTO poll){
        rabbitTemplate.convertAndSend(logs, routingkey, poll);
        System.out.println("Send message " + poll);
    }
}