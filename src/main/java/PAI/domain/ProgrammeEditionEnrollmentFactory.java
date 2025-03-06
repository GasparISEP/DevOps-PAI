package PAI.domain;

import java.time.LocalDate;

public class ProgrammeEditionEnrollmentFactory implements ProgrammeEditionEnrollmentFactoryInterface {

    public ProgrammeEditionEnrollment newProgrammeEditionEnrollment(
            Student student, ProgrammeEdition programmeEdition, LocalDate enrollmentDate) {
        return new ProgrammeEditionEnrollment( student, programmeEdition, enrollmentDate);
    }
}