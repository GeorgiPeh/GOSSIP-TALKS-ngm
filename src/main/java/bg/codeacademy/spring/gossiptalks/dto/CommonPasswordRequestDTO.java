package bg.codeacademy.spring.gossiptalks.dto;

import bg.codeacademy.spring.gossiptalks.validation.FieldMatch;
import bg.codeacademy.spring.gossiptalks.validation.ValidPassword;

/**
 * description:
 * Common password object.
 * <p>
 * password*	string($password)
 * passwordConfirmation*	string($password)
 */
//@FieldMatch(first = "password", second = "passwordConfirmation", message = "Password doesn't match!")
public class CommonPasswordRequestDTO
{
  @ValidPassword
  private String password;
  @ValidPassword
  private String passwordConfirmation;

  public CommonPasswordRequestDTO()
  {
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getPasswordConfirmation()
  {
    return passwordConfirmation;
  }

  public void setPasswordConfirmation(String passwordConfirmation)
  {
    this.passwordConfirmation = passwordConfirmation;
  }
}
