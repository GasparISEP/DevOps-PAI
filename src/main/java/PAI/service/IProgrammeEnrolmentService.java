package PAI.service;

import PAI.VOs.*;

public interface IProgrammeEnrolmentService {

    boolean enrolStudentInProgramme (StudentID studentID, AccessMethodID accessMethodID, ProgrammeID programmeID, Date date);
}