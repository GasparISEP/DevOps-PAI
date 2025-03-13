package PAI.factory;

import PAI.domain.ProgrammeEdition;
import PAI.domain.ProgrammeEditionEnrollment;
import PAI.domain.Student;

public interface IProgrammeEditionEnrollmentFactory {

    ProgrammeEditionEnrollment newProgrammeEditionEnrollment(Student student, ProgrammeEdition programmeEdition);
}
