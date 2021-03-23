package bg.codeacademy.spring.gossiptalks.service;

import bg.codeacademy.spring.gossiptalks.dto.ChangePasswordRequestDTO;
import bg.codeacademy.spring.gossiptalks.dto.UserResponseDTO;
import bg.codeacademy.spring.gossiptalks.model.Gossip;
import bg.codeacademy.spring.gossiptalks.model.User;
import bg.codeacademy.spring.gossiptalks.repository.GossipRepository;
import bg.codeacademy.spring.gossiptalks.repository.UserRepository;
import org.codehaus.groovy.runtime.callsite.BooleanReturningMethodInvoker;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.testng.Assert.*;

public class UserServiceImplTest
{

  UserRepository  userRepository  = mock(UserRepository.class);
  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  UserService     userService     = new UserServiceImpl(userRepository, passwordEncoder);

  @Test
  public void testGetUser()
  {
    User user = new User();
    user.setUsername("username");
    Optional<User> user1 = userService.getUser(user.getUsername());
    Mockito.verify(userRepository, times(1)).findByUsername(user.getUsername());
  }

  @Test
  public void testGetUsers()
  {
    List<User> users = userService.getUsers();
    Mockito.verify(userRepository, times(1)).findAll();
  }

  @Test
  public void testCreateUser()
  {
    User user = new User();
    user.setEmail("email");
    user.setUsername("username");
    user.setName("Name");
    user.setPassword("password");
    user.setPasswordConfirmation("password");
    User saved = userService.createUser(user.getEmail(), user.getUsername(), user.getName(),
        user.getPassword(), user.getPasswordConfirmation());
    Mockito.verify(userRepository, times(1)).saveAndFlush(saved);
  }

  @Test
  public void testGetUsersFollowing()
  {
  }
}