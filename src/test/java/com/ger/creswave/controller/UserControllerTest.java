package com.ger.creswave.controller;

import com.ger.creswave.entity.User;
import com.ger.creswave.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void authenticatedUser_returnsUser() {
        // Arrange
        User expectedUser = new User();

        // Act
        User actualUser = userController.authenticatedUser().getBody();

        // Assert
        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void allUsers_returnsUsersList() {
        // Arrange
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User());
        expectedUsers.add(new User());

        when(userService.allUsers()).thenReturn(expectedUsers);

        // Act
        List<User> actualUsers = (List<User>) userController.allUsers();

        // Assert
        assertNotNull(actualUsers);
        assertEquals(expectedUsers, actualUsers);
    }
}

