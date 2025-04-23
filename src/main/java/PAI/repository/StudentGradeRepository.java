package PAI.repository;

import PAI.VOs.*;
import PAI.domain.StudentGrade;
import PAI.factory.IStudentGradeFactory;
import PAI.factory.IStudentGradeListFactory;
import PAI.factory.IStudentGradeRepository;

import java.util.List;
import java.util.Optional;

public class StudentGradeRepository implements IStudentGradeRepository {
    private List<StudentGrade> _StudentGradeList;

    public StudentGradeRepository(IStudentGradeListFactory studentGradeListFactory){
        if (studentGradeListFactory == null) {
            throw new IllegalArgumentException("Factory cannot be null!");

        }
        _StudentGradeList = studentGradeListFactory.newArrayList();
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
