package PAI.controller;

import PAI.domain.*;
import java.util.List;

public class RegisterTeacherAndRelevantDataController {

    private final TeacherCategoryRepository _teacherCategoryRepository;
    private final DepartmentRepository _departmentRepository;
    private final TeacherRepository _teacherRepository;

    //Constructor
    public RegisterTeacherAndRelevantDataController(TeacherCategoryRepository teacherCategoryRepository, DepartmentRepository departmentRepository, TeacherRepository teacherRepository) {
        this._teacherCategoryRepository = teacherCategoryRepository;
        this._departmentRepository = departmentRepository;
        this._teacherRepository = teacherRepository;
    }

    // Method to fetch all Teacher Categories
    public List<TeacherCategory> getTeacherCategoriesList() {
        return _teacherCategoryRepository.getTeacherCategoriesList();
    }

    // Method to fetch all Departments
    public List<Department> getDepartmentsList() {
        return _departmentRepository.getDepartmentsList();
    }

    public boolean registerTeacher(String acronym, String name, String email, String nif, String phoneNumber, String academicBackground, String street, String postalCode, String location, String country, String date, TeacherCategory category, int workingPercentage,
                                   Department department) throws IllegalArgumentException {

        // Method to register the Teacher object
        return _teacherRepository.registerTeacher(acronym, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, date, category, workingPercentage, department);
    }

}
