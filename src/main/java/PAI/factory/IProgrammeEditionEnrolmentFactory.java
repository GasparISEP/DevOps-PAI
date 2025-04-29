package PAI.factory;

import PAI.VOs.Date;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.domain.ProgrammeEditionEnrolment;

import java.time.LocalDate;

public interface IProgrammeEditionEnrolmentFactory {

    ProgrammeEditionEnrolment newProgrammeEditionEnrolment(StudentID studentId, ProgrammeEditionID programmeEditionId);

    ProgrammeEditionEnrolment createWithEnrolmentDate(StudentID studentId, ProgrammeEditionID programmeEditionId, Date enrolmentDate);
}
