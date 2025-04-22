package PAI.mapper.programmeEdition;

import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionDataModel;

import java.util.Optional;

public interface IProgrammeEditionMapper {

    Optional<ProgrammeEditionDataModel> toDataModel(ProgrammeEdition programmeEdition) throws Exception;

    Optional<ProgrammeEdition> toDomain(ProgrammeEditionDataModel programmeEditionDataModel) throws Exception;
}
