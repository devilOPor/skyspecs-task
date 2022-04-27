package com.skyspecs.task1.repository;

import com.skyspecs.task1.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {

}
