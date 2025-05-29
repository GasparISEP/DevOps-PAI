package PAI.domain.teacherCategory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.ddd.AggregateRoot;
import PAI.utils.ValidationUtils;

import java.util.Objects;
import java.util.UUID;

public class TeacherCategory implements AggregateRoot<TeacherCategoryID> {

    private final TeacherCategoryID id;
    private final Name name;

    public TeacherCategory(TeacherCategoryID id, Name name) {

        this.id = ValidationUtils.validateNotNull(id, "Teacher Category ID");
        this.name = ValidationUtils.validateNotNull(name, "Teacher Category Name");
    }

    public Name getName() {
        return name;
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
        if (!(object instanceof TeacherCategory other)) return false;
        return id.equals(other.id) && name.equals(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeacherCategory other)) return false;
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
