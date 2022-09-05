package in.one2n.studentgrading.service;

import java.util.List;

import in.one2n.studentgrading.entity.Student;

public interface StudentService {

  List<Student> getAllStudents();

  Student getStudentById(Long id);

  Student addStudent(Student student);

  Student updateStudent(Student student);

  void deleteById(Long id);
}
