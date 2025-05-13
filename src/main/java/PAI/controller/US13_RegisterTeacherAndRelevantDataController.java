package PAI.controller;
import PAI.VOs.*;
import PAI.domain.department.Department;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.service.department.IDepartmentService;
import PAI.service.teacherCareerProgression.ITeacherCareerProgressionService;
import PAI.service.teacherCategory.ITeacherCategoryService;
import PAI.service.teacher.ITeacherService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class US13_RegisterTeacherAndRelevantDataController {

    private ITeacherCategoryService _teacherCategoryService;
    private IDepartmentService _departmentService;
    private ITeacherService _teacherService;
    private ITeacherCareerProgressionService _tcpService;

    //Constructor
    public US13_RegisterTeacherAndRelevantDataController(ITeacherCategoryService teacherCategoryService,
                                                         IDepartmentService departmentService, ITeacherService teacherService, ITeacherCareerProgressionService tcpService) {

        if (teacherCategoryService == null) {
            throw new IllegalArgumentException("Teacher Category Repository cannot be null");
        }

        if (departmentService == null) {
            throw new IllegalArgumentException("Department Repository cannot be null");
        }

        if (teacherService == null) {
            throw new IllegalArgumentException("Teacher Service cannot be null");
        }

        if (tcpService == null){
            throw new IllegalArgumentException("Teacher Career Progression Service cannot be null");
        }

        this._teacherCategoryService = teacherCategoryService;
        this._departmentService = departmentService;
        this._teacherService = teacherService;
        this._tcpService = tcpService;
    }

    // Method to get all Teacher Categories
    public Iterable<TeacherCategory> getTeacherCategoryList() throws Exception {
        return _teacherCategoryService.getAllTeacherCategories();
    }

    // Method to get all Departments
    public Iterable<Department> getDepartmentList() {
        return _departmentService.findAll();
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
        Optional<TeacherID> optionalTeacherID = _teacherService.registerTeacher(vo.teacherID, vo.name, vo.email,
                vo.nif, vo.phoneNumber, vo.academicBackground, vo.street, vo.postalCode, vo.location, vo.country, vo.departmentID);

        if (optionalTeacherID.isEmpty()) {
            return false;
        }

        TeacherID teacherID = optionalTeacherID.get();

        // If teacher was created and saved, then create and save first Teacher Career Progression
        _tcpService.createTeacherCareerProgression(vo.Date, vo.teacherCategoryID, vo.workingPercentage, teacherID);

        return true;
    }


    // Records variable as TeacherInput
    private record TeacherVOs(TeacherID teacherID, Name name, Email email, NIF nif, PhoneNumber phoneNumber,
            AcademicBackground academicBackground, Street street, PostalCode postalCode, Location location,
            Country country, DepartmentID departmentID, Date Date, TeacherCategoryID teacherCategoryID,
            WorkingPercentage workingPercentage
    ) {}

    // turn Input into VOs
    private TeacherVOs inputToVO(String teacherAcronym, String name, String email, String nif, String phoneNumber,
           String academicBackground, String street, String postalCode, String location, String country,
           String departmentAcronym, String date, String teacherCategoryID, int workingPercentage, String countryCode) {

        TeacherAcronym teacherAcronymVO = new TeacherAcronym(teacherAcronym);
        Country countryVO = new Country(country);
        DepartmentAcronym departmentAcronymVO = new DepartmentAcronym(departmentAcronym);
        UUID teacherCategoriID_UUID = UUID.fromString(teacherCategoryID);
        return new TeacherVOs(
                new TeacherID(teacherAcronymVO),
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


