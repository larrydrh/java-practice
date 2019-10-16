package grpc.client;

import com.ficus.inference.speech.rpcdata.*;
import com.ficus.middleware.ragnaros.extensions.simple_async.SerializableSimpleAsyncWrapper;
import com.ficus.middleware.ragnaros.extensions.simple_async.SimpleAsyncWrappers;
import grpc.AsrException;
import grpc.config.PropertiesConfig;
import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author yhwu
 */
@Component
public class PostProcessClient {
    private ManagedChannel channel;
    private PropertiesConfig propertiesConfig;
    private SerializableSimpleAsyncWrapper<BatchPostProcessRequest, BatchPostProcessResponse> postProcessWrapper;

    @Autowired
    public PostProcessClient(ManagedChannel channel, PropertiesConfig propertiesConfig) {
        this.channel = channel;
        this.propertiesConfig = propertiesConfig;
    }

    public BatchPostProcessResponse checkPostProcess(BatchRecognizeResponse batchRecognizeResponse, boolean enableAddPunctuation, boolean enableConvertNumber, boolean enableReplaceRule) throws ExecutionException, InterruptedException, AsrException {
        postProcessWrapper = SimpleAsyncWrappers.protobufWrapper(this.channel, propertiesConfig.getPostProcessGroup(), BatchPostProcessResponse.class);

        PostProcessOption option = PostProcessOption.newBuilder().setEnableAddPunctuation(enableAddPunctuation).setEnableConvertNumber(enableConvertNumber).setEnableReplaceRule(enableReplaceRule).build();

        List<SpeechRecogResult> recogResults = new ArrayList<SpeechRecogResult>();
        for(RecognizeResponse recResponse:batchRecognizeResponse.getRecResponsesList()) {
            recogResults.addAll(recResponse.getRecResultsList());
        }
        BatchPostProcessRequest batchPostProcessRequest =
                BatchPostProcessRequest.newBuilder().setOption(option).addAllInputResults(recogResults).build();
        
        BatchPostProcessResponse batchPostProcessResponse = postProcessWrapper.call(batchPostProcessRequest, 50000L).get();
        AsrException.checkAsrStatusAndThrowException(batchPostProcessResponse.getStatus());

        return batchPostProcessResponse;

    }
}
