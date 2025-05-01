package PAI.repository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.*;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IProgrammeEditionEnrolmentRepository  extends IRepository <ProgrammeEditionEnrolmentID, ProgrammeEditionEnrolment>{

    boolean isStudentEnrolledInThisProgrammeEdition (StudentID studentId, ProgrammeEditionID programmeEditionId);

    List<ProgrammeEditionEnrolment> getAllProgrammeEditionsEnrollmentByProgrammeEditionID(ProgrammeEditionID programmeEditionId) throws Exception;

    List<ProgrammeEditionID> findProgrammeEditionsThatStudentIsEnrolled(StudentID studentId);

    int countStudentsInProgrammesFromDepartmentInSchoolYear(SchoolYearID schoolYear, List<ProgrammeID> programmeIDS);

    Optional<ProgrammeEditionEnrolment> findByStudentAndProgrammeEdition(StudentID studentId, ProgrammeEditionID programmeEditionId);

    Set<ProgrammeEditionEnrolment> getInternalSet() throws Exception;
}
