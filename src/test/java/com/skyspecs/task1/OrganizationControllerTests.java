package com.skyspecs.task1;

import com.skyspecs.task1.controller.OrganizationController;
import com.skyspecs.task1.dto.AddressDto;
import com.skyspecs.task1.dto.OrganizationAddressForm;
import com.skyspecs.task1.dto.OrganizationDto;
import com.skyspecs.task1.entity.Organization;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OrganizationControllerTests {

    @Autowired
    OrganizationController organizationController;

    @Test
    void fetchAllOrganizationsTest(){
        assertEquals(organizationController.fetchAllOrganizations().getStatusCodeValue(),200);
    }

    @Test
    void fetchOrganizationByIdTest(){
        assertEquals(organizationController.fetchOrganizationById(21).getStatusCodeValue(),200);
    }

    @Test
    void saveOrganizationTest(){
        OrganizationAddressForm form = new OrganizationAddressForm();
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setName("JPMC");
        AddressDto addressDto = new AddressDto();
        addressDto.setAddress("lala");
        addressDto.setPincode(50048);
        addressDto.setState("Telangana");
        addressDto.setCountry("India");
        form.setOrganizationDto(organizationDto);
        form.setAddressDto(addressDto);
        assertEquals(201,organizationController.saveOrganization(form).getStatusCodeValue());
    }

    @Test
    void updateOrganizationTest(){
        OrganizationAddressForm form = new OrganizationAddressForm();
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setName("JPMC");
        AddressDto addressDto = new AddressDto();
        addressDto.setAddress("lala");
        addressDto.setPincode(50048);
        addressDto.setState("Telangana");
        addressDto.setCountry("India");
        form.setOrganizationDto(organizationDto);
        form.setAddressDto(addressDto);
        assertEquals(200,organizationController.editOrganization(21, form).getStatusCodeValue());
    }
}
