package PAI.domain;

import PAI.VOs.*;
import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.ddd.AggregateRoot;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ProgrammeEditionEnrolment implements AggregateRoot<ProgrammeEditionEnrolmentID> {
    private ProgrammeEditionID _programmeEditionId;
    private LocalDate _enrolmentDate;
    private ProgrammeEditionEnrolmentID _programmeEditionEnrolmentID;
    private StudentID _studentId;

    //constructor
    public ProgrammeEditionEnrolment(StudentID studentId, ProgrammeEditionID programmeEditionId) {
        validateStudent(studentId);
        validateProgrammeEdition(programmeEditionId);
        this._enrolmentDate = LocalDate.now();
        this._programmeEditionEnrolmentID = new ProgrammeEditionEnrolmentID(programmeEditionId, studentId);
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

    public boolean isEnrolmentAssociatedToProgrammeAndSchoolYear(SchoolYearID schoolYear, List<ProgrammeID> programmeIDS) {
        for (ProgrammeID programmeID : programmeIDS) {
            if (_programmeEditionId.isSameProgrammeEdition(programmeID, schoolYear)) {
                return true;
            }
        }
        return false;
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammeEditionEnrolment that = (ProgrammeEditionEnrolment) o;
        return Objects.equals(_programmeEditionEnrolmentID, that._programmeEditionEnrolmentID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_programmeEditionId, _studentId);
    }

    @Override
    public ProgrammeEditionEnrolmentID identity() {
        return _programmeEditionEnrolmentID;
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
