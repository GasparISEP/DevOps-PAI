package PAI.persistence.datamodel;

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
        // Construtor padrão protegido para JPA
    }

    public TeacherCategoryDataModel(TeacherCategoryIDDataModel id, String name) {
        if (id == null) {
            throw new IllegalArgumentException("id não pode ser nulo");
        }
        if (name == null) {
            throw new IllegalArgumentException("name não pode ser nulo");
        }
        this.id = id;
        this.name = name;
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
