package PAI.domain;

import PAI.VOs.NIF;
import PAI.VOs.StudentID;
import PAI.ddd.AggregateRoot;

public class Student implements AggregateRoot<StudentID> {

    private StudentID _studentID;
    private String _name;
    private String _NIF;
    private String _phone;
    private String _email;
    private Address _address;
    private String _institutionalEmail;

    //constructor validation
    public Student(StudentID studentID, String name, String NIF, String phone, String email, Address address) {

        // Validation of StudentID
        if (studentID == null)
            throw new IllegalArgumentException("Student's ID is invalid.");

        this._studentID = studentID;

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

        _institutionalEmail = generateInstitutionalEmail(studentID.getUniqueNumber());
    }

    private boolean areParametersInvalid(String parameter) {
        return parameter == null || parameter.isBlank();
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

    // NOTE: In future iterations, implementing a config file might be considered to replace the hardcoded email domain.
    private String generateInstitutionalEmail(int uniqueNumber){
        return uniqueNumber + "@isep.ipp.pt";
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
}
