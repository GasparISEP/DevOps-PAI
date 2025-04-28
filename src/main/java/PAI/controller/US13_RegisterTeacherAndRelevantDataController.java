package PAI.controller;
import PAI.VOs.*;
import PAI.domain.Department;
import PAI.domain.TeacherCategory;
import PAI.repository.*;
import PAI.service.ITeacherCareerProgressionService;
import PAI.service.ITeacherCategoryService;
import PAI.service.ITeacherService;
import java.util.Optional;
import java.util.UUID;

public class US13_RegisterTeacherAndRelevantDataController {

    private ITeacherCategoryService _teacherCategoryService;
    private IDepartmentRepository _departmentRepository;
    private ITeacherService _teacherService;
    private ITeacherCareerProgressionService _tcpService;

    //Constructor
    public US13_RegisterTeacherAndRelevantDataController(ITeacherCategoryService teacherCategoryService,
                                                         IDepartmentRepository departmentRepository, ITeacherService teacherService, ITeacherCareerProgressionService tcpService) {

        if (teacherCategoryService == null) {
            throw new IllegalArgumentException("Teacher Category Repository cannot be null");
        }

        if (departmentRepository == null) {
            throw new IllegalArgumentException("Department Repository cannot be null");
        }

        if (teacherService == null) {
            throw new IllegalArgumentException("Teacher Service cannot be null");
        }

        if (tcpService == null){
            throw new IllegalArgumentException("Teacher Career Progression Service cannot be null");
        }

        this._teacherCategoryService = teacherCategoryService;
        this._departmentRepository = departmentRepository;
        this._teacherService = teacherService;
        this._tcpService = tcpService;
    }

    // Method to get all Teacher Categories
    public Iterable<TeacherCategory> getTeacherCategoryList() throws Exception {
        return _teacherCategoryService.getAllTeacherCategories();
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
        Optional<TeacherID> optionalTeacherID = _teacherService.registerTeacher(vo.teacherAcronym, vo.name, vo.email,
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
    private record TeacherVOs(TeacherAcronym teacherAcronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber,
            AcademicBackground academicBackground, Street street, PostalCode postalCode, Location location,
            Country country, DepartmentID departmentID, Date Date, TeacherCategoryID teacherCategoryID,
            WorkingPercentage workingPercentage
    ) {}

    // turn Input into VOs
    private TeacherVOs inputToVO(String teacherAcronym, String name, String email, String nif, String phoneNumber,
           String academicBackground, String street, String postalCode, String location, String country,
           String departmentAcronym, String date, String teacherCategoryID, int workingPercentage, String countryCode) {

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


