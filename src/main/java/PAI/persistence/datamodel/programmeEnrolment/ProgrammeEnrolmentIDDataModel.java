package PAI.persistence.datamodel.programmeEnrolment;

import PAI.persistence.datamodel.Student.StudentIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProgrammeEnrolmentIDDataModel implements Serializable {

    private StudentIDDataModel peStudentID;
    private ProgrammeIDDataModel peProgrammeID;

    public ProgrammeEnrolmentIDDataModel () {}

    public ProgrammeEnrolmentIDDataModel (StudentIDDataModel studentID, ProgrammeIDDataModel programmeID) {
        peStudentID = studentID;
        peProgrammeID = programmeID;
    }

    public StudentIDDataModel getStudentID () {
        return peStudentID;
    }

    public ProgrammeIDDataModel getProgrammeID () {
        return peProgrammeID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof ProgrammeEnrolmentIDDataModel other)) return false;

        return Objects.equals(this.peStudentID, other.peStudentID) &&
                Objects.equals(this.peProgrammeID, other.peProgrammeID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peStudentID, peProgrammeID);
    }
}
