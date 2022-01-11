package com.example.softwarebackend.repositories;

import com.example.softwarebackend.models.UserMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMailRepository extends JpaRepository<UserMail, Long> {

    List<UserMail> findAllByUserId(Long userId);

}
