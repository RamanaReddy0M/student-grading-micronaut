package in.one2n.studentgrading.exception;

import in.one2n.studentgrading.dto.ErrorDTO;
import in.one2n.studentgrading.dto.ErrorResponseDTO;
import io.micrometer.core.instrument.MeterRegistry;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
@Requires(classes = { UserNotFoundException.class, ExceptionHandler.class })
public class UserNotFoundExceptionHandler
    implements ExceptionHandler<UserNotFoundException, HttpResponse<ErrorResponseDTO>> {

  @Inject
  private MeterRegistry meterRegistry;

  @Override
  public HttpResponse<ErrorResponseDTO> handle(HttpRequest request,
      UserNotFoundException exception) {
    meterRegistry.counter("error.student.not.found").increment();
    return HttpResponse.notFound(
        new ErrorResponseDTO(new ErrorDTO(exception.getMessage()), HttpStatus.NOT_FOUND.getCode()));
  }
}
