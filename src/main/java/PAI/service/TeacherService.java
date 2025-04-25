package PAI.service;

import PAI.VOs.*;
import PAI.domain.Teacher;
import PAI.factory.ITeacherFactory;
import PAI.persistence.springdata.TeacherRepositorySpringData;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService implements ITeacherService {

    private ITeacherFactory _teacherFactory;
    private TeacherRepositorySpringData _teacherRepository;

    public TeacherService (ITeacherFactory teacherFactory, TeacherRepositorySpringData teacherRepository) {

        if (teacherFactory == null)
            throw new IllegalArgumentException("Teacher Factory must not be null.");

        _teacherFactory = teacherFactory;

        if (teacherRepository == null)
            throw new IllegalArgumentException("Teacher Repository must not be null.");

        _teacherRepository = teacherRepository;
    }

    public Optional<TeacherID> registerTeacher(TeacherAcronym acronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
                                               Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID) {

        Teacher teacher = _teacherFactory.createTeacher(acronym, name, email, nif, phoneNumber, academicBackground,
                street, postalCode, location, country, departmentID);

        if (_teacherRepository.containsOfIdentity(teacher.identity())){
            return Optional.empty();
        }

        _teacherRepository.save(teacher);

        return Optional.of(teacher.identity());
    }

    public boolean existsById(TeacherID teacherID) {
        return _teacherRepository.containsOfIdentity(teacherID);
    }
}
