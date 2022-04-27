package com.skyspecs.task1.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@Validated
public class OrganizationDto {
    private int id;
    @NotNull
    @NotEmpty(message = "cannot be empty")
    private String name;
}
