package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class Teacher {

    private String _acronym;

    private String _name;

    private String _email;

    private String _nif;

    private String _phoneNumber;

    private String _academicBackground;

    private Address _address;

    private Department _department;

    private List<TeacherCareerProgression> _teacherCareerProgression;


    //constructor
    public Teacher(String acronym, String name, String email, String nif, String phoneNumber, String academicBackground, String street, String postalCode, String location, String country, String date, TeacherCategory category, int workingPercentage,
                   Department department) throws IllegalArgumentException {
        validateAcronym(acronym);
        validateName(name);
        validateEmail(email);
        validateNif(nif);
        validatePhoneNumber(phoneNumber);
        validateAcademicBackground(academicBackground);

        this._address = new Address (street, postalCode, location,country);

        this._teacherCareerProgression = new ArrayList<>();
        TeacherCareerProgression tcp = new TeacherCareerProgression(date, category, workingPercentage);
        this._teacherCareerProgression.add(tcp);

        this._department = department;
    }


    private void validateAcronym(String teacherAcronym) throws IllegalArgumentException {
        if (teacherAcronym == null || teacherAcronym.isBlank()) {
            throw new IllegalArgumentException("Teacher´s acronym must be a 3 capital letter non-empty String.");
        }
        if (!teacherAcronym.matches("^[A-Z]{3}$")) {
            throw new IllegalArgumentException("Teacher´s acronym must contain only three capital letters.");
        }
        this._acronym = teacherAcronym;
    }

    private void validateName(String name) throws IllegalArgumentException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Teacher´s name must be a non-empty String.");
        }
        if (name.length() < 2 || name.length() > 100) {
            throw new IllegalArgumentException("Teacher´s name must be between 2 and 100 characters.");
        }
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!Character.isLetter(c) && !Character.isSpaceChar(c) && c != '-') {
                throw new IllegalArgumentException("Teacher´s name must contain only letters and spaces.");
            }
        }
        if (!name.matches("^[A-Z].*")) {
            throw new IllegalArgumentException("Teacher´s name should start with a capital letter.");
        }
        this._name = name;
    }

    private void validateEmail(String email) throws IllegalArgumentException {

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Teacher´s email must be a non-empty String.");
        }
        if (!email.toLowerCase().matches(_acronym.toLowerCase() + "@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this._email = email;
    }

    private void validateNif(String nif) throws IllegalArgumentException {
        if (nif == null || nif.isBlank())
            throw new IllegalArgumentException("Teacher´s NIF must be a non-empty String.");

        if (!nif.matches("^[0-9]{9}$"))
            throw new IllegalArgumentException("Teacher´s NIF must contain only 9 characters.");
        this._nif = nif;
    }

    private void validatePhoneNumber(String phoneNumber) throws IllegalArgumentException {
        if (phoneNumber == null || phoneNumber.isBlank())
            throw new IllegalArgumentException("Teacher´s office phoneNumber must be a non-empty String.");

        if (!phoneNumber.matches("^[A-Z][0-9]{3}$") || phoneNumber.length() != 4)
            throw new IllegalArgumentException("Teacher´s office phoneNumber must have a letter followed by 3 digits.");

        this._phoneNumber = phoneNumber;
    }

    private void validateAcademicBackground(String academicBackground) throws IllegalArgumentException {
        if (academicBackground == null || academicBackground.isBlank())
            throw new IllegalArgumentException("Teacher's academic background must be a non-empty String.");
    }

    public boolean hasSameAcronym(Teacher teacher) {
        return this._acronym.equals(teacher._acronym);
    }

    public boolean hasSameNif(Teacher teacher) {
        return this._nif.equals(teacher._nif);
    }

    protected boolean isInDepartment(Department department) {
        return _department == department;
    }

    public boolean hasThisNIF(String NIF) {

        return _nif.equals(NIF);
    }

    public boolean updateWorkingPercentageInTeacherCareerProgression (String date, int workingPercentage) throws IllegalArgumentException {

        TeacherCareerProgression lastCareerProgression = _teacherCareerProgression.getLast();

        TeacherCategory lastCategory = lastCareerProgression.getCategory();

        if (lastCareerProgression.getWorkingPercentage() == workingPercentage)
            throw new IllegalArgumentException("Working percentage must be different than the last working percentage!");

        TeacherCareerProgression tcp = new TeacherCareerProgression(date, lastCategory, workingPercentage);

        if(!tcp.isDateAfter(lastCareerProgression))
            throw new IllegalArgumentException("Date must be greater than the last date registered!");

        _teacherCareerProgression.add(tcp);

        return true;
    }
}

