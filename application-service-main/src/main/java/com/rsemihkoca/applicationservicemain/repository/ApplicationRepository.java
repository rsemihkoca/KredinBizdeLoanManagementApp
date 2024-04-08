package com.rsemihkoca.applicationservicemain.repository;

import com.rsemihkoca.applicationservicemain.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;



@Repository
public interface ApplicationRepository extends JpaRepository<User, Long> {


}