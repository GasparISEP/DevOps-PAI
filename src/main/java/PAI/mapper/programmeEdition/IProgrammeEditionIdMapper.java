package PAI.mapper.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;

public interface IProgrammeEditionIdMapper {
    ProgrammeEditionID toDomain(ProgrammeEditionIdDataModel programmeEditionIdDataModel) throws Exception;

    ProgrammeEditionIdDataModel toDataModel(ProgrammeEditionID programmeEditionId) throws Exception;
}
