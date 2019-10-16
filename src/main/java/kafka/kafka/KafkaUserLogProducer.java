package kafka.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;


@Component
public class KafkaUserLogProducer {

    @Autowired
    private KafkaTemplate<String, ByteBuffer> userLogkafkaTemplate;

    @Value(value = "${kafka-asr.topic.userlog.name}")
    private String userLogTopicName;

    public void sendMessage(ByteBuffer message) {
        userLogkafkaTemplate.send(userLogTopicName, message);
    }

}
