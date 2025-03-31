package PAI.VOs;

import PAI.ddd.DomainId;
import PAI.ddd.ValueObject;

import java.util.Objects;

public class StudentAcademicEmail implements ValueObject {

    private static final String emailDomain = "isep.ipp.pt";
    private final String _studentEmail;

    public StudentAcademicEmail(StudentID studentID) {

        if (studentID == null) {
            throw new IllegalArgumentException("Student's unique number cannot be null!");
        }

        _studentEmail = studentID.getUniqueNumber() + "@" + emailDomain;
    }

    public String getStudentEmail () {
        return _studentEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentAcademicEmail that = (StudentAcademicEmail) o;
        return _studentEmail.equals(that._studentEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_studentEmail);
    }

    @Override
    public String toString() {
        return _studentEmail;
    }
}