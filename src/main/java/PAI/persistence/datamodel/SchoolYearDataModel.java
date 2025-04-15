package PAI.persistence.datamodel;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "School_Year")
public class SchoolYearDataModel {

    @Id
    private String id;

    private String description;
    private String startDate;
    private String endDate;

    public SchoolYearDataModel() {}

    public SchoolYearDataModel(String id, String description, String startDate, String endDate) {
        this.id = id;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
