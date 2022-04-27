package com.skyspecs.task1.service;

import com.skyspecs.task1.dto.AddressDto;
import com.skyspecs.task1.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> fetchAll();
    Address updateAddress(int id, Address address);
    void deleteAddress(int id);
    AddressDto entityToDto(Address address);
    Address dtoToEntity(AddressDto addressDto);
    List<AddressDto> entityToDto(List<Address> addresses);
    public List<Address> dtoToEntity(List<AddressDto> addressDtos);
}
