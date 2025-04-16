package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProgrammeEditionIdDataModel {
    private String _programmeName;
    private String _programmeAcronym;
    private String _schoolYearIDd;

    public ProgrammeEditionIdDataModel() {}
}
