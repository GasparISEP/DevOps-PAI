package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;

public class StudentGradeID implements DomainId {
    private  final StudentID _studentID;
    private final CourseEditionID _courseEdition;

    public StudentGradeID(StudentID studentID, CourseEditionID courseEditionID) {
        this._studentID = studentID;
        this._courseEdition = courseEditionID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentGradeID that)) return false;
        return Objects.equals(_studentID, that._studentID) && Objects.equals(_courseEdition, that._courseEdition);
    }

    @Override
    public String toString() {
        return "StudentGradeID{" +
                "_studentID=" + _studentID +
                ", _courseEdition=" + _courseEdition +
                '}';
    }
}