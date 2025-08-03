package org.example.product;

import org.example.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

@Component
public class KafkaProduct {

    @Autowired
    KafkaTemplate<String, String> template;

    @Transactional
    public void sendIntoMQ(String key, String value) {
        try {
            template.send(Constants.MY_TEST_TOPIC, value).get();
            System.out.println("send information success");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
