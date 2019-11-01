package kafka.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteBufferSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaUserLogProducerConfig {

    @Value(value = "${kafka-asr.bootstrap.address}")
    private String bootstrapAddress;

    @Value(value = "${kafka-asr.topic.userlog.name}")
    private String userlogTopicName;

    @Bean
    public ProducerFactory<String, ByteBuffer> userLogProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteBufferSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, ByteBuffer> userLogKafkaTemplate() {
        return new KafkaTemplate<>(userLogProducerFactory());
    }

    @Bean
    public NewTopic adviceTopic() {
        return new NewTopic(userlogTopicName, 3, (short) 1);
    }

}


