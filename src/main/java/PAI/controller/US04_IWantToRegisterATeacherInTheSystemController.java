package PAI.controller;

import PAI.domain.*;

public class US04_IWantToRegisterATeacherInTheSystemController {

    private TeacherRepository _teacherRepository;
    private TeacherCategoryRepository _teacherCategoryRepository;
    private DepartmentRepository _departmentRepository;

    public US04_IWantToRegisterATeacherInTheSystemController( TeacherRepository teacherRepository,
                                                              TeacherCategoryRepository teacherCategoryRepository,
                                                              DepartmentRepository departmentRepository) {

        validateTeacherRepository(teacherRepository);
        validateTeacherCategoryRepository(teacherCategoryRepository);
        validateDepartmentRepository(departmentRepository);
    }

    public boolean registerATeacherInTheSystem(
            String acronym, String name, String email, String nif, String phoneNumber,
            String academicBackground, String street, String postalCode, String location,
            String country, String date, TeacherCategory category,
            int workingPercentage, Department department ) {

        if(!isCategoryInTeacherCategoryRepository(category)){
            return false;
        }
        if(!isDepartmentInDepartmentRepository(department)){
            return false;
        }

        _teacherRepository.registerTeacher(
                acronym,name,email,nif,phoneNumber,academicBackground,street,postalCode,
                location,country,date,category,workingPercentage,department);
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
        this._teacherRepository = teacherRepository;
    }
    private void validateTeacherCategoryRepository(TeacherCategoryRepository teacherCategoryRepository) {
        if (teacherCategoryRepository == null) {
            throw new IllegalStateException("TeacherCategoryRepository is null.");
        }
        this._teacherCategoryRepository =teacherCategoryRepository;
    }
    private void validateDepartmentRepository(DepartmentRepository departmentRepository) {
        if (departmentRepository == null) {
            throw new IllegalStateException("DepartmentRepository is null.");
        }
        this._departmentRepository = departmentRepository;
    }
}
