package bg.codeacademy.spring.gossiptalks.validation;
/*
 * Thanks to THE BOOK PROJECT
 * */

import bg.codeacademy.spring.gossiptalks.utils.DetectHtml;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotHTMLValidator implements ConstraintValidator<NotHTML, String>
{

  @Override
  public void initialize(NotHTML constraintAnnotation)
  {
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext)
  {
    if (s == null || s.isEmpty()) {
      return false;
    }
    return !DetectHtml.isHtml(s);
  }
}

