package com.skyspecs.task1;

import com.skyspecs.task1.dto.TaskDto;
import com.skyspecs.task1.dto.TaskUserForm;
import com.skyspecs.task1.dto.UserDto;
import com.skyspecs.task1.service.TaskService;
import com.skyspecs.task1.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class TaskServiceTests {

    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;

    @Test
    void createTaskTest(){
        TaskUserForm form = new TaskUserForm();
        TaskDto taskDto = new TaskDto();
        UserDto userDto = userService.userEntityToDto(userService.fetchUserById(22));
        form.setTaskDto(taskDto);
        form.setUserDto(userDto);
        assertEquals(taskService.createTask(form),form);
    }

    @Test
    void getTaskByIdTest(){
        assertEquals(taskService.fetchTaskById(29).getDescription(),"add test cases");
    }

    @Test
    void  deleteTaskTest(){
        taskService.deleteTask(18);
        assertNull(taskService.fetchTaskById(18));
    }
}
