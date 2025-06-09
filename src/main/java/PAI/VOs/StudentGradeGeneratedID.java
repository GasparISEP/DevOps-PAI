package PAI.VOs;

import java.util.Objects;
import java.util.UUID;

import PAI.ddd.ValueObject;

public class StudentGradeGeneratedID implements ValueObject {

    private final UUID studentGradeGeneratedID;

    public StudentGradeGeneratedID(UUID studentGradeGeneratedID) {
        if (studentGradeGeneratedID == null){
            throw new IllegalArgumentException("Student Grade Generated ID cannot be null");
        }
        this.studentGradeGeneratedID = studentGradeGeneratedID;
    }

    public StudentGradeGeneratedID() {
        this.studentGradeGeneratedID=UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGradeGeneratedID that = (StudentGradeGeneratedID) o;
        return studentGradeGeneratedID.equals(that.studentGradeGeneratedID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentGradeGeneratedID);
    }

    @Override
    public String toString() {
        return studentGradeGeneratedID.toString();
    }

    public UUID getStudentGradeGeneratedID() {
        return studentGradeGeneratedID;
    }
}
