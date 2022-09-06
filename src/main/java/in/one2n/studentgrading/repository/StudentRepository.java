package in.one2n.studentgrading.repository;

import java.util.List;

import in.one2n.studentgrading.entity.Student;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
  List<Student> findAll();

  @Query(
      value = "select * from students s " +
          "where (test1_score+test2_score+test3_score+test4_score) = " +
          "(select MAX(test1_score+test2_score+test3_score+test4_score) from students)"
      , nativeQuery = true)
  List<Student> findOverallTopper();

  @Query(
      value = "select * from students s " +
          "where (test1_score+test2_score+test3_score+test4_score, university) IN " +
          "(select MAX(test1_score+test2_score+test3_score+test4_score), university from students s " +
          "group by university)",
      nativeQuery = true)
  List<Student> findUniversityWiseTopper();
}
