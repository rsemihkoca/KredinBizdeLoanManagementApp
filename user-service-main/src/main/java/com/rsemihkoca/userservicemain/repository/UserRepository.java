package com.rsemihkoca.userservicemain.repository;

import com.rsemihkoca.userservicemain.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByUserId(Long userId);

}
