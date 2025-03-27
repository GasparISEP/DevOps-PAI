package PAI.repository;

import PAI.VOs.Date;
import PAI.VOs.Grade;
import PAI.VOs.StudentGradeID;
import PAI.domain.CourseEdition;
import PAI.domain.StudentGrade;
import PAI.factory.IStudentGradeFactory;
import PAI.domain.Student;
import PAI.factory.IStudentGradeListFactory;
import PAI.factory.IStudentGradeRepository;

import java.util.List;
import java.util.Optional;

public class StudentGradeRepository implements IStudentGradeRepository {
    private final IStudentGradeFactory _IStudentGradeFactory;
    private List<StudentGrade> _StudentGradeList;

    public StudentGradeRepository(IStudentGradeFactory studentGradeFactory, IStudentGradeListFactory studentGradeListFactory){
        if (studentGradeFactory == null){
            throw new IllegalArgumentException("Factory cannot be null!");
        }
        this._IStudentGradeFactory = studentGradeFactory;

        if (studentGradeListFactory == null) {
            throw new IllegalArgumentException("Factory cannot be null!");

        }
        _StudentGradeList = studentGradeListFactory.newArrayList();
    }


    public boolean addGradeToStudent (Grade grade, Date date, Student student, CourseEdition courseEdition) throws Exception{
        if (!hasStudentAlreadyGradeAtThisCourseEdition(student,courseEdition)){
            StudentGrade studentGrade = _IStudentGradeFactory.newGradeStudent(grade,date,student,courseEdition);
            _StudentGradeList.add(studentGrade);
            return true;
        }
        return false;
    }

    private boolean hasStudentAlreadyGradeAtThisCourseEdition (Student student, CourseEdition courseEdition){
        for ( StudentGrade existingGradeStudent : _StudentGradeList){
            if ( existingGradeStudent.hasThisStudent(student) && existingGradeStudent.hasThisCourseEdition(courseEdition)) return true;
            }
        return false;
        }


    public Double KnowAverageGrade(CourseEdition courseEdition) {
        int numOfStudent = 0;
        double sumGrade = 0;

        for (StudentGrade studentGrade : _StudentGradeList) {
            if (studentGrade.hasThisCourseEdition(courseEdition)) {
                Grade grade1 = studentGrade.get_grade();
                double grade = grade1.knowGrade();
                sumGrade += grade;
                numOfStudent++;
            }
        }
        if (numOfStudent == 0) {
            return null;
        }
        return sumGrade/numOfStudent;
    }


    public double knowApprovalRate(CourseEdition courseEdition) {
        int totalApprovalStudents = 0;
        int totalOfStudents = 0;

        for (StudentGrade studentGrade : _StudentGradeList) {
            if (studentGrade.hasThisCourseEdition(courseEdition)) {
                totalOfStudents++;
                Grade grade1 = studentGrade.get_grade();
                if (grade1.knowGrade() >= 10) {
                    totalApprovalStudents++;
                }
            }
        }

        if (totalOfStudents == 0) {
            return 0.0;
        }

        double approvalRate = ((double) totalApprovalStudents / totalOfStudents) * 100;
        return approvalRate;
    }

    public Optional<StudentGradeID> findIdByStudent (StudentGrade studentGrade){
        for(StudentGrade existingStudentGrade : _StudentGradeList){
            if(existingStudentGrade.equals(studentGrade)){
                return Optional.of(studentGrade.get_StudentGradeID()) ;
            }
        }
        return Optional.empty();
    }


}
