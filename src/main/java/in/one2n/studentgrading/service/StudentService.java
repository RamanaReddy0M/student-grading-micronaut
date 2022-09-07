package in.one2n.studentgrading.service;

import java.util.List;

import in.one2n.studentgrading.entity.Student;
import io.micronaut.data.model.Pageable;

public interface StudentService {

  List<Student> getAllStudents(Pageable pageable);

  Student getStudentById(Long id);

  Student addStudent(Student student);

  Student updateStudent(Student student);

  void deleteById(Long id);

  List<Student> getOverallTopper();

  List<Student> getUniversityWiseTopper();
}
