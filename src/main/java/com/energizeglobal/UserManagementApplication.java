package com.energizeglobal;

import com.energizeglobal.model.User;
import com.energizeglobal.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserManagementApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {

//		new SpringApplicationBuilder()
//				.sources(UserManagementApplication.class)
//				.bannerMode(Banner.Mode.OFF)
//				.run(args);
        SpringApplication.run(UserManagementApplication.class, args);

    }


    @Override
    public void run(String... strings) throws Exception {
        userService.add(new User("Admin", "Administrator", "admin@mail.ru", true, "admin"));
    }
}
