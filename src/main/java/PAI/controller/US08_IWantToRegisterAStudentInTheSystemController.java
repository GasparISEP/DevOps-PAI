package PAI.controller;

import PAI.VOs.StudentID;
import PAI.domain.Address;
import PAI.repository.StudentRepository;

public class US08_IWantToRegisterAStudentInTheSystemController {

    private StudentRepository _studentRepository;

    public US08_IWantToRegisterAStudentInTheSystemController(StudentRepository studentRepository) {

        if (studentRepository == null)
            throw new IllegalArgumentException ("Student repository cannot be null!");

         _studentRepository = studentRepository;

    }

    public boolean registerStudent (StudentID studentID, String name, String NIF, String phone, String email, Address address) throws Exception {

        _studentRepository.registerStudent(studentID, name, NIF, phone, email, address);

        return true;


    }
}
