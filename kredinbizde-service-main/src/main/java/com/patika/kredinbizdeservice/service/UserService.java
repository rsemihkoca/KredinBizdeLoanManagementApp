package com.patika.kredinbizdeservice.service;

import com.patika.kredinbizdeservice.exceptions.ExceptionMessages;
import com.patika.kredinbizdeservice.exceptions.KredinbizdeException;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.producer.NotificationProducer;
import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import com.patika.kredinbizdeservice.producer.enums.NotificationType;
import com.patika.kredinbizdeservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope(value = "singleton")
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private final NotificationProducer notificationProducer;

    @Autowired
    public UserService(UserRepository userRepository, NotificationProducer notificationProducer) {
        this.userRepository = userRepository;
        this.notificationProducer = notificationProducer;
    }

    public User save(User user) {
        System.out.println("userRepository: " + userRepository.hashCode());

        userRepository.save(user);

        notificationProducer.sendNotification(prepareNotificationDTO(NotificationType.EMAIL, user.getEmail()));

        return user;
    }

    private NotificationDTO prepareNotificationDTO(NotificationType notificationType, String email) {
        return NotificationDTO.builder()
                .message("user kaydedildi.")
                .notificationType(notificationType)
                .email(email)
                .build();
    }


    public List<User> getAll() {
        System.out.println("userRepository: " + userRepository.hashCode());
        return userRepository.getAll();
    }


    public User getByEmail(String email) {

        Optional<User> foundUser = userRepository.findByEmail(email);

        User user = foundUser.orElseThrow(() -> new KredinbizdeException(ExceptionMessages.USER_NOT_FOUND));

        if (foundUser.isPresent()) {
            user = foundUser.get();
        }

        return user;

    }

    public User update(String email, User user) {

        Optional<User> foundUser = userRepository.findByEmail(email);

        foundUser.get().setName(user.getName());

        foundUser.get().setSurname(user.getSurname());

        userRepository.delete(user);

        userRepository.save(foundUser.get());

        return foundUser.get();
    }

    public User getById(Long userId) {
        return userRepository.findByUserId(userId);
    }
}
