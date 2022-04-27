package com.skyspecs.task1;

import com.skyspecs.task1.dto.AddressDto;
import com.skyspecs.task1.dto.OrganizationAddressForm;
import com.skyspecs.task1.dto.OrganizationDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.skyspecs.task1.service.OrganizationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrganizationServiceTests {

    @Autowired
    private OrganizationService organizationService;

    @Test
    void saveOrganizationTest(){
        OrganizationAddressForm form = new OrganizationAddressForm();
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setName("JPMC");
        AddressDto addressDto = new AddressDto();
        addressDto.setAddress("lala");
        addressDto.setPincode(500048);
        addressDto.setState("Telangana");
        addressDto.setCountry("India");
        form.setOrganizationDto(organizationDto);
        form.setAddressDto(addressDto);
        assertEquals(form,organizationService.saveOrganization(form));
    }

    @Test
    void fetchAllOrganizationsTest(){
        assertEquals(22,organizationService.fetchAllOrganizations().size());
    }

    @Test
    void fetchOrganizationByIdTest(){
        assertEquals("SkySpecs",organizationService.fetchOrganizationById(3).getName());
    }

    @Test
    void deleteOrganizationByIdTest(){
        if(organizationService.findOrganizationById(22)!=null)
        organizationService.deleteOrganizationById(22);
        assertNull(organizationService.fetchOrganizationById(22));
    }

    @Test
    void updateOrganizationtest(){
        OrganizationAddressForm form = new OrganizationAddressForm();
        AddressDto addressDto = new AddressDto();
        OrganizationDto organizationDto = new OrganizationDto();
        addressDto.setAddress("asd");
        addressDto.setPincode(500034);
        addressDto.setState("telangana");
        addressDto.setCountry("India");
        organizationDto.setName("dell");
        organizationDto.setId(21);
        form.setAddressDto(addressDto);
        form.setOrganizationDto(organizationDto);
        assertEquals(form,organizationService.updateOrganization(21,form));
    }


}
