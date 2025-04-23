package PAI.persistence.datamodel;


import PAI.VOs.CourseEditionID;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="StudentGrade")
public class StudentGradeDM {

 @EmbeddedId
 private StudentGradeIDDataModel _studentGradeIDDataModel;

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

 public StudentGradeDM(StudentGradeIDDataModel studentGradeIDDataModel, double _grade, LocalDate _date, CourseEditionIDDataModel courseEditionID, StudentIDDataModel studentId) {
  this._studentGradeIDDataModel = studentGradeIDDataModel;
  this._grade = _grade;
  this._date = _date;
  this.courseEditionID = courseEditionID;
  this.StudentId = studentId;
 }

 public StudentGradeDM() {
 }


 public StudentGradeIDDataModel getId() {
  return _studentGradeIDDataModel;
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