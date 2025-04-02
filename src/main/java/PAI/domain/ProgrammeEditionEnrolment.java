package PAI.domain;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.SchoolYearID;
import PAI.VOs.StudentID;
import PAI.ddd.AggregateRoot;

import java.time.LocalDate;
import java.util.Objects;

public class ProgrammeEditionEnrolment implements AggregateRoot<ProgrammeEditionEnrolmentID> {
    private Student _student;
    private ProgrammeEditionID _programmeEditionId;
    private ProgrammeEdition _programmeEdition;
    private LocalDate _enrolmentDate;
    private ProgrammeEditionEnrolmentID _enrolmentId;
    private StudentID _studentId;
    //constructor
    public ProgrammeEditionEnrolment(StudentID studentId, ProgrammeEditionID programmeEditionId) {
        validateStudent(studentId);
        validateProgrammeEdition(programmeEditionId);
        this._enrolmentDate = LocalDate.now();
    }

    private void validateStudent(StudentID studentId) {
        if (studentId == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        this._studentId = studentId;
    }

    private void validateProgrammeEdition(ProgrammeEditionID programmeEditionId) {
        if (programmeEditionId == null) {
            throw new IllegalArgumentException("ProgrammeEdition cannot be null");
        }
        this._programmeEditionId = programmeEditionId;
    }

    public boolean isEnrolmentAssociatedToDepartmentAndSchoolYear(Department department, SchoolYear schoolYear) {
        return _programmeEdition.isEditionAssociatedToDepartmentAndSchoolYear(department, schoolYear);
    }

    public StudentID getStudentID() {
        return _studentId;
    }

    public boolean hasSameStudent(StudentID studentId) {
        return _studentId.equals(studentId);
    }

    public boolean hasSameProgrammeEdition(ProgrammeEditionID programmeEditionId) {
        return _programmeEditionId.equals(programmeEditionId);
    }

    public ProgrammeEditionID findProgrammeEditionInEnrolment() {
        return _programmeEditionId;
    }

    public StudentID findStudentInProgrammeEdition() {
        return _studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_studentId, _programmeEditionId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProgrammeEditionEnrolment other = (ProgrammeEditionEnrolment) obj;
        return Objects.equals(this._studentId, other._studentId) && Objects.equals(this._programmeEditionId, other._programmeEditionId);
    }

    @Override
    public ProgrammeEditionEnrolmentID identity() {
        return _enrolmentId;
    }

    @Override
    public boolean sameAs(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        ProgrammeEditionEnrolment other = (ProgrammeEditionEnrolment) object;
        return this.identity().equals(other.identity());
    }
}
