package PAI.repository;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.ddd.IRepository;
import PAI.domain.*;

import java.util.List;

public interface IProgrammeEditionEnrolmentRepository  extends IRepository <ProgrammeEditionEnrolmentID, ProgrammeEditionEnrolment>{

    boolean enrolStudentInProgrammeEdition (Student student, ProgrammeEdition programmeEdition);

    boolean isStudentEnrolledInThisProgrammeEdition (Student student, ProgrammeEdition programmeEdition);

    int countStudentsInProgrammesFromDepartmentInSchoolYear(Department department, SchoolYear schoolYear);

    int getTheNumberOfStudentsEnrolledInAProgrammeEdition(ProgrammeEdition programmeEdition);

    List<ProgrammeEdition> findProgrammeEditionsThatStudentIsEnrolled(Student student);


}
