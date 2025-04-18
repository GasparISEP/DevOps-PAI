package PAI.persistence.datamodel.studyPlan;

import PAI.persistence.datamodel.ProgrammeIDDataModel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class StudyPlanIDDataModel implements Serializable {

    @Embedded
    private ProgrammeIDDataModel _programmeIDDataModel;
    private LocalDate _implementationDate;

    protected StudyPlanIDDataModel() {}

    public StudyPlanIDDataModel(ProgrammeIDDataModel programmeIDDataModel, LocalDate implementationDate) {
        this._programmeIDDataModel = programmeIDDataModel;
        this._implementationDate = implementationDate;
    }

    public ProgrammeIDDataModel getProgrammeID() {
        return _programmeIDDataModel;
    }

    public LocalDate getImplementationDate() {
        return _implementationDate;
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (!(objectToCompare instanceof StudyPlanIDDataModel)) return false;
        StudyPlanIDDataModel studyPlanIDDataModel = (StudyPlanIDDataModel) objectToCompare;
        return _programmeIDDataModel.equals(studyPlanIDDataModel._programmeIDDataModel) &&
                _implementationDate.equals(studyPlanIDDataModel._implementationDate);
    }

    @Override
    public int hashCode() {
        return _programmeIDDataModel.hashCode() + _implementationDate.hashCode();
    }
}