package bg.codeacademy.spring.gossiptalks.dto;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.testng.Assert.*;

public class ChangePasswordRequestDTOTestVal
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
    ChangePasswordRequestDTO changePass = new ChangePasswordRequestDTO();
    changePass.setOldPassword("Aa123!");//valid
    changePass.setPassword("P@ssw0rd!");//valid
    changePass.setPasswordConfirmation("P@ssw0rd!");//valid and match


    Set<ConstraintViolation<ChangePasswordRequestDTO>> constraintViolations = validator.validate(changePass);

    assertEquals(constraintViolations.size(), 0);
  }

  @Test
  public void testInvalidPassword()
  {
    ChangePasswordRequestDTO changePass = new ChangePasswordRequestDTO();
    changePass.setOldPassword("");//invalid
    changePass.setPassword("password");//invalid
    changePass.setPasswordConfirmation("password");//invalid but match

    Set<ConstraintViolation<ChangePasswordRequestDTO>> constraintViolations = validator.validate(changePass);

    assertEquals(constraintViolations.size(), 3);
  }

  @Test
  public void testInvalidPasswordMatch()
  {
    ChangePasswordRequestDTO changePass = new ChangePasswordRequestDTO();
    changePass.setOldPassword("");//invalid
    changePass.setPassword("password");//invalid
    changePass.setPasswordConfirmation("mypass");//invalid but match

    Set<ConstraintViolation<ChangePasswordRequestDTO>> constraintViolations = validator.validate(changePass);

    assertEquals(constraintViolations.size(), 4);
  }
}