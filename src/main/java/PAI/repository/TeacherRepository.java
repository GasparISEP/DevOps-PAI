package PAI.repository;

import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.ddd.IRepository;
import PAI.domain.*;
import PAI.factory.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherRepository implements IRepository<TeacherID, Teacher> {
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
                                   Street street, PostalCode postalCode, Location location, Country country, Department department) {

        Teacher teacher = _teacherFactory.createTeacher(acronym, name, email, nif, phoneNumber, academicBackground,
                street, postalCode, location, country, department);

        if (isDuplicateTeacherInList(teacher)){
            return Optional.empty();
        }

        save(teacher);

        return Optional.of(teacher.identity());
    }

    private boolean isDuplicateTeacherInList (Teacher teacher) {

        try {
            for (Teacher existingTeacher : _teachers) {
                if (teacher.sameAs(existingTeacher)) {
                    throw new IllegalArgumentException("A teacher with the same identity already exists in the system.");
                }
            }
        } catch (IllegalArgumentException e){
            return true;
        }
        return false;
    }

    // US20 - retrieves all the teachers in the repository
    // This method is to be deleted and instead use findAll
    public List<Teacher> getAllTeachers() {
        return new ArrayList<>(_teachers);
    }

    public Optional<Teacher> getTeacherByNIF(NIF nif) {

        for (Teacher existingTeacher : _teachers) {
            if (existingTeacher.hasThisNIF(nif)) {
                return Optional.of(existingTeacher);
            }
        }
        return Optional.empty();
    }
}
