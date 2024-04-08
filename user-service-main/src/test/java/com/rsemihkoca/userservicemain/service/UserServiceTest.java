//package com.rsemihkoca.userservicemain.service;
//
//
//import com.rsemihkoca.userservicemain.exceptions.dto.request.CreateAddressRequest;
//import com.rsemihkoca.userservicemain.exceptions.dto.request.CreateUserRequest;
//import com.rsemihkoca.userservicemain.exceptions.dto.response.UserResponse;
//import com.rsemihkoca.userservicemain.model.Address;
//import com.rsemihkoca.userservicemain.model.User;
//import com.rsemihkoca.userservicemain.repository.UserRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.catchThrowable;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//
//    @InjectMocks
//    private UserService userService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Test
//    public void should_create_user_successfully() {
//
//        //given
//        //Mockito.when(userRepository.save(prepareUser())).thenReturn(prepareUser()); //neden hata veriyor?
//
//        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(prepareUser()); //neden hata vermiyor
//        System.out.println(prepareUser().hashCode());
//        //when
//        UserResponse userResponse = userService.createUser(prepareUser());
//
//        //then --assertion -- doğrulama
//
//        assertThat(userResponse).isNotNull();
//        assertThat(userResponse.getName()).isEqualTo("test name");
//        assertThat(userResponse.getEmail()).isEqualTo(prepareUser().getEmail());
//        assertThat(userResponse.getAge()).isEqualTo(prepareUser().getAge());
//        assertThat(userResponse.getAddress()).isEqualTo(prepareUser().getAddress());
//
//        verify(userRepository, times(1)).save(Mockito.any(User.class));
//    }
//
//
//    @Test
//    public void should_return_user_by_email_successfully() {
//
//        //given
//        Mockito.when(userRepository.findByEmail(prepareUser().getEmail())).thenReturn(Optional.of(prepareUser()));
//
//        //when
//        User userResponse = userService.getByEmail("test@gmail.com");
//
//        //then
//        assertThat(userResponse).isNotNull();
//        assertThat(userResponse.getName()).isEqualTo("test name");
//        assertThat(userResponse.getSurname()).isEqualTo(prepareUser().getSurname());
//        assertThat(userResponse.getEmail()).isEqualTo(prepareUser().getEmail());
//        assertThat(userResponse.getPassword()).isEqualTo(prepareUser().getPassword());
//        assertThat(userResponse.getIsActive()).isTrue();
//
//        verify(userRepository, times(1)).findByEmail("test@gmail.com");
//    }
//
//
//    @Test
//    public void should_throw_kredinbizdeException_whenUserNotFound() {
//        //given
//
//        //when
//        Throwable throwable = catchThrowable(() -> userService.getByEmail("test@gmail.com"));
//
//        //then
//        assertThat(throwable).isInstanceOf(KredinbizdeException.class);
//        assertThat(throwable.getMessage()).isEqualTo("user bulunamadı!");
//    }
//
//    @Test
//    public void should_throw_kredinbizdeException_whenUserNotFound2() {
//        //given
//
//        //when
//        Assertions.assertThrows(KredinbizdeException.class, () -> userService.getByEmail("test"), "user bulunamadı!");
//
//    }
//
//    public CreateUserRequest prepareUser() {
//        CreateUserRequest user = CreateUserRequest.builder().build();
//
//        CreateAddressRequest address = CreateAddressRequest.builder().build();
//        address.setAddressTitle("Ev");
//        address.setAddressDescription("istanbul, istanbul istanbul");
//
//        user.setName("test name");
//        user.setEmail("test@gmail.com");
//        user.setAge(25);
//        user.setAddress(address);
//        return user;
//    }
//
//}
