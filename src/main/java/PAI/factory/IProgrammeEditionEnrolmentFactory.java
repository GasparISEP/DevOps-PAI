package PAI.factory;

import PAI.domain.ProgrammeEdition;
import PAI.domain.ProgrammeEditionEnrolment;
import PAI.domain.Student;

public interface IProgrammeEditionEnrolmentFactory {

    ProgrammeEditionEnrolment newProgrammeEditionEnrollment(Student student, ProgrammeEdition programmeEdition);
}
