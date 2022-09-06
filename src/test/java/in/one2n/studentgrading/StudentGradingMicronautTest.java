package in.one2n.studentgrading;

import java.util.List;

import in.one2n.studentgrading.entity.Student;
import in.one2n.studentgrading.repository.StudentRepository;
import io.micronaut.core.type.Argument;
import static io.micronaut.http.HttpRequest.GET;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

@MicronautTest
class StudentGradingMicronautTest {

  @Inject
  EmbeddedApplication<?> application;

  @Inject
  private StudentRepository studentRepository;

  @Inject
  @Client("/")
  private HttpClient client;

  @Test
  void testItWorks() {
    Assertions.assertTrue(application.isRunning());
  }

  @Test
  void getOverallTopper_Test() {
    Student student = new Student( "Chinmay", "Somani", "RCOEM", 99.0, 100.0, 99.0, 99.0);
    studentRepository.save(student);
    System.out.println(studentRepository.findAll());
    List<Student> topperStudentsList = studentRepository.getOverallTopper();
    List<Student> topperListFromController = client.toBlocking()
        .retrieve(GET("/v1/api/student/topper"), Argument.listOf(Student.class));

    System.out.println("topper info database:" +topperStudentsList);
    System.out.println("topper info controller" +topperListFromController);
    assertEquals(topperStudentsList.get(0).getFirstName(),
        topperListFromController.get(0).getFirstName());
  }
}
