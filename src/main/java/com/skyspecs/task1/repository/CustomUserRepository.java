package com.skyspecs.task1.repository;

import com.skyspecs.task1.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CustomUserRepository{
    Page<User> findByFirstNameAndEmail(List<String> firstName, List<String> email, Pageable pageable);
    List<User> findByFirstNameFilters(List<String> firstNamefilter, Pageable pageable);
    List<User> findByEmailFilters(List<String> emailFilters, Pageable pageable);
}
