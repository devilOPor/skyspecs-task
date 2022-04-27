package com.skyspecs.task1;

import com.skyspecs.task1.entity.Address;
import com.skyspecs.task1.service.AddressService;
import com.skyspecs.task1.service.OrganizationService;
import com.skyspecs.task1.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @Autowired
    private  UserService userService;

    @Autowired
    private OrganizationService organizationService;

    @Test
    void getAllAddressesTest(){

        assertEquals(51,addressService.fetchAll().size());
    }

}
