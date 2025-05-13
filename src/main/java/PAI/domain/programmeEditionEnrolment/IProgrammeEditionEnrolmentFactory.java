package PAI.domain.programmeEditionEnrolment;

import PAI.VOs.Date;
import PAI.VOs.EnrolmentStatus;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;

public interface IProgrammeEditionEnrolmentFactory {

    ProgrammeEditionEnrolment newProgrammeEditionEnrolment(StudentID studentId, ProgrammeEditionID programmeEditionId);

    ProgrammeEditionEnrolment createWithEnrolmentDate(StudentID studentId, ProgrammeEditionID programmeEditionId, Date enrolmentDate, EnrolmentStatus isActive);
}
