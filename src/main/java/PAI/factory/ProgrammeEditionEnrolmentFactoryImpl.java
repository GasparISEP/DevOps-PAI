package PAI.factory;

import PAI.domain.ProgrammeEdition;
import PAI.domain.ProgrammeEditionEnrolment;
import PAI.domain.Student;

public class ProgrammeEditionEnrolmentFactoryImpl implements IProgrammeEditionEnrolmentFactory {

    public ProgrammeEditionEnrolment newProgrammeEditionEnrollment(
            Student student, ProgrammeEdition programmeEdition) {

        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        if (programmeEdition == null) {
            throw new IllegalArgumentException("ProgrammeEdition cannot be null.");
        }

        return new ProgrammeEditionEnrolment( student, programmeEdition);
    }
}