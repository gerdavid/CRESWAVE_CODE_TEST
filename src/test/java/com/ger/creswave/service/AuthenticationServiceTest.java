package com.ger.creswave.service;

import com.ger.creswave.dto.RegisterUserDto;
import com.ger.creswave.entity.Role;
import com.ger.creswave.entity.RoleEnum;
import com.ger.creswave.entity.User;
import com.ger.creswave.repository.RoleRepository;
import com.ger.creswave.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    public void testSignup_success() {
        // Arrange
        RegisterUserDto input = new RegisterUserDto();
        input.setFullName("John Doe");
        input.setEmail("john@email.com");
        input.setPassword("password123");

        Role userRole = new Role();
        userRole.setName(RoleEnum.valueOf("USER"));

        Optional<Role> roleOptional = Optional.of(userRole);

        when(roleRepository.findByName(RoleEnum.valueOf("USER"))).thenReturn(roleOptional);

        User savedUser = new User();
        savedUser.setId(1);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        User result = authenticationService.signup(input);

        // Assert
        assertNotNull(result);
        assertEquals(1L, Optional.ofNullable(result.getId()));
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testSignup_roleNotFound() {
        // Arrange
        RegisterUserDto input = new RegisterUserDto();
        input.setFullName("John Doe");
        input.setEmail("john@email.com");
        input.setPassword("password123");

        when(roleRepository.findByName(RoleEnum.valueOf("USER"))).thenReturn(Optional.empty());

        // Act
        User result = authenticationService.signup(input);

        // Assert
        assertNull(result);
    }

}
