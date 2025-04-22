package PAI.persistence.datamodel;


import PAI.VOs.CourseEditionID;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="StudentGrade")
public class StudentGradeDM {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private long id;

 @Column(name = "Grade")
 private double _grade;

 @Column(name = "Date")
 private LocalDate _date;

 @Embedded
 @Column(name = "CourseEdition")
 private CourseEditionIDDataModel courseEditionID;

 @Embedded
 @Column(name = "Student")
 private StudentIDDataModel StudentId;

 public StudentGradeDM(long id, double _grade, LocalDate _date, CourseEditionIDDataModel courseEditionID, StudentIDDataModel studentId) {
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

 public CourseEditionIDDataModel getCourseEditionID() {
  return courseEditionID;
 }

 public StudentIDDataModel getStudentId() {
  return StudentId;
 }
}
