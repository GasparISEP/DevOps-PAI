package PAI.domain;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;
import PAI.ddd.DomainEntity;

public class Teacher implements AggregateRoot<TeacherID>, DomainEntity<TeacherID> {

    private TeacherID _teacherID;

    private TeacherAcronym _acronym;

    private Name _name;

    private Email _email;

    private NIF _nif;

    private PhoneNumber _phoneNumber;

    private AcademicBackground _academicBackground;

    private AddressVO _address;

    private Department _department;

    //constructor
    public Teacher(TeacherAcronym acronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
                   AddressVO address, Department department) {

        isObjectNull(acronym, acronym, name, email, nif, phoneNumber, academicBackground, address, department);

        this._acronym = acronym;
        this._name = name;
        this._email = email;
        this._nif = nif;
        this._phoneNumber = phoneNumber;
        this._academicBackground = academicBackground;
        this._address = address;
        this._department = department;

        this._teacherID = TeacherID.createNew();
    }

    private void isObjectNull(Object... objects){
        for (Object object : objects) {
            if (object == null) {
                throw new IllegalArgumentException("Parameters should not be null.");
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
        if (other == null || getClass() != other.getClass()) return false;
        Teacher teacher = (Teacher) other;
        return  this._teacherID.equals(teacher._teacherID) ||
                this._nif.equals(teacher._nif);
    }

    public boolean isInDepartment(Department department) {
        return _department == department;
    }

    public boolean hasThisNIF(NIF nif) {
        return _nif.getNIF().equals(nif.getNIF());
    }
}