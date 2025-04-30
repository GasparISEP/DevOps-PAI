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

 @Embedded
 @Column(name = "CourseEdition")
 private CourseEditionIDDataModel courseEditionID;

 @Embedded
 @Column(name = "Student")
 private StudentIDDataModel StudentId;

 public StudentGradeDM(StudentGradeIDDataModel studentGradeIDDataModel, double grade, LocalDate date, CourseEditionIDDataModel courseEditionID, StudentIDDataModel studentId) {
  this.studentGradeIDDataModel = studentGradeIDDataModel;
  this.grade = grade;
  this.date = date;
  this.courseEditionID = courseEditionID;
  this.StudentId = studentId;
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
  return courseEditionID;
 }

 public StudentIDDataModel getStudentId() {
  return StudentId;
 }
}