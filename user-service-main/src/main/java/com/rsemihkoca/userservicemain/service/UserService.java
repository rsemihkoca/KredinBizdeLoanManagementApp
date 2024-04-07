package com.rsemihkoca.userservicemain.service;

import java.util.List;

import com.rsemihkoca.userservicemain.model.User;
import com.rsemihkoca.userservicemain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getById(String userId) {
        return userRepository.getUserById(userId);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }
}
