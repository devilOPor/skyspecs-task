package com.skyspecs.task1.service;

import com.skyspecs.task1.dto.UserAddressOrganizationForm;
import com.skyspecs.task1.dto.UserDto;
import com.skyspecs.task1.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService{

    List<UserDto> getUserList(Pageable pageable);
    User fetchUserById(int id);
    void deleteUser(int id);
    UserDto userEntityToDto(User user);
    User userDtoToEntity(UserDto userDto);

    List<UserDto> userEntityToDto(List<User> users);
    UserAddressOrganizationForm saveUser(UserAddressOrganizationForm userAddressOrganizationDto);

    UserAddressOrganizationForm updateUser(UserAddressOrganizationForm userAddressOrganizationDto, int id);
    User findUserByEmail(String email);
    void addRoleToUser(String userEmail, String roleName);
    List<UserDto> fetchWithFilters(List<String> firstNamefilter, List<String> lastNameFilter, Pageable pageable);
}
