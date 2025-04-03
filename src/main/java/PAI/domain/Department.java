package PAI.domain;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.DepartmentID;
import PAI.VOs.Name;
import PAI.VOs.TeacherID;
import PAI.ddd.AggregateRoot;
import java.util.Objects;

public class Department implements AggregateRoot <DepartmentID> {

    private final DepartmentID _departmentId;
    private final Name _departmentName;
    private TeacherID _director;

    public Department(DepartmentAcronym acronym, Name name) throws Exception {
        if (acronym == null || name == null) {
            throw new IllegalArgumentException("Acronym and Name cannot be null.");
        }
        this._departmentId = new DepartmentID(acronym);
        this._departmentName = name;
    }

    public Department(DepartmentAcronym acronym, Name name, TeacherID teacherDirector) throws Exception {
        if (acronym == null || name == null) {
            throw new IllegalArgumentException("Acronym and name cannot be null.");
        }
        if (teacherDirector == null) {
            throw new IllegalArgumentException("Teacher Director cannot be null.");
        }
        this._departmentId = new DepartmentID(acronym);
        this._departmentName = name;
        this._director = teacherDirector;
    }

    public DepartmentID identity() { return _departmentId; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Department that = (Department) obj;
        return Objects.equals(_departmentId, that._departmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_departmentId);
    }

    public boolean sameAs(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Department that = (Department) obj;
        return Objects.equals(_departmentName, that._departmentName) && Objects.equals(_departmentId, that._departmentId);
    }

    public Name getName() { return _departmentName; }

    public DepartmentAcronym getAcronym() { return _departmentId.getAcronym(); }

    public DepartmentID getDepartmentID() {return _departmentId;}


    //US06
    public boolean changeDirector(TeacherID furtherDirectorID) {
        if (furtherDirectorID == null) {
            return false;
        }
        this._director = furtherDirectorID;
        return true;
    }
}
