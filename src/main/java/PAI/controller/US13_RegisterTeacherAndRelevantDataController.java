package PAI.controller;

import PAI.VOs.*;
import PAI.domain.Department;
import PAI.domain.TeacherCategory;
import PAI.repository.DepartmentRepository;
import PAI.repository.TeacherCareerProgressionRepository;
import PAI.repository.TeacherCategoryRepositoryImpl;
import PAI.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class US13_RegisterTeacherAndRelevantDataController {

    private TeacherCategoryRepositoryImpl _teacherCategoryRepository;
    private DepartmentRepository _departmentRepository;
    private TeacherRepository _teacherRepository;
    private TeacherCareerProgressionRepository _teacherCareerProgressionRepository;

    //Constructor
    public US13_RegisterTeacherAndRelevantDataController(TeacherCategoryRepositoryImpl teacherCategoryRepository,
        DepartmentRepository departmentRepository, TeacherRepository teacherRepository, TeacherCareerProgressionRepository teacherCareerProgressionRepository) {

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
    public List<TeacherCategory> getTeacherCategoryList() throws IllegalStateException{
        return _teacherCategoryRepository.getTeacherCategoryList();
    }

    // Method to get all Departments
    public Set<Department> getDepartmentsList() throws IllegalStateException{
        return _departmentRepository.getDepartments();
    }

    // Method to register the Teacher object
    public boolean registerTeacher(TeacherAcronym acronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber,
                                   AcademicBackground academicBackground, Street street, PostalCode postalCode,
                                   Location location, Country country, DepartmentID departmentID, Date date,
                                   TeacherCategoryID teacherCategoryID, WorkingPercentage workingPercentage) throws Exception {


        // register Teacher
        Optional<TeacherID> optionalTeacherID = _teacherRepository.registerTeacher(acronym, name, email, nif, phoneNumber, academicBackground,
            street, postalCode, location, country, departmentID);

        if (optionalTeacherID.isEmpty()) {
            return false;
        }

        TeacherID teacherID = optionalTeacherID.get();

        // If teacher was created and saved, then create and save first Teacher Career Progression
        _teacherCareerProgressionRepository.createTeacherCareerProgression(date, teacherCategoryID, workingPercentage, teacherID);

        return true;
    }
}
