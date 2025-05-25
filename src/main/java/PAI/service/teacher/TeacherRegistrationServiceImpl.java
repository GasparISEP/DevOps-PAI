package PAI.service.teacher;

import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.teacher.ITeacherFactory;
import PAI.domain.teacher.Teacher;
import PAI.dto.teacher.RegisterTeacherCommandDTO;
import PAI.exception.BusinessRuleViolationException;
import org.springframework.stereotype.Service;

@Service
public class TeacherRegistrationServiceImpl implements ITeacherRegistrationService {

    private final ITeacherFactory teacherFactory;
    private final ITeacherRepository teacherRepository;

    public TeacherRegistrationServiceImpl (ITeacherFactory teacherFactory, ITeacherRepository teacherRepository) {

        if (teacherFactory == null)
            throw new IllegalArgumentException("Teacher Factory cannot be null.");
        this.teacherFactory = teacherFactory;

        if (teacherRepository == null)
            throw new IllegalArgumentException("Teacher Repository cannot be null.");
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher createAndSaveTeacher(RegisterTeacherCommandDTO teacherCommandDTO) throws Exception {
        if (teacherCommandDTO.id() == null || teacherCommandDTO.name() == null || teacherCommandDTO.email() == null ||
            teacherCommandDTO.nif() == null || teacherCommandDTO.phoneNumber() == null ||
            teacherCommandDTO.academicBackground() == null || teacherCommandDTO.street() == null ||
            teacherCommandDTO.postalCode() == null || teacherCommandDTO.location() == null ||
            teacherCommandDTO.country() == null || teacherCommandDTO.departmentID() == null) {
            throw new IllegalArgumentException("All fields are required");}

        Teacher teacher = teacherFactory.createTeacher(teacherCommandDTO.id(), teacherCommandDTO.name(),
                teacherCommandDTO.email(), teacherCommandDTO.nif(), teacherCommandDTO.phoneNumber(),
                teacherCommandDTO.academicBackground(), teacherCommandDTO.street(), teacherCommandDTO.postalCode(),
                teacherCommandDTO.location(), teacherCommandDTO.country(), teacherCommandDTO.departmentID());

        if(teacher == null){
            throw new IllegalArgumentException("Failed to create teacher");}
        if (teacherRepository.existsByTeacherIdOrNif(teacher.identity(), teacher.getNIF())) {
            throw new BusinessRuleViolationException("Teacher with the provided Acronym or Nif is already registered.");}
        teacherRepository.save(teacher);
        return teacher;
    }

    public Iterable<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
}
