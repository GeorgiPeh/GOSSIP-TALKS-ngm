package bg.codeacademy.spring.gossiptalks.controller;

import bg.codeacademy.spring.gossiptalks.dto.*;
import bg.codeacademy.spring.gossiptalks.model.Gossip;
import bg.codeacademy.spring.gossiptalks.model.User;
import bg.codeacademy.spring.gossiptalks.service.GossipService;
import bg.codeacademy.spring.gossiptalks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistration;

import javax.swing.text.html.Option;
import javax.validation.constraints.Pattern;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RestController
@RequestMapping("/api/v1")
public class GossipController
{
  private GossipService gossipServices;
  private UserService   userServices;

  @Autowired
  public GossipController(GossipService gossipService, UserService userService)
  {
    this.gossipServices = gossipService;
    this.userServices = userService;
  }

  @PostMapping("/gossips")
  public GossipDTO addGossips(@ModelAttribute() CreateGossipRequestDTO gossipDTO, Principal principal)
  {

    Optional<User> user = userServices.getUser(principal.getName());
    Gossip gossip = gossipServices.addGossip(gossipDTO.getText(), principal.getName(), LocalDateTime.now());
    GossipDTO response = new GossipDTO()
        .setId(gossip.getId())
        .setText(gossip.getText())
        .setUsername(gossip.getUsername())
        .setDate(gossip.getLocalDateTime());
    return response;
  }


  @GetMapping("/users/{username}/gossips")
  public ResponseEntity<GossipListDTO> getAllGossips(int pageNo, int pageSize, @PathVariable("username")
      String username)
  {

    Comparator<GossipDTO> comp = (u, a) -> a.getDate().compareTo(a.getDate());
    List<GossipDTO> gossipList = new ArrayList<>();
    List<Gossip> gossips = gossipServices.getAllGossips();
    for (Gossip g : gossips) {

//
      if (username.equals(g.getUsername())) {
        GossipDTO gossipDTO = new GossipDTO()
            .setUsername(g.getUsername())
            .setText(g.getText())
            .setDate(g.getLocalDateTime())
            .setId(g.getId());
        gossipList.add(gossipDTO);
      }
    }
    Collections.sort(gossipList, comp);
    GossipListDTO sortedGossip = new GossipListDTO()
        .setNumberOfElements(pageNo)
        .setTotalElements(pageSize)
        .setContent(gossipList);

    return ResponseEntity.ok(sortedGossip);
  }


  //TODO-"/gossips"-get all of the gossips of all the users - page + number of gossips in it
  @GetMapping("/gossips")
  public ResponseEntity<GossipListDTO> getAllGossips()
  {
//1. get current user
// 2 get user friends
    //for each friend get gossips

    Comparator<GossipDTO> comp = (u, a) -> a.getDate().compareTo(u.getDate());
    List<GossipDTO> gossipList = new ArrayList<>();
    List<Gossip> gossips = gossipServices.getAllGossips();
    for (Gossip g : gossips) {
      GossipDTO gossipDTO = new GossipDTO()
          .setUsername(g.getUsername())
          .setText(g.getText())
          .setDate(g.getLocalDateTime())
          .setId(g.getId());
      gossipList.add(gossipDTO);
    }
    Collections.sort(gossipList, comp);
    GossipListDTO sortedGossip = new GossipListDTO()
        .setNumberOfElements(gossipList.size())
        .setTotalElements(gossipList.size())
        .setContent(gossipList);
    return ResponseEntity.ok(sortedGossip);
  }
}
