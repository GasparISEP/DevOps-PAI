package PAI.domain.programme;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;

public class Programme implements AggregateRoot<ProgrammeID> {

    private NameWithNumbersAndSpecialChars _name;
    private QuantSemesters _quantSemesters;
    private MaxEcts _maxEcts;
    private Acronym _acronym;
    private DegreeTypeID _degreeTypeID;
    private DepartmentID _department;
    private TeacherID _programmeDirectorID;
    private ProgrammeID _programmeID;

    public Programme(NameWithNumbersAndSpecialChars name, Acronym acronym, MaxEcts maxOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID, ProgrammeID programmeID) throws IllegalArgumentException {
        if(name==null) {
            throw new IllegalArgumentException("Programme name cannot be null");
        }
        _name = name;

        if (acronym == null) {
            throw new IllegalArgumentException("Acronym must not be null");
        }
        _acronym = acronym;

        if (maxOfEcts == null) {
            throw new IllegalArgumentException("Number of ECTS must not be null");
        }
        _maxEcts = maxOfEcts;

        if (quantityOfSemesters==null) {
            throw new IllegalArgumentException("Quantity of Semesters must not be null");
        }
        _quantSemesters = quantityOfSemesters;

        if (degreeTypeID == null) {
            throw new IllegalArgumentException("DegreeTypeID must not be null");
        }
        _degreeTypeID = degreeTypeID;

        if (departmentID == null) {
            throw new IllegalArgumentException("DepartmentID must not be null");
        }
        _department = departmentID;

        if (programmeDirectorID == null) {
            throw new IllegalArgumentException("TeacherID must not be null");
        }
        _programmeDirectorID = programmeDirectorID;

        if (programmeID == null) {
            throw new IllegalArgumentException("ProgrammeID must not be null");
        }
        _programmeID = programmeID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Programme programme = (Programme) o;
        return this._programmeID.equals(programme._programmeID);
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

    public MaxEcts getMaxEcts() {
        return _maxEcts;
    }

    public QuantSemesters getQuantSemesters() {return _quantSemesters;}


    public boolean hasThisProgrammeName(NameWithNumbersAndSpecialChars name) {return _name.equals(name);}

    public boolean hasThisProgrammeAcronym(Acronym acronym) {return _acronym.equals(acronym);}

    public boolean hasThisDegreeTypeID(DegreeTypeID degreeTypeID) {
        return _degreeTypeID.equals(degreeTypeID);
    }

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

    public boolean sameAs(Object object) {
        if (this == object) return true;
        if (!(object instanceof Programme thatProgramme)) return false;
        return this._name.equals(thatProgramme._name) && this._acronym.equals(thatProgramme._acronym);
    }
}