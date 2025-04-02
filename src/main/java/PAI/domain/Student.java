package PAI.domain;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;

public class Student implements AggregateRoot<StudentID> {

    private StudentID _studentID;
    private Name _name;
    private NIF _NIF;
    private PhoneNumber _phone;
    private Email _email;
    private AddressVO _address;
    private StudentAcademicEmail _institutionalEmail;

    //constructor validation
    public Student(StudentID studentID, Name name, NIF NIF, PhoneNumber phone, Email email, AddressVO address, StudentAcademicEmail academicEmail) {

        // Validation of StudentID
        if (studentID == null)
            throw new IllegalArgumentException("Student's ID is invalid.");

        this._studentID = studentID;

        //Student name validation
        if (name == null)
            throw new IllegalArgumentException("Student's name cannot be empty!");

        _name = name;

        //Student NIF validation
        if (NIF == null)
            throw new IllegalArgumentException("Student's NIF is invalid!");

        _NIF = NIF;

        //Student phone validation
        if (phone == null)
            throw new IllegalArgumentException("Student's phone is invalid!");

        _phone = phone;

        //Student email validation
        if (email == null)
            throw new IllegalArgumentException("Student's email is not valid!");

        _email = email;

        _address = address;

        _institutionalEmail = academicEmail;
    }

    // NOTE: In future iterations, implementing a config file might be considered to replace the hardcoded email domain.
//    private StudentAcademicEmail generateInstitutionalEmail(int uniqueNumber){
//        return uniqueNumber + "@isep.ipp.pt";
//    }

    @Override
    public StudentID identity() {
        return _studentID;
    }

    @Override
    public boolean sameAs(Object object) {

        if (this == object) return true;

        if (!(object instanceof Student)) return false;

        Student other = (Student) object;
        return _NIF.equals(other._NIF);
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) return true;

        if (!(object instanceof Student)) return false;

        Student other = (Student) object;
        return _studentID.equals(other._studentID);
    }

    // Wrapper for the equals method
    public boolean isEquals (Student student) {
        return this.equals(student);
    }
}
