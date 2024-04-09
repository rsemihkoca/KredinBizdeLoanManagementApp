package com.patika.garantiservice.repository;

import com.patika.garantiservice.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
