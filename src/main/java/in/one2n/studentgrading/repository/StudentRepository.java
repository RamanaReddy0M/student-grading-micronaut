package in.one2n.studentgrading.repository;

import java.util.List;

import in.one2n.studentgrading.entity.Student;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

  String queryForFindingTopper =
      "select id, first_name, last_name, university, \n" + "test1_score, test2_score, test3_score, test4_score, (test1_score+test2_score+test3_score+test4_score) as final_score\n" + "from students s\n" + "where (test1_score+test2_score+test3_score+test4_score) = \n" + "(select MAX(test1_score+test2_score+test3_score+test4_score) from students)";

  List<Student> findAll();

  @Query(value = queryForFindingTopper, nativeQuery = true)
  List<Student> getOverallTopper();
}
