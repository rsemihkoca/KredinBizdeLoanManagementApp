package com.rsemihkoca.userservicemain.service;

import java.util.List;
import java.util.stream.Collectors;

import com.rsemihkoca.userservicemain.exceptions.dto.request.CreateUserRequest;
import com.rsemihkoca.userservicemain.exceptions.dto.response.UserResponse;
import com.rsemihkoca.userservicemain.model.Constants;
import com.rsemihkoca.userservicemain.model.User;
import com.rsemihkoca.userservicemain.producer.GenericKafkaProducer;
import com.rsemihkoca.userservicemain.producer.dto.DeletedUser;
import com.rsemihkoca.userservicemain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final ModelMapper modelMapper;
    private final GenericKafkaProducer genericKafkaProducer;
    private UserRepository userRepository;

    @Autowired
    public UserService(ModelMapper modelMapper, UserRepository userRepository,
                       GenericKafkaProducer genericKafkaProducer) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.genericKafkaProducer = genericKafkaProducer;
    }

    @Cacheable(value = Constants.userTable.TABLE_NAME, key = "#email", unless = "#result == null")
    public UserResponse getByEmail(String email) {
        User getUser = userRepository.getUserByEmail(email);
        if (getUser == null) {
            return null;
        }
        return modelMapper.map(getUser, UserResponse.class);
    }

    @CacheEvict(value = Constants.userTable.TABLE_NAME, allEntries = true)
    public UserResponse createUser(CreateUserRequest user) {
        User userEntity = modelMapper.map(user, User.class);
        User savedUser = userRepository.save(userEntity);
        log.info("User saved successfully");
        return modelMapper.map(savedUser, UserResponse.class);
    }
    @Cacheable(value = Constants.userTable.TABLE_NAME, key = "#userId", unless = "#result == null")
    public UserResponse getById(Long userId) {
        User getUser = userRepository.getUserByUserId(userId);
        if (getUser == null) {
            return null;
        }
        return modelMapper.map(getUser, UserResponse.class);
    }
    @Cacheable(value = Constants.userTable.TABLE_NAME)
    public List<UserResponse> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());

    }

    @CacheEvict(value = Constants.userTable.TABLE_NAME, allEntries = true)
    public UserResponse deleteByEmail(String email) {
        User user = userRepository.getUserByEmail(email);
        userRepository.delete(user);
        genericKafkaProducer.sendUserDeletion(modelMapper.map(user, DeletedUser.class));
        return modelMapper.map(user, UserResponse.class);
    }
}
