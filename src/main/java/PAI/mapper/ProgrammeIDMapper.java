package PAI.mapper;

import PAI.IProgrammeIDMapper;
import PAI.VOs.Acronym;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.ProgrammeID;
import PAI.persistence.datamodel.ProgrammeIDDataModel;

public class ProgrammeIDMapper implements IProgrammeIDMapper {

    public ProgrammeID toDomain(ProgrammeIDDataModel programmeIDDataModel) {

        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars(programmeIDDataModel.getName());
        Acronym acronym = new Acronym(programmeIDDataModel.getAcronym());
        return new ProgrammeID(name,acronym);
    }

    public ProgrammeIDDataModel toData(ProgrammeID programmeID) {

        String name = programmeID.getName().getnameWithNumbersAndSpecialChars();
        String acronym = programmeID.getAcronym().getAcronym();
        return new ProgrammeIDDataModel(name, acronym);
    }
}