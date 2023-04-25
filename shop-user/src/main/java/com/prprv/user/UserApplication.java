package com.prprv.user;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Yoooum
 */
@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    public void receiveMessage(String message) {
        System.out.println("RabbitMQ Received message: " + message);
    }
}
