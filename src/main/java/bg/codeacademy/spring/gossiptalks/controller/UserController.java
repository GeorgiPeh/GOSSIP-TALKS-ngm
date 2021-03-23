package bg.codeacademy.spring.gossiptalks.controller;


import bg.codeacademy.spring.gossiptalks.dto.ChangePasswordRequestDTO;
import bg.codeacademy.spring.gossiptalks.dto.CreateUserRequestDTO;
import bg.codeacademy.spring.gossiptalks.dto.GossipDTO;
import bg.codeacademy.spring.gossiptalks.dto.UserResponseDTO;
import bg.codeacademy.spring.gossiptalks.model.User;
import bg.codeacademy.spring.gossiptalks.repository.UserRepository;
import bg.codeacademy.spring.gossiptalks.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Validated
@Controller
@RestController
@RequestMapping("/api/v1/users")
public class UserController
{

  private final UserService    userService;
  private final UserRepository userRepository;

  public UserController(UserService userService, UserRepository userRepository)
  {
    this.userService = userService;
    this.userRepository = userRepository;
  }


  @GetMapping
  public List<UserResponseDTO> getUsers(String name, boolean f, Principal principal)
  {
    User current = userService.getUser(principal.getName()).get();
    List<User> users = userService.getUsers();
    List<UserResponseDTO> userResponseDTOS = new ArrayList<>();

    for (User user : users) {
      boolean following = current.getFollowings().contains(user);
      boolean match = true;
      if (name != null) {
        match = user.getName().contains(name);
      }
      if (f) {
        match = match && following;
      }
      if (match) {
        UserResponseDTO userResponseDTO = new UserResponseDTO()
            .setUsername(user.getUsername())
            .setEmail(user.getEmail())
            .setName(user.getName())
            .setFollowing(following);
        userResponseDTOS.add(userResponseDTO);
      }
    }
    return userResponseDTOS;
  }


  @GetMapping("/me")//?
  public ResponseEntity<UserResponseDTO> getUser(Principal principle)
  {

    User current = userService.getUser(principle.getName()).get();
    Optional<User> user = userService.getUser(principle.getName());
    boolean following = current.getFollowings().contains(user);
    if (!user.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    UserResponseDTO response = new UserResponseDTO()
        .setEmail(user.get().getEmail())
        .setUsername(user.get().getUsername())
        .setName(user.get().getName())
        .setFollowing(following);

    return ResponseEntity.ok(response);

  }


//  @RequestMapping(method = RequestMethod.POST)
//  public ResponseEntity<?> createUser(@ModelAttribute() CreateUserRequestDTO createUserRequestDTO) throws URISyntaxException
//  {
//    if (userService.getUser (createUserRequestDTO.getUsername ()).isPresent ()) {
//      return ResponseEntity.status (HttpStatus.CONFLICT).build ();
//    }
//    userService.createUser (createUserRequestDTO.getEmail (), createUserRequestDTO.getUsername (), createUserRequestDTO.getName (),
//        createUserRequestDTO.getPassword (), createUserRequestDTO.getPasswordConfirmation ());
//    return ResponseEntity.created (new URI ("/users" + createUserRequestDTO.getUsername ())).build ();
//  }

  @PostMapping("/me")
  public ResponseEntity<UserResponseDTO> changePassword(@ModelAttribute() ChangePasswordRequestDTO changePasswordDTO, Principal principal) throws URISyntaxException
  {
    User current = userService.getUser(principal.getName()).get();

    Optional<User> user = userService.getUser(principal.getName());
    UserResponseDTO response = new UserResponseDTO();

    ChangePasswordRequestDTO passwordDTO = new ChangePasswordRequestDTO();

    if (user.isPresent()) {
      boolean following = current.getFollowings().contains(user);
      passwordDTO.setOldPassword(changePasswordDTO.getOldPassword());
      passwordDTO.setPassword(changePasswordDTO.getPassword());
      passwordDTO.setPasswordConfirmation(changePasswordDTO.getPasswordConfirmation());

      userService.changePassword(userService.getUser(principal.getName()).get().getUsername(),
          changePasswordDTO.getOldPassword(), changePasswordDTO.getPassword(), changePasswordDTO.getPasswordConfirmation());
      response.setEmail(user.get().getEmail());
      response.setUsername(user.get().getUsername());
      response.setName(user.get().getName());
      response.setFollowing(following);
      return ResponseEntity.ok(response);
    }
    return ResponseEntity.notFound().build();
  }


  @PostMapping("/{username}/follow")
  public ResponseEntity<UserResponseDTO> followUser(
      @Pattern(regexp = "^[a-z0-8\\\\.\\\\-]+$")
      @PathVariable("username")
          String username,
      @RequestParam(value = "follow", required = true)
          Boolean follow, Principal principal)
  {
    List<User> users = userService.getUsers();

    Optional<User> followee = userService.getUser(username);
    Optional<User> user = userService.getUser(principal.getName());

    if (!user.isPresent() || !followee.isPresent()) {
      return ResponseEntity.notFound().build();

    }
    if (follow) {
      user.get().getFollowings().add(followee.get());
      userRepository.saveAndFlush(user.get());
    }
    else {
      user.get().getFollowings().remove(followee.get());
      userRepository.saveAndFlush(user.get());
    }

    UserResponseDTO userResponseDTOS = new UserResponseDTO()
        .setFollowing(follow)
        .setName(followee.get().getName())
        .setEmail(followee.get().getEmail())
        .setUsername(followee.get().getUsername());

    return ResponseEntity.ok(userResponseDTOS);
  }
}
