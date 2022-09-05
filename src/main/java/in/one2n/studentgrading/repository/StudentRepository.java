package in.one2n.studentgrading.repository;

import java.util.List;

import in.one2n.studentgrading.entity.Student;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

  List<Student> findAll();
}
