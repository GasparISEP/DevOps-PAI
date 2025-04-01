package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;

public class ProgrammeEditionEnrolmentID implements DomainId {

    private final ProgrammeEditionID _programmeEditionId;
    private final StudentID _studentiD;
    private final SchoolYearID _schoolYearId;

    public ProgrammeEditionEnrolmentID(ProgrammeEditionID programmeEditionId, StudentID studentiD, SchoolYearID schoolYearId) {

        validateProgrammeEditionID(programmeEditionId);
        validateStudentID(studentiD);
        validateSchoolYearID(schoolYearId);

        this._programmeEditionId = programmeEditionId;
        this._studentiD = studentiD;
        this._schoolYearId = schoolYearId;
    }

    private void validateProgrammeEditionID (ProgrammeEditionID programmeEditioniD) {
        if (programmeEditioniD == null)
            throw new IllegalArgumentException ("ProgrammeEdition must be valid");
    }

    private void validateStudentID (StudentID studentId) {
        if (studentId == null)
            throw new IllegalArgumentException ("Student must be valid");
    }

    private void validateSchoolYearID (SchoolYearID schoolYearId) {
        if (schoolYearId == null)
            throw new IllegalArgumentException ("SchoolYear must be valid");
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (objectToCompare == null || getClass() != objectToCompare.getClass()) return false;
        ProgrammeEditionEnrolmentID that = (ProgrammeEditionEnrolmentID) objectToCompare;
        return Objects.equals(_programmeEditionId, that._programmeEditionId) &&
                Objects.equals(_studentiD, that._studentiD) &&
                Objects.equals(_schoolYearId, that._schoolYearId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_programmeEditionId, _studentiD, _schoolYearId);
    }

    @Override
    public String toString() {
        return "ProgrammeEditionEnrolmentID{" +
                "_programmeEditionId=" + _programmeEditionId +
                ", _studentiD=" + _studentiD +
                ", _schoolYearId=" + _schoolYearId +
                '}';
    }
}
