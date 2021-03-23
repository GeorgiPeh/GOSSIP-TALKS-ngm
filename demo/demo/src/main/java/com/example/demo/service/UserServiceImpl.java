package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class UserServiceImpl implements UserService
{
  private final UserRepository  userRepository;
  private final PasswordEncoder passwordEncoder;


  @Autowired
  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder)
  {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Optional<User> getUser(String username)
  {
    return userRepository.findByUsername(username);
  }

  @Override
  public boolean changePassword(String userName, String oldPassword, String newPassword, String newPasswordConfirmation)
  {
    Optional<User> user = getUser(userName);
    if (user.isPresent()) {
      if (new BCryptPasswordEncoder().matches(oldPassword, user.get().getPassword())) {
        user.get().setPassword(passwordEncoder.encode(newPassword));
        user.get().setPassword(passwordEncoder.encode(newPasswordConfirmation));
        userRepository.saveAndFlush(user.get());
        return true;
      }
    }
    return false;
  }


  @Override
  public List<User> getUsers()
  {
    List<User> users = userRepository.findAll();
    return users;
  }

  @Override
  public User createUser(String email, String username, String name, String password, String passwordConfirmation)
  {
    User user = new User();
    user.setEmail(email);
    user.setUsername(username);
    user.setName(name);
    user.setPassword(passwordEncoder.encode(password));
    user.setPasswordConfirmation(passwordEncoder.encode(passwordConfirmation));
    userRepository.saveAndFlush(user);

    return user;
  }

}