package kafka.service;

import kafka.kafka.KafkaUserLogProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    @Autowired
    KafkaUserLogProducer kafkaUserLogProducer;
}
