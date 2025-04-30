package PAI.factory;

import PAI.VOs.*;
import PAI.domain.ProgrammeEnrolment;
import org.springframework.stereotype.Component;

@Component
public class ProgrammeEnrolmentFactoryImpl implements IProgrammeEnrolmentFactory {

    public ProgrammeEnrolment createProgrammeEnrolment (StudentID studentID, AccessMethodID accessMethodID, ProgrammeID programmeID, Date date) throws IllegalArgumentException {

        return new ProgrammeEnrolment(studentID, accessMethodID, programmeID, date);
    }
}
