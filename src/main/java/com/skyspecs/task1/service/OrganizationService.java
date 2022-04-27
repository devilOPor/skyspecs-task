package com.skyspecs.task1.service;

import com.skyspecs.task1.dto.OrganizationAddressForm;
import com.skyspecs.task1.dto.OrganizationDto;
import com.skyspecs.task1.entity.Organization;

import java.util.List;

public interface OrganizationService {

    List<Organization> fetchAllOrganizations();
    Organization fetchOrganizationById(int id);
    OrganizationAddressForm saveOrganization(OrganizationAddressForm form);
    Organization findOrganizationByName(String name);
    OrganizationAddressForm updateOrganization(int id, OrganizationAddressForm form);
    Organization findOrganizationById(int id);
    void deleteOrganizationById(int id);
    OrganizationDto entityToDto(Organization organization);
    Organization dtoToEntity(OrganizationDto organizationDto);
    List<OrganizationDto> entityToDto(List<Organization> organizations);
}
