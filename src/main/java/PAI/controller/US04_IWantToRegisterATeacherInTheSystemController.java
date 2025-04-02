package PAI.controller;

import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.repository.DepartmentRepository;
import PAI.repository.ITeacherRepository;

public class US04_IWantToRegisterATeacherInTheSystemController {

    private final ITeacherRepository _iteacherRepository;
    private final DepartmentRepository _departmentRepository;

    public US04_IWantToRegisterATeacherInTheSystemController( ITeacherRepository iteacherRepository,
                                                              DepartmentRepository departmentRepository) {

        validateTeacherRepository(iteacherRepository);
        validateDepartmentRepository(departmentRepository);

        this._iteacherRepository = iteacherRepository;
        this._departmentRepository = departmentRepository;
    }

    public boolean registerATeacherInTheSystem(
            TeacherAcronym acronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
            Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID) {

        if(!isDepartmentInDepartmentRepository(departmentID)){
            return false;
        }

        _iteacherRepository.registerTeacher(
                 acronym,  name,  email,  nif,  phoneNumber,  academicBackground,street, postalCode,  location,  country,  departmentID);
        return true;
    }

    private boolean isDepartmentInDepartmentRepository(DepartmentID departmentID) {
        return _departmentRepository.departmentExists(departmentID);
    }

    private void validateTeacherRepository(ITeacherRepository iteacherRepository) {
        if (iteacherRepository == null) {
            throw new IllegalStateException("TeacherRepository is null.");
        }
    }
    private void validateDepartmentRepository(DepartmentRepository departmentRepository) {
        if (departmentRepository == null) {
            throw new IllegalStateException("DepartmentRepository is null.");
        }
    }
}
