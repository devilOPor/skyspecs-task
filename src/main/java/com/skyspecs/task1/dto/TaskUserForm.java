package com.skyspecs.task1.dto;


import com.skyspecs.task1.entity.User;
import lombok.Data;

import javax.validation.Valid;

@Data
public class TaskUserForm {
    @Valid
    private TaskDto taskDto;
    private UserDto userDto;
}
