package bg.codeacademy.spring.gossiptalks.repository;

import bg.codeacademy.spring.gossiptalks.model.Gossip;
import bg.codeacademy.spring.gossiptalks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GossipRepository extends JpaRepository<Gossip, Integer>
{

  List<Gossip> findAllByUser(String username);
}
