package PAI.service;

import PAI.VOs.*;
import PAI.domain.ProgrammeEnrolment;
import PAI.factory.IProgrammeEnrolmentFactory;
import PAI.repository.IProgrammeEnrolmentRepository;
import org.springframework.stereotype.Service;

@Service
public class ProgrammeEnrolmentServiceImpl implements IProgrammeEnrolmentService {

    private IProgrammeEnrolmentFactory _peFactory;
    private IProgrammeEnrolmentRepository _peRepository;

    public ProgrammeEnrolmentServiceImpl(IProgrammeEnrolmentFactory programmeEnrolmentFactory, IProgrammeEnrolmentRepository programmeEnrolmentRepository){
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

        ProgrammeEnrolmentID programmeEnrolmentID = pe.getProgrammeEnrolmentID();

        if(_peRepository.containsOfIdentity(programmeEnrolmentID)){
            throw new IllegalArgumentException("Programme Enrolment already exists.");
        }

        _peRepository.save(pe);

        return true;
    }
}
