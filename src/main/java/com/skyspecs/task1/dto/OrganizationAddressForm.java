package com.skyspecs.task1.dto;

import lombok.Data;

import javax.validation.Valid;

@Data
public class OrganizationAddressForm {
    @Valid
    private OrganizationDto organizationDto;
    @Valid
    private AddressDto addressDto;
}
