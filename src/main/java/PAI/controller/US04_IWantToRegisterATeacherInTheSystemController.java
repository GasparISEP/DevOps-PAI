package PAI.controller;

public class US04_IWantToRegisterATeacherInTheSystemController {

   /*
    private final TeacherRepository _teacherRepository;
    private final TeacherCategoryRepository _teacherCategoryRepository;
    private final DepartmentRepository _departmentRepository;

    public US04_IWantToRegisterATeacherInTheSystemController( TeacherRepository teacherRepository,
                                                              TeacherCategoryRepository teacherCategoryRepository,
                                                              DepartmentRepository departmentRepository) {

        validateTeacherRepository(teacherRepository);
        validateTeacherCategoryRepository(teacherCategoryRepository);
        validateDepartmentRepository(departmentRepository);

        this._teacherRepository = teacherRepository;
        this._teacherCategoryRepository = teacherCategoryRepository;
        this._departmentRepository = departmentRepository;
    }

    public boolean registerATeacherInTheSystem(
            String acronym, String name, String email, String nif, String phoneNumber,
            String academicBackground, String street, String postalCode, String location,
            String country, IAddressFactory addressFactory, Date date, TeacherCategoryID category,
            WorkingPercentage workingPercentage, TeacherID teacherID, Department department) {

//        if(!isCategoryInTeacherCategoryRepository(category)){
//            return false;
//        }
        if(!isDepartmentInDepartmentRepository(department)){
            return false;
        }

        _teacherRepository.registerTeacher(
                acronym,name,email,nif,phoneNumber,academicBackground,street,postalCode,
                location,country, addressFactory,date,category,workingPercentage, teacherID, department);
        return true;
    }

    private boolean isDepartmentInDepartmentRepository(Department department) {
        return _departmentRepository.departmentExists(department);
    }

    private boolean isCategoryInTeacherCategoryRepository(TeacherCategory category) {
        return _teacherCategoryRepository.getTeacherCategoryByName(category.getName()).isPresent();
    }

    private void validateTeacherRepository(TeacherRepository teacherRepository) {
        if (teacherRepository == null) {
            throw new IllegalStateException("TeacherRepository is null.");
        }
    }
    private void validateTeacherCategoryRepository(TeacherCategoryRepository teacherCategoryRepository) {
        if (teacherCategoryRepository == null) {
            throw new IllegalStateException("TeacherCategoryRepository is null.");
        }
    }
    private void validateDepartmentRepository(DepartmentRepository departmentRepository) {
        if (departmentRepository == null) {
            throw new IllegalStateException("DepartmentRepository is null.");
        }
    }
    */
}
