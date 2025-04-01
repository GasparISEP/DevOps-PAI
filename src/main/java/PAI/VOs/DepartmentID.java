package PAI.VOs;
import PAI.ddd.DomainId;
import java.util.Objects;

public class DepartmentID implements DomainId {

    private final DepartmentAcronym _departmentAcronym;

    public DepartmentID(DepartmentAcronym acronym) {
        if (acronym == null) {
            throw new IllegalArgumentException("Department ID cannot be null.");
        }
        this._departmentAcronym = acronym;
    }

    public DepartmentAcronym getAcronym() {
        return _departmentAcronym;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DepartmentID that = (DepartmentID) obj;
        return Objects.equals(_departmentAcronym, that._departmentAcronym);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_departmentAcronym);
    }
}
