package PAI.controller;

import PAI.VOs.*;
import PAI.domain.Address;
import PAI.repository.IStudentRepository;
import PAI.repository.StudentRepository;

public class US08_IWantToRegisterAStudentInTheSystemController {

    private IStudentRepository _IStudentRepository;

    public US08_IWantToRegisterAStudentInTheSystemController(IStudentRepository iStudentRepository) {

        if (iStudentRepository == null)
            throw new IllegalArgumentException ("Student repository cannot be null!");

         _IStudentRepository = iStudentRepository;

    }

    public boolean registerStudent (StudentID studentID, Name name, NIF NIF, PhoneNumber phone, Email email, Street street, PostalCode postalCode, Location location, Country country, StudentAcademicEmail academicEmail) throws Exception {

        _IStudentRepository.registerStudent(studentID, name, NIF, phone, email, street, postalCode, location, country, academicEmail);

        return true;


    }
}
