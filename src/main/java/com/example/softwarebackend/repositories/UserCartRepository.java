package com.example.softwarebackend.repositories;

import com.example.softwarebackend.models.Product;
import com.example.softwarebackend.models.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCartRepository extends JpaRepository<UserCart, Long> {

    UserCart findUserCartByUserId(Long userId);


}
