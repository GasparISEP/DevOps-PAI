package PAI.mapper.programmeEdition;

import PAI.VOs.ProgrammeEditionGeneratedID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.mapper.schoolYear.ISchoolYearIDMapper;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionGeneratedIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static PAI.utils.ValidationUtils.validateNotNull;

@Component
public class ProgrammeEditionMapperImpl implements  IProgrammeEditionMapper{

    private final IProgrammeEditionFactory programmeEditionFactory;
    private final IProgrammeEditionIdMapper programmeEditionIDMapper;
    private final IProgrammeIDMapper programmeIDMapper;
    private final ISchoolYearIDMapper schoolYearIDMapper;
    private final IProgrammeEditionGeneratedIDMapper programmeEditionGeneratedIDMapper;

    public ProgrammeEditionMapperImpl(IProgrammeEditionFactory programmeEditionFactory, IProgrammeEditionIdMapper programmeEditionIDMapper,
                                      IProgrammeIDMapper programmeIDMapper, ISchoolYearIDMapper schoolYearIDMapper, IProgrammeEditionGeneratedIDMapper programmeEditionGeneratedIDMapper){

        this.programmeEditionFactory = validateNotNull(programmeEditionFactory, "ProgrammeEditionFactory");
        this.programmeEditionIDMapper = validateNotNull(programmeEditionIDMapper, "ProgrammeEditionIDMapper");
        this.programmeIDMapper = validateNotNull(programmeIDMapper, "ProgrammeIDMapper");
        this.schoolYearIDMapper = validateNotNull(schoolYearIDMapper, "SchoolYearIDMapper");
        this.programmeEditionGeneratedIDMapper = validateNotNull(programmeEditionGeneratedIDMapper, "ProgrammeEditionGeneratedIDMapper");
    }

    @Override
    public Optional<ProgrammeEditionDataModel> toDataModel(ProgrammeEdition programmeEdition) {
        if (programmeEdition == null) {
            return Optional.empty();
        }
        try {
            ProgrammeEditionIdDataModel programmeEditionIdDataModel = programmeEditionIDMapper.toDataModel(programmeEdition.identity());
            ProgrammeEditionGeneratedID programmeEditionGeneratedID = programmeEdition.getProgrammeEditionGeneratedGID();
            ProgrammeEditionGeneratedIDDataModel programmeEditionGeneratedIDDataModel = programmeEditionGeneratedIDMapper.toDataModel(programmeEditionGeneratedID);

            ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeEditionGeneratedIDDataModel);
            return Optional.of(programmeEditionDataModel);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProgrammeEdition> toDomain(ProgrammeEditionDataModel programmeEditionDataModel){
        if(programmeEditionDataModel == null) {
            return Optional.empty();
        }

        if(programmeEditionDataModel.getProgrammeEditionGeneratedIDDataModel() == null) {
            return Optional.empty();
        }

        try {
            ProgrammeEditionID programmeEditionID = programmeEditionIDMapper.toDomain(programmeEditionDataModel.getProgrammeEditionIDDataModel());
            ProgrammeID programmeID = programmeIDMapper.toDomain(programmeEditionDataModel.getProgrammeEditionIDDataModel().getProgrammeIdDataModel());
            SchoolYearID schoolYearID = schoolYearIDMapper.toDomain(programmeEditionDataModel.getProgrammeEditionIDDataModel().getSchoolYearIDDataModel());
            ProgrammeEditionGeneratedID programmeEditionIDGenerated = new ProgrammeEditionGeneratedID(programmeEditionDataModel
                    .getProgrammeEditionGeneratedIDDataModel().getGeneratedId());

            ProgrammeEdition programmeEdition = programmeEditionFactory.createProgrammeEdition(programmeEditionID, programmeID, schoolYearID,programmeEditionIDGenerated);
            return Optional.of(programmeEdition);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}