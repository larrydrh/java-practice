package grpc.client;

import com.ficus.inference.speech.rpcdata.AudioConfig;
import com.ficus.inference.speech.rpcdata.BatchShortVADRequest;
import com.ficus.inference.speech.rpcdata.BatchShortVADResponse;
import com.ficus.inference.speech.rpcdata.ShortVADRequest;
import com.ficus.middleware.ragnaros.extensions.simple_async.SerializableSimpleAsyncWrapper;
import com.ficus.middleware.ragnaros.extensions.simple_async.SimpleAsyncWrappers;
import com.google.protobuf.ByteString;
import grpc.AsrException;
import grpc.config.PropertiesConfig;
import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author yhwu
 */

@Component
public class ShortVadClient {
    private ManagedChannel channel;
    private PropertiesConfig propertiesConfig;
    private SerializableSimpleAsyncWrapper<BatchShortVADRequest, BatchShortVADResponse> shortVadWrapper;

    @Autowired
    public ShortVadClient(ManagedChannel channel, PropertiesConfig propertiesConfig) {
        this.channel = channel;
        this.propertiesConfig = propertiesConfig;
    }

    public BatchShortVADResponse checkShortVad(List<byte[]> audios, AudioConfig.AudioEncoding aue, int sampleRate, int size) throws ExecutionException, InterruptedException, AsrException {

        shortVadWrapper = SimpleAsyncWrappers.protobufWrapper(this.channel, propertiesConfig.getShortVadGroup(), BatchShortVADResponse.class);

        BatchShortVADRequest.Builder batchShortVADRequestBuilder = BatchShortVADRequest.newBuilder();

        for (int i = 0; i < size; i++) {
            batchShortVADRequestBuilder.addRequests(
                    ShortVADRequest.newBuilder()
                            .setAudios(ByteString.copyFrom(audios.get(0)))
                            .setAudioConfig(
                                    AudioConfig.newBuilder()
                                            .setAue(aue)
                                            .setSampleRate(sampleRate)
                                            .build()
                            )
                            .build()
            );
        }
        BatchShortVADResponse batchShortVADResponse = shortVadWrapper.call(batchShortVADRequestBuilder.build(), 50000L).get();
        AsrException.checkAsrStatusAndThrowException(batchShortVADResponse.getStatus());
        return batchShortVADResponse;
    }
}
