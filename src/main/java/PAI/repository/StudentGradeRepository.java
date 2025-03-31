package PAI.repository;

import PAI.VOs.*;
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


    public boolean addGradeToStudent (Grade grade, Date date, StudentID student, CourseEditionID courseEditionID) throws Exception{
        if (!hasStudentAlreadyGradeAtThisCourseEdition(student,courseEditionID)){
            StudentGrade studentGrade = _IStudentGradeFactory.newGradeStudent(grade,date,student,courseEditionID);
            _StudentGradeList.add(studentGrade);
            return true;
        }
        return false;
    }


    private boolean hasStudentAlreadyGradeAtThisCourseEdition (StudentID student, CourseEditionID courseEditionID){
        for ( StudentGrade existingGradeStudent : _StudentGradeList){
            if ( existingGradeStudent.hasThisStudent(student) && existingGradeStudent.hasThisCourseEdition(courseEditionID)) return true;
            }
        return false;
        }


    public Double KnowAverageGrade(CourseEditionID courseEditionID) {
        int numOfStudent = 0;
        double sumGrade = 0;

        for (StudentGrade studentGrade : _StudentGradeList) {
            if (studentGrade.hasThisCourseEdition(courseEditionID)) {
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


    public double knowApprovalRate(CourseEditionID courseEditionID) {
        int totalApprovalStudents = 0;
        int totalOfStudents = 0;

        for (StudentGrade studentGrade : _StudentGradeList) {
            if (studentGrade.hasThisCourseEdition(courseEditionID)) {
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
                return Optional.of(studentGrade.identity()) ;
            }
        }
        return Optional.empty();
    }


    @Override
    public StudentGrade save(StudentGrade entity) {
        _StudentGradeList.add(entity);
        return entity;
    }

    @Override
    public Iterable<StudentGrade> findAll() {
        if (_StudentGradeList.isEmpty()){
            throw new IllegalStateException("Student Grade List is empty.");
        }
        return _StudentGradeList;
    }

    @Override
    public Optional<StudentGrade> ofIdentity(StudentGradeID id) {
        return _StudentGradeList.stream()
                .filter(stl -> stl.identity().equals(id))
                .findAny();
    }

    @Override
    public boolean containsOfIdentity(StudentGradeID id) {
        return false;
    }

}
