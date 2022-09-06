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
import org.junit.jupiter.api.Order;
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
    assertTrue(embeddedServer.isRunning());
  }

  @Test
  @Order(1)
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

    //when: student
    request = HttpRequest.GET("/v1/api/student?id=10001");
    HttpResponse<Object> response2 = httpClient.toBlocking().exchange(request, Object.class);

    // then:
    assertNotNull(response2);
  }

  @Test
  @Order(2)
  void getAllStudents() {

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
  @Order(3)
  void getUniversityWiseTopper() {
    //when:
    List<Student> topperListFromController = httpClient.toBlocking()
        .retrieve(HttpRequest.GET("/v1/api/student/university-wise-topper"),
            Argument.listOf(Student.class));

    //then:
    assertNotNull(topperListFromController);
    assertEquals("Izayah", topperListFromController.get(0).getFirstName());
    assertEquals("University of California", topperListFromController.get(1).getUniversity());
  }

  @Test
  @Order(4)
  void getOverallTopperTest() {
    //when:
    String requestBody =
        "{" + "\"firstName\": \"Pratham\",\n"
            + "\"lastName\": \"Ajmire\",\n" +
            "\"university\": \"RCOEM\",\n" +
            "\"test1Score\": 100.0,\n" +
            "\"test2Score\": 100.0,\n" +
            "\"test3Score\": 99.0,\n" +
            "\"test4Score\": 99.0 }";
    HttpRequest<Object> request = HttpRequest.POST("/v1/api/student/create", requestBody);
    HttpResponse<Student> response = httpClient.toBlocking().exchange(request, Student.class);
    Student student = response.body();

    //then:
    assertNotNull(student);
    assertEquals("Ajmire", student.getLastName());

    //when:
    List<Student> topperList = httpClient.toBlocking()
        .retrieve(HttpRequest.GET("/v1/api/student/topper"), Argument.listOf(Student.class));

    //then:
    assertNotNull(topperList);
    assertEquals("Pratham", topperList.get(0).getFirstName());
  }

  @Test
  @Order(5)
  void updateStudent() {
  }

  @Test
  @Order(6)
  void addStudent() {
    //when:
    String requestBody =
        "{" + "\"firstName\": \"Ragnar\",\n" +
            "\"lastName\": \"Lothruk\",\n" +
            "\"university\": \"Duke University\",\n" +
            "\"test1Score\": 99.0,\n" +
            "\"test2Score\": 99.0,\n" +
            "\"test3Score\": 99.0,\n" +
            "\"test4Score\": 90.0 }";
    HttpRequest<Object> request = HttpRequest.POST("/v1/api/student/create", requestBody);
    HttpResponse<Student> response = httpClient.toBlocking().exchange(request, Student.class);

    //then:
    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatus());

    //when:
    Student student = response.body();

    //then:
    assertNotNull(student);
    assertEquals("Ragnar", student.getFirstName());
  }

  @Test
  @Order(7)
  void deleteById() {
  }
}