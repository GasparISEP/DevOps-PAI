package PAI.domain;

import java.time.LocalDate;
import java.util.Objects;

public class ProgrammeEditionEnrollment {
    private Student _student;
    private ProgrammeEdition _programmeEdition;
    private LocalDate _enrollmentDate;

    //constructor
    public ProgrammeEditionEnrollment(Student student, ProgrammeEdition programmeEdition) {
        validateStudent(student);
        validateProgrammeEdition(programmeEdition);
        this._enrollmentDate = LocalDate.now();
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
        return Objects.equals(this._student, other._student) && Objects.equals(this._programmeEdition, other._programmeEdition);
    }

    public ProgrammeEdition findProgrammeEditionInEnrollment() {
        return _programmeEdition;
    }

    public Student findStudentInProgrammeEdition() {
        return _student;
    }
}
