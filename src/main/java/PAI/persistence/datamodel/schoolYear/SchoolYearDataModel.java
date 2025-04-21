package PAI.persistence.datamodel.schoolYear;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "School_Year")
public class SchoolYearDataModel {

    @EmbeddedId
    private SchoolYearIDDataModel id;

    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    public SchoolYearDataModel() {}

    public SchoolYearDataModel(SchoolYearIDDataModel id, String description, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters
    public SchoolYearIDDataModel getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    // Equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchoolYearDataModel)) return false;
        SchoolYearDataModel that = (SchoolYearDataModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(description, that.description) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, startDate, endDate);
    }
}
