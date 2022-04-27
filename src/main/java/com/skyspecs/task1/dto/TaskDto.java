package com.skyspecs.task1.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TaskDto {
    int id;
    @NotEmpty
    String description;
}
