package PAI.service;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.VOs.StudentID;

import java.util.List;

public interface IProgrammeEditionEnrolmentService {

    boolean enrolStudentInProgrammeEditionAndSetOfCoursesEditions(StudentID studentID, ProgrammeID programmeID, SchoolYearID schoolYearID) throws Exception;

    List<ProgrammeID> getAllProgrammesIDs();

    List<SchoolYearID> getAllSchoolYearIDs();

}
