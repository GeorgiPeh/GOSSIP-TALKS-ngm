package bg.codeacademy.spring.gossiptalks.model;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.time.LocalDateTime;
import java.util.Set;

import static org.testng.Assert.*;

public class GossipTest
{
  private static ValidatorFactory validatorFactory;
  private static Validator        validator;

  @BeforeClass
  public static void createValidator()
  {
    validatorFactory = Validation.buildDefaultValidatorFactory();
    validator = validatorFactory.getValidator();
  }

  @AfterClass
  public static void close()
  {
    validatorFactory.close();
  }

  @Test
  public void testGossipNull()
  {
    Gossip gossip = new Gossip();
    Set<ConstraintViolation<Gossip>> violations = validator.validate(gossip);
    Assert.assertFalse(violations.isEmpty());
    Assert.assertEquals(violations.size(), 2);
  }

  @Test
  public void testGossipInvalid()
  {
    Gossip gossip = new Gossip();
    gossip.setText("s");
    gossip.setUsername("A");
    Set<ConstraintViolation<Gossip>> violations = validator.validate(gossip);
    Assert.assertFalse(violations.isEmpty());
    Assert.assertEquals(violations.size(), 3);
  }

  @Test
  public void testGossipValid()
  {
    Gossip gossip = new Gossip();
    gossip.setId(125);
    gossip.setText("Hello!");
    gossip.setUsername("username");
    gossip.setLocalDateTime(LocalDateTime.now());
    Set<ConstraintViolation<Gossip>> violations = validator.validate(gossip);
    Assert.assertTrue(violations.isEmpty());
    Assert.assertEquals(violations.size(), 0);
  }
}