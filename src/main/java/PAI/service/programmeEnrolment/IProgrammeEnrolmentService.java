package PAI.service.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;

import java.util.List;

public interface IProgrammeEnrolmentService {

    ProgrammeEnrolment enrolStudentInProgramme (StudentID studentID, AccessMethodID accessMethodID, ProgrammeID programmeID, Date date) throws Exception;
    ProgrammeEnrolment findEnrolmentByStudentAndProgramme(StudentID sid, ProgrammeID pid);
    US34ListOfProgrammes getProgrammesStudentIsEnrolled(StudentID studentID);
    List<ProgrammeID> getProgrammeIDsByProgrammeEnrolment (List<ProgrammeEnrolment> list);
    List<ProgrammeSummary> mappingVOsIntoRecord(List<ProgrammeEnrolment> enrolments, List<Programme> programmes);
}