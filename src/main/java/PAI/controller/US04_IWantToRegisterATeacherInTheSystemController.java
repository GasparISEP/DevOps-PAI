package PAI.controller;

import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.domain.*;
import PAI.repository.DepartmentRepository;
import PAI.repository.TeacherRepository;

public class US04_IWantToRegisterATeacherInTheSystemController {

    private final TeacherRepository _teacherRepository;
    private final DepartmentRepository _departmentRepository;

    public US04_IWantToRegisterATeacherInTheSystemController( TeacherRepository teacherRepository,
                                                              DepartmentRepository departmentRepository) {

        validateTeacherRepository(teacherRepository);
        validateDepartmentRepository(departmentRepository);

        this._teacherRepository = teacherRepository;
        this._departmentRepository = departmentRepository;
    }

    public boolean registerATeacherInTheSystem(
            TeacherAcronym acronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
            Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID) {

//        if(!isDepartmentInDepartmentRepository(departmentID)){
//            return false;
//        }

        _teacherRepository.registerTeacher(
                 acronym,  name,  email,  nif,  phoneNumber,  academicBackground,street, postalCode,  location,  country,  departmentID);
        return true;
    }

    private boolean isDepartmentInDepartmentRepository(Department department) {
        return _departmentRepository.departmentExists(department);
    }

    private void validateTeacherRepository(TeacherRepository teacherRepository) {
        if (teacherRepository == null) {
            throw new IllegalStateException("TeacherRepository is null.");
        }
    }
    private void validateDepartmentRepository(DepartmentRepository departmentRepository) {
        if (departmentRepository == null) {
            throw new IllegalStateException("DepartmentRepository is null.");
        }
    }
}
