package PAI.mapper.programme;

import PAI.domain.programme.Programme;
import PAI.persistence.datamodel.programme.ProgrammeDataModel;

public interface IProgrammeMapper {

    ProgrammeDataModel toData(Programme programme);

    Programme toDomain(ProgrammeDataModel programmeDataModel);
}
