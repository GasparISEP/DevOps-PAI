package PAI.service.programmeEnrolment;

import PAI.VOs.*;

public interface IProgrammeEnrolmentService {

    boolean enrolStudentInProgramme (StudentID studentID, AccessMethodID accessMethodID, ProgrammeID programmeID, Date date);
}