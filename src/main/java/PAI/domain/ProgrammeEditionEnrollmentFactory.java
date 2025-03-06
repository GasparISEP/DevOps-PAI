package PAI.domain;

import java.time.LocalDate;

public class ProgrammeEditionEnrollmentFactory implements ProgrammeEditionEnrollmentFactoryInterface {

    public ProgrammeEditionEnrollment newProgrammeEditionEnrollment(
            Student student, ProgrammeEdition programmeEdition) {
        return new ProgrammeEditionEnrollment( student, programmeEdition);
    }
}