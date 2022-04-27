package com.skyspecs.task1.service;

import com.skyspecs.task1.dto.UserAddressOrganizationForm;
import com.skyspecs.task1.dto.UserDto;
import com.skyspecs.task1.entity.Address;
import com.skyspecs.task1.entity.Organization;
import com.skyspecs.task1.entity.Role;
import com.skyspecs.task1.entity.User;
import com.skyspecs.task1.repository.CustomUserRepository;
import com.skyspecs.task1.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private AddressService addressService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserRepository customUserRepository;



    public List<UserDto> getUserList(Pageable pageable) {
        List<User> users = userRepository.findAll(pageable).getContent();
        return userEntityToDto(users);
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    
    public User fetchUserById(int id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            return null;
        }
        return optionalUser.get();
    }

    public void deleteUser(int id){
        if(fetchUserById(id)!=null){
            userRepository.delete(fetchUserById(id));
        }
    }



    public UserDto userEntityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public User userDtoToEntity(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public List<UserDto> userEntityToDto(List<User> users){
        List<UserDto> userDtos = new ArrayList<>();
        UserDto userDto = new UserDto();
        for(User user:users){
            userDto = userEntityToDto(user);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    public UserAddressOrganizationForm saveUser(UserAddressOrganizationForm form){
        User user = userDtoToEntity(form.getUserDto());
        Address address = addressService.dtoToEntity(form.getAddressDto());
        Organization organization = organizationService.dtoToEntity(form.getOrganizationDto());
        user.setAddress(address);
        if(findUserByEmail(user.getEmail())!=null){
            return null;
        }
               organization = organizationService.fetchOrganizationById(organization.getId());
        if(organization==null)
        {
            return null;
        }
        user.setOrganization(organization);
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user = userRepository.save(user);
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }

        form.setUserDto(userEntityToDto(user));
        form.setOrganizationDto(organizationService.entityToDto(organization));
        form.setAddressDto(addressService.entityToDto(address));
        return form;
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    public UserAddressOrganizationForm updateUser(UserAddressOrganizationForm form, int id){
        User user = null;

        if(fetchUserById(id)!=null) {
        user = fetchUserById(id);
        }
        else {
            return null;
        }
        Address address = addressService.dtoToEntity(form.getAddressDto());
        address.setId(user.getAddress().getId());
        user=userDtoToEntity(form.getUserDto());
        address = addressService.updateAddress(address.getId(),address);
        user.setId(id);
        user.setAddress(address);
        Organization organization = organizationService.fetchOrganizationById(form.getOrganizationDto().getId());
        user.setOrganization(organization);
        form.setUserDto(userEntityToDto(user));
        form.setOrganizationDto(organizationService.entityToDto(user.getOrganization()));
        form.setAddressDto(addressService.entityToDto(user.getAddress()));
        saveUser(form);
        return form;
    }

    public void addRoleToUser(String userEmail, String roleName){
        User user = findUserByEmail(userEmail);
        Role role = roleService.findByRole(roleName);
        user.getRoles().add(role);
        role.getUsers().add(user);
    }
    public List<UserDto> fetchWithFilters(List<String> firstNameFilter, List<String> emailFilter, Pageable pageable){
        if(firstNameFilter!=null && emailFilter!=null) {
            System.out.println(firstNameFilter);
            System.out.println(emailFilter);
            return userEntityToDto(customUserRepository.findByFirstNameAndEmail(emailFilter,firstNameFilter));
        } else if (firstNameFilter!=null) {
            return userEntityToDto(customUserRepository.findByFirstNameFilters(firstNameFilter));
        } else if (emailFilter!=null) {
            return userEntityToDto(customUserRepository.findByEmailFilters(emailFilter));
        }
        return getUserList(pageable);
    }
}
