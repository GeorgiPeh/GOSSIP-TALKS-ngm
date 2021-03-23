package com.example.demo.service;

import com.example.demo.model.Gossip;
import com.example.demo.repository.GossipRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class GossipServiceImpl implements GossipService
{
  private final GossipRepository gossipRepo;

  public GossipServiceImpl(GossipRepository gossipRepo)
  {
    this.gossipRepo = gossipRepo;
  }

  @Override
  public Optional<Gossip> getGossip(Integer id)
  {
    return gossipRepo.findById(id);
  }

  @Override
  public List<Gossip> getAllGossipsByUser(String username)
  {
    return gossipRepo.findAllByUser(username);
  }

  @Override
  public Gossip addGossip(String text, String username, LocalDateTime localDateTime)
  {
    Gossip gossip = new Gossip();

    gossip.setText(text);
    gossip.setUsername(username);
    gossip.setLocalDateTime(localDateTime);
    gossipRepo.save(gossip);
    return gossip;
  }


  @Override
  public List<Gossip> getAllGossips()
  {
    return gossipRepo.findAll();
  }
}
