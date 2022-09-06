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
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "students")
@Introspected
@NoArgsConstructor
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

  public Student(String firstName, String lastName, String university, Double test1Score,
      Double test2Score, Double test3Score, Double test4Score) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.university = university;
    this.test1Score = test1Score;
    this.test2Score = test2Score;
    this.test3Score = test3Score;
    this.test4Score = test4Score;
  }
}
