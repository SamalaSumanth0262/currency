package com.example.currency_conversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.currency_conversion.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
