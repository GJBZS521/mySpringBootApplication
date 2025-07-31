package org.example.consumer;

import io.netty.util.Constant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.example.constants.Constants;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(
        topics = Constants.MY_TEST_TOPIC,
        groupId = Constants.MY_TEST_GROUP,
        concurrency = "1"
    )
    public void listener(ConsumerRecord<String, String> record, Acknowledgment ack) {
        try {
            System.out.println("kafkaConsumer : record.key :" + record.key() + " value :" + record.value());
            ack.acknowledge();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
