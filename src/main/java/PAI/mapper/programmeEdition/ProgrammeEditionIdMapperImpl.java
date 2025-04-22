package PAI.mapper.programmeEdition;

import PAI.VOs.*;
import PAI.mapper.IProgrammeIDMapper;
import PAI.mapper.schoolYearID.ISchoolYearIDMapper;
import PAI.persistence.datamodel.ProgrammeIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.springframework.stereotype.Component;
//This annotation manage this class lifecycle, it creates an instance of it automatically
@Component
public class ProgrammeEditionIdMapperImpl implements IProgrammeEditionIdMapper {

    private final IProgrammeIDMapper _programmeIdMapper;
    private final ISchoolYearIDMapper _schoolYearIDMapper;

    public ProgrammeEditionIdMapperImpl(IProgrammeIDMapper programmeIdMapper, ISchoolYearIDMapper schoolYearIDMapper) {
        if(programmeIdMapper == null) {
            throw new IllegalArgumentException("ProgrammeIdMapper cannot be null");
        }
        this._programmeIdMapper = programmeIdMapper;

        if(schoolYearIDMapper == null) {
            throw new IllegalArgumentException("SchoolYearIDMapper cannot be null");
        }
        this._schoolYearIDMapper = schoolYearIDMapper;
    }

    @Override
    public ProgrammeEditionID toDomain(ProgrammeEditionIdDataModel programmeEditionIdDataModel) throws Exception {
        ProgrammeIDDataModel programmeIDDataModel = programmeEditionIdDataModel.getProgrammeIdDataModel();
        SchoolYearIDDataModel schoolYearIDDataModel = programmeEditionIdDataModel.get_schoolYearIDDataModel();

        ProgrammeID programmeID = _programmeIdMapper.toDomain(programmeIDDataModel);
        SchoolYearID schoolYearID = _schoolYearIDMapper.toDomain(schoolYearIDDataModel);

        return new ProgrammeEditionID(programmeID, schoolYearID);
    }

    @Override
    public ProgrammeEditionIdDataModel toDataModel(ProgrammeEditionID programmeEditionId) throws Exception {
        ProgrammeID programmeID = programmeEditionId.getProgrammeID();
        SchoolYearID schoolYearID = programmeEditionId.getSchoolYearID();

        ProgrammeIDDataModel programmeIDDataModel = _programmeIdMapper.toData(programmeID);
        SchoolYearIDDataModel schoolYearIDDataModel = _schoolYearIDMapper.toDataModel(schoolYearID);

        return new ProgrammeEditionIdDataModel(programmeIDDataModel, schoolYearIDDataModel);
    }


}
