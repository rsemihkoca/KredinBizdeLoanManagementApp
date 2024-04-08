package com.rsemihkoca.applicationservicemain.service;

import com.rsemihkoca.applicationservicemain.dto.request.CreateUserRequest;
import com.rsemihkoca.applicationservicemain.dto.response.UserResponse;
import com.rsemihkoca.applicationservicemain.model.Constants;
import com.rsemihkoca.applicationservicemain.model.User;
import com.rsemihkoca.applicationservicemain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @CacheEvict(value = Constants.userTable.TABLE_NAME, allEntries = true)
    public UserResponse createUser(CreateUserRequest user) {
        User userEntity = modelMapper.map(user, User.class);
        User savedUser = userRepository.save(userEntity);
        log.info("User saved successfully");
        return modelMapper.map(savedUser, UserResponse.class);
    }
    @Cacheable(value = Constants.userTable.TABLE_NAME, key = "#userId")
    public UserResponse getById(Long userId) {
        User getUser = userRepository.getUserByUserId(userId);
        log.info("User with " + userId + " retrieved from database");
        return modelMapper.map(getUser, UserResponse.class);
    }
    @Cacheable(value = Constants.userTable.TABLE_NAME)
    public List<UserResponse> getAll() {
        List<User> users = userRepository.findAll();

        //                .toList(); When you call toList() and then serialize the object, the collection type simply disappears.
//                Jackson expects to see a string (VALUE STRING) with a collection type, but receives the beginning of an object (OBJECT_VALUE)
//
//        log.info("All users retrieved from database");
        return users.stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());

    }
}
