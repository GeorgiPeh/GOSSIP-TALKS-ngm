package bg.codeacademy.spring.gossiptalks.dto;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.testng.Assert.*;

public class GossipDTOTestVal
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
  public void gossipCannotBeNull()
  {
    GossipDTO gossipDTO = new GossipDTO();

    Set<ConstraintViolation<GossipDTO>> violations = validator.validate(gossipDTO);
    Assert.assertFalse(violations.isEmpty());
    Assert.assertEquals(violations.size(), 5);
  }

  @Test
  public void testGossipInvalid()
  {
    GossipDTO gossipDTO = new GossipDTO();
    gossipDTO.setUsername("a");
    gossipDTO.setText("a");

    Set<ConstraintViolation<GossipDTO>> violations = validator.validate(gossipDTO);
    Assert.assertFalse(violations.isEmpty());
    Assert.assertEquals(violations.size(), 5);
  }


}