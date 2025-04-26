package PAI.controller;

import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.repository.DepartmentRepositoryImpl;
import PAI.repository.IDepartmentRepository;
import PAI.repository.ITeacherRepository;

public class US04_IWantToRegisterATeacherInTheSystemController {

    private final ITeacherRepository _iTeacherRepository;
    private final IDepartmentRepository _iDepartmentRepository;

    public US04_IWantToRegisterATeacherInTheSystemController( ITeacherRepository iTeacherRepository,
                                                              IDepartmentRepository iDepartmentRepository) {

        validateTeacherRepository(iTeacherRepository);
        validateDepartmentRepository(iDepartmentRepository);

        this._iTeacherRepository = iTeacherRepository;
        this._iDepartmentRepository = iDepartmentRepository;
    }

    public boolean registerATeacherInTheSystem(
            TeacherAcronym acronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
            Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID) {

        if(!isDepartmentInDepartmentRepository(departmentID)){
            return false;
        }

        _iTeacherRepository.registerTeacher(
                 acronym,  name,  email,  nif,  phoneNumber,  academicBackground,street, postalCode,  location,  country,  departmentID);
        return true;
    }

    private boolean isDepartmentInDepartmentRepository(DepartmentID departmentID) {
        return _iDepartmentRepository.containsOfIdentity(departmentID);
    }

    private void validateTeacherRepository(ITeacherRepository teacherRepository) {
        if (teacherRepository == null) {
            throw new IllegalStateException("TeacherRepository is null.");
        }
    }
    private void validateDepartmentRepository(IDepartmentRepository iDepartmentRepository) {
        if (iDepartmentRepository == null) {
            throw new IllegalStateException("DepartmentRepository is null.");
        }
    }
}
