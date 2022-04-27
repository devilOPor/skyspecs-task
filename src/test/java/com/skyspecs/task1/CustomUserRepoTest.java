package com.skyspecs.task1;

import com.skyspecs.task1.entity.User;
import com.skyspecs.task1.repository.CustomUserRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CustomUserRepoTest {

    @Autowired
    private CustomUserRepository customUserRepository;

    @Test
    void implTest(){
        List<String> firstName = new ArrayList<>();
        List<String> email = new ArrayList<>();

        firstName.add("vishnu");
        firstName.add("zoro");
        firstName.add("doctor");

        email.add("konark@gmail.com");
        email.add("ashok@gmail.com");
        email.add("teja@gmail.com");
        email.add("vv0i@gmail.com");


        List<User> users = customUserRepository.findByFirstNameAndEmail(email,firstName);
        System.out.println(users.size());
        for(User user:users){
            System.out.println(user.getId()+"   "+user.getFirstName()+"  "+user.getEmail());
        }
    }
}
