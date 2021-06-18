package com.arch.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServer {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServer.class, args);
        String[] greetings = {"Hi!", "Hello!", "Privet!"};
        greetEveryone(greetings);
    }

    private static void greetEveryone(String[] greetings) {
        for (int i = 0; i < greetings.length; i++) {
            System.out.println(greetings[i]);
        }
    }


    /**
     * sayHello method return greeting!
     * **/
    public static void testGreeting(String greeting) {
        System.out.println(greeting);
    }

}
