package PAI.mapper;

import PAI.VOs.ProgrammeEnrolmentID;
import PAI.VOs.StudentID;
import PAI.VOs.ProgrammeID;
import PAI.persistence.datamodel.ProgrammeEnrolmentIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.ProgrammeIDDataModel;

public class ProgrammeEnrolmentIDMapper implements IProgrammeEnrolmentIDMapper {

    private final StudentIDMapper studentIDMapper = new StudentIDMapper();
    private final ProgrammeIDMapper programmeIDMapper = new ProgrammeIDMapper();

    public ProgrammeEnrolmentIDDataModel domainToDataModel(ProgrammeEnrolmentID programmeEnrolmentID) {
        if (programmeEnrolmentID == null) return null;

        StudentIDDataModel studentIDDataModel = studentIDMapper.domainToDataModel(programmeEnrolmentID.getStudentID());
        ProgrammeIDDataModel programmeIDDataModel = programmeIDMapper.toData(programmeEnrolmentID.getProgrammeID());

        return new ProgrammeEnrolmentIDDataModel(studentIDDataModel, programmeIDDataModel);
    }

    public ProgrammeEnrolmentID dataModelToDomain(ProgrammeEnrolmentIDDataModel programmeEnrolmentIDDataModel) {
        if (programmeEnrolmentIDDataModel == null) return null;

        StudentID studentID = studentIDMapper.dataModelToDomain(programmeEnrolmentIDDataModel.getStudentID());
        ProgrammeID programmeID = programmeIDMapper.toDomain(programmeEnrolmentIDDataModel.getProgrammeID());

        return new ProgrammeEnrolmentID(studentID, programmeID);
    }
}


