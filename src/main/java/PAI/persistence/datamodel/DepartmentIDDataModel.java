package PAI.persistence.datamodel;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DepartmentIDDataModel implements Serializable {

    @Column
    private String departmentID;

    public DepartmentIDDataModel() {}
    public  DepartmentIDDataModel(String departmentID) {
        if (departmentID == null || departmentID.isBlank()) {
            throw new IllegalArgumentException("Department ID cannot be an empty string.");
        }
        if(departmentID.length() < 3) {
            throw new IllegalArgumentException("Department ID must contain at least 3 characters.");
        }
        this.departmentID = departmentID.toUpperCase();
    }
    public String getDepartmentID() {
        return departmentID;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DepartmentIDDataModel that = (DepartmentIDDataModel) obj;
        return Objects.equals(departmentID, that.departmentID);
    }
    @Override
    public int hashCode() {
        return Objects.hash(departmentID);
    }
}
