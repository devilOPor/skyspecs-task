package com.skyspecs.task1.service;

import com.skyspecs.task1.dto.AddressDto;
import com.skyspecs.task1.dto.UserAddressOrganizationForm;
import com.skyspecs.task1.entity.Address;
import com.skyspecs.task1.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> fetchAll(){
        return addressRepository.findAll();
    }

    public Address updateAddress(int id, Address address){
        address.setId(id);
         return addressRepository.save(address);
        }
    public void deleteAddress(int id){
        addressRepository.deleteById(id);
    }

    public Address dtoToEntity(AddressDto addressDto){
        Address address = new Address();
        address.setId(addressDto.getId());
        address.setAddress(addressDto.getAddress());
        address.setPincode(addressDto.getPincode());
        address.setState(addressDto.getState());
        address.setCountry(addressDto.getCountry());
        return address;

    }

    public AddressDto entityToDto(Address address){
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setAddress(address.getAddress());
        addressDto.setPincode(address.getPincode());
        addressDto.setState(address.getState());
        addressDto.setCountry(address.getCountry());
        return addressDto;
    }

    public List<AddressDto> entityToDto(List<Address> addresses){
        List<AddressDto> addressDtos = new ArrayList<>();
        for(Address address:addresses){
            addressDtos.add(entityToDto(address));
        }
        return addressDtos;
    }

    public List<Address> dtoToEntity(List<AddressDto> addressDtos){
        List<Address> addresses = new ArrayList<>();
        if(addressDtos!=null) {
            for (AddressDto addressDto : addressDtos) {
                addresses.add(dtoToEntity(addressDto));
            }
        }
        return addresses;
    }
}
