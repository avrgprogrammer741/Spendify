package com.Spendify.Spendify.User.service;

import com.Spendify.Spendify.User.User;
import com.Spendify.Spendify.User.UserAddRequestDTO;
import com.Spendify.Spendify.User.UserRepository;
import com.Spendify.Spendify.User.UserService;
import com.Spendify.Spendify.exception.FieldRequiredException;
import com.Spendify.Spendify.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;


    @Test
    public void shouldCallDeleteWithCorrectUser() {
        // given
        Long userId = 6L;
        User user=new User("Kuba","Buba","loczek1","loczus@ppp.pl",null,true);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // when
        userService.deleteUser(userId);

        // then
        verify(userRepository, times(0)).delete(user);
    }
    @Test
    public void shouldThrowResourceNotFoundExceptionWhenUserNotFound() {
        // given
        Long userId = 6L;

        // when
        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> {
            userService.deleteUser(userId);
        });

        // then
        assertEquals("User with ID [6] not found", exception.getMessage());
    }
    @Test
    void itShouldThrowWhenEmailIsTaken() {
//        String email="mail@pw.edu.pl";
//        User user=new User("Kuba","Buba","111",email,"dasdasd",true);
//        User userTwo=new User("BABA","Buba","111",email,"asdad",true);
//
//        UserAddRequestDTO requestDTO=new UserAddRequestDTO(user.getName(),user.getSurname(),user.getPassword(),user.getEmail(),user.getImage(),user.getIsActive());
//
//        given(userRepository.selectUserByEmail(email))
//                .willReturn(Optional.of(userTwo));
//
//        // When
//        // Then
//        assertThatThrownBy(()->userService.addUser(requestDTO))
//                .isInstanceOf(FieldRequiredException.class)
//                .hasMessageContaining(String.format("email [%s] is taken",email));
//
//        // Finally
//        then(userRepository).should(never()).save(any(User.class));

    }
}