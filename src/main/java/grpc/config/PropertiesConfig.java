package grpc.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author yhwu
 */
@Data
@Configuration
public class PropertiesConfig {

    public static ConcurrentHashMap<String, Long> errorHashMap = new ConcurrentHashMap<>();

    public static final String REVIEW_SAAS_FILE_QUERY_MAP = "REVIEW_SAAS_FILE_QUERY_MAP:";
    public static final String REVIEW_SAAS_FILE_QUERY_MAP_FINISH_FLAG = "REVIEW_SAAS_FILE_QUERY_MAP_FINISH_FLAG:";
    public static final String REVIEW_SAAS_REQUEST_INFO = "REVIEW_SAAS_REQUEST_DEVID:";


    //research group
    @Value("${research.short.vad.group}")
    String shortVadGroup;
    @Value("${research.recognition.group}")
    String recognitionGroup;
    @Value("${research.text.moderation.group}")
    String textModerationGroup;
    @Value("${research.post.process.group}")
    String postProcessGroup;
    @Value("${research.moderation.combine.group}")
    String moderationCombineGroup;
    @Value("${research.audio.moderation.group}")
    String audioModerationGroup;

    //ragnaros
    @Value("${ragnaros.client.host}")
    String ragnarosClientHost;
    @Value("${ragnaros.client.port}")
    Integer ragnarosClientPort;

    //threadpool
    @Value("${threadpool.stream.corepoolsize}")
    Integer streamCorePoolSize;
    @Value("${threadpool.stream.maxpoolsize}")
    Integer streamMaxPoolSize;
    @Value("${threadpool.stream.queuesize}")
    Integer streamQueueSize;
    @Value("${threadpool.audio.corepoolsize}")
    Integer audioCorePoolSize;
    @Value("${threadpool.audio.maxpoolsize}")
    Integer audioMaxPoolSize;
    @Value("${threadpool.audio.queuesize}")
    Integer audioQueueSize;
    @Value("${threadpool.callback.corepoolsize}")
    Integer callbackCorePoolSize;
    @Value("${threadpool.callback.maxpoolsize}")
    Integer callbackMaxPoolSize;
    @Value("${threadpool.callback.queuesize}")
    Integer callbackQueueSize;
    @Value("${threadpool.kafka.corepoolsize}")
    Integer kafkaCorePoolSize;
    @Value("${threadpool.kafka.maxpoolsize}")
    Integer kafkaMaxPoolSize;
    @Value("${threadpool.kafka.queuesize}")
    Integer kafkaQueueSize;
    @Value("${threadpool.oss.corepoolsize}")
    Integer ossCorePoolSize;
    @Value("${threadpool.oss.maxpoolsize}")
    Integer ossMaxPoolSize;
    @Value("${threadpool.oss.queuesize}")
    Integer ossQueueSize;

    //queue config
    @Value("${audio.list.queue.batch.size}")
    Integer audioListQueueBatchSize;
    @Value("${audio.list.queue.max.size}")
    Integer audioListQueueMaxSize;
    @Value("${audio.list.queue.waiting.time}")
    Integer audioListQueueWaitingTime;
    @Value("${audio.list.queue.timeout}")
    Integer audioListQueueTimeout;

    @Value("${audio.seperate.time}")
    Double audioSeperateTime;

    //oss
    @Value(value = "${oss.endpoint}")
    private String ossEndpoint;
    @Value(value = "${oss.access.key.id}")
    private String ossAccessKeyId;
    @Value(value = "${oss.access.key.secret}")
    private String ossAccessKeySecret;
    @Value(value = "${oss.bucket.name}")
    private String ossBucketName;
    @Value(value = "${oss.url.expire.time}")
    private Long ossUrlExpireTime;
    @Value(value = "${oss.url}")
    private String ossUrl;

    @Value(value = "${http.timeout}")
    private Integer httpTimeout;
    @Value(value = "${http.times}")
    private Integer httpTimes;

    @Value(value = "${stream.error.timeout}")
    private Long streamErrorTimeout;
    @Value(value = "${audio.moderation.timeout}")
    private Long audioModerationTimeout;

    @Value("${kubernetes.pod.ip}")
    private String serviceName;
    @Value("${etcd.server.address}")
    private String serverName;

    @Value(value = "${etcd.stream.get.time}")
    private Long etcdStreamGetTime;

    @Value(value = "${error.waiting.time}")
    private Long errorWaitingTime;

    @Value(value = "${http.etcd.timeout}")
    private Integer httpEtcdTimeout;

    @Value(value = "${server.port}")
    private String serverPort;


    @Bean
    public ManagedChannel managedChannel() {
        return  ManagedChannelBuilder.forAddress(
                this.getRagnarosClientHost(),
                this.getRagnarosClientPort()).usePlaintext().build();
    }


}

