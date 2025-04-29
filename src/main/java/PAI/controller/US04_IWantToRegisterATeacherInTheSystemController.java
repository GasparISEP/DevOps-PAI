package PAI.controller;

import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.service.ITeacherService;
import PAI.service.department.IDepartmentService;
import org.springframework.stereotype.Controller;

@Controller
public class US04_IWantToRegisterATeacherInTheSystemController {

    private final ITeacherService _teacherService;
    private final IDepartmentService _iDepartmentService;

    public US04_IWantToRegisterATeacherInTheSystemController( ITeacherService teacherService,
                                                              IDepartmentService iDepartmentService) {

        validateTeacherService(teacherService);
        validateDepartmentService(iDepartmentService);

        this._teacherService = teacherService;
        this._iDepartmentService = iDepartmentService;
    }

    public boolean registerATeacherInTheSystem(
            TeacherAcronym acronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
            Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID) throws Exception {

        if(!isDepartmentInDepartmentRepository(departmentID)){
            return false;
        }

        _teacherService.registerTeacher(
                 acronym,  name,  email,  nif,  phoneNumber,  academicBackground,street, postalCode,  location,  country,  departmentID);
        return true;
    }

    private boolean isDepartmentInDepartmentRepository(DepartmentID departmentID) {
        return _iDepartmentService.containsOfIdentity(departmentID);
    }

    private void validateTeacherService(ITeacherService teacherService) {
        if (teacherService == null) {
            throw new IllegalStateException("TeacherService is null.");
        }
    }
    private void validateDepartmentService(IDepartmentService iDepartmentService) {
        if (iDepartmentService == null) {
            throw new IllegalStateException("DepartmentService is null.");
        }
    }
}
