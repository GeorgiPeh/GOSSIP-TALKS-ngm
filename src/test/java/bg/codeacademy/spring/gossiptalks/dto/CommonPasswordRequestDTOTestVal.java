package bg.codeacademy.spring.gossiptalks.dto;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


import java.util.Set;

import static org.testng.Assert.*;

public class CommonPasswordRequestDTOTestVal
{

  private static Validator validator;

  @BeforeTest
  public static void setUp()
  {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = (Validator) factory.getValidator();
  }

  @Test
  public void testValidPasswords()
  {
    CommonPasswordRequestDTO changePass = new CommonPasswordRequestDTO();
    changePass.setPassword("P@ssw0rd!");
    changePass.setPasswordConfirmation("P@ssw0rd!");

    Set<ConstraintViolation<CommonPasswordRequestDTO>> constraintViolations = validator.validate(changePass);

    assertEquals(constraintViolations.size(), 0);
  }

  @Test
  public void testInvalidPassword()
  {
    CommonPasswordRequestDTO changePass = new CommonPasswordRequestDTO();
    changePass.setPassword("password");//invalid
    changePass.setPasswordConfirmation("password");//invalid

    Set<ConstraintViolation<CommonPasswordRequestDTO>> constraintViolations = validator.validate(changePass);

    assertEquals(constraintViolations.size(), 2);
  }

  @Test
  public void testInvalidPasswordsMatch()
  {
    CommonPasswordRequestDTO changePass = new CommonPasswordRequestDTO();
    changePass.setPassword("P@ssw0rd!");
    changePass.setPasswordConfirmation("P@ssw0rd!123");//invalid
    Set<ConstraintViolation<CommonPasswordRequestDTO>> constraintViolations = validator.validate(changePass);
    assertEquals(constraintViolations.size(), 1);

  }
}