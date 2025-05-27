package PAI.service.teacher;

import PAI.VOs.*;
import PAI.domain.department.Department;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.repositoryInterfaces.teacherCareerProgression.ITeacherCareerProgressionRepository;
import PAI.domain.repositoryInterfaces.teacherCategory.ITeacherCategoryRepository;
import PAI.domain.teacher.ITeacherFactory;
import PAI.domain.teacher.Teacher;
import PAI.domain.teacherCareerProgression.ITeacherCareerProgressionFactory;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.Teacher.TeacherWithRelevantDataAssembler;
import PAI.dto.Teacher.TeacherWithRelevantDataDTO;
import PAI.exception.BusinessRuleViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherWithRelevantDataServiceImpl {

    private ITeacherCategoryRepository _teacherCategoryRepository;
    private ITeacherCareerProgressionRepository _teacherCareerProgressionRepository;
    private ITeacherRepository _teacherRepository;
    private ITeacherFactory _teacherFactory;
    private ITeacherCareerProgressionFactory _teacherCareerProgressionFactory;
    private TeacherWithRelevantDataAssembler _teacherWithRelevantDataAssembler;
    private IDepartmentRepository _departmentRepository;

    public TeacherWithRelevantDataServiceImpl(ITeacherCategoryRepository teacherCategoryRepository, ITeacherCareerProgressionRepository teacherCareerProgressionRepository,
                                              ITeacherRepository teacherRepositorySpringData, ITeacherFactory teacherFactory, ITeacherCareerProgressionFactory teacherCareerProgressionFactory,
                                              TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler, IDepartmentRepository departmentRepository) {
        this._teacherCategoryRepository = teacherCategoryRepository;
        this._teacherCareerProgressionRepository = teacherCareerProgressionRepository;
        this._teacherRepository = teacherRepositorySpringData;
        this._teacherFactory = teacherFactory;
        this._teacherCareerProgressionFactory = teacherCareerProgressionFactory;
        this._teacherWithRelevantDataAssembler = teacherWithRelevantDataAssembler;
        this._departmentRepository = departmentRepository;
    }

    public TeacherWithRelevantDataDTO registerTeacherWithRelevantData (TeacherID teacherID, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
                                                                       Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID, Date date,
                                                                       TeacherCategoryID teacherCategoryID, WorkingPercentage wp) throws Exception {

        Optional<Teacher> teacher = registerTeacher(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);

        Optional<TeacherCareerProgression> teacherCareerProgression = createTeacherCareerProgression(date, teacherCategoryID, wp, teacherID);

        Optional<TeacherCategory> teacherCategory = getTeacherCategory(teacherCareerProgression.get());

        if (teacherCategory.isEmpty()) {
            throw new Exception("Teacher Category not found.");
        }

        return _teacherWithRelevantDataAssembler.toDTOWithNameAndCategory(
                teacher.get(), teacherCategory.get(), teacherCareerProgression.get()
        );
    }

    public Optional<Teacher> registerTeacher(TeacherID teacherID, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
                                               Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID) throws Exception {

        Teacher teacher = _teacherFactory.createTeacher(teacherID, name, email, nif, phoneNumber, academicBackground,
                street, postalCode, location, country, departmentID);

        if (_teacherRepository.existsByTeacherIdOrNif(teacher.identity(), teacher.getNIF()))
            throw new BusinessRuleViolationException("Teacher with the provided Acronym or NIF is already registered.");

        _teacherRepository.save(teacher);


        return Optional.of(teacher);
    }

    public Optional<TeacherCareerProgression> createTeacherCareerProgression(Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage wp, TeacherID teacherID) throws Exception {
        if (date == null || teacherCategoryID == null || wp == null || teacherID == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }

        TeacherCareerProgression tcp = _teacherCareerProgressionFactory.createTeacherCareerProgression(date, teacherCategoryID, wp, teacherID);

        if (_teacherCareerProgressionRepository.containsOfIdentity(tcp.getID())) {
            throw new BusinessRuleViolationException("Teacher Career Progression is already registered.");
        }

        _teacherCareerProgressionRepository.save(tcp);

        return Optional.of(tcp);
    }

    public Optional<TeacherCategory> getTeacherCategory(TeacherCareerProgression teacherCareerProgression){
        return _teacherCategoryRepository.ofIdentity(teacherCareerProgression.getTeacherCategoryID());
    }

    public Iterable<Department> findAll() {
        return _departmentRepository.findAll();
    }

    public Iterable<TeacherCategory> getAllTeacherCategories() {
        return _teacherCategoryRepository.findAll();
    }
}
