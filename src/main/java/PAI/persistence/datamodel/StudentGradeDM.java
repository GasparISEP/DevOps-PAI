package PAI.persistence.datamodel;


import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="StudentGrade")
public class StudentGradeDM {

 @EmbeddedId
 private StudentGradeIDDataModel studentGradeIDDataModel;

 @Column(name = "Grade")
 private double grade;

 @Column(name = "Date")
 private LocalDate date;

 public StudentGradeDM(StudentGradeIDDataModel studentGradeIDDataModel, double grade, LocalDate date) {
  this.studentGradeIDDataModel = studentGradeIDDataModel;
  this.grade = grade;
  this.date = date;
 }

 public StudentGradeDM() {
 }


 public StudentGradeIDDataModel getId() {
  return studentGradeIDDataModel;
 }

 public double getGrade() {
  return grade;
 }

 public LocalDate getDate() {
  return date;
 }

 public CourseEditionIDDataModel getCourseEditionID() {
  return studentGradeIDDataModel.getCourseEditionIDDataModel();
 }

 public StudentIDDataModel getStudentId() {
  return studentGradeIDDataModel.getStudentIDDataModel();
 }
}