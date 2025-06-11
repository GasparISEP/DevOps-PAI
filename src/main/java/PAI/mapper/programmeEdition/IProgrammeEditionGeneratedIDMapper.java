package PAI.mapper.programmeEdition;

import PAI.VOs.ProgrammeEditionGeneratedID;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionGeneratedIDDataModel;

public interface IProgrammeEditionGeneratedIDMapper {

    ProgrammeEditionGeneratedID toDomain(ProgrammeEditionGeneratedIDDataModel dataModel) throws Exception;

    ProgrammeEditionGeneratedIDDataModel toDataModel(ProgrammeEditionGeneratedID programmeEditionGeneratedID) throws Exception;
}
