package PAI.service;

import PAI.VOs.*;
import PAI.domain.Teacher;
import PAI.factory.ITeacherFactory;
import PAI.repository.ITeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherServiceImpl implements ITeacherService {

    private ITeacherFactory _teacherFactory;
    private ITeacherRepository _teacherRepository;

    public TeacherServiceImpl (ITeacherFactory teacherFactory, ITeacherRepository teacherRepository) {

        if (teacherFactory == null)
            throw new IllegalArgumentException("Teacher Factory must not be null.");

        _teacherFactory = teacherFactory;

        if (teacherRepository == null)
            throw new IllegalArgumentException("Teacher Repository must not be null.");

        _teacherRepository = teacherRepository;
    }

    public Optional<TeacherID> registerTeacher(TeacherAcronym acronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
                                               Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID) throws Exception {

        Teacher teacher = _teacherFactory.createTeacher(acronym, name, email, nif, phoneNumber, academicBackground,
                street, postalCode, location, country, departmentID);

        _teacherRepository.save(teacher);

        return Optional.of(teacher.identity());
    }

    public boolean existsById(TeacherID teacherID) {
        return _teacherRepository.containsOfIdentity(teacherID);
    }

    public Iterable<Teacher> getAllTeachers() {
        return _teacherRepository.findAll();
    }
}
