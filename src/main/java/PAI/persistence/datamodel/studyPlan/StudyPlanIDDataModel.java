package PAI.persistence.datamodel.studyPlan;

import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class StudyPlanIDDataModel implements Serializable {

    @Embedded
    private ProgrammeIDDataModel programmeIDDataModel;
    private LocalDate implementationDate;

    protected StudyPlanIDDataModel() {}

    public StudyPlanIDDataModel(ProgrammeIDDataModel programmeIDDataModel, LocalDate implementationDate) {
        this.programmeIDDataModel = programmeIDDataModel;
        this.implementationDate = implementationDate;
    }

    public ProgrammeIDDataModel getProgrammeID() {
        return programmeIDDataModel;
    }

    public LocalDate getImplementationDate() {
        return implementationDate;
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (!(objectToCompare instanceof StudyPlanIDDataModel)) return false;
        StudyPlanIDDataModel studyPlanIDDataModel = (StudyPlanIDDataModel) objectToCompare;
        return programmeIDDataModel.equals(studyPlanIDDataModel.programmeIDDataModel) &&
                implementationDate.equals(studyPlanIDDataModel.implementationDate);
    }

    @Override
    public int hashCode() {
        return programmeIDDataModel.hashCode() + implementationDate.hashCode();
    }
}