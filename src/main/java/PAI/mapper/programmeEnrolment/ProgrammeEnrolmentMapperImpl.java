package PAI.mapper.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.domain.programmeEnrolment.IProgrammeEnrolmentFactory;

import PAI.mapper.student.IStudentIDMapper;
import PAI.mapper.accessMethod.IAccessMethodIDMapper;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.persistence.datamodel.programmeEnrolment.ProgrammeEnrolmentDataModel;
import PAI.persistence.datamodel.programmeEnrolment.ProgrammeEnrolmentIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import PAI.persistence.datamodel.accessMethod.AccessMethodIDDataModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProgrammeEnrolmentMapperImpl implements IProgrammeEnrolmentMapper {

    private IProgrammeEnrolmentFactory _peFactory;
    private IProgrammeEnrolmentIDMapper _peIDMapper;
    private IProgrammeIDMapper _programmeIDMapper;
    private IStudentIDMapper _studentIDMapper;
    private IAccessMethodIDMapper _amIDMapper;

    public ProgrammeEnrolmentMapperImpl(IProgrammeEnrolmentFactory peFactory, IProgrammeEnrolmentIDMapper peIDMapper, IProgrammeIDMapper programmeIDMapper, IStudentIDMapper studentIDMapper, IAccessMethodIDMapper amIDMapper) {
        if (peFactory == null || peIDMapper == null || programmeIDMapper == null || studentIDMapper == null || amIDMapper == null)
            throw new IllegalArgumentException("Parameter cannot be null.");

        this._peFactory = peFactory;
        this._peIDMapper = peIDMapper;
        this._programmeIDMapper = programmeIDMapper;
        this._studentIDMapper = studentIDMapper;
        this._amIDMapper = amIDMapper;
    }

    public ProgrammeEnrolment toDomain(ProgrammeEnrolmentDataModel programmeEnrolmentDataModel) {
        if (programmeEnrolmentDataModel == null) {
            throw new IllegalArgumentException("Programme Enrolment Data Model cannot be null");
        }

        // Mapping DataModels to VO
        StudentID studentID = _studentIDMapper.dataModelToDomain(programmeEnrolmentDataModel.getProgrammeEnrolmentID().getStudentID());
        AccessMethodID accessMethodID = _amIDMapper.toVO(programmeEnrolmentDataModel.getAccessMethodID()).get();
        ProgrammeID programmeID = _programmeIDMapper.toDomain(programmeEnrolmentDataModel.getProgrammeEnrolmentID().getProgrammeID());
        Date enrolmentDate = new Date(programmeEnrolmentDataModel.getDate());

        // Factory to create Domain object
        ProgrammeEnrolment programmeEnrolment = _peFactory.createProgrammeEnrolment(studentID, accessMethodID, programmeID, enrolmentDate);

        return programmeEnrolment;
    }

    public ProgrammeEnrolmentDataModel toDataModel(ProgrammeEnrolment programmeEnrolment) {
        if (programmeEnrolment == null) {
            throw new IllegalArgumentException("Programme Enrolment cannot be null");
        }

        // Convert VO to respective DataModels
        ProgrammeEnrolmentIDDataModel peID = _peIDMapper.domainToDataModel(programmeEnrolment.getProgrammeEnrolmentID());
        ProgrammeIDDataModel programmeID = _programmeIDMapper.toData(programmeEnrolment.getProgrammeID());
        StudentIDDataModel studentID = _studentIDMapper.domainToDataModel(programmeEnrolment.getStudentID());
        AccessMethodIDDataModel amID = _amIDMapper.toDataModel(programmeEnrolment.getAccessMethodID()).get();
        LocalDate date = programmeEnrolment.getDate().getLocalDate();

        // Creating DataModel
        ProgrammeEnrolmentDataModel peDataModel = new ProgrammeEnrolmentDataModel(peID, programmeID, studentID, amID, date);

        return peDataModel;
    }
}