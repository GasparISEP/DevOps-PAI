package PAI.service;

import PAI.VOs.AccessMethodID;
import PAI.VOs.Date;
import PAI.VOs.ProgrammeID;
import PAI.VOs.StudentID;
import PAI.domain.ProgrammeEnrolment;
import PAI.factory.IProgrammeEnrolmentFactory;
import PAI.repository.IProgrammeEnrolmentRepository;

public class ProgrammeEnrolmentService implements IProgrammeEnrolmentService {

    private IProgrammeEnrolmentFactory _peFactory;
    private IProgrammeEnrolmentRepository _peRepository;

    public ProgrammeEnrolmentService (IProgrammeEnrolmentFactory programmeEnrolmentFactory, IProgrammeEnrolmentRepository programmeEnrolmentRepository){
        if (programmeEnrolmentFactory == null || programmeEnrolmentRepository == null){
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        _peFactory = programmeEnrolmentFactory;
        _peRepository = programmeEnrolmentRepository;
    }

    public boolean enrolStudentInProgramme (StudentID studentID, AccessMethodID accessMethodID, ProgrammeID programmeID, Date date){
        if (studentID == null || accessMethodID == null || programmeID == null || date == null){
            return false;
        }

        ProgrammeEnrolment pe = _peFactory.createProgrammeEnrolment(studentID,accessMethodID, programmeID, date);

        _peRepository.save(pe);

        return true;
    }
}
