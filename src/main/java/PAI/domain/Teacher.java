package PAI.domain;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;

public class Teacher implements AggregateRoot<TeacherID> {

    private TeacherID _teacherID;

    private Name _name;

    private Email _email;

    private NIF _nif;

    private PhoneNumber _phoneNumber;

    private AcademicBackground _academicBackground;

    private Address _address;

    private DepartmentID _departmentID;

    //constructor
    public Teacher(TeacherAcronym acronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
                   Address address, DepartmentID departmentID) {

        isObjectNull(acronym, name, email, nif, phoneNumber, academicBackground, address, departmentID);

        this._teacherID = new TeacherID(acronym);
        this._name = name;
        this._email = email;
        this._nif = nif;
        this._phoneNumber = phoneNumber;
        this._academicBackground = academicBackground;
        this._address = address;
        this._departmentID = departmentID;
    }

    private void isObjectNull(Object... objects){
        for (Object object : objects) {
            if (object == null) {
                throw new IllegalArgumentException("Passing parameters should not be null.");
            }
        }
    }

    @Override
    public TeacherID identity() {
        return _teacherID;
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (!(other instanceof Teacher teacher)) return false;
        return this._teacherID.equals(teacher._teacherID);
    }

    public boolean isInDepartment(DepartmentID departmentID) {
        return _departmentID.equals(departmentID);
    }

    public boolean hasThisNIF(NIF nif) {
        return _nif.getNIF().equals(nif.getNIF());
    }
}