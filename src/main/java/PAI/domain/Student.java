package PAI.domain;

import java.util.List;

public class Student {

    private int _uniqueNumber;
    private String _name;
    private String _NIF;
    private String _phone;
    private String _email;
    private Address _address;

    //constructor validation
    public Student(int uniqueNumber, String name, String NIF, String phone, String email, Address address) {

        //validação Student Unique Number
        if (isUniqueNumberInvalid(uniqueNumber))
            throw new IllegalArgumentException("Student's identification number must be greater than zero!");

        _uniqueNumber = uniqueNumber;

        //Student name validation
        if (areParametersInvalid(name))
            throw new IllegalArgumentException("Student's name cannot be empty!");

        _name = name;

        //Student NIF validation
        if (areParametersInvalid(NIF))
            throw new IllegalArgumentException("Student's NIF cannot be empty!");

        _NIF = NIF;

        //Student phone validation
        if (areParametersInvalid(phone))
            throw new IllegalArgumentException("Student's phone cannot be empty!");

        _phone = phone;

        //Student email validation
        if (areParametersInvalid(email))
            throw new IllegalArgumentException("Student's email cannot be empty!");

        _email = email;

        _address = address;

    }

    private boolean isUniqueNumberInvalid(int studentNumber) {

        return studentNumber <= 0;
    }

    private boolean areParametersInvalid(String parameter) {

        return parameter == null || parameter.isBlank();
    }

    // Check for matching uniqueNumber
    public boolean hasSameUniqueNumber(Student student) {
        return _uniqueNumber == student._uniqueNumber;
    }

    // Check for matching NIF
    public boolean hasSameNIF(Student student) {
        return _NIF.equals(student._NIF);
    }

    public int getUniqueNumber() {
        return _uniqueNumber;
    }

    public boolean hasThisUniqueNumber(int uniqueNumber) {return uniqueNumber == _uniqueNumber;}
}
