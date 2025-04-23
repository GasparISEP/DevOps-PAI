package PAI.repository;

import PAI.VOs.*;
import PAI.domain.StudentGrade;
import PAI.factory.IStudentGradeFactory;
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
            if ( existingGradeStudent.hasThisStudentID(student) && existingGradeStudent.hasThisCourseEditionID(courseEditionID)) return true;
        }
        return false;
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
        if (!ofIdentity(id).isPresent()){
            return false;
        }
        return true;
    }
}
