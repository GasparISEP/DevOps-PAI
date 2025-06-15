package PAI.mapper.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.mapper.schoolYear.ISchoolYearIDMapper;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.springframework.stereotype.Component;
//This annotation manage this class lifecycle, it creates an instance of it automatically
@Component
public class ProgrammeEditionIdMapperImpl implements IProgrammeEditionIdMapper {

    private final IProgrammeIDMapper programmeIdMapper;
    private final ISchoolYearIDMapper schoolYearIDMapper;

    public ProgrammeEditionIdMapperImpl(IProgrammeIDMapper programmeIdMapper, ISchoolYearIDMapper schoolYearIDMapper) {
        if(programmeIdMapper == null) {
            throw new IllegalArgumentException("ProgrammeIdMapper cannot be null");
        }
        this.programmeIdMapper = programmeIdMapper;

        if(schoolYearIDMapper == null) {
            throw new IllegalArgumentException("SchoolYearIDMapperImpl cannot be null");
        }
        this.schoolYearIDMapper = schoolYearIDMapper;
    }

    @Override
    public ProgrammeEditionID toDomain(ProgrammeEditionIdDataModel programmeEditionIdDataModel){
        ProgrammeIDDataModel programmeIDDataModel = programmeEditionIdDataModel.getProgrammeIdDataModel();
        SchoolYearIDDataModel schoolYearIDDataModel = programmeEditionIdDataModel.getSchoolYearIDDataModel();

        ProgrammeID programmeID = programmeIdMapper.toDomain(programmeIDDataModel);
        SchoolYearID schoolYearID = schoolYearIDMapper.toDomain(schoolYearIDDataModel);

        return new ProgrammeEditionID(programmeID, schoolYearID);
    }

    @Override
    public ProgrammeEditionIdDataModel toDataModel(ProgrammeEditionID programmeEditionId){
        ProgrammeID programmeID = programmeEditionId.getProgrammeID();
        SchoolYearID schoolYearID = programmeEditionId.getSchoolYearID();

        ProgrammeIDDataModel programmeIDDataModel = programmeIdMapper.toData(programmeID);
        SchoolYearIDDataModel schoolYearIDDataModel = schoolYearIDMapper.toDataModel(schoolYearID);

        return new ProgrammeEditionIdDataModel(programmeIDDataModel, schoolYearIDDataModel);
    }


}
