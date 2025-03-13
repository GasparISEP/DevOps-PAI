package PAI.factory;

import PAI.domain.ProgrammeEdition;
import PAI.domain.ProgrammeEditionEnrollment;
import PAI.domain.Student;

public class ProgrammeEditionEnrollmentFactoryImpl implements IProgrammeEditionEnrollmentFactory {

    public ProgrammeEditionEnrollment newProgrammeEditionEnrollment(
            Student student, ProgrammeEdition programmeEdition) {
        return new ProgrammeEditionEnrollment( student, programmeEdition);
    }
}