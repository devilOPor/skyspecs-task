package com.skyspecs.task1.dto;


import com.skyspecs.task1.entity.Address;
import com.skyspecs.task1.entity.Organization;
import com.skyspecs.task1.entity.User;
import lombok.Data;

import javax.validation.Valid;

@Data
public class UserAddressOrganizationForm {
  @Valid
  private UserDto userDto;
  @Valid
  private AddressDto addressDto;
  @Valid
  private OrganizationDto organizationDto;
}
