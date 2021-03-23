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

public class UserResponseDTOTestVal
{
  private static ValidatorFactory validatorFactory;
  private static Validator        validator;

  @BeforeClass
  public static void createValidator()
  {
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    validator = validatorFactory.getValidator();
  }

  //    @AfterClass
//    public static void close()
//    {
//        validatorFactory.close();
//    }
  @Test
  public void testUserNotNull()
  {
    UserResponseDTO userResponseDTO = new UserResponseDTO();
    userResponseDTO.setUsername(null);
    userResponseDTO.setEmail(null);
    userResponseDTO.setName(null);
    Set<ConstraintViolation<UserResponseDTO>> violations = validator.validate(userResponseDTO);
    Assert.assertFalse(violations.isEmpty());
    Assert.assertEquals(violations.size(), 3);
  }

  @Test
  public void testUserInvalid()
  {
    UserResponseDTO userResponseDTO = new UserResponseDTO();
    userResponseDTO.setName("a");
    userResponseDTO.setEmail("afas");
    userResponseDTO.setUsername("a");
    Set<ConstraintViolation<UserResponseDTO>> violations = validator.validate(userResponseDTO);
    Assert.assertFalse(violations.isEmpty());
    Assert.assertEquals(violations.size(), 3);
  }

}