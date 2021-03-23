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

public class FollowRequestDTOTestVal
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
  public void testFollowInvalid()
  {
    FollowRequestDTO followRequestDTO = new FollowRequestDTO();
    followRequestDTO.setFollow(false);
    Set<ConstraintViolation<FollowRequestDTO>> violations = validator.validate(followRequestDTO);
    Assert.assertTrue(violations.isEmpty());
    Assert.assertEquals(violations.size(), 0);
  }

}