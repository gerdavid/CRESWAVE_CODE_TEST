package com.ger.creswave.controller;

import com.ger.creswave.dto.RegisterUserDto;
import com.ger.creswave.entity.User;
import com.ger.creswave.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AdminController adminController;

    @Test
    public void testCreateAdministrator_success() {
        RegisterUserDto registerDto = new RegisterUserDto();
        User createdUser = new User();

        when(userService.createAdministrator(registerDto)).thenReturn(createdUser);

        ResponseEntity<User> response = adminController.createAdministrator(registerDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(createdUser, response.getBody());
    }

    @Test
    public void testCreateAdministrator_invalidInput() {
        RegisterUserDto invalidDto = new RegisterUserDto();

        assertThrows(IllegalArgumentException.class, () -> {
            adminController.createAdministrator(invalidDto);
        });
    }

}

