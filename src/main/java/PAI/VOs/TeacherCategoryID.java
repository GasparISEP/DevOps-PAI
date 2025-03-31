package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;
import java.util.UUID;

/**
 * Value Object that uniquely identifies a TeacherCategory.
 * This ID wraps a UUID and ensures immutability and equality based solely on identity.
 */
public class TeacherCategoryID implements DomainId {

    private final UUID id;

    /**
     * Constructs a new TeacherCategoryID with a randomly generated UUID.
     * This constructor should be used when creating a new aggregate instance.
     */
    public TeacherCategoryID() {
        this.id = UUID.randomUUID();
    }

    /**
     * Constructs a TeacherCategoryID from an existing UUID.
     * This is useful for reconstructing objects from persistent storage.
     *
     * @param id the UUID to wrap
     * @throws IllegalArgumentException if the provided UUID is null
     */
    public TeacherCategoryID(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("UUID cannot be null.");
        }
        this.id = id;
    }

    /**
     * Retrieves the underlying UUID value of this ID.
     *
     * @return the UUID
     */
    public UUID getValue() {
        return id;
    }
    /**
     * Equality is based on the UUID value, ensuring consistent identity semantics.
     *
     * @param obj the object to compare
     * @return true if both IDs are equal by UUID value
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TeacherCategoryID other)) return false;
        return id.equals(other.id);
    }
    /**
     * Returns the hash code based on the UUID.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    /**
     * String representation of the ID (UUID format).
     *
     * @return the string version of the UUID
     */
    @Override
    public String toString() {
        return id.toString();
    }
}