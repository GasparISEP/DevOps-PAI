package PAI.persistence.datamodel.studyPlan;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "Study_Plan")
public class StudyPlanDataModel {

    @EmbeddedId
    private StudyPlanIDDataModel _studyPlanIDDataModel;

    protected StudyPlanDataModel() {}

    public StudyPlanDataModel (StudyPlanIDDataModel studyPlanIDDataModel, LocalDate implementationDate) {
        this._studyPlanIDDataModel = studyPlanIDDataModel;
    }
}