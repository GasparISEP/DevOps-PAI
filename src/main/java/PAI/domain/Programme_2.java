package PAI.domain;

import PAI.VOs.*;

import java.util.Objects;

public class Programme_2 {

    private NameWithNumbersAndSpecialChars _name;
    private QuantSemesters _quantSemesters;
    private QuantEcts _quantEcts;
    private Acronym _acronym;
    private DegreeType_ID _degreeTypeID;
    private Department _department;
    private TeacherID _programmeDirectorID;
    private ProgrammeID _programmeID;

    public Programme_2(NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeType_ID degreeTypeID, Department department, TeacherID programmeDirectorID) throws IllegalArgumentException {
        if(name==null) {
            throw new IllegalArgumentException("Programme name cannot be null");
        }
        _name = name;

        if (acronym == null) {
            throw new IllegalArgumentException("Acronym must not be null");
        }
        _acronym = acronym;

        if (quantityOfEcts == null) {
            throw new IllegalArgumentException("Number of ECTS must not be null");
        }
        _quantEcts = quantityOfEcts;

        if (quantityOfSemesters==null) {
            throw new IllegalArgumentException("Quantity of Semesters must not be null");
        }
        _quantSemesters = quantityOfSemesters;

        if (degreeTypeID == null) {
            throw new IllegalArgumentException("DegreeTypeID must not be null");
        }
        _degreeTypeID = degreeTypeID;

        if (department == null) {
            throw new IllegalArgumentException("Department must not be null");
        }
        _department = department;

        if (programmeDirectorID == null) {
            throw new IllegalArgumentException("Insert a valid Programme Director");
        }
        _programmeDirectorID = programmeDirectorID;
        _programmeID = new ProgrammeID();
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Programme_2 programme = (Programme_2) o;
        return _quantEcts == programme._quantEcts && _quantSemesters == programme._quantSemesters &&
                Objects.equals(_name, programme._name) && Objects.equals(_acronym, programme._acronym);
    }

    public boolean isEquals (ProgrammeID programmeID) {
        return this._programmeID.equals(programmeID);
    }


    public boolean newProgrammeDirector(TeacherID teacherDirectorID) throws Exception {
        if (teacherDirectorID == null) return false;
        _programmeDirectorID = teacherDirectorID;
        return true;
    }


    public boolean isInDepartment(Department department) {
        return _department.equals(department);
    }

    public ProgrammeID getProgrammeID(){
        return this._programmeID;
    }

    public QuantEcts getQuantEcts() {
        return _quantEcts;
    }

    public QuantSemesters getQuantSemesters() {return _quantSemesters;}


    public boolean hasThisProgrammeName(NameWithNumbersAndSpecialChars name) {return _name.equals(name);}

    public Acronym getAcronym() {
        return _acronym;
    }

    public NameWithNumbersAndSpecialChars getProgrammeName() {
        return _name;

    }
    public DegreeType_ID getDegreeTypeID() {
        return _degreeTypeID;
    }

    public Department getDepartment() {
        return _department;
    }

    public TeacherID getProgrammeDirectorID() {
        return _programmeDirectorID;
    }
}