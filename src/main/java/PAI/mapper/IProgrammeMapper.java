package PAI.mapper;

import PAI.domain.programme.Programme;
import PAI.persistence.datamodel.ProgrammeDataModel;

public interface IProgrammeMapper {

    ProgrammeDataModel toData(Programme programme);

    Programme toDomain(ProgrammeDataModel programmeDataModel);
}
