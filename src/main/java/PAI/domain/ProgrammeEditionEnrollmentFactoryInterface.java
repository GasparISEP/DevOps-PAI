package PAI.domain;

import java.time.LocalDate;

public interface ProgrammeEditionEnrollmentFactoryInterface {

    ProgrammeEditionEnrollment newProgrammeEditionEnrollment(Student student, ProgrammeEdition programmeEdition, LocalDate enrollmentDate);
}
