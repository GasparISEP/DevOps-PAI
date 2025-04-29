package PAI.mapper;

import PAI.VOs.ProgrammeEnrolmentID;
import PAI.VOs.StudentID;
import PAI.VOs.ProgrammeID;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.persistence.datamodel.ProgrammeEnrolmentIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import org.springframework.stereotype.Component;

@Component
public class ProgrammeEnrolmentIDMapper implements IProgrammeEnrolmentIDMapper {

    private final IStudentIDMapper studentIDMapper;
    private final IProgrammeIDMapper programmeIDMapper;

    public ProgrammeEnrolmentIDMapper(IStudentIDMapper studentIDMapper, IProgrammeIDMapper programmeIDMapper) {
        this.studentIDMapper = studentIDMapper;
        this.programmeIDMapper = programmeIDMapper;
    }

    public ProgrammeEnrolmentIDDataModel domainToDataModel(ProgrammeEnrolmentID programmeEnrolmentID) {
        if (programmeEnrolmentID == null) return null;

        StudentID studentID = programmeEnrolmentID.getStudentID();
        ProgrammeID programmeID = programmeEnrolmentID.getProgrammeID();

        StudentIDDataModel studentIDDataModel = studentIDMapper.domainToDataModel(studentID);
        ProgrammeIDDataModel programmeIDDataModel = programmeIDMapper.toData(programmeID);

        return new ProgrammeEnrolmentIDDataModel(studentIDDataModel, programmeIDDataModel);
    }

    public ProgrammeEnrolmentID dataModelToDomain(ProgrammeEnrolmentIDDataModel programmeEnrolmentIDDataModel) {
        if (programmeEnrolmentIDDataModel == null) return null;

        StudentIDDataModel studentIDDataModel = programmeEnrolmentIDDataModel.getStudentID();
        ProgrammeIDDataModel programmeIDDataModel = programmeEnrolmentIDDataModel.getProgrammeID();

        StudentID studentID = studentIDMapper.dataModelToDomain(studentIDDataModel);
        ProgrammeID programmeID = programmeIDMapper.toDomain(programmeIDDataModel);

        return new ProgrammeEnrolmentID(studentID, programmeID);
    }
}
