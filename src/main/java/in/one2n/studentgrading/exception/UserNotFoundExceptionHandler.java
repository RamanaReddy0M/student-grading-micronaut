package in.one2n.studentgrading.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Singleton
@Requires(classes = { UserNotFoundException.class, ExceptionHandler.class })
public class UserNotFoundExceptionHandler
    implements ExceptionHandler<UserNotFoundException, HttpResponse<Object>> {

  @Override
  public HttpResponse<Object> handle(HttpRequest request, UserNotFoundException exception) {
    return HttpResponse.notFound(exception.getMessage());
  }
}
