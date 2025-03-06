package PAI.factory;

import PAI.domain.ProgrammeEdition;
import PAI.domain.ProgrammeEditionEnrollment;
import PAI.domain.ProgrammeEditionEnrollmentFactoryInterface;
import PAI.domain.Student;

import java.time.LocalDate;

public class ProgrammeEditionEnrollmentFactory implements ProgrammeEditionEnrollmentFactoryInterface {

    public ProgrammeEditionEnrollment newProgrammeEditionEnrollment(
            Student student, ProgrammeEdition programmeEdition, LocalDate enrollmentDate) {
        return new ProgrammeEditionEnrollment( student, programmeEdition, enrollmentDate);
    }
}