package PAI.domain;

import java.time.LocalDate;
import java.util.Objects;

public class ProgrammeEditionEnrollment {
    private Student _student;
    private ProgrammeEdition _programmeEdition;
    private LocalDate _enrollmentDate;

    //constructor
    public ProgrammeEditionEnrollment(Student student, ProgrammeEdition programmeEdition, LocalDate enrollmentDate) {
        validateStudent(student);
        validateProgrammeEdition(programmeEdition);
        validateEnrollmentDate(enrollmentDate);
    }

    private void validateStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        this._student = student;
    }

    private void validateProgrammeEdition(ProgrammeEdition programmeEdition) {
        if (programmeEdition == null) {
            throw new IllegalArgumentException("ProgrammeEdition cannot be null");
        }
        this._programmeEdition = programmeEdition;
    }

    private void validateEnrollmentDate(LocalDate enrollmentDate) throws IllegalArgumentException {
        if (enrollmentDate == null) {
            throw new IllegalArgumentException("Enrollment date cannot be null!");
        }
        if (!enrollmentDate.equals(LocalDate.now())) {
            throw new IllegalArgumentException("Enrollment date must be the current day!");
        }
        this._enrollmentDate = enrollmentDate;
    }

    public boolean isEnrollmentAssociatedToDepartmentAndSchoolYear(Department department, SchoolYear schoolYear) {
        return _programmeEdition.isEditionAssociatedToDepartmentAndSchoolYear(department, schoolYear);
    }

    public int getStudentUniqueNumber() {
        return _student.getUniqueNumber();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProgrammeEditionEnrollment other = (ProgrammeEditionEnrollment) obj;
        return Objects.equals(this._student, other._student) && Objects.equals(this._programmeEdition, other._programmeEdition)
                && Objects.equals(this._enrollmentDate, other._enrollmentDate);
    }

    public ProgrammeEdition findProgrammeEditionInEnrollment() {
        return _programmeEdition;
    }

    public Student findStudentInProgrammeEdition() {
        return _student;
    }
}
