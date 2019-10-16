package grpc;


import grpc.service.AudioProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GrpcApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(GrpcApplication.class, args);
        AudioProcessService audioProcessService = context.getBean(AudioProcessService.class);
        audioProcessService.transcribe("/home/rhding/myfile.wav");
    }
}
