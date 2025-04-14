package PAI.persistence.datamodel;

import PAI.VOs.CourseEditionID;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Student Grade")
public class StudentGradeDM {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private long id;

 private double _grade;
 private LocalDate _date;

 private String courseEditionID; // To change after it is done
 private int StudentId;

 public StudentGradeDM(long id, double _grade, LocalDate _date, String courseEditionID, int studentId) {
  this.id = id;
  this._grade = _grade;
  this._date = _date;
  this.courseEditionID = courseEditionID;
  StudentId = studentId;
 }

 public StudentGradeDM() {
 }

}
