package PAI.mapper.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;

public interface IProgrammeEditionIdMapper {
    ProgrammeEditionID dataModelToDomain (ProgrammeEditionIdDataModel programmeEditionIdDataModel) throws Exception;
}
