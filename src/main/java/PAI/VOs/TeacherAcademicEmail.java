package PAI.VOs;

import java.util.Objects;

public class TeacherAcademicEmail {

    private static final String _emailDomain = "isep.ipp.pt";
    private final String _teacherAcademicEmail;

    public TeacherAcademicEmail (TeacherAcronym teacherAcronym) {

        if (teacherAcronym == null) {
            throw new IllegalArgumentException("Teacher's acronym cannot be null!");
        }

        _teacherAcademicEmail = teacherAcronym.getAcronym() + "@" + _emailDomain;
    }

    public String getEmailDomain() { return _emailDomain; }

    public String getTeacherAcademicEmail() {
            return _teacherAcademicEmail;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TeacherAcademicEmail that = (TeacherAcademicEmail) o;
            return _teacherAcademicEmail.equals(that._teacherAcademicEmail);
        }

        @Override
        public int hashCode() {
            return Objects.hash(_teacherAcademicEmail);
        }

        @Override
        public String toString() {
            return _teacherAcademicEmail;
        }
    }

