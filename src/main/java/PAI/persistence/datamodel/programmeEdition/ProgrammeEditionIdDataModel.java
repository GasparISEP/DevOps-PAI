package PAI.persistence.datamodel.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.domain.SchoolYear;
import PAI.domain.programme.Programme;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProgrammeEditionIdDataModel {
    private String _programmeName;
    private String _programmeAcronym;
    private String _schoolYearId;

    public ProgrammeEditionIdDataModel() {}

    public ProgrammeEditionIdDataModel(Programme programme, SchoolYear schoolYear) {
        this._programmeName = programme.getProgrammeName().toString();
        this._programmeAcronym = programme.getAcronym().toString();
        this._schoolYearId = schoolYear.identity().toString();
    }

    public ProgrammeEditionIdDataModel(String programmeName, String programmeAcronym, String schoolYearId) {
        this._programmeName = programmeName;
        this._programmeAcronym = programmeAcronym;
        this._schoolYearId = schoolYearId;
    }


    public String getProgrammeName() {
        return this._programmeName;
    }
    public String getProgrammeAcronym() {
        return this._programmeAcronym;
    }

    public String getSchoolYearId() {
        return this._schoolYearId;
    }
}
