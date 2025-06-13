package PAI.service.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;

import java.util.List;

public interface IProgrammeEnrolmentService {

    ProgrammeEnrolment enrolStudentInProgramme (StudentID studentID, AccessMethodID accessMethodID, ProgrammeID programmeID, Date date) throws Exception;
    ProgrammeEnrolment findEnrolmentByStudentAndProgramme(StudentID sid, ProgrammeID pid);
    List<ProgrammeEnrolment> listOfProgrammesStudentIsEnrolledIn(StudentID studentID);
    List<ProgrammeID> getProgrammeIDsByProgrammeEnrolment (List<ProgrammeEnrolment> list);
}