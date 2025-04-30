package PAI.repository;

import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.domain.*;
import PAI.factory.*;

import java.util.List;
import java.util.Optional;

public class TeacherRepositoryImpl implements ITeacherRepository {
    private List<Teacher> _teachers;

    //constructor
    public TeacherRepositoryImpl(ITeacherListFactory teacherListFactoryImpl){

        _teachers = teacherListFactoryImpl.newList();
    }

    @Override
    public Teacher save(Teacher teacher) {
        _teachers.add(teacher);
        return teacher;
    }

    @Override
    public Iterable<Teacher> findAll() {
        return _teachers;
    }

    @Override
    public Optional<Teacher> ofIdentity(TeacherID id) {
        for (Teacher existingTeacher : _teachers) {
            if (existingTeacher.identity().equals(id)) {
                return Optional.of(existingTeacher);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(TeacherID id) {
        for (Teacher existingTeacher : _teachers) {
            if (existingTeacher.identity().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean existsByTeacherIdOrNif(TeacherID teacherID, NIF nif) {

        for (Teacher teacher : _teachers) {
            if (teacher.sameAs(teacherID) || teacher.hasThisNIF(nif)) {
                return true;
            }
        }
        return false;
    }
}