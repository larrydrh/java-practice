package grpc.client;

import com.ficus.inference.speech.rpcdata.*;
import com.ficus.middleware.ragnaros.extensions.simple_async.SerializableSimpleAsyncWrapper;
import com.ficus.middleware.ragnaros.extensions.simple_async.SimpleAsyncWrappers;
import com.google.protobuf.ByteString;
import grpc.AsrException;
import grpc.config.PropertiesConfig;
import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * @author yhwu
 */
@Component
public class RecognitionClient {
    private ManagedChannel channel;
    private PropertiesConfig propertiesConfig;
    private SerializableSimpleAsyncWrapper<ShortASRRequest, ShortASRResponse> recognitionWrapper;

    @Autowired
    public RecognitionClient(ManagedChannel channel, PropertiesConfig propertiesConfig) {
        this.channel = channel;
        this.propertiesConfig = propertiesConfig;
    }

    public BatchRecognizeResponse checkRecognition(List<byte[]> audios, boolean needCombinedDecoderResult, int size) throws ExecutionException, InterruptedException, AsrException {
        recognitionWrapper = SimpleAsyncWrappers.protobufWrapper(this.channel, propertiesConfig.getRecognitionGroup(), ShortASRResponse.class);

        InternalResultSwitch internalSwitch = InternalResultSwitch.newBuilder().setNeedCombinedDecoderResult(needCombinedDecoderResult).build();

        // prepare recognize requests
        List<RecognizeRequest> recognizeRequests = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            RecognizeRequest recognizeRequest;
            recognizeRequest = RecognizeRequest.newBuilder().setAudios(ByteString.copyFrom(audios.get(i))).build();
            recognizeRequests.add(recognizeRequest);
        }

        BatchRecognizeRequest batchRecognizeRequest = BatchRecognizeRequest.newBuilder().addAllRequests(recognizeRequests)
                .setInternalSwitch(internalSwitch)
                .setMaxTopn(1)
                .build();

        ShortASRRequest request = ShortASRRequest.newBuilder().setRequestType(RequestType.BATCH_RECOGNIZE).setBatchRecognizeRequest(batchRecognizeRequest).build();

        ShortASRResponse response = recognitionWrapper.call(request, 500000L).get();

        AsrException.checkAsrStatusAndThrowException(response.getStatus());

        return response.getBatchRecognizeResponse();
    }

}
