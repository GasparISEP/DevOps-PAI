package PAI.domain;

import java.util.List;

public class Student {

    private String _uniqueNumber;
    private String _name;
    private String _NIF;
    private String _phone;
    private String _email;
    private Address _address;
    private String _institutionalEmail;

    //constructor validation
    public Student(String uniqueNumber, String name, String NIF, String phone, String email, Address address) {

        //validação Student Unique Number
        if (isUniqueNumberInvalid(uniqueNumber))
            throw new IllegalArgumentException("Student's unique number must have 7 digits and start with 1!");

        _uniqueNumber = uniqueNumber;

        //Student name validation
        if (areParametersInvalid(name))
            throw new IllegalArgumentException("Student's name cannot be empty!");

        _name = name;

        //Student NIF validation
        if (isNIFInvalid(NIF))
            throw new IllegalArgumentException("Student's NIF is invalid!");

        _NIF = NIF;

        //Student phone validation
        if (isPhoneNumberInvalid(phone))
            throw new IllegalArgumentException("Student's phone is invalid!");

        _phone = phone;

        //Student email validation
        if (isEmailInvalid(email))
            throw new IllegalArgumentException("Student's email is not valid!");

        _email = email;

        _address = address;

        _institutionalEmail = generateInstitutionalEmail(uniqueNumber);
    }

    private boolean areParametersInvalid(String parameter) {
        return parameter == null || parameter.isBlank();
    }

    private boolean isUniqueNumberInvalid(String studentNumber) {
        return !studentNumber.matches("^1\\d{6}$") || areParametersInvalid(studentNumber);
    }

    private boolean isNIFInvalid(String NIF){
        return !NIF.matches("^[A-Z]{0,2}?\\d{8,14}[A-Z0-9]?$") || areParametersInvalid(NIF);
    }

    private boolean isPhoneNumberInvalid(String phoneNumber){
        return !phoneNumber.matches("^\\+?\\d{1,4}?[ -.]?\\(?\\d{1,4}?\\)?[ -.]?\\d{3,4}[ -.]?\\d{3,4}$") || areParametersInvalid(phoneNumber);
    }

    private boolean isEmailInvalid(String email){
        return !email.matches("^[a-zA-Z0-9][a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-][a-zA-z0-9]+\\.[a-zA-Z]{2,}+(\\.[a-zA-Z]{2,})?$") || areParametersInvalid(email);
    }

    private String generateInstitutionalEmail(String uniqueNumber){
        return uniqueNumber + "@isep.ipp.pt";
    }

    // Check for matching uniqueNumber
    public boolean hasSameUniqueNumber(Student student) {
        return _uniqueNumber.equals(student._uniqueNumber);
    }

    // Check for matching NIF
    public boolean hasSameNIF(Student student) {
        return _NIF.equals(student._NIF);
    }

    public String getUniqueNumber() {
        return _uniqueNumber;
    }

    public boolean hasThisUniqueNumber(String uniqueNumber) {return uniqueNumber.equals(_uniqueNumber);}
}
