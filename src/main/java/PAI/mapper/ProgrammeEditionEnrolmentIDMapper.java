package PAI.mapper;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.springframework.stereotype.Component;

@Component
public class ProgrammeEditionEnrolmentIDMapper implements IProgrammeEditionEnrolmentIDMapper {

    private final IProgrammeEditionIdMapper _programmeEditionIdMapper;
    private final IStudentIDMapper _studentIdMapper;

    public ProgrammeEditionEnrolmentIDMapper(IProgrammeEditionIdMapper programmeEditionIdMapper, IStudentIDMapper studentIdMapper) {

        if (programmeEditionIdMapper == null) {
            throw new IllegalArgumentException("ProgrammeEditionIDMapper cannot be null");
        }

        if (studentIdMapper == null) {
            throw new IllegalArgumentException("StudentIDMapper cannot be null");
        }

        this._programmeEditionIdMapper = programmeEditionIdMapper;
        this._studentIdMapper = studentIdMapper;
    }

    @Override
    public ProgrammeEditionEnrolmentID toDomain(ProgrammeEditionEnrolmentIDDataModel programmeEditionEnrolmentIDDataModel) throws Exception {

        ProgrammeEditionIdDataModel programmeEditionIdDataModel = programmeEditionEnrolmentIDDataModel.getProgrammeEditionIdDataModel();
        StudentIDDataModel studentIDDataModel = programmeEditionEnrolmentIDDataModel.getStudentIdDataModel();

        ProgrammeEditionID programmeEditionID = _programmeEditionIdMapper.toDomain(programmeEditionIdDataModel);
        StudentID studentID = _studentIdMapper.dataModelToDomain(studentIDDataModel);

        return new ProgrammeEditionEnrolmentID(programmeEditionID, studentID);

    }

    @Override
    public ProgrammeEditionEnrolmentIDDataModel toDataModel(ProgrammeEditionEnrolmentID programmeEditionEnrolmentId) throws Exception {

        ProgrammeEditionID programmeEditionID = programmeEditionEnrolmentId.getProgrammeEditionId();
        StudentID studentID = programmeEditionEnrolmentId.getStudentiD();

        ProgrammeEditionIdDataModel programmeEditionIdDataModel = _programmeEditionIdMapper.toDataModel(programmeEditionID);
        StudentIDDataModel studentIDDataModel = _studentIdMapper.domainToDataModel(studentID);

        return new ProgrammeEditionEnrolmentIDDataModel(studentIDDataModel, programmeEditionIdDataModel);
    }
}
