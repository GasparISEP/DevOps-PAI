package PAI.domain.programmeEnrolment;

import PAI.VOs.AccessMethodID;
import PAI.VOs.Date;
import PAI.VOs.ProgrammeID;
import PAI.VOs.StudentID;

public interface IProgrammeEnrolmentFactory {

    ProgrammeEnrolment createProgrammeEnrolment (StudentID studentID, AccessMethodID accessMethodID, ProgrammeID programmeID, Date date);
}
