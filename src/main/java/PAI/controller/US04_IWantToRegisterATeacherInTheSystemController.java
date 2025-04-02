package PAI.controller;

import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.repository.DepartmentRepositoryImpl;
import PAI.repository.TeacherRepository;

public class US04_IWantToRegisterATeacherInTheSystemController {

    private final TeacherRepository _teacherRepository;
    private final DepartmentRepositoryImpl _departmentRepository;

    public US04_IWantToRegisterATeacherInTheSystemController( TeacherRepository teacherRepository,
                                                              DepartmentRepositoryImpl departmentRepository) {

        validateTeacherRepository(teacherRepository);
        validateDepartmentRepository(departmentRepository);

        this._teacherRepository = teacherRepository;
        this._departmentRepository = departmentRepository;
    }

    public boolean registerATeacherInTheSystem(
            TeacherAcronym acronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
            Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID) {

        if(!isDepartmentInDepartmentRepository(departmentID)){
            return false;
        }

        _teacherRepository.registerTeacher(
                 acronym,  name,  email,  nif,  phoneNumber,  academicBackground,street, postalCode,  location,  country,  departmentID);
        return true;
    }

    private boolean isDepartmentInDepartmentRepository(DepartmentID departmentID) {
        return _departmentRepository.departmentExists(departmentID);
    }

    private void validateTeacherRepository(TeacherRepository teacherRepository) {
        if (teacherRepository == null) {
            throw new IllegalStateException("TeacherRepository is null.");
        }
    }
    private void validateDepartmentRepository(DepartmentRepositoryImpl departmentRepository) {
        if (departmentRepository == null) {
            throw new IllegalStateException("DepartmentRepository is null.");
        }
    }
}
