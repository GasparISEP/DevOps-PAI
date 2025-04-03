package PAI.domain.programme;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;
import PAI.domain.Department;
import PAI.domain.studyPlan.StudyPlanDDD;

import java.util.Objects;

public class ProgrammeDDD implements AggregateRoot<ProgrammeID> {

    private NameWithNumbersAndSpecialChars _name;
    private QuantSemesters _quantSemesters;
    private QuantEcts _quantEcts;
    private Acronym _acronym;
    private DegreeTypeID _degreeTypeID;
    private DepartmentID _department;
    private TeacherID _programmeDirectorID;
    private ProgrammeID _programmeID;

    public ProgrammeDDD(NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID) throws IllegalArgumentException {
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

        if (departmentID == null) {
            throw new IllegalArgumentException("Department must not be null");
        }
        _department = departmentID;

        if (programmeDirectorID == null) {
            throw new IllegalArgumentException("Insert a valid Programme Director");
        }
        _programmeDirectorID = programmeDirectorID;
        _programmeID = new ProgrammeID(name,acronym);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammeDDD programme = (ProgrammeDDD) o;
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

    public boolean isInDepartment(DepartmentID departmentID) {
        return _department.equals(departmentID);
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
    public DegreeTypeID getDegreeTypeID() {
        return _degreeTypeID;
    }

    public DepartmentID getDepartment() {
        return _department;
    }

    public TeacherID getProgrammeDirectorID() {
        return _programmeDirectorID;
    }

    @Override
    public ProgrammeID identity() {
        return _programmeID;
    }

    @Override
    public boolean sameAs(Object object) {
        if (this == object) return true;
        if (!(object instanceof ProgrammeDDD programmeDDD)) return false;
        return this._programmeID.equals(programmeDDD._programmeID);
    }
}