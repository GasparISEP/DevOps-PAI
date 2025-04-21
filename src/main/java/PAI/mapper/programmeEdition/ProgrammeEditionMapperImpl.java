package PAI.mapper.programmeEdition;

import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.mapper.IProgrammeIDMapper;
import PAI.mapper.schoolYearID.ISchoolYearIDMapper;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionDataModel;

import java.util.Optional;

public class ProgrammeEditionMapperImpl implements  IProgrammeEditionMapper{

    private IProgrammeEditionFactory _programmeEditionFactory;
    private IProgrammeEditionIdMapper _programmeEditionIDMapper;
    private IProgrammeIDMapper _programmeIDMapper;
    private ISchoolYearIDMapper _schoolYearIDMapper;

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
        this._programmeEditionFactory = programmeEditionFactory;
        this._programmeEditionIDMapper = programmeEditionIDMapper;
        this._programmeIDMapper = programmeIDMapper;
        this._schoolYearIDMapper = schoolYearIDMapper;
    }

    @Override
    public Optional<ProgrammeEditionDataModel> toDataModel(ProgrammeEdition programmeEdition) {
        return Optional.empty();
    }

    @Override
    public Optional<ProgrammeEdition> toDomain(ProgrammeEditionDataModel programmeEditionDataModel) {
        return Optional.empty();
    }
}