package PAI.persistence.datamodel.department;
import PAI.persistence.datamodel.TeacherIDDataModel;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "department")
public class DepartmentDataModel {

    @EmbeddedId
    private DepartmentIDDataModel id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="acronym", nullable = false)
    private String acronym;

    @Embedded
    private TeacherIDDataModel directorId;

    @Version
    private Long _version;

    public DepartmentDataModel() {}

    public DepartmentDataModel(String name, String acronym) {
        this.name = name;
        this.acronym = acronym;
        this.id = new DepartmentIDDataModel(acronym);
    }

    public DepartmentDataModel(String name, String acronym, TeacherIDDataModel directorId) {
        this.name = name;
        this.acronym = acronym;
        this.id = new DepartmentIDDataModel(acronym);
        this.directorId = directorId;
    }

    public DepartmentIDDataModel getId() { return id; }

    public String getName() { return name; }

    public String getAcronym() { return acronym; }

    public TeacherIDDataModel  getDirectorId() { return directorId; }

    public void setDirectorId(TeacherIDDataModel directorId) { this.directorId = directorId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentDataModel that = (DepartmentDataModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

