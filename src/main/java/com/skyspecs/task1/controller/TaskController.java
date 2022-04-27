package com.skyspecs.task1.controller;

import com.skyspecs.task1.dto.TaskUserForm;
import com.skyspecs.task1.dto.UserDto;
import com.skyspecs.task1.service.TaskService;
import com.skyspecs.task1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity createTask(@Valid  @RequestBody TaskUserForm taskUserForm){
        System.out.println("task controller");
        return new ResponseEntity(taskService.createTask(taskUserForm),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity fetchAllTasks(){
        return new ResponseEntity<>(taskService.fetchAllTasks(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity fetchById(@PathVariable int id){
        return new ResponseEntity(taskService.entityToDto(taskService.fetchTaskById(id)),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteTask(@PathVariable int id){
        taskService.deleteTask(id);
        return HttpStatus.OK;
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity editTask(@PathVariable int id, @RequestBody TaskUserForm taskUserForm, @RequestBody UserDto userDto){
        return new ResponseEntity( taskService.editTask(id,taskUserForm),HttpStatus.OK);
    }
}
