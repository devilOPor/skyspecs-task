package com.skyspecs.task1.service;

import com.skyspecs.task1.dto.TaskDto;
import com.skyspecs.task1.dto.TaskUserForm;
import com.skyspecs.task1.entity.Task;
import com.skyspecs.task1.entity.User;

import java.util.List;

public interface TaskService {
    TaskUserForm createTask(TaskUserForm taskUserForm);
    List<Task> fetchAllTasks();
    void deleteTask(int id);
    TaskUserForm editTask(int id, TaskUserForm form);
    Task fetchTaskById(int id);
    Task dtoToEntity(TaskDto taskDto);
    TaskDto entityToDto(Task task);
}
