package PAI.VOs;

import java.util.UUID;

public class TeacherID {

    private final UUID id;

    private TeacherID(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }
        this.id = id;
    }

    public UUID getID() {
        return id;
    }

    public static TeacherID createNew() {
        return new TeacherID(UUID.randomUUID());
    }
}
