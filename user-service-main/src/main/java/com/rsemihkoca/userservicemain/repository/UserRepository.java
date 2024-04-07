package com.rsemihkoca.userservicemain.repository;

import com.rsemihkoca.userservicemain.model.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final List<User> userList = new ArrayList<>();

    public User save(User user) {
        userList.add(user);
        return user;
    }

    public User getUserById(String userId) {
        return userList.stream().filter(user -> user.getUserId().equals(userId)).findFirst().orElse(null);
    }

    public List<User> getAll() {
        return userList;
    }

    public void initializeDatabase() {
        userList.add(new User("1", "Cem", 30));
        userList.add(new User("2", "Cem", 25));
        userList.add(new User("3", "Cem", 28));
        userList.add(new User("4", "Cem", 27));
        userList.add(new User("5", "Semih", 24)); 
    }

}
