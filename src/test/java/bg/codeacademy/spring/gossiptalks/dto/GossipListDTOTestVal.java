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

public class GossipListDTOTestVal
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
  public void testGossipListDtoNotNull()
  {
    GossipListDTO gossipListDTO = new GossipListDTO();
    gossipListDTO.setContent(null);
    Set<ConstraintViolation<GossipListDTO>> violations = validator.validate(gossipListDTO);
    Assert.assertFalse(violations.isEmpty());
    Assert.assertEquals(violations.size(), 1);
  }

  @Test
  public void testGossipListDtoNull()
  {
    GossipListDTO gossipListDTO = new GossipListDTO();
    gossipListDTO.setTotalElements(-1);
    gossipListDTO.setNumberOfElements(-4);
    Set<ConstraintViolation<GossipListDTO>> violations = validator.validate(gossipListDTO);
    Assert.assertFalse(violations.isEmpty());
    Assert.assertEquals(violations.size(), 2);
  }

}