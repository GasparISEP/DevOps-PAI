package PAI.VOs;

import java.util.Objects;
import java.util.UUID;

import PAI.ddd.ValueObject;

public class CourseGeneratedID implements ValueObject {

    private final UUID courseGeneratedID;

    public CourseGeneratedID(UUID courseGeneratedID) {
        if (courseGeneratedID == null){
            throw new IllegalArgumentException("Course Generated ID cannot be null");
        }
        this.courseGeneratedID = courseGeneratedID;
    }
    
    public CourseGeneratedID() {
        this.courseGeneratedID = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseGeneratedID that = (CourseGeneratedID) o;
        return courseGeneratedID.equals(that.courseGeneratedID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseGeneratedID);
    }

    @Override
    public String toString() {
        return courseGeneratedID.toString();
    }

    public UUID getCourseGeneratedID() {
        return courseGeneratedID;
    }
    
}