package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class TeacherCategoryIDDataModel implements Serializable {

    private UUID value;

    protected TeacherCategoryIDDataModel() {
        // JPA only
    }

    public TeacherCategoryIDDataModel(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("UUID cannot be null");
        }
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeacherCategoryIDDataModel that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
