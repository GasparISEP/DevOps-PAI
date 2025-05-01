package PAI.persistence.datamodel;

import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProgrammeEditionEnrolmentIDDataModel implements Serializable {

    @Embedded
    private StudentIDDataModel studentIdDataModel;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "_programmeEditionIdDataModel._programmeIDDataModel.programmeAcronym",
                    column = @Column(name = "pEE_programme_acronym")
            ),
            @AttributeOverride(
                    name = "_programmeEditionIdDataModel._programmeIDDataModel.programmeName",
                    column = @Column(name = "pEE_programme_name")
            )
    })
    private ProgrammeEditionIdDataModel programmeEditionIdDataModel;

    public ProgrammeEditionEnrolmentIDDataModel() {
    }

    public ProgrammeEditionEnrolmentIDDataModel(StudentIDDataModel studentIdDataModel, ProgrammeEditionIdDataModel programmeEditionIdDataModel) {
        if (studentIdDataModel == null) {
            throw new IllegalArgumentException("studentId cannot be null or blank");
        }
        if (programmeEditionIdDataModel == null) {
            throw new IllegalArgumentException("programmeEditionId cannot be null or blank");
        }
        this.studentIdDataModel = studentIdDataModel;
        this.programmeEditionIdDataModel = programmeEditionIdDataModel;
    }

    public StudentIDDataModel getStudentIdDataModel() {
        return studentIdDataModel;
    }

    public ProgrammeEditionIdDataModel getProgrammeEditionIdDataModel() {
        return programmeEditionIdDataModel;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammeEditionEnrolmentIDDataModel that = (ProgrammeEditionEnrolmentIDDataModel) o;
        return Objects.equals(studentIdDataModel, that.studentIdDataModel) && Objects.equals(programmeEditionIdDataModel, that.programmeEditionIdDataModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentIdDataModel, programmeEditionIdDataModel);
    }
}


