package com.skyspecs.task1;

import com.skyspecs.task1.controller.UserController;
import com.skyspecs.task1.dto.AddressDto;
import com.skyspecs.task1.dto.OrganizationDto;
import com.skyspecs.task1.dto.UserAddressOrganizationForm;
import com.skyspecs.task1.dto.UserDto;
import com.skyspecs.task1.entity.Organization;
import com.skyspecs.task1.entity.User;
import com.skyspecs.task1.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class UserControllerTests {

   @Autowired
    UserController userController;

   @Autowired
    UserRepository userRepository;

   @Test
   void saveUserTest(){
       UserAddressOrganizationForm form = new UserAddressOrganizationForm();
       UserDto userDto = new UserDto();
       AddressDto addressDto = new AddressDto();
       OrganizationDto organizationDto = new OrganizationDto();
       userDto.setEmail("yo@yo.com");
       userDto.setFirstName("rana");
       userDto.setPassword("lalala");
       addressDto.setAddress("hill");
       addressDto.setCountry("USA");
       addressDto.setState("new york");
       addressDto.setPincode(100012);
       organizationDto.setId(3);
       form.setUserDto(userDto);
       form.setAddressDto(addressDto);
       form.setOrganizationDto(organizationDto);
       ResponseEntity responseEntity =userController.saveUser(form);
       assertEquals(responseEntity.getStatusCodeValue(),201);
   }

   @Test
   void getUserByIdTest(){
       ResponseEntity responseEntity = userController.getUserById(18);
       assertEquals(responseEntity.getStatusCodeValue(),201);
   }

//   @Test
//    void getAllUsersTest(){
//       ResponseEntity responseEntity = userController.getAllUsers();
//       assertEquals(responseEntity.getStatusCodeValue(),200);
//   }

   @Test
    void updateUserTest(){
       UserAddressOrganizationForm form = new UserAddressOrganizationForm();
       UserDto userDto = new UserDto();
       userDto.setId(18);
       userDto.setFirstName("Vishnu");
       userDto.setEmail("uchiha@konoha.com");
       userDto.setPassword("dragonBall");
       form.setUserDto(userDto);
       AddressDto addressDto = new AddressDto();
       addressDto.setAddress("gg lane");
       addressDto.setState("Karnataka");
       addressDto.setPincode(25111);
       addressDto.setCountry("India");
       OrganizationDto organizationDto = new OrganizationDto();
       organizationDto.setId(3);
       organizationDto.setName("mo");
       form.setAddressDto(addressDto);
       form.setOrganizationDto(organizationDto);
       ResponseEntity responseEntity = userController.updateUser(form,18);
       assertEquals(responseEntity.getStatusCodeValue(),201);

   }

   @Test
    void deleteUserTest(){
       HttpStatus status = userController.deleteUser(25);
       assertEquals(status.value(),200);
       assertEquals(userRepository.findById(25),Optional.empty());
   }

}