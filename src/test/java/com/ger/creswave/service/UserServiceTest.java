package com.ger.creswave.service;

import com.ger.creswave.dto.RegisterUserDto;
import com.ger.creswave.entity.RoleEnum;
import com.ger.creswave.entity.User;
import com.ger.creswave.repository.RoleRepository;
import com.ger.creswave.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    @Test
    void testAllUsers() {
        UserService userService = new UserService(userRepository, roleRepository, passwordEncoder);
        List<User> users = userService.allUsers();

        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    void testCreateAdministrator() {
        UserService userService = new UserService(userRepository, roleRepository, passwordEncoder);
        RegisterUserDto input = new RegisterUserDto();
        input.setFullName("John Doe");
        input.setEmail("john@doe.com");
        input.setPassword("password123");

        User admin = userService.createAdministrator(input);

        assertNotNull(admin);
        assertEquals("John Doe", admin.getFullName());
        assertEquals("john@doe.com", admin.getEmail());
        assertTrue(admin.getRole().getName().equals(RoleEnum.ADMIN));
    }

}

