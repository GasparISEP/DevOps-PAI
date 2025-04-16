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

 @Embedded
 private CourseEditionID courseEditionID; // To change after it is done

 @Embedded
 private StudentIDDataModel StudentId;

 public StudentGradeDM(long id, double _grade, LocalDate _date, CourseEditionID courseEditionID, StudentIDDataModel studentId) {
  this.id = id;
  this._grade = _grade;
  this._date = _date;
  this.courseEditionID = courseEditionID;
  this.StudentId = studentId;
 }

 public StudentGradeDM() {
 }

 public long getId() {
  return id;
 }

 public double get_grade() {
  return _grade;
 }

 public LocalDate get_date() {
  return _date;
 }

 public CourseEditionID getCourseEditionID() {
  return courseEditionID;
 }

 public StudentIDDataModel getStudentId() {
  return StudentId;
 }
}
