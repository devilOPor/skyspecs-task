package com.skyspecs.task1.service;

import com.skyspecs.task1.dto.OrganizationAddressForm;
import com.skyspecs.task1.dto.OrganizationDto;
import com.skyspecs.task1.dto.UserDto;
import com.skyspecs.task1.entity.Address;
import com.skyspecs.task1.entity.Organization;
import com.skyspecs.task1.entity.User;
import com.skyspecs.task1.repository.OrganizationRepository;
import com.skyspecs.task1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressService addressService;

    public List<Organization> fetchAllOrganizations() {
        return organizationRepository.findAll();
    }

    public Organization fetchOrganizationById(int id){
        Optional<Organization> optional = organizationRepository.findById(id);
        if(!optional.isPresent())
        {
            return null;
        }
        return optional.get();
    }

    public void deleteOrganization(int id){
        organizationRepository.delete(fetchOrganizationById(id));
    }

    public OrganizationAddressForm saveOrganization(OrganizationAddressForm form){
        Organization organization = new Organization();
        organization = dtoToEntity(form.getOrganizationDto());
        Address address = addressService.dtoToEntity(form.getAddressDto());
        organization.setAddress(address);
        organization =  organizationRepository.save(organization);
        form.setOrganizationDto(entityToDto((organization)));
        form.setAddressDto(addressService.entityToDto(organization.getAddress()));
        return form;
    }

    public Organization findOrganizationByName(String name){
        return organizationRepository.findByName(name);
    }

    public OrganizationAddressForm updateOrganization(int id, OrganizationAddressForm form){
        Organization organization = new Organization();
        organization = dtoToEntity(form.getOrganizationDto());
        organization.setAddress(addressService.dtoToEntity(form.getAddressDto()));
        organization.setId(id);
        System.out.println(organization.getAddress());
        organization = organizationRepository.save(organization);
        form.setOrganizationDto(entityToDto(organization));
        form.setAddressDto(addressService.entityToDto(organization.getAddress()));
        return form;
    }

    public Organization findOrganizationById(int id){
        Optional<Organization> optional = organizationRepository.findById(id);
        if(!optional.isPresent()){
            System.out.println("debug");
            return null;
        }
        return optional.get();
    }

    public void deleteOrganizationById(int id){
        organizationRepository.deleteById(id);
    }

    public Organization dtoToEntity(OrganizationDto organizationDto){
        Organization organization = new Organization();
        if(organizationDto!=null) {
            organization.setId(organizationDto.getId());
            organization.setName(organizationDto.getName());
        }
        return organization;
    }

    public OrganizationDto entityToDto(Organization organization){
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setId(organization.getId());
        organizationDto.setName(organization.getName());
        return organizationDto;
    }

    public List<OrganizationDto> entityToDto(List<Organization> organizations){
        List<OrganizationDto> organizationDtos = new ArrayList<>();
        OrganizationDto organizationDto = new OrganizationDto();
        for(Organization organization:organizations){
            organizationDto = entityToDto(organization);
            organizationDtos.add(organizationDto);
        }
        return organizationDtos;
    }

}
