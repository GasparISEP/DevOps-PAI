package PAI.domain;

import java.time.LocalDate;
import java.util.Objects;

public class ProgrammeEditionEnrolment {
    private Student _student;
    private ProgrammeEdition _programmeEdition;
    private LocalDate _enrolmentDate;

    //constructor
    public ProgrammeEditionEnrolment(Student student, ProgrammeEdition programmeEdition) {
        validateStudent(student);
        validateProgrammeEdition(programmeEdition);
        this._enrolmentDate = LocalDate.now();
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


    public boolean isEnrolmentAssociatedToDepartmentAndSchoolYear(Department department, SchoolYear schoolYear) {
        return _programmeEdition.isEditionAssociatedToDepartmentAndSchoolYear(department, schoolYear);
    }

    public String getStudentUniqueNumber() {
        return _student.getUniqueNumber();
    }

    public boolean hasSameStudent(Student student) {
        return _student.equals(student);
    }

    public boolean hasSameProgrammeEdition(ProgrammeEdition programmeEdition) {
        return _programmeEdition.equals(programmeEdition);
    }

    public ProgrammeEdition findProgrammeEditionInEnrolment() {
        return _programmeEdition;
    }

    public Student findStudentInProgrammeEdition() {
        return _student;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_student, _programmeEdition);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProgrammeEditionEnrolment other = (ProgrammeEditionEnrolment) obj;
        return Objects.equals(this._student, other._student) && Objects.equals(this._programmeEdition, other._programmeEdition);
    }
}
