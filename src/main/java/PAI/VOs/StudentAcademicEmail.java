package PAI.VOs;

import PAI.ddd.DomainId;
import PAI.ddd.ValueObject;

import java.util.Objects;

public class StudentAcademicEmail implements ValueObject {

    private static final String emailDomain = "isep.ipp.pt";
    private final String _studentEmail;

    public StudentAcademicEmail(int uniqueNumber) {

        if (uniqueNumber <= 1000000 || uniqueNumber >= 2000000)
            throw new IllegalArgumentException("Invalid uniqueNumber!");

        _studentEmail = uniqueNumber + "@" + emailDomain;
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