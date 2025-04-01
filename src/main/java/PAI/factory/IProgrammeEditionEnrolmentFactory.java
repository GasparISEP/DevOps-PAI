package PAI.factory;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.domain.ProgrammeEdition;
import PAI.domain.ProgrammeEditionEnrolment;
import PAI.domain.Student;

public interface IProgrammeEditionEnrolmentFactory {

    ProgrammeEditionEnrolment newProgrammeEditionEnrolment(StudentID studentId, ProgrammeEditionID programmeEditionId);
}
