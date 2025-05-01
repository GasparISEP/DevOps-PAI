package PAI.persistence.datamodel.programmeEdition;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class ProgrammeEditionIdDataModel implements Serializable {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "programmeName",
                    column = @Column(name = "edition_programme_name")),
            @AttributeOverride(name = "programmeAcronym",
                    column = @Column(name = "edition_programme_acronym"))
    })
    private ProgrammeIDDataModel programmeIDDataModel;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id",
                    column = @Column(name = "edition_school_year"))
    })
    private SchoolYearIDDataModel schoolYearIDDataModel;

    public ProgrammeEditionIdDataModel() {}

    public ProgrammeEditionIdDataModel(ProgrammeIDDataModel programmeIDDataModel, SchoolYearIDDataModel schoolYearIDDataModel) {
        if(programmeIDDataModel == null) {
            throw new IllegalArgumentException("ProgrammeIDDataModel cannot be null");
        }
        this.programmeIDDataModel = programmeIDDataModel;

        if(schoolYearIDDataModel == null) {
            throw new IllegalArgumentException("SchoolYearIDDataModel cannot be null");
        }
        this.schoolYearIDDataModel = schoolYearIDDataModel;
    }

    //Getters
    public ProgrammeIDDataModel getProgrammeIdDataModel() {
        return this.programmeIDDataModel;
    }
    public SchoolYearIDDataModel getSchoolYearIDDataModel() {
        return this.schoolYearIDDataModel;
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this==objectToCompare) return true;

        if (objectToCompare instanceof ProgrammeEditionIdDataModel oProgrammeEditionIdDataModel) {
            return this.programmeIDDataModel.equals(oProgrammeEditionIdDataModel.programmeIDDataModel) && (this.schoolYearIDDataModel.equals(oProgrammeEditionIdDataModel.schoolYearIDDataModel));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.programmeIDDataModel.hashCode() + this.schoolYearIDDataModel.hashCode();
    }
}
