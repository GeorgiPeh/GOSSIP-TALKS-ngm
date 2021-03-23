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

public class CreateUserRequestDTOTestVal
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
  public void testCreateUserRequestNotNull()
  {
    CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO();
    createUserRequestDTO.setEmail(null);
    createUserRequestDTO.setName(null);
    createUserRequestDTO.setUsername(null);
    createUserRequestDTO.setPassword("P@ss0rd!");
    createUserRequestDTO.setPasswordConfirmation("P@ss0rd!");
    Set<ConstraintViolation<CreateUserRequestDTO>> violations = validator.validate(createUserRequestDTO);
    Assert.assertFalse(violations.isEmpty());
    Assert.assertEquals(violations.size(), 3);
  }

  @Test
  public void testCreateUserRequestInvalid()
  {
    CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO();
    createUserRequestDTO.setPasswordConfirmation("a");
    createUserRequestDTO.setPassword("ab");
    createUserRequestDTO.setUsername("a");
    createUserRequestDTO.setName("b");
    createUserRequestDTO.setEmail("a");
    Set<ConstraintViolation<CreateUserRequestDTO>> violations = validator.validate(createUserRequestDTO);
    Assert.assertFalse(violations.isEmpty());
    Assert.assertEquals(violations.size(), 7);
  }

}