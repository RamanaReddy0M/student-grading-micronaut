package in.one2n.studentgrading.service;

import java.util.List;

import in.one2n.studentgrading.entity.Student;
import in.one2n.studentgrading.repository.StudentRepository;
import jakarta.inject.Singleton;

@Singleton
public class StudentService {

  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }
}
