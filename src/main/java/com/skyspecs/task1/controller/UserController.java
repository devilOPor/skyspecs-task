package com.skyspecs.task1.controller;

import com.skyspecs.task1.dto.RoleToUserDto;
import com.skyspecs.task1.dto.UserAddressOrganizationForm;
import com.skyspecs.task1.dto.UserDto;
import com.skyspecs.task1.service.AddressService;
import com.skyspecs.task1.service.OrganizationService;
import com.skyspecs.task1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OrganizationService organizationService;



    @GetMapping("/page={page}")
    public ResponseEntity getAllUsers(@PathVariable(required = false) Integer page,
                                      @RequestParam(required = false) String sort,
                                      @RequestParam(required = false) String sortBy,
                                      @RequestParam(required = false) List<String> nameFilter,
                                      @RequestParam(required = false) List<String> emailFilter){

        Pageable pageable =  PageRequest.of(page,15, Sort.Direction.DESC,sortBy);
        if(sort!=null && (sort.toLowerCase()!="desc" || sort.toLowerCase()!="descending" )){
            pageable = PageRequest.of(page,15, Sort.Direction.ASC,sortBy);
        }

        return new ResponseEntity<>(userService.fetchWithFilters(nameFilter,emailFilter,pageable),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable int id){
        UserDto userDto = userService.userEntityToDto(userService.fetchUserById(id));
        HttpStatus status =  HttpStatus.CREATED;
        if(userDto==null)
        {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity(userDto,status);
    }

    @PostMapping("/create")
    public ResponseEntity saveUser(@Valid @RequestBody UserAddressOrganizationForm form){
        form = userService.saveUser(form);
        HttpStatus status =  HttpStatus.CREATED;
        if(form==null)
        {
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity(form,status);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@RequestBody UserAddressOrganizationForm userAddressOrganizationDto, @PathVariable int id){
        userAddressOrganizationDto = userService.updateUser(userAddressOrganizationDto, id);
        HttpStatus status =  HttpStatus.CREATED;
        if(userAddressOrganizationDto==null)
        {
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity(userAddressOrganizationDto,status);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return HttpStatus.OK;
    }

    @PutMapping("/addroletouser")
    public void addRoleToUser(RoleToUserDto roleToUserDto){
        userService.addRoleToUser(roleToUserDto.getEmail(),roleToUserDto.getRole());
    }
}
