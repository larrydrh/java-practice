package grpc.service;


import com.ficus.inference.speech.rpcdata.BatchPostProcessResponse;
import com.ficus.inference.speech.rpcdata.BatchRecognizeResponse;
import grpc.client.PostProcessClient;
import grpc.client.RecognitionClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yhwu
 */
@Service
@Slf4j
public class AudioProcessService {
    private RecognitionClient recognitionClient;
    private PostProcessClient postProcessClient;
    public AudioProcessService(RecognitionClient recognitionClient, PostProcessClient postProcessClient) {
        this.recognitionClient = recognitionClient;
        this.postProcessClient = postProcessClient;
    }
    public void transcribe(String path) {
        List<byte[]> audios = new ArrayList<>();
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(path)));
            byte[] audio = inputStream.readAllBytes();
            audios.add(audio);
            BatchRecognizeResponse responses = recognitionClient.checkRecognition(audios, false, 1);
            responses.getRecResponses(0).getRecResults(0).getText();
            System.out.println( responses.getRecResponses(0).getRecResults(0).getText().getText());
            BatchPostProcessResponse result = postProcessClient.checkPostProcess(responses, true, true, true);
            System.out.println( result.getResponses(0).getResult().getText().getText());
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

}
