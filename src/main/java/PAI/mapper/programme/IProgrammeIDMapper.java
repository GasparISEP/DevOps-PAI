package PAI.mapper.programme;

import PAI.VOs.ProgrammeID;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;

public interface IProgrammeIDMapper {

    ProgrammeID toDomain(ProgrammeIDDataModel programmeIDDataModel);

    ProgrammeIDDataModel toData(ProgrammeID programmeID);
}
