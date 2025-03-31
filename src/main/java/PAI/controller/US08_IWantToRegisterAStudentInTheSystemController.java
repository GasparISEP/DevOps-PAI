package PAI.controller;

import PAI.VOs.*;
import PAI.domain.Address;
import PAI.repository.StudentRepository;

public class US08_IWantToRegisterAStudentInTheSystemController {

    private StudentRepository _studentRepository;

    public US08_IWantToRegisterAStudentInTheSystemController(StudentRepository studentRepository) {

        if (studentRepository == null)
            throw new IllegalArgumentException ("Student repository cannot be null!");

         _studentRepository = studentRepository;

    }

    public boolean registerStudent (StudentID studentID, Name name, NIF NIF, PhoneNumber phone, Email email, Address address, StudentAcademicEmail academicEmail) throws Exception {

        _studentRepository.registerStudent(studentID, name, NIF, phone, email, address, academicEmail);

        return true;


    }
}
