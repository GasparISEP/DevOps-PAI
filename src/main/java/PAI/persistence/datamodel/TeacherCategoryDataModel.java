package PAI.persistence.datamodel;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "teacher_category")
public class TeacherCategoryDataModel {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    protected TeacherCategoryDataModel() {
        // Construtor padrão protegido para JPA
    }

    public TeacherCategoryDataModel(UUID id, String name) {
        if (id == null) {
            throw new IllegalArgumentException("id não pode ser nulo");
        }
        if (name == null) {
            throw new IllegalArgumentException("name não pode ser nulo");
        }
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
