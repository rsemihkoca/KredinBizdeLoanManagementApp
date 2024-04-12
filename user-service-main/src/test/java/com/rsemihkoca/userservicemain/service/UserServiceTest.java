package com.rsemihkoca.userservicemain.service;

import com.rsemihkoca.userservicemain.exceptions.dto.request.CreateAddressRequest;
import com.rsemihkoca.userservicemain.exceptions.dto.request.CreateUserRequest;
import com.rsemihkoca.userservicemain.exceptions.dto.response.AddressResponse;
import com.rsemihkoca.userservicemain.exceptions.dto.response.UserResponse;
import com.rsemihkoca.userservicemain.model.*;
import com.rsemihkoca.userservicemain.producer.GenericKafkaProducer;
import com.rsemihkoca.userservicemain.producer.dto.DeletedUser;
import com.rsemihkoca.userservicemain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private GenericKafkaProducer genericKafkaProducer;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    // Test for getById method
    @Test
    void test_getUserByEmail_should_returnUserResponse_when_userExist() {
        // Given
        String email = "test@gmail.com";
        User user = new User();
        user.setEmail(email);
        Optional<UserResponse> expected = Optional.of(UserResponse.builder().email(email).build());

        Mockito.when(userRepository.getUserByEmail(Mockito.any())).thenReturn(user);
        Mockito.when(modelMapper.map(user, UserResponse.class)).thenReturn(UserResponse.builder().email(email).build());

        // When
        UserResponse result = userService.getByEmail(email);

        // Then
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(expected.get().getEmail(), result.getEmail())
        );
    }

    @Test
    void test_getUserByEmail_should_returnNull_when_userNotExist() {
        // Given
        String email = "test@gmail.com";

        Mockito.when(userRepository.getUserByEmail(Mockito.any())).thenReturn(null);

        // When
        UserResponse result = userService.getByEmail(email);

        // Then
        assertAll(
                () -> assertNull(result)
        );
    }

    @Test
    void test_createUser_should_returnUserResponse_when_requestValid() {
        // Given
        CreateUserRequest request = CreateUserRequest.builder()
                .name("test")
                .age(30)
                .address(CreateAddressRequest.builder().addressTitle("Home").addressDescription("Istanbul").build())
                .email("test@gmail.com")
                .build();
        User expected = User.builder().userId(1L)
                .name("test").age(30).email("test@gmail.com")
                .address(Address.builder().addressTitle("Home").addressDescription("Istanbul").build())
                .build();

        UserResponse expectedResponse = UserResponse.builder().name("test").age(30).email("test@gmail.com")
                .address(AddressResponse.builder().addressTitle("Home").addressDescription("Istanbul").build())
                .build();



        Mockito.when(userRepository.save(Mockito.any())).thenReturn(expected);
        Mockito.when(modelMapper.map(request, User.class)).thenReturn(expected);
        Mockito.when(modelMapper.map(expected, UserResponse.class)).thenReturn(expectedResponse);
        // When
        UserResponse result = userService.createUser(request);

        // Then
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(expectedResponse.getEmail(), result.getEmail()),
                () -> assertEquals(expectedResponse.getName(), result.getName()),
                () -> assertEquals(expectedResponse.getAge(), result.getAge()),
                () -> assertEquals(expectedResponse.getAddress().getAddressTitle(), result.getAddress().getAddressTitle()),
                () -> assertEquals(expectedResponse.getAddress().getAddressDescription(), result.getAddress().getAddressDescription()),
                () -> verify(userRepository, times(1)).save(Mockito.any()),
                () -> verify(modelMapper, times(1)).map(request, User.class),
                () -> verify(modelMapper, times(1)).map(expected, UserResponse.class),
                () -> verifyNoMoreInteractions(userRepository, modelMapper),
                () -> verifyNoInteractions(genericKafkaProducer)
        );
    }


    @Test
    void test_getById_should_returnUserResponse_when_userExist() {
        // Given
        Long userId = 1L;
        User expected = User.builder().userId(userId)
                .name("test").age(30).email("test@gmail.com")
                .address(Address.builder().addressTitle("Home").addressDescription("Istanbul").build())
                .build();

        UserResponse expectedResponse = UserResponse.builder().name("test").age(30).email("test@gmail.com")
                .address(AddressResponse.builder().addressTitle("Home").addressDescription("Istanbul").build())
                .build();

        Mockito.when(userRepository.getUserByUserId(Mockito.any())).thenReturn(expected);
        Mockito.when(modelMapper.map(expected, UserResponse.class)).thenReturn(expectedResponse);

        // When
        UserResponse result = userService.getById(userId);

        // Then
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(expectedResponse.getEmail(), result.getEmail()),
                () -> assertEquals(expectedResponse.getName(), result.getName()),
                () -> assertEquals(expectedResponse.getAge(), result.getAge()),
                () -> assertEquals(expectedResponse.getAddress().getAddressTitle(), result.getAddress().getAddressTitle()),
                () -> assertEquals(expectedResponse.getAddress().getAddressDescription(), result.getAddress().getAddressDescription()),
                () -> verify(userRepository, times(1)).getUserByUserId(Mockito.any()),
                () -> verify(modelMapper, times(1)).map(expected, UserResponse.class),
                () -> verifyNoMoreInteractions(userRepository, modelMapper),
                () -> verifyNoInteractions(genericKafkaProducer)
        );
    }
    // Test for deleteByEmail method
    @Test
    void test_getAll_should_returnListUserResponse_when_success() {
        // Given
        User user1 = User.builder().userId(1L)
                .name("test").age(30).email("test@gmail.com")
                .address(Address.builder().addressTitle("Home").addressDescription("Istanbul").build())
                .build();

        UserResponse expectedResponse = UserResponse.builder().name("test").age(30).email("test@gmail.com")
                .address(AddressResponse.builder().addressTitle("Home").addressDescription("Istanbul").build())
                .build();

        List<User> users = Arrays.asList(user1, user1);
        List<UserResponse> expected = Arrays.asList(expectedResponse, expectedResponse);
        when(userRepository.findAll()).thenReturn(users);
        when(modelMapper.map(any(User.class), eq(UserResponse.class))).thenReturn(expectedResponse);

        // When
        List<UserResponse> result = userService.getAll();

        // Then
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(expected, result)
        );
    }

    @Test
    void test_deleteByEmail_should_returnUserResponse_when_success() {
        // Given
        String email = "test@gmail.com";
        User user = User.builder().userId(1L)
                .name("test").age(30).email(email)
                .address(Address.builder().addressTitle("Home").addressDescription("Istanbul").build())
                .build();

        when(userRepository.getUserByEmail(email)).thenReturn(user);
        when(modelMapper.map(user, DeletedUser.class)).thenReturn(new DeletedUser());
        when(modelMapper.map(user, UserResponse.class)).thenReturn(
                UserResponse.builder()
                        .name(user.getName())
                        .age(user.getAge())
                        .email(user.getEmail())
                        .address(AddressResponse.builder()
                                .addressTitle(user.getAddress().getAddressTitle())
                                .addressDescription(user.getAddress().getAddressDescription())
                                .build())
                        .build()
        );

        // When
        UserResponse result = userService.deleteByEmail(email);

        // Then
        assertNotNull(result);
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getAge(), result.getAge());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getAddress().getAddressTitle(), result.getAddress().getAddressTitle());
        assertEquals(user.getAddress().getAddressDescription(), result.getAddress().getAddressDescription());
        verify(userRepository).delete(user);
        verify(genericKafkaProducer).sendUserDeletion(any(DeletedUser.class));
    }
}
