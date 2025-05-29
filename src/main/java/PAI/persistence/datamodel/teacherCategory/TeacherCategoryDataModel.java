package PAI.persistence.datamodel.teacherCategory;

import PAI.utils.ValidationUtils;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "teacher_category")
public class TeacherCategoryDataModel {

    @EmbeddedId
    private TeacherCategoryIDDataModel id;

    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    protected TeacherCategoryDataModel() {
    }

    public TeacherCategoryDataModel(TeacherCategoryIDDataModel id, String name) {

        this.id = ValidationUtils.validateNotNull(id, "Teacher Category ID DataModel");
        this.name = ValidationUtils.validateNotNull(name, "Teacher Category Name");
    }

    public TeacherCategoryIDDataModel getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeacherCategoryDataModel that)) return false;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
