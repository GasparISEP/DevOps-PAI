package PAI.mapper;

import PAI.VOs.ProgrammeID;
import PAI.persistence.datamodel.ProgrammeIDDataModel;

public interface IProgrammeIDMapper {

    ProgrammeID toDomain(ProgrammeIDDataModel programmeIDDataModel);

    ProgrammeIDDataModel toData(ProgrammeID programmeID);
}
