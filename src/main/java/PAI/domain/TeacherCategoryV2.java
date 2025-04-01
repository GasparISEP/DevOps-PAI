package PAI.domain;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.ddd.AggregateRoot;

import java.util.Objects;
import java.util.UUID;

public class TeacherCategoryV2 implements AggregateRoot<TeacherCategoryID> {

    private final TeacherCategoryID id;
    private final Name name;

    public TeacherCategoryV2(TeacherCategoryID id, Name name) {
        if (id == null || name == null) {
            throw new IllegalArgumentException("Id and Name cannot be null.");
        }
        this.id = id;
        this.name = name;
    }

    public TeacherCategoryID getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public UUID getIdValue() {
        return id.getValue();
    }

    public String getNameValue() {
        return name.getName();
    }

    // From DomainEntity interface
    @Override
    public TeacherCategoryID identity() {
        return id;
    }

    // From DomainEntity interface
    @Override
    public boolean sameAs(Object object) {
        if (this == object) return true;
        if (!(object instanceof TeacherCategoryV2 other)) return false;
        return id.equals(other.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeacherCategoryV2 other)) return false;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TeacherCategoryV2{id=" + id + ", name=" + name + "}";
    }

}
