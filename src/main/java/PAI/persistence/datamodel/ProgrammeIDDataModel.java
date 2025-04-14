package PAI.persistence.datamodel;

import PAI.VOs.ProgrammeID;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProgrammeIDDataModel {

    private String _name;
    private String _acronym;


    public ProgrammeIDDataModel() {
    }


    public ProgrammeIDDataModel(ProgrammeID programmeID) {
        _name = programmeID.getName().toString();
        _acronym = programmeID.getAcronym().toString();
    }

}
