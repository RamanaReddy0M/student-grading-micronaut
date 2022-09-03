package in.one2n.studentgrading.controller;

import java.util.List;

import in.one2n.studentgrading.entity.Student;
import in.one2n.studentgrading.service.StudentService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;

@Controller("/v1/api/student")
public class StudentController {

  public final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @Get
  public String hello(@QueryValue String name) {
    return "Hello " + name;
  }

  @Get("/all")
  public HttpResponse<List<Student>> getAllStudents() {

    return HttpResponse.ok(studentService.getAllStudents());
  }
}
