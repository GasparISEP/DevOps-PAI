package PAI.repository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.*;
import PAI.factory.IProgrammeRepository;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepositoryDDD;
import PAI.repository.programmeRepo.IProgrammeDDDRepository;

import java.util.List;

public interface IProgrammeEditionEnrolmentRepository  extends IRepository <ProgrammeEditionEnrolmentID, ProgrammeEditionEnrolment>{

    boolean enrolStudentInProgrammeEdition (StudentID studentId, ProgrammeEditionID programmeEditionID);

    boolean isStudentEnrolledInThisProgrammeEdition (StudentID studentId, ProgrammeEditionID programmeEditionId);

    int getTheNumberOfStudentsEnrolledInAProgrammeEdition(ProgrammeEditionID programmeEditionId);

    List<ProgrammeEditionID> findProgrammeEditionsThatStudentIsEnrolled(StudentID studentId);

    int countStudentsInProgrammesFromDepartmentInSchoolYear(SchoolYearID schoolYear, List<ProgrammeID> programmeIDS);

}
