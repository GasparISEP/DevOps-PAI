package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;
import java.util.UUID;

/**
 * Value Object representing the unique ID for a TeacherCategory.
 */
public class TeacherCategoryID implements DomainId {

    private final UUID id;

    // Auto-generate a new ID
    public TeacherCategoryID() {
        this.id = UUID.randomUUID();
    }

    // Allow passing an existing UUID (for reconstruction from repo, etc.)
    public TeacherCategoryID(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("UUID cannot be null.");
        }
        this.id = id;
    }

    public UUID getValue() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TeacherCategoryID other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id.toString();
    }
}