package PAI.mapper.programme;

import PAI.VOs.Acronym;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.ProgrammeID;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import org.springframework.stereotype.Component;

@Component
public class ProgrammeIDMapperImpl implements IProgrammeIDMapper {

    public ProgrammeID toDomain(ProgrammeIDDataModel programmeIDDataModel) {

        Acronym acronym = new Acronym(programmeIDDataModel.getAcronym());
        return new ProgrammeID(acronym);
    }

    public ProgrammeIDDataModel toData(ProgrammeID programmeID) {

        String acronym = programmeID.getAcronym().getAcronym();
        return new ProgrammeIDDataModel(acronym);
    }
}