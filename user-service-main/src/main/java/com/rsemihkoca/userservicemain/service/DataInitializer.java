package com.rsemihkoca.userservicemain.service;

import com.rsemihkoca.userservicemain.model.*;
import com.rsemihkoca.userservicemain.repository.UserRepository;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) {

        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            log.info("Data already initialized");
            return;
        } else {
            log.info("Data is empty, initializing data");
            User user1 = new User();
            Address address1 = new Address();
            address1.setAddressTitle("EV");
            address1.setAddressDescription("ISTANBUL TURKİYE");

            user1.setName("Cem");
            user1.setAge(30);
            user1.setEmail("cem30@gmail.com");
            user1.setAddress(address1);

            userRepository.save(user1);

            User user2 = new User();
            Address address2 = new Address();
            address2.setAddressTitle("EV");
            address2.setAddressDescription("KARAMAN TURKİYE");

            user2.setName("Cem");
            user2.setAge(25);
            user2.setEmail("cem25@gmail.com");
            user2.setAddress(address2);

            userRepository.save(user2);

            User user3 = new User();
            Address address3 = new Address();
            address3.setAddressTitle("EV");
            address3.setAddressDescription("ANKARA TURKİYE");

            user3.setName("Cem");
            user3.setAge(28);
            user3.setEmail("cem28@gmail.com");
            user3.setAddress(address3);

            userRepository.save(user3);

            User user4 = new User();
            Address address4 = new Address();
            address4.setAddressTitle("EV");
            address4.setAddressDescription("ISTANBUL TURKİYE");

            user4.setName("Cem");
            user4.setAge(27);
            user4.setEmail("cem27@gmail.com");
            user4.setAddress(address4);

            userRepository.save(user4);

            User user5 = new User();
            Address address5 = new Address();
            address5.setAddressTitle("EV");
            address5.setAddressDescription("MANISA TURKİYE");

            user5.setName("Semih");
            user5.setAge(24);
            user5.setEmail("semih24@gmail.com");
            user5.setAddress(address5);

            userRepository.save(user5);

            log.info("Data initialized");
        }

    }
}
