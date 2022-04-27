package com.skyspecs.task1.repository;

import com.skyspecs.task1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomUserRepository{
    List<User> findByFirstNameAndEmail(List<String> firstName, List<String> email);
    List<User> findByFirstNameFilters(List<String> firstNamefilter);
    List<User> findByEmailFilters(List<String> emailFilters);
}
