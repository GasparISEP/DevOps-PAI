package PAI.persistence.datamodel;

import PAI.domain.SchoolYear;
import PAI.domain.programme.Programme;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProgrammeEditionIdDataModel {
    private String _programmeName;
    private String _programmeAcronym;
    private String _schoolYearIDd;

    public ProgrammeEditionIdDataModel() {}

    public ProgrammeEditionIdDataModel(Programme programme, SchoolYear schoolYear) {
        this._programmeName = programme.getProgrammeName().toString();
        this._programmeAcronym = programme.getAcronym().toString();
        this._schoolYearIDd = schoolYear.identity().toString();
    }
}
