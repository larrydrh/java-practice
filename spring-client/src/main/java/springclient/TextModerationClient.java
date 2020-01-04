package springclient;

import com.google.common.base.Stopwatch;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import springclient.model.Dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TextModerationClient {
    static final String URL_TEXT_MODERATION = "http://10.10.200.127:30081/v1/dictionary/";
//    static final String URL_TEXT_MODERATION = "http://localhost:8085/v1/dictionary/";
    static final String DICTIONARY_PATH = "/home/rhding/projects/java-practice/spring-client/src/main/resources/dictionary.txt";
    static final Integer READ_LINE = 1000000;

    public static void main(String[] args) throws IOException, InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        Dictionary newDictionary = new Dictionary();
        Stopwatch stopWatch = Stopwatch.createStarted();
        newDictionary.setLabel("摄政");
        Path path = Paths.get(DICTIONARY_PATH);
        BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        List<String> words = new ArrayList<>();
        String word;
        int i =0;
        while ((word = reader.readLine()) != null && i++ < READ_LINE) {
            words.add(word);
        }
//        words.forEach(word1 -> System.out.println(word1));

        newDictionary.setContext(words);
        HttpEntity<Dictionary>  requestBody = new HttpEntity<>(newDictionary);
//        Thread.sleep(1000);
//        restTemplate.put (URL_TEXT_MODERATION+"1000000", requestBody);
        restTemplate.postForObject (URL_TEXT_MODERATION+"1000000", requestBody, Object.class);
//        restTemplate.delete(URL_TEXT_MODERATION+"12345679");
        System.out.println(stopWatch.elapsed(TimeUnit.MILLISECONDS));

    }
}
