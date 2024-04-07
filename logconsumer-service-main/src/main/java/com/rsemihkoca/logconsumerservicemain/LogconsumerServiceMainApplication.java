package com.rsemihkoca.logconsumerservicemain;

import com.rsemihkoca.logconsumerservicemain.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class LogconsumerServiceMainApplication{


    public static void main(String[] args) {
        SpringApplication.run(LogconsumerServiceMainApplication.class, args);
    }

}



