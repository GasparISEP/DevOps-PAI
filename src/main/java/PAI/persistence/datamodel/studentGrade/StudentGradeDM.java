package PAI.persistence.datamodel.studentGrade;


import PAI.VOs.StudentGradeGeneratedID;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="StudentGrade")
public class StudentGradeDM {

 @EmbeddedId
 private StudentGradeIDDataModel studentGradeIDDataModel;

 @Column(name = "student_grade_generated_id")
 private UUID studentGradeGeneratedID;

 @Column(name = "Grade")
 private double grade;

 @Column(name = "Date")
 private LocalDate date;

 public StudentGradeDM(StudentGradeIDDataModel studentGradeIDDataModel, UUID studentGradeGeneratedID, double grade, LocalDate date) {
  this.studentGradeIDDataModel = studentGradeIDDataModel;
  this.studentGradeGeneratedID = studentGradeGeneratedID;
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

 public UUID getStudentGradeGeneratedID() {return studentGradeGeneratedID;}
}