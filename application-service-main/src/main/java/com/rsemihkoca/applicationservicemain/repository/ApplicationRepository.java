package com.rsemihkoca.applicationservicemain.repository;

import com.rsemihkoca.applicationservicemain.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
//public class ApplicationRepository {
    @Query("SELECT a FROM Application a WHERE a.isActive = true AND a.userEmail = :email")
    List<Application> findActiveApplicationsByUserEmail(@Param("email") String userEmail);
//
    @Query("SELECT a FROM Application a WHERE a.isActive = true")
    List<Application> findAllActiveApplications();


}