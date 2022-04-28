package com.skyspecs.task1.controller;

import com.skyspecs.task1.dto.OrganizationAddressForm;
import com.skyspecs.task1.entity.Organization;
import com.skyspecs.task1.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/organizations")
@Validated
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/all")
    public ResponseEntity fetchAllOrganizations(){
        return new ResponseEntity(organizationService.entityToDto(organizationService.fetchAllOrganizations()),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity fetchOrganizationById(@PathVariable int id){
        return new ResponseEntity(organizationService.entityToDto(organizationService.fetchOrganizationById(id)),HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity saveOrganization(@Valid @RequestBody OrganizationAddressForm form){
        form = organizationService.saveOrganization(form);
        HttpStatus status =  HttpStatus.CREATED;
        if(form==null)
        {
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity(form,status);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity editOrganization(@PathVariable int id,@Valid @RequestBody OrganizationAddressForm form){
        return new ResponseEntity(organizationService.updateOrganization(id, form),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteOrganization(@PathVariable int id){
        organizationService.deleteOrganizationById(id);
        return HttpStatus.OK;
    }

}
