package rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class Send {
    public static final String QUEUE_NAME="hello1";

    public static void main(String[] args) throws Exception{
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        factory.setPassword("guest");
//        factory.setUsername("guest");
//        try (Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel()) {
//            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//            String message = "hello world";
//            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//            System.out.println(" [x] Sent '" + message + "'");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("localhost");
            connectionFactory.setUsername("guest");
            connectionFactory.setPassword("guest");
            connectionFactory.setPort(5672);

            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "hello world";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("send message: " + message);

            channel.close();
            connection.close();
    }
}
