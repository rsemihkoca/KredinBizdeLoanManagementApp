package com.rsemihkoca.userservicemain.service;

import java.util.List;
import java.util.stream.Collectors;

import com.rsemihkoca.userservicemain.exceptions.dto.request.CreateUserRequest;
import com.rsemihkoca.userservicemain.exceptions.dto.response.GenericResponse;
import com.rsemihkoca.userservicemain.exceptions.dto.response.UserResponse;
import com.rsemihkoca.userservicemain.model.Constants;
import com.rsemihkoca.userservicemain.model.User;
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
    private UserRepository userRepository;

    @Autowired
    public UserService(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
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
}
