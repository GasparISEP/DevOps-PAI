package PAI.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department {

    private String _name;
    private String _acronym;
    private Teacher _director;

    //constructor
    public Department(String acronym, String name) throws Exception {
        validateAcronym(acronym);
        validateName(name);
    }

    //construtor
    public Department(String acronym, String name,Teacher teacherDirector) throws IllegalArgumentException {
        validateAcronym(acronym);
        validateName(name);
        this._director = teacherDirector;
    }

    private void validateName(String name) throws IllegalArgumentException {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Department´s name must be a non-empty String.");
        if(!name.matches("^[A-Z].*")){
            throw new IllegalArgumentException("Department´s name should start with a capital letter.");
        }
        if (name.length() <2 || name.length() > 100){
            throw new IllegalArgumentException("Department´s name must be between 2 and 100 characters.");
        }
        this._name = name;
    }

    private void validateAcronym(String departmentAcronym) throws IllegalArgumentException {
        if (departmentAcronym == null || departmentAcronym.isBlank())
            throw new IllegalArgumentException("Department´s acronym must be a 3 letter non-empty String.");

        if(!departmentAcronym.matches("^[A-Z]{3}$")){
            throw new IllegalArgumentException("Department´s acronym must contain only three capital letters.");
        }
        this._acronym = departmentAcronym;
    }

    public boolean hasSameAcronym(Department department) {
        return this._acronym.equals(department._acronym);
    }

    public boolean hasSameName(Department department) {
        return this._name.equals(department._name);
    }

    //US06
    public boolean changeDirector(Teacher furtherDirector) {
        _director = furtherDirector;
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Department that = (Department) obj;
        return Objects.equals(_name, that._name) && Objects.equals(_acronym, that._acronym);
    }

    public String getName() { return _name; }

    public String getAcronym() { return _acronym; }
}
