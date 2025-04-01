package PAI.repository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.*;

import java.util.List;

public interface IProgrammeEditionEnrolmentRepository  extends IRepository <ProgrammeEditionEnrolmentID, ProgrammeEditionEnrolment>{

    boolean enrolStudentInProgrammeEdition (StudentID studentId, ProgrammeEditionID programmeEditionID);

    boolean isStudentEnrolledInThisProgrammeEdition (StudentID studentId, ProgrammeEditionID programmeEditionId);

    int countStudentsInProgrammesFromDepartmentInSchoolYear(Department department, SchoolYear schoolYear);

    int getTheNumberOfStudentsEnrolledInAProgrammeEdition(ProgrammeEditionID programmeEditionId);

    List<ProgrammeEditionID> findProgrammeEditionsThatStudentIsEnrolled(StudentID studentId);


}
