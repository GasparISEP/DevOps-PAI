package PAI.persistence.datamodel;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


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
}
