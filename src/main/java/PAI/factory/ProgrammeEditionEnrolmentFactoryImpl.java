package PAI.factory;

import PAI.domain.ProgrammeEdition;
import PAI.domain.ProgrammeEditionEnrolment;
import PAI.domain.Student;

public class ProgrammeEditionEnrolmentFactoryImpl implements IProgrammeEditionEnrolmentFactory {

    public ProgrammeEditionEnrolment newProgrammeEditionEnrollment(
            Student student, ProgrammeEdition programmeEdition) {
        return new ProgrammeEditionEnrolment( student, programmeEdition);
    }
}