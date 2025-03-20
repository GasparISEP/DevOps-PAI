package PAI.domain;

import PAI.factory.*;

import java.util.List;

public class Teacher {

    private String _acronym;

    private String _name;

    private String _email;

    private String _nif;

    private String _phoneNumber;

    private String _academicBackground;

    private Address _address;

    private AddressFactory _addressFactory;

    private Department _department;

    private List<TeacherCareerProgression> _teacherCareerProgressionList;

    private TeacherCareerProgressionFactory _teacherCareerProgressionFactory;

    //constructor
    public Teacher(String acronym, String name, String email, String nif, String phoneNumber, String academicBackground,
                   String street, String postalCode, String location, String country, AddressFactory addressFactory,
                   String date, TeacherCategory category, int workingPercentage, Department department,
                   TeacherCareerProgressionFactory teacherCareerProgressionFactory,
                   TeacherCareerProgressionListFactory teacherCareerProgressionListFactory) throws IllegalArgumentException {

        validateAcronym(acronym);
        validateName(name);
        validateEmail(email);
        validateNif(nif);
        validatePhoneNumber(phoneNumber);
        validateAcademicBackground(academicBackground);
        validateFactories(teacherCareerProgressionFactory, teacherCareerProgressionListFactory);

        this._address = addressFactory.createAddress(street, postalCode, location, country);

        _teacherCareerProgressionList = teacherCareerProgressionListFactory.createTeacherCareerProgressionList();

        this._teacherCareerProgressionFactory = teacherCareerProgressionFactory;

        TeacherCareerProgression tcp = _teacherCareerProgressionFactory.createTeacherCareerProgression(date, category, workingPercentage);

        this._teacherCareerProgressionList.add(tcp);

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

        if (!phoneNumber.matches("^[A-Z][0-9]{3}$"))
            throw new IllegalArgumentException("Teacher´s office phoneNumber must have a letter followed by 3 digits.");

        this._phoneNumber = phoneNumber;
    }

    private void validateAcademicBackground(String academicBackground) throws IllegalArgumentException {
        if (academicBackground == null || academicBackground.isBlank())
            throw new IllegalArgumentException("Teacher's academic background must be a non-empty String.");

        this._academicBackground = academicBackground;
    }

    private void validateFactories (TeacherCareerProgressionFactory tcpFactory, TeacherCareerProgressionListFactory tcpListFactory) {

        if (tcpFactory == null)
            throw new IllegalArgumentException("Teacher Career Progression Factory must not be null.");

        if (tcpListFactory == null) {
            throw new IllegalArgumentException("Teacher Career Progression List Factory must not be null.");
        }
    }

    public boolean hasSameAcronym(Teacher teacher) {
        return this._acronym.equals(teacher._acronym);
    }

    public boolean hasSameNif(Teacher teacher) {
        return this._nif.equals(teacher._nif);
    }

    public boolean isInDepartment(Department department) {
        return _department == department;
    }

    public boolean hasThisNIF(String NIF) {

        return _nif.equals(NIF);
    }

    public boolean updateWorkingPercentageInTeacherCareerProgression (String date, int workingPercentage) throws IllegalArgumentException {

        TeacherCareerProgression lastCareerProgression = getLastTeacherCareerProgression();

        TeacherCategory lastCategory = lastCareerProgression.getCategory();

        if (lastCareerProgression.getWorkingPercentage() == workingPercentage)
            throw new IllegalArgumentException("Working percentage must be different than the last working percentage!");

        TeacherCareerProgression tcp = _teacherCareerProgressionFactory.createTeacherCareerProgression(date, lastCategory, workingPercentage);

        if(!tcp.isDateAfter(lastCareerProgression))
            throw new IllegalArgumentException("Date must be greater than the last date registered!");

        _teacherCareerProgressionList.add(tcp);

        return true;
    }

    public boolean updateTeacherCategoryInTeacherCareer(String date, TeacherCategory teacherCategory) throws IllegalArgumentException {

        if (teacherCategory == null) {
            throw new IllegalArgumentException("Teacher category cannot be null.");
        }

        TeacherCareerProgression lastTeacherCareerProgression = getLastTeacherCareerProgression();

        int lastWorkingPercentage = lastTeacherCareerProgression.getWorkingPercentage();

        if(teacherCategory == lastTeacherCareerProgression.getCategory())
            throw new IllegalArgumentException("The Teacher Category provided is already active.");

        TeacherCareerProgression updatedTeacherCareerProgression = _teacherCareerProgressionFactory.createTeacherCareerProgression(date, teacherCategory, lastWorkingPercentage);

        if(!updatedTeacherCareerProgression.isDateAfter(lastTeacherCareerProgression))
            throw new IllegalArgumentException("The date must be greater than the last date registered!");
        else {
            _teacherCareerProgressionList.add(updatedTeacherCareerProgression);
            return true;
        }
    }

    private TeacherCareerProgression getLastTeacherCareerProgression() {

        return _teacherCareerProgressionList.getLast();

    }
}