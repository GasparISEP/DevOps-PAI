package PAI.repository;

import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.domain.*;
import PAI.factory.*;

import java.util.List;
import java.util.Optional;

public class TeacherRepository implements ITeacherRepository {
    private List<Teacher> _teachers;
    private ITeacherFactory _teacherFactory;

    //constructor
    public TeacherRepository(ITeacherFactory teacherFactory, ITeacherListFactory teacherListFactoryImpl){

        _teachers = teacherListFactoryImpl.newList();
        _teacherFactory = teacherFactory;
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

    public Optional<TeacherID> registerTeacher(TeacherAcronym acronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
                                   Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID) {

        Teacher teacher = _teacherFactory.createTeacher(acronym, name, email, nif, phoneNumber, academicBackground,
                street, postalCode, location, country, departmentID);

        if (containsOfIdentity(teacher.identity())){
            return Optional.empty();
        }

        save(teacher);

        return Optional.of(teacher.identity());
    }

    public Optional<TeacherID> findTeacherIdByTeacher (Teacher teacher) {
        for (Teacher existingTeacher : _teachers) {
            if (existingTeacher.equals(teacher)) {
                return Optional.of(teacher.identity());
            }
        }
        return Optional.empty();
    }
}
