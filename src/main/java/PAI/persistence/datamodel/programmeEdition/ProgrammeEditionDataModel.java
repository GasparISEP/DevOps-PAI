package PAI.persistence.datamodel.programmeEdition;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "programmeEdition")
public class ProgrammeEditionDataModel {

    @EmbeddedId
    private ProgrammeEditionIdDataModel _programmeEditionID;

    private String _programmeID;
    private String _schoolYearID;

    public ProgrammeEditionDataModel() {}

}
