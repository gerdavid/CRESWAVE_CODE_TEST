package com.ger.creswave.controller;

// Existing imports from shared context

// New imports
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ger.creswave.dto.LoginUserDto;
import com.ger.creswave.dto.RegisterUserDto;
import com.ger.creswave.entity.User;
import com.ger.creswave.response.LoginResponse;
import com.ger.creswave.service.AuthenticationService;
import com.ger.creswave.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class AuthenticationControllerTest {

    private JwtService mockJwtService = mock(JwtService.class);
    private AuthenticationService mockAuthService = mock(AuthenticationService.class);
    private AuthenticationController controller;

    @BeforeEach
    void setup() {
        controller = new AuthenticationController(mockJwtService, mockAuthService);
    }

    @Test
    void testRegister() {
        // Arrange
        RegisterUserDto dto = new RegisterUserDto();
        User expectedUser = new User();

        when(mockAuthService.signup(dto)).thenReturn(expectedUser);

        // Act
        ResponseEntity<User> response = controller.register(dto);

        // Assert
        assertEquals(expectedUser, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(mockAuthService).signup(dto);
    }

    @Test
    void testAuthenticate() {
        // Arrange
        LoginUserDto dto = new LoginUserDto();
        User user = new User();
        String token = "token";
        LoginResponse expectedResponse = new LoginResponse().setToken(token);

        when(mockAuthService.authenticate(dto)).thenReturn(user);
        when(mockJwtService.generateToken(user)).thenReturn(token);

        // Act
        ResponseEntity<LoginResponse> response = controller.authenticate(dto);

        // Assert
        assertEquals(expectedResponse, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(mockAuthService).authenticate(dto);
        verify(mockJwtService).generateToken(user);
    }
}
