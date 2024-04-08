package com.rsemihkoca.applicationservicemain.repository;

import com.rsemihkoca.applicationservicemain.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByUserId(Long userId);

}
