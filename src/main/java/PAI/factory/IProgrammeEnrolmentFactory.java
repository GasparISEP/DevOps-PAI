package PAI.factory;

import PAI.VOs.AccessMethodID;
import PAI.VOs.Date;
import PAI.VOs.ProgrammeID;
import PAI.VOs.StudentID;
import PAI.domain.ProgrammeEnrolment;

public interface IProgrammeEnrolmentFactory {

    ProgrammeEnrolment createProgrammeEnrolment (StudentID studentID, AccessMethodID accessMethodID, ProgrammeID programmeID, Date date);
}
