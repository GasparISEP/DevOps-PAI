package PAI.service.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;

public interface IProgrammeEnrolmentService {

    ProgrammeEnrolment enrolStudentInProgramme (StudentID studentID, AccessMethodID accessMethodID, ProgrammeID programmeID, Date date) throws Exception;
}