package PAI.factory;

import PAI.domain.ProgrammeEdition;
import PAI.domain.ProgrammeEditionEnrolment;
import PAI.domain.Student;

public interface IProgrammeEditionEnrolmentFactory {

    ProgrammeEditionEnrolment newProgrammeEditionEnrolment(Student student, ProgrammeEdition programmeEdition);
}
