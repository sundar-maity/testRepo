package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.InvalidInputException;
import java.util.Set;
import javax.validation.ConstraintViolation;

public class InvalidInputExceptionFactory<E> {
  public InvalidInputException buildException(Set<ConstraintViolation<E>> violations) {
    InvalidInputException ex = new InvalidInputException();
    for (ConstraintViolation<E> violation : violations)
      ex.addMessage(violation.getMessage()); 
    return ex;
  }
}
