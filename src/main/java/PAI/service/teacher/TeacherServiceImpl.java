package PAI.service.teacher;

import PAI.VOs.*;
import PAI.domain.teacher.Teacher;
import PAI.exception.BusinessRuleViolationException;
import PAI.domain.teacher.ITeacherFactory;
import PAI.domain.teacher.ITeacherRepository;
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

    public Optional<TeacherID> registerTeacher(TeacherID teacherID, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
                                               Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID) throws Exception {

        Teacher teacher = _teacherFactory.createTeacher(teacherID, name, email, nif, phoneNumber, academicBackground,
                street, postalCode, location, country, departmentID);

        if (_teacherRepository.existsByTeacherIdOrNif(teacher.identity(), nif))
            throw new BusinessRuleViolationException("Teacher with the provided Acronym or NIF is already registered.");

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
