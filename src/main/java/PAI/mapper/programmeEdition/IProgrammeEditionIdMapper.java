package PAI.mapper.programmeEdition;

import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;

public interface IProgrammeEditionIdMapper {
    ProgrammeEdition dataModelToDomain (ProgrammeEditionIdDataModel programmeEditionIdDataModel);
    Iterable<ProgrammeEdition> dataModelToDomain (Iterable<ProgrammeEditionIdDataModel> listProgrammeEditionDataModel);
}
