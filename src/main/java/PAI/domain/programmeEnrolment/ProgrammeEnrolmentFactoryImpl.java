package PAI.domain.programmeEnrolment;

import PAI.VOs.*;
import org.springframework.stereotype.Component;

@Component
public class ProgrammeEnrolmentFactoryImpl implements IProgrammeEnrolmentFactory {

    @Override
    public ProgrammeEnrolment createProgrammeEnrolment (StudentID studentID, AccessMethodID accessMethodID, ProgrammeID programmeID, Date date) throws IllegalArgumentException {

        // Geração automática do UUID técnico
        ProgrammeEnrolmentGeneratedID generatedID = new ProgrammeEnrolmentGeneratedID();

        return new ProgrammeEnrolment(studentID, accessMethodID, programmeID, date, generatedID);
    }
}
