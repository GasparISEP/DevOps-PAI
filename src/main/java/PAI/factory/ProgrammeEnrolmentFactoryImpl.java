package PAI.factory;

import PAI.VOs.*;
import PAI.domain.ProgrammeEnrolment;
import PAI.repository.IProgrammeEnrolmentRepository;
import org.springframework.stereotype.Component;

@Component
public class ProgrammeEnrolmentFactoryImpl implements IProgrammeEnrolmentFactory {

    private IProgrammeEnrolmentRepository programmeEnrolmentRepository;

    public ProgrammeEnrolmentFactoryImpl(IProgrammeEnrolmentRepository peRepository){
        if (peRepository == null){
            throw new IllegalArgumentException("Repository cannot be null.");
        }

        programmeEnrolmentRepository = peRepository;
    }

    public ProgrammeEnrolment createProgrammeEnrolment (StudentID studentID, AccessMethodID accessMethodID, ProgrammeID programmeID, Date date) throws IllegalArgumentException {

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentID, accessMethodID, programmeID, date);

        ProgrammeEnrolmentID programmeEnrolmentID = programmeEnrolment.getProgrammeEnrolmentID();

        if(programmeEnrolmentRepository.containsOfIdentity(programmeEnrolmentID)){
            throw new IllegalArgumentException("Programme Enrolment already exists.");
        }

        return programmeEnrolment;
    }
}
