package PAI.controller;
import PAI.domain.*;
import java.util.List;

public class US13_RegisterTeacherAndRelevantDataController {

    private final TeacherCategoryRepository _teacherCategoryRepository;
    private final DepartmentRepository _departmentRepository;
    private final TeacherRepository _teacherRepository;

    //Constructor
    public US13_RegisterTeacherAndRelevantDataController(TeacherCategoryRepository teacherCategoryRepository, DepartmentRepository departmentRepository, TeacherRepository teacherRepository) {
        this._teacherCategoryRepository = teacherCategoryRepository;
        this._departmentRepository = departmentRepository;
        this._teacherRepository = teacherRepository;
    }

    // Method to get all Teacher Categories
    public List<TeacherCategory> getTeacherCategoryList() throws IllegalStateException{
        return _teacherCategoryRepository.getTeacherCategoryList();
    }

    // Method to get all Departments
    public List<Department> getDepartmentsList() throws IllegalStateException{
        return _departmentRepository.getDepartmentList();
    }

    // Method to register the Teacher object
    public boolean registerTeacher(String acronym, String name, String email, String nif, String phoneNumber, String academicBackground, String street, String postalCode, String location, String country, String date, TeacherCategory category, int workingPercentage,
                                   Department department) throws IllegalArgumentException {

        _teacherRepository.registerTeacher(acronym, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, date, category, workingPercentage, department);

        return true;
    }
}
