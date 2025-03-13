package PAI.controller;
import PAI.domain.*;
import PAI.factory.AddressFactoryImpl;

import PAI.repository.DepartmentRepository;
import PAI.repository.TeacherCategoryRepository;
import PAI.repository.TeacherRepository;

import java.util.List;
import java.util.Set;

public class US13_RegisterTeacherAndRelevantDataController {

    private final TeacherCategoryRepository _teacherCategoryRepository;
    private final DepartmentRepository _departmentRepository;
    private final TeacherRepository _teacherRepository;

    //Constructor
    public US13_RegisterTeacherAndRelevantDataController(TeacherCategoryRepository teacherCategoryRepository, DepartmentRepository departmentRepository, TeacherRepository teacherRepository) {
        if (teacherCategoryRepository == null) {
            throw new IllegalArgumentException("Teacher Category Repository cannot be null");
        }

        if (departmentRepository == null) {
            throw new IllegalArgumentException("Department Repository cannot be null");
        }

        if (teacherRepository == null) {
            throw new IllegalArgumentException("Teacher Repository cannot be null");
        }

        this._teacherCategoryRepository = teacherCategoryRepository;
        this._departmentRepository = departmentRepository;
        this._teacherRepository = teacherRepository;
    }

    // Method to get all Teacher Categories
    public List<TeacherCategory> getTeacherCategoryList() throws IllegalStateException{
        return _teacherCategoryRepository.getTeacherCategoryList();
    }

    // Method to get all Departments
    public Set<Department> getDepartmentsList() throws IllegalStateException{
        return _departmentRepository.getDepartments();
    }

    // Method to register the Teacher object
    public boolean registerTeacher(String acronym, String name, String email, String nif, String phoneNumber, String academicBackground, String street, String postalCode, String location, String country, AddressFactoryImpl addressFactory, String date, TeacherCategory category, int workingPercentage,
                                   Department department) throws IllegalArgumentException {

        _teacherRepository.registerTeacher(acronym, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, addressFactory, date, category, workingPercentage, department);

        return true;
    }
}
