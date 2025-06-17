package PAI.persistence.datamodel.courseEdition;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CourseEditionGeneratedIDDataModel implements Serializable {

    @Column(name = "course_edition_generated_id",nullable = false,unique = true)
    private String id;

    public CourseEditionGeneratedIDDataModel() {
    }

    public CourseEditionGeneratedIDDataModel(String id) {
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
        if (!(o instanceof CourseEditionGeneratedIDDataModel)) return false;
       CourseEditionGeneratedIDDataModel that = (CourseEditionGeneratedIDDataModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}