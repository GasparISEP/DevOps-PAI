package PAI.repository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.*;

import java.util.List;

public interface IProgrammeEditionEnrolmentRepository  extends IRepository <ProgrammeEditionEnrolmentID, ProgrammeEditionEnrolment>{

    boolean isStudentEnrolledInThisProgrammeEdition (StudentID studentId, ProgrammeEditionID programmeEditionId);

    List<ProgrammeEditionEnrolment> getAllProgrammeEditionsEnrollmentByProgrammeEditionID(ProgrammeEditionID programmeEditionId) throws Exception;

    List<ProgrammeEditionID> findProgrammeEditionsThatStudentIsEnrolled(StudentID studentId);

    int countStudentsInProgrammesFromDepartmentInSchoolYear(SchoolYearID schoolYear, List<ProgrammeID> programmeIDS);

}
