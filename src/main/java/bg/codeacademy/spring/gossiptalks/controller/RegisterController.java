package bg.codeacademy.spring.gossiptalks.controller;

import bg.codeacademy.spring.gossiptalks.dto.CreateUserRequestDTO;
import bg.codeacademy.spring.gossiptalks.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController
{
  private final UserService userService;

  public RegisterController(UserService userService)
  {
    this.userService = userService;
  }

  @GetMapping("/register")
  private String register()
  {
    return "register";
  }

  @PostMapping("/register")
    public String createUser(@ModelAttribute() CreateUserRequestDTO createUserRequestDTO)
    {
      if (!createUserRequestDTO.getPassword().equals(createUserRequestDTO.getPasswordConfirmation())) {
        return "/register";
      }
      this.userService.createUser(createUserRequestDTO.getEmail(), createUserRequestDTO.getUsername(), createUserRequestDTO.getName(),
          createUserRequestDTO.getPassword(), createUserRequestDTO.getPasswordConfirmation());
      return "redirect:/index";
  }
    @GetMapping("/login")
    public String getLoginPage() {
      return "login2";
    }

}
