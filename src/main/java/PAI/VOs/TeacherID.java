package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.UUID;

public class TeacherID implements DomainId {

    private final UUID id;

    private TeacherID(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Teacher ID cannot be null");
        }
        this.id = id;
    }

    public UUID getIDValue() {
        return this.id;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        TeacherID teacherID = (TeacherID) other;
        return this.id.equals(teacherID.id);
    }

    public static TeacherID createNew() {
        return new TeacherID(UUID.randomUUID());
    }
}
