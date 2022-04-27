package com.skyspecs.task1.repository;

import com.skyspecs.task1.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Integer> {

    @Query(value = "select * from organization where name LIKE %?1%",nativeQuery = true)
    Organization findByName(String name);
}
