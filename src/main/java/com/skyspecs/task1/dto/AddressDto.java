package com.skyspecs.task1.dto;

import lombok.Data;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class AddressDto {
    private int id;
    @Min(10000)
    @Max(99999)
    private int pincode;
    @NotEmpty
    private String address;
    @NotEmpty
    private String state;
    @NotEmpty
    private String country;
}
