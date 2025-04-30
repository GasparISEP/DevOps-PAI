package PAI.domain;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;

public class Student implements AggregateRoot<StudentID> {

    private StudentID _studentID;
    private Name _name;
    private NIF _NIF;
    private PhoneNumber _phone;
    private Email _email;
    private Address _address;
    private StudentAcademicEmail _institutionalEmail;

    //constructor validation
    public Student(StudentID studentID, Name name, NIF NIF, PhoneNumber phone, Email email, Address address, StudentAcademicEmail academicEmail) {

        // Validation of StudentID
        if (isArgumentValid(studentID))
            _studentID = studentID;
        else
            throw new IllegalArgumentException("Student's ID is invalid.");

        //Student name validation
        if (isArgumentValid(name))
            _name = name;
        else
            throw new IllegalArgumentException("Student's name cannot be empty!");


        //Student NIF validation
        if (isArgumentValid(NIF))
            _NIF = NIF;
        else
            throw new IllegalArgumentException("Student's NIF is invalid!");

        //Student phone validation
        if (isArgumentValid(phone))
            _phone = phone;
        else
            throw new IllegalArgumentException("Student's phone is invalid!");

        //Student email validation
        if (isArgumentValid(email))
            _email = email;
        else
            throw new IllegalArgumentException("Student's email is not valid!");

        if (isArgumentValid(address))
            _address = address;
        else
            throw new IllegalArgumentException("Address is not valid!");

        if (isArgumentValid(academicEmail))
            _institutionalEmail = academicEmail;
        else
            throw new IllegalArgumentException("Student's Academic Email is not valid!");
    }

    public boolean isArgumentValid (Object object) {

        if (object == null)
            return false;

        return true;
    }

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

    public StudentID getStudentID() {
        return _studentID;
    }

    public Name getStudentName() {
        return _name;
    }

    public NIF getStudentNIF() {
        return _NIF;
    }

    public PhoneNumber getStudentPhoneNumber() {
        return _phone;
    }

    public Email getStudentEmail() {
        return _email;
    }

    public Address getStudentAddress() {
        return _address;
    }

    public StudentAcademicEmail getStudentAcademicEmail() {
        return _institutionalEmail;
    }

    public boolean hasNIF(NIF nif) {
        return _NIF.equals(nif);
    }
}
