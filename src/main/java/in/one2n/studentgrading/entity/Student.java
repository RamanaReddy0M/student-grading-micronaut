package in.one2n.studentgrading.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "students")
@Introspected
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private String firstName;

  private String lastName;

  private String university;

  private Double test1Score;

  private Double test2Score;

  private Double test3Score;

  private Double test4Score;

  @Transient
  private Double finalScore;

  @Transient
  private Grade grade;

  public Double getFinalScore() {
    return (test1Score + test2Score + test3Score + test4Score);
  }

  public Grade getGrade() {
    double finalScore = getFinalScore() / 4;
    if (finalScore < 35) {
      grade = Grade.F;
    } else if (finalScore >= 35 && finalScore < 50) {
      grade = Grade.C;
    } else if (finalScore >= 50 && finalScore < 70) {
      grade = Grade.B;
    } else {
      grade = Grade.A;
    }
    return grade;
  }
}
