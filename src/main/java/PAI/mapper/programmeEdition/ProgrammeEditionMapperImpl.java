package PAI.mapper.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.mapper.schoolYearID.ISchoolYearIDMapper;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
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
    public Optional<ProgrammeEditionDataModel> toDataModel(ProgrammeEdition programmeEdition) throws Exception {
        if(programmeEdition == null) {
            return Optional.empty();
        }
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = _programmeEditionIDMapper.toDataModel(programmeEdition.identity());

        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel);
        return Optional.of(programmeEditionDataModel);
    }

    @Override
    public Optional<ProgrammeEdition> toDomain(ProgrammeEditionDataModel programmeEditionDataModel) throws Exception {
        if(programmeEditionDataModel == null) {
            return Optional.empty();
        }
        ProgrammeEditionID programmeEditionID = _programmeEditionIDMapper.toDomain(programmeEditionDataModel.getProgrammeEditionIDDataModel());
//        ProgrammeID programmeID = _programmeIDMapper.toDomain(programmeEditionDataModel.getProgrammeIDDataModel());
        ProgrammeID programmeID = _programmeIDMapper.toDomain(programmeEditionDataModel.getProgrammeEditionIDDataModel().getProgrammeIdDataModel());
//        SchoolYearID schoolYearID = _schoolYearIDMapper.toDomain(programmeEditionDataModel.getSchoolYearIDDataModel());
        SchoolYearID schoolYearID = _schoolYearIDMapper.toDomain(programmeEditionDataModel.getProgrammeEditionIDDataModel().get_schoolYearIDDataModel());

        ProgrammeEdition programmeEdition = _programmeEditionFactory.createProgrammeEdition(programmeEditionID, programmeID, schoolYearID);
//        return Optional.empty();
        return Optional.of(programmeEdition);
    }
}