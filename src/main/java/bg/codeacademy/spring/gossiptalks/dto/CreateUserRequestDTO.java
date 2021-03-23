package bg.codeacademy.spring.gossiptalks.dto;

import bg.codeacademy.spring.gossiptalks.validation.FieldMatch;
import bg.codeacademy.spring.gossiptalks.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * description:
 * The payload send by client,
 * when creating a new user.
 * The optional parameter following
 * should be ignored.
 */
@FieldMatch(first = "password", second = "passwordConfirmation", message = "Password doesn't match!")
public class CreateUserRequestDTO
{
  @NotNull
  @Size(min = 7)
  @Email
  private String  email;
  @NotNull
  @Size(min = 2, max = 40)
  @Pattern(regexp = "^[a-z0-8.\\-]+$")
  private String  username;
  @NotNull
  @Size(min = 5, max = 90)
  private String  name;
  @ValidPassword
  private String  password;
  @ValidPassword
  private String  passwordConfirmation;
  private boolean following;


  public CreateUserRequestDTO()
  {
  }

  public CreateUserRequestDTO(@NotNull @Size(min = 7) @Email String email, @NotNull @Size(min = 2, max = 40) @Pattern(regexp = "^[a-z0-8.\\-]+$") String username, @NotNull @Size(min = 5, max = 90) String name, String password, String passwordConfirmation, boolean following)
  {
    this.email = email;
    this.username = username;
    this.name = name;
    this.password = password;
    this.passwordConfirmation = passwordConfirmation;
    this.following = following;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
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

  public boolean isFollowing()
  {
    return following;
  }

  public void setFollowing(boolean following)
  {
    this.following = following;
  }

}
