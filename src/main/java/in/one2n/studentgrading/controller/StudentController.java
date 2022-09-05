package in.one2n.studentgrading.controller;

import java.util.List;

import in.one2n.studentgrading.entity.Student;
import in.one2n.studentgrading.service.impl.StudentServiceImpl;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;

@Controller("/v1/api/student")
public class StudentController {

  public final StudentServiceImpl studentServiceImpl;

  public StudentController(StudentServiceImpl studentServiceImpl) {
    this.studentServiceImpl = studentServiceImpl;
  }

  @Get
  public HttpResponse<Student> getStudentById(@QueryValue Long id) {
    return HttpResponse.ok(studentServiceImpl.getStudentById(id));
  }

  @Post("/create")
  public HttpResponse<Student> addStudent(@Body Student student) {
    return HttpResponse.ok(studentServiceImpl.addStudent(student));
  }

  @Put("/update")
  public HttpResponse<Student> updateStudent(@Body Student student) {
    return HttpResponse.ok(studentServiceImpl.updateStudent(student));
  }

  @Get("/all")
  public HttpResponse<List<Student>> getAllStudents() {
    return HttpResponse.ok(studentServiceImpl.getAllStudents());
  }

  @Delete
  public HttpStatus deleteById(@QueryValue Long id) {
    studentServiceImpl.deleteById(id);
    return HttpStatus.NO_CONTENT;
  }
}
