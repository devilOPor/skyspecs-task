package com.skyspecs.task1.repository;

import com.skyspecs.task1.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByRole(String role);
}
