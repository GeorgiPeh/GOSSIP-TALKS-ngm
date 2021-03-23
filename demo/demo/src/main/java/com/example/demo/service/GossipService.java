package com.example.demo.service;

import com.example.demo.model.Gossip;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public interface GossipService
{

  Optional<Gossip> getGossip(Integer id);

  List<Gossip> getAllGossipsByUser(String username);

  Gossip addGossip(String text, String username, LocalDateTime localDateTime);

  List<Gossip> getAllGossips();


}
