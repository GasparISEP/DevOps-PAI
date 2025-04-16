package PAI.controller;
import PAI.VOs.*;
import PAI.domain.Department;
import PAI.domain.TeacherCategory;
import PAI.repository.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class US13_RegisterTeacherAndRelevantDataController {

    private ITeacherCategoryRepository _teacherCategoryRepository;
    private IDepartmentRepository _departmentRepository;
    private ITeacherRepository _teacherRepository;
    private ITeacherCareerProgressionRepository _teacherCareerProgressionRepository;

    //Constructor
    public US13_RegisterTeacherAndRelevantDataController(ITeacherCategoryRepository teacherCategoryRepository,
                                                         IDepartmentRepository departmentRepository, ITeacherRepository teacherRepository, ITeacherCareerProgressionRepository teacherCareerProgressionRepository) {

        if (teacherCategoryRepository == null) {
            throw new IllegalArgumentException("Teacher Category Repository cannot be null");
        }

        if (departmentRepository == null) {
            throw new IllegalArgumentException("Department Repository cannot be null");
        }

        if (teacherRepository == null) {
            throw new IllegalArgumentException("Teacher Repository cannot be null");
        }

        if (teacherCareerProgressionRepository == null){
            throw new IllegalArgumentException("Teacher Career Progression Repository cannot be null");
        }

        this._teacherCategoryRepository = teacherCategoryRepository;
        this._departmentRepository = departmentRepository;
        this._teacherRepository = teacherRepository;
        this._teacherCareerProgressionRepository = teacherCareerProgressionRepository;
    }

    // Method to get all Teacher Categories
    public Iterable<TeacherCategory> getTeacherCategoryList() throws IllegalStateException{
        return _teacherCategoryRepository.findAll();
    }

    // Method to get all Departments
    public Iterable<Department> getDepartmentList() {
        return _departmentRepository.findAll();
    }

    // Method to register the Teacher object
    public boolean registerTeacher(String teacherAcronym, String name, String email, String nif, String phoneNumber,
                                   String academicBackground, String street, String postalCode,
                                   String location, String country, String departmentAcronym, String date,
                                   String teacherCategoryID, int workingPercentage, String countryCode) throws Exception {

        // turn inputs into VOs
        TeacherVOs vo = inputToVO(teacherAcronym, name, email, nif, phoneNumber, academicBackground, street,
                postalCode, location, country, departmentAcronym, date, teacherCategoryID, workingPercentage, countryCode);


        // register Teacher
        Optional<TeacherID> optionalTeacherID = _teacherRepository.registerTeacher(vo.teacherAcronym, vo.name, vo.email,
                vo.nif, vo.phoneNumber, vo.academicBackground, vo.street, vo.postalCode, vo.location, vo.country, vo.departmentID);

        if (optionalTeacherID.isEmpty()) {
            return false;
        }

        TeacherID teacherID = optionalTeacherID.get();

        // If teacher was created and saved, then create and save first Teacher Career Progression
        _teacherCareerProgressionRepository.createTeacherCareerProgression(vo.Date, vo.teacherCategoryID, vo.workingPercentage, teacherID);

        return true;
    }


    // Records variable as TeacherInput
    private record TeacherVOs(TeacherAcronym teacherAcronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber,
            AcademicBackground academicBackground, Street street, PostalCode postalCode, Location location,
            Country country, DepartmentID departmentID, Date Date, TeacherCategoryID teacherCategoryID,
            WorkingPercentage workingPercentage
    ) {}

    // turn Input into VOs
    private TeacherVOs inputToVO(String teacherAcronym, String name, String email, String nif, String phoneNumber,
           String academicBackground, String street, String postalCode, String location, String country,
           String departmentAcronym, String date, String teacherCategoryID, int workingPercentage, String countryCode) throws Exception {

        Country countryVO = new Country(country);
        DepartmentAcronym departmentAcronymVO = new DepartmentAcronym(departmentAcronym);
        UUID teacherCategoriID_UUID = UUID.fromString(teacherCategoryID);
        return new TeacherVOs(
                new TeacherAcronym(teacherAcronym),
                new Name(name),
                new Email(email),
                new NIF(nif, countryVO),
                new PhoneNumber(countryCode, phoneNumber),
                new AcademicBackground(academicBackground),
                new Street(street),
                new PostalCode(postalCode),
                new Location(location),
                countryVO,
                new DepartmentID(departmentAcronymVO),
                new Date(date),
                new TeacherCategoryID(teacherCategoriID_UUID),
                new WorkingPercentage(workingPercentage)
        );
    }
}
