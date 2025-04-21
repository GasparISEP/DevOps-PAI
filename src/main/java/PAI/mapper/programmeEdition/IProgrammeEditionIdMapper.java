package PAI.mapper.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.domain.SchoolYear;
import PAI.domain.programme.Programme;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;

public interface IProgrammeEditionIdMapper {
    ProgrammeEditionID dataModelToDomain (ProgrammeEditionIdDataModel programmeEditionIdDataModel) throws Exception;

    ProgrammeEditionIdDataModel domainToDataModel (Programme programme, SchoolYear schoolYear) throws Exception;

    ProgrammeEditionIdDataModel domainToDataModel (ProgrammeEditionID programmeEditionId) throws Exception;
}
