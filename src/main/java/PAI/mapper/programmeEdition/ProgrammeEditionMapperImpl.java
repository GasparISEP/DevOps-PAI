package PAI.mapper.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.mapper.schoolYearID.ISchoolYearIDMapper;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProgrammeEditionMapperImpl implements  IProgrammeEditionMapper{

    private final IProgrammeEditionFactory programmeEditionFactory;
    private final IProgrammeEditionIdMapper programmeEditionIDMapper;
    private final IProgrammeIDMapper programmeIDMapper;
    private final ISchoolYearIDMapper schoolYearIDMapper;

    public ProgrammeEditionMapperImpl(IProgrammeEditionFactory programmeEditionFactory, IProgrammeEditionIdMapper programmeEditionIDMapper,
                                      IProgrammeIDMapper programmeIDMapper, ISchoolYearIDMapper schoolYearIDMapper){
        if(programmeEditionFactory == null){
            throw new IllegalArgumentException("ProgrammeEdition Factory cannot be null");
        }
        if(programmeEditionIDMapper == null){
            throw new IllegalArgumentException("ProgrammeEdition Factory cannot be null");
        }
        if(programmeIDMapper == null){
            throw new IllegalArgumentException("ProgrammeEdition Factory cannot be null");
        }
        if(schoolYearIDMapper == null){
            throw new IllegalArgumentException("ProgrammeEdition Factory cannot be null");
        }
        this.programmeEditionFactory = programmeEditionFactory;
        this.programmeEditionIDMapper = programmeEditionIDMapper;
        this.programmeIDMapper = programmeIDMapper;
        this.schoolYearIDMapper = schoolYearIDMapper;
    }

    @Override
    public Optional<ProgrammeEditionDataModel> toDataModel(ProgrammeEdition programmeEdition) {
        if(programmeEdition == null) {
            return Optional.empty();
        }
        try {
            ProgrammeEditionIdDataModel programmeEditionIdDataModel = programmeEditionIDMapper.toDataModel(programmeEdition.identity());

            ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel);
            return Optional.of(programmeEditionDataModel);
        } catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProgrammeEdition> toDomain(ProgrammeEditionDataModel programmeEditionDataModel) throws Exception {
        if(programmeEditionDataModel == null) {
            return Optional.empty();
        }
        ProgrammeEditionID programmeEditionID = programmeEditionIDMapper.toDomain(programmeEditionDataModel.getProgrammeEditionIDDataModel());
        ProgrammeID programmeID = programmeIDMapper.toDomain(programmeEditionDataModel.getProgrammeEditionIDDataModel().getProgrammeIdDataModel());
        SchoolYearID schoolYearID = schoolYearIDMapper.toDomain(programmeEditionDataModel.getProgrammeEditionIDDataModel().getSchoolYearIDDataModel());

        ProgrammeEdition programmeEdition = programmeEditionFactory.createProgrammeEdition(programmeEditionID, programmeID, schoolYearID);
        return Optional.of(programmeEdition);
    }
}