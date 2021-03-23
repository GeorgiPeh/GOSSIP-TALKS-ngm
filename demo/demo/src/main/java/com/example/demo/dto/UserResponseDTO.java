package com.example.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserResponseDTO
{
  @NotNull
  @Email
  private   String  email;
  @NotNull
  @Size(min = 2, max = 26, message = "Provide username between 2 and 26 chars")
  @Pattern(regexp = "^[a-z0-8.\\-]+$")
  protected String  username; //A short nick name of the user.
  @NotNull
  @Size(min = 2, max = 86, message = "Provide your full name plz")
  private   String  name; //Full name.
  private   boolean following; //True if the current user is following the listed one.


  public UserResponseDTO()
  {
  }

  public UserResponseDTO(@Email String email, @Pattern(regexp = "^[a-z0-8.\\-]+$") String username, String name, boolean following)
  {
    this.email = email;
    this.username = username;
    this.name = name;
    this.following = following;
  }

  public String getEmail()
  {
    return email;
  }

  public UserResponseDTO setEmail(String email)
  {
    this.email = email;
    return this;
  }

  public String getUsername()
  {
    return username;
  }

  public UserResponseDTO setUsername(String username)
  {
    this.username = username;
    return this;
  }

  public String getName()
  {
    return name;
  }

  public UserResponseDTO setName(String name)
  {
    this.name = name;
    return this;
  }

  public boolean isFollowing()
  {
    return following;
  }

  public UserResponseDTO setFollowing(boolean following)
  {
    this.following = following;
    return this;
  }

}
