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
    private Date _enrolmentDate;
    private ProgrammeEditionEnrolmentID _programmeEditionEnrolmentID;
    private StudentID _studentId;
    private EnrolmentStatus _isActive;

    //constructor
    public ProgrammeEditionEnrolment(StudentID studentId, ProgrammeEditionID programmeEditionId) {
        validateStudent(studentId);
        validateProgrammeEdition(programmeEditionId);
        this._enrolmentDate = new Date(LocalDate.now());
        this._programmeEditionEnrolmentID = new ProgrammeEditionEnrolmentID(programmeEditionId, studentId);
        this._isActive = new EnrolmentStatus(true);
    }

    public ProgrammeEditionEnrolment(StudentID studentId, ProgrammeEditionID programmeEditionId, Date enrolmentDate, EnrolmentStatus isActive) {
        validateStudent(studentId);
        validateProgrammeEdition(programmeEditionId);
        this._enrolmentDate = ((enrolmentDate != null) ? enrolmentDate : Date.now());
        this._programmeEditionEnrolmentID = new ProgrammeEditionEnrolmentID(programmeEditionId, studentId);
        this._isActive = isActive;
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
        if(programmeIDS != null && schoolYear != null) {
        for (ProgrammeID programmeID : programmeIDS) {
            if (_programmeEditionId.isSameProgrammeEdition(programmeID, schoolYear)) {
                return true;
            }
        }}
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

    public Date getEnrolmentDate() {
        return _enrolmentDate;
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

    public boolean isEnrolmentActive() {
        return _isActive.isEnrolmentActive();
    }

    public void deactivateEnrolment() {
        this._isActive = new EnrolmentStatus(false);
    }
}
