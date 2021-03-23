package bg.codeacademy.spring.gossiptalks.service;

import bg.codeacademy.spring.gossiptalks.model.Gossip;
import bg.codeacademy.spring.gossiptalks.model.User;
import bg.codeacademy.spring.gossiptalks.repository.GossipRepository;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.testng.Assert.*;

public class GossipServiceImplTest
{

  GossipRepository gossipRepository = mock(GossipRepository.class);
  GossipService    gossipService    = new GossipServiceImpl(gossipRepository);

  @Test
  public void testGetGossip()
  {
    Gossip gossip = new Gossip();
    gossip.setId(1234);
    gossip.setText("Hello!");
    gossip.setUsername("username");
    gossip.setLocalDateTime(LocalDateTime.now());
    Optional<Gossip> gossip1 = gossipService.getGossip(gossip.getId());

    Mockito.verify(gossipRepository, times(1)).findById(gossip.getId());
  }

  @Test
  public void testGetAllGossipsByUser()
  {
    User user = new User();
    user.setUsername("username");
    List<Gossip> gossips = gossipService.getAllGossipsByUser(user.getUsername());
    Mockito.verify(gossipRepository, times(1)).findAllByUser(user.getUsername());
  }

  @Test
  public void testAddGossip()
  {
    Gossip gossip = new Gossip();
    gossip.setId(1234);
    gossip.setText("Hello!");
    gossip.setUsername("username");
    gossip.setLocalDateTime(LocalDateTime.now());
    Gossip saved = gossipService.addGossip(gossip.getText(), gossip.getText(), gossip.getLocalDateTime());

    Mockito.verify(gossipRepository, times(1)).save(saved);
  }

  @Test
  public void testGetAllGossips()
  {
    List<Gossip> gossips = gossipService.getAllGossips();
    Mockito.verify(gossipRepository, times(1)).findAll();
  }
}