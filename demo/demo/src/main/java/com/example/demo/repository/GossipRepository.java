package com.example.demo.repository;

import com.example.demo.model.Gossip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GossipRepository extends JpaRepository<Gossip, Integer>
{

  List<Gossip> findAllByUser(String username);
}
