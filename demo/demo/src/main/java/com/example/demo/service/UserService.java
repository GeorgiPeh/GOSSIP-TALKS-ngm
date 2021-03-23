package com.example.demo.service;


import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService
{

  Optional<User> getUser(String userName);

  boolean changePassword(String userName, String oldPassword, String newPassword, String newPasswordConfirmation);

  List<User> getUsers();

  User createUser(String email, String username, String name, String password, String passwordConfirmation);


}


