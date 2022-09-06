package in.one2n.studentgrading.controller;

import java.util.List;

import in.one2n.studentgrading.entity.Student;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MicronautTest
class StudentControllerTest {

  @Inject
  EmbeddedServer embeddedServer;

  @Inject
  @Client("/")
  HttpClient httpClient;

  @Test
  void testItWorks() {
    System.out.println(embeddedServer.getHost() + ", " + embeddedServer.getPort());
    assertTrue(embeddedServer.isRunning());
  }

  @Test
  void getStudentById() throws InterruptedException {
    //when:
    HttpRequest<Object> request = HttpRequest.GET("/v1/api/student?id=1");

    HttpResponse<Student> response = httpClient.toBlocking().exchange(request, Student.class);

    //then:
    assertEquals(HttpStatus.OK, response.getStatus());
    assertNotNull(response);

    //when:
    Student student = response.body();

    //then:
    assertNotNull(student);
    assertEquals("Kaylen", student.getFirstName());

    Thread.sleep((1000 * 60 * 3));
  }

  @Test
  void addStudent() {
  }

  @Test
  void updateStudent() {
  }

  @Test
  void getAllStudents() throws InterruptedException {

    //when:
    HttpRequest<Object> request = HttpRequest.GET("/v1/api/student/all");

    HttpResponse<List<Student>> response =
        httpClient.toBlocking().exchange(request, Argument.listOf(Student.class));

    //then:
    assertEquals(HttpStatus.OK, response.getStatus());
    assertNotNull(response);

    //when:
    List<Student> studentList = response.body();

    //then:
    assertNotNull(studentList);
    assertEquals("Kendall", studentList.get(15).getFirstName());
    assertEquals(30, studentList.size());
  }

  @Test
  void deleteById() {
  }
}