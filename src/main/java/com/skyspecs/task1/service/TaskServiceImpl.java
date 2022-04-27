package com.skyspecs.task1.service;

import com.skyspecs.task1.dto.TaskDto;
import com.skyspecs.task1.dto.TaskUserForm;
import com.skyspecs.task1.dto.UserDto;
import com.skyspecs.task1.entity.Task;
import com.skyspecs.task1.entity.User;
import com.skyspecs.task1.repository.TaskRepository;
import com.skyspecs.task1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    public TaskUserForm createTask(TaskUserForm form){
        TaskDto taskDto = form.getTaskDto();
        Task task = dtoToEntity(form.getTaskDto());
        task.setUser(userService.userDtoToEntity(form.getUserDto()));
        int userId = task.getUser().getId();
        User user = userService.fetchUserById(task.getUser().getId());
        task.setUser(user);
        task = taskRepository.save(task);
        form.setTaskDto(entityToDto(task));
        form.setUserDto(userService.userEntityToDto(user));
        return form;
    }

    public List<Task> fetchAllTasks(){
        return taskRepository.findAll();
    }

    public void deleteTask(int id){
        taskRepository.deleteById(id);
    }

    public TaskUserForm editTask(int id, TaskUserForm form){
        Task task = new Task();
        task.setDescription(form.getTaskDto().getDescription());
        task.setId(id);
        User user = userRepository.getById(task.getUser().getId());
        if(user==null ) {
          return null;
        }
        task.setUser(user);
        task =  taskRepository.save(task);
        form.setTaskDto(entityToDto(task));
        form.setUserDto(userService.userEntityToDto(task.getUser()));
        return form;
    }
    
    public Task fetchTaskById(int id){
        Optional<Task> optional = taskRepository.findById(id);
        return optional.get();
    }

    public TaskDto entityToDto(Task task){
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setDescription(task.getDescription());
        return dto;
    }

    public Task dtoToEntity(TaskDto taskDto){
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setDescription(taskDto.getDescription());
        return task;
    }
}
