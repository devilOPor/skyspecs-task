package com.skyspecs.task1;

import com.skyspecs.task1.entity.Role;
import com.skyspecs.task1.service.RoleService;
import com.skyspecs.task1.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Task1Application{

	public static void main(String[] args) {
		SpringApplication.run(Task1Application.class, args);
	}

	CommandLineRunner runner(RoleService roleService){
		return args -> {
			Role role = new Role();
			role.setRole("ROLE_ADMIN");
			Role role2 = new Role();
			role.setRole("ROLE_MANAGER");
			Role role3 = new Role();
			role.setRole("ROLE_USER");
			roleService.createNewRole(role);
			roleService.createNewRole(role2);
			roleService.createNewRole(role3);
			System.out.println("commandLineRunner");
		};
	}

}
