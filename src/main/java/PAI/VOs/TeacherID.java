package PAI.VOs;

import PAI.ddd.DomainId;
import PAI.ddd.ValueObject;

import java.util.UUID;

public class TeacherID implements DomainId {

    private final UUID id;

    private TeacherID(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }
        this.id = id;
    }

    public UUID identity() {
        return this.id;
    }

    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        TeacherID teacherID = (TeacherID) other;
        return this.id.equals(teacherID.id);
    }

    public static TeacherID createNew() {
        return new TeacherID(UUID.randomUUID());
    }
}
