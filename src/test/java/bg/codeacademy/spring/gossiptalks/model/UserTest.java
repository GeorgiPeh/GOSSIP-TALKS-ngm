package bg.codeacademy.spring.gossiptalks.model;

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

public class UserTest
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
  public void testUserNull()
  {
    User user = new User();
    Set<ConstraintViolation<User>> violations = validator.validate(user);
    Assert.assertFalse(violations.isEmpty());
    Assert.assertEquals(violations.size(), 5);
  }

  @Test
  public void testUserInvalid()
  {
    User user = new User();
    user.setEmail("g");
    user.setName("1");
    user.setUsername("A");
    user.setPassword("hi");
    user.setPasswordConfirmation("po");
    Set<ConstraintViolation<User>> violations = validator.validate(user);
    Assert.assertFalse(violations.isEmpty());
    Assert.assertEquals(violations.size(), 5);
  }

  @Test
  public void testUserValid()
  {
    User user = new User();
    user.setEmail("gm@gm.com");
    user.setName("My Name");
    user.setUsername("username");
    user.setPassword("password");
    user.setPasswordConfirmation("password");
    Set<ConstraintViolation<User>> violations = validator.validate(user);
    Assert.assertTrue(violations.isEmpty());
    Assert.assertEquals(violations.size(), 0);
  }
}
