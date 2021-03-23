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

public class CreateGossipRequestDTOTestVal
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
  public void testCreateGossipRequestDTONotNull()
  {
    CreateGossipRequestDTO createGossipRequestDTO = new CreateGossipRequestDTO();
    createGossipRequestDTO.setText(null);
    Set<ConstraintViolation<CreateGossipRequestDTO>> violations = validator.validate(createGossipRequestDTO);
    Assert.assertFalse(violations.isEmpty());
    Assert.assertEquals(violations.size(), 1);
  }

  @Test
  public void testCreateGossipRequestDtoInvalid()
  {
    CreateGossipRequestDTO createGossipRequestDTO = new CreateGossipRequestDTO();
    createGossipRequestDTO.setText("a");
    Set<ConstraintViolation<CreateGossipRequestDTO>> violations = validator.validate(createGossipRequestDTO);
    Assert.assertFalse(violations.isEmpty());
    Assert.assertEquals(violations.size(), 1);
  }
}