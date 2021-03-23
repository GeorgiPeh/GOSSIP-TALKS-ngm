package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "users")
public class User
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer   Id;
  @NotNull
  @Column(name = "username", unique = true)
  @NotNull
  @Size(min = 2, max = 40)
  @Pattern(regexp = "^[a-z0-8.\\-]+$")
  private String    username;
  @NotNull
  @Size(min = 7)
  @Email
  @Column(name = "email")
  private String    email;
  @NotNull
  @Size(min = 5, max = 90)
  @Column(name = "name")
  private String    name;
  @NotNull
  @Column(name = "password")
  private String    password;
  @NotNull
  @Column(name = "passwordConfirmation")
  private String    passwordConfirmation;
  @OneToMany
  @JoinTable(name = "following",
      joinColumns = @JoinColumn(name = "id"),
      inverseJoinColumns = @JoinColumn(name = "following"))
  private Set<User> followings;//list ?


  public User()
  {
  }

  public Integer getId()
  {
    return Id;
  }

  public void setId(Integer id)
  {
    Id = id;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
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


  public Set<User> getFollowings()
  {
    return followings;
  }

  public Optional<User> setFollowings(Set<User> followings)
  {
    this.followings = followings;
    return setFollowings(followings);
  }


}
