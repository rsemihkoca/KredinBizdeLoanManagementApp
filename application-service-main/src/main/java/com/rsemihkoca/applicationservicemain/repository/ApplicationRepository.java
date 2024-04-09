package com.rsemihkoca.applicationservicemain.repository;

import com.rsemihkoca.applicationservicemain.model.Application;
import com.rsemihkoca.applicationservicemain.model.User;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByUserEmail(String email);
}