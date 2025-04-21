package PAI.persistence.datamodel.schoolYear;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SchoolYearIDDataModel implements Serializable {

    private String id;

    public SchoolYearIDDataModel() {}

    public SchoolYearIDDataModel(String id) {
        this.id = id;
    }

    // Getters
    public String getId() {
        return id;
    }

    // Equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchoolYearIDDataModel)) return false;
        SchoolYearIDDataModel that = (SchoolYearIDDataModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
