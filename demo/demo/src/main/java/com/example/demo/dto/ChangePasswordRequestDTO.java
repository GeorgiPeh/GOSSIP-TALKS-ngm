package com.example.demo.dto;

import com.example.demo.validation.FieldMatch;
import com.example.demo.validation.ValidPassword;

/**
 * description:
 * The payload send by user, to change his password.
 * <p>
 * password*	string($password)
 * passwordConfirmation*	string($password)
 * oldPassword*	string($password)
 */
@FieldMatch(first = "password", second = "passwordConfirmation")
public class ChangePasswordRequestDTO
{

  @ValidPassword
  private String password;
  @ValidPassword
  private String passwordConfirmation;
  @ValidPassword
  private String oldPassword;

  public ChangePasswordRequestDTO()
  {
  }

  public String getPassword()
  {
    return password;
  }

  public ChangePasswordRequestDTO setPassword(String password)
  {
    this.password = password;
    return this;
  }

  public String getOldPassword()
  {
    return oldPassword;
  }

  public ChangePasswordRequestDTO setOldPassword(String oldPassword)
  {
    this.oldPassword = oldPassword;
    return this;
  }

  public String getPasswordConfirmation()
  {
    return passwordConfirmation;
  }

  public ChangePasswordRequestDTO setPasswordConfirmation(String passwordConfirmation)
  {
    this.passwordConfirmation = passwordConfirmation;
    return this;
  }
}