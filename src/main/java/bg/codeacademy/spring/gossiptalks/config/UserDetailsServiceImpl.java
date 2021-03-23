package bg.codeacademy.spring.gossiptalks.config;

import bg.codeacademy.spring.gossiptalks.model.User;
import bg.codeacademy.spring.gossiptalks.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
  private UserService userService;

  public UserDetailsServiceImpl(UserService userService)
  {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
  {
    List<User> users = userService.getUsers();

    if (users.isEmpty()) {
      // first start, create default users
      userService.createUser("john@mail.com", "john.dow", "John Dow", "Aaa@1234", "Aaa@1234");
      userService.createUser("jessie.c@mail.com", "jessie.c", "Jessie Carter", "jessie123", "jessie123");
    }

    Optional<User> optionalUser = userService.getUser(username);

    if (!optionalUser.isPresent()) {
      throw new UsernameNotFoundException("User not found.");
    }

    User user = optionalUser.get();

    return org.springframework.security.core.userdetails.User.withUsername(username)
        .password(user.getPassword())
        .roles("USER")
        .build();
  }
}
