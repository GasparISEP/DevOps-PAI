package PAI.mapper.programmeEdition.impl;

import PAI.VOs.*;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.springframework.stereotype.Component;

import java.util.UUID;
//This annotation manage this class lifecycle, it creates an instance of it automatically
@Component
public class ProgrammeEditionIdMapperImpl implements IProgrammeEditionIdMapper {

    @Override
    public ProgrammeEditionID dataModelToDomain (ProgrammeEditionIdDataModel programmeEditionIdDataModel) throws Exception {
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars(programmeEditionIdDataModel.getProgrammeName());
        Acronym programmeAcronym = new Acronym(programmeEditionIdDataModel.getProgrammeAcronym());
        ProgrammeID programmeId = new ProgrammeID(programmeName, programmeAcronym);

        UUID schoolYearUUID = UUID.fromString(programmeEditionIdDataModel.getSchoolYearId());
        SchoolYearID schoolYearId = new SchoolYearID(schoolYearUUID);

        return new ProgrammeEditionID(programmeId, schoolYearId);
    }


}
