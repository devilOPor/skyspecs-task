package com.skyspecs.task1;

import com.skyspecs.task1.dto.AddressDto;
import com.skyspecs.task1.dto.OrganizationDto;
import com.skyspecs.task1.dto.UserAddressOrganizationForm;
import com.skyspecs.task1.dto.UserDto;
import com.skyspecs.task1.entity.User;
import com.skyspecs.task1.service.UserService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    void getUserTest(){
        User user = userService.fetchUserById(26);
        assertEquals("venkata",user.getFirstName());
        assertEquals("gg@gmail.com",user.getEmail());
    }

//    @Test
//    void getAllUsersTest(){
//        List<User> users = userService.getUserList();
//        assertEquals(10,users.size() );
//    }

    @Test
    void createNewUserTest(){
        UserAddressOrganizationForm form = new UserAddressOrganizationForm();
        UserDto userDto = new UserDto();
        userDto.setFirstName("zoro");
        userDto.setEmail("zoro@task1.com");
        userDto.setPassword("password");

        AddressDto addressDto = new AddressDto();
        addressDto.setAddress("123");
        addressDto.setPincode(500034);
        addressDto.setCountry("India");
        addressDto.setState("Telangana");

        OrganizationDto organization = new OrganizationDto();
        organization.setId(3);
        form.setUserDto(userDto);
        form.setAddressDto(addressDto);
        form.setOrganizationDto(organization);
        assertEquals(form,userService.saveUser(form));
    }

    @Test
    void deleteUserTest(){
        userService.deleteUser(26);
        assertNull(userService.fetchUserById(26));
    }

    @Test
    void updateUserTest(){
        UserAddressOrganizationForm form = new UserAddressOrganizationForm();
        UserDto userDto = new UserDto();
        userDto.setId(25);
        userDto.setFirstName("zoro");
        userDto.setEmail("zoro1@task1.com");
        userDto.setPassword("password");

        AddressDto addressDto = new AddressDto();
        addressDto.setAddress("123");
        addressDto.setPincode(500034);
        addressDto.setCountry("India");
        addressDto.setState("Telangana");

        OrganizationDto organization = new OrganizationDto();
        organization.setId(3);
        form.setUserDto(userDto);
        form.setAddressDto(addressDto);
        form.setOrganizationDto(organization);
        assertEquals(form,userService.updateUser(form,25));
    }
}
