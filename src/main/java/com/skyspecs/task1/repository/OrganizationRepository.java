package com.skyspecs.task1.repository;

import com.skyspecs.task1.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Integer> {

    Organization findByName(String name);
}
