package PAI.repository;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.ddd.IRepository;
import PAI.domain.ProgrammeEdition;
import PAI.domain.ProgrammeEditionEnrolment;
import PAI.domain.Student;

public interface IProgrammeEditionEnrolmentRepository  extends IRepository <ProgrammeEditionEnrolmentID, ProgrammeEditionEnrolment>{

    boolean enrolStudentInProgrammeEdition (Student student, ProgrammeEdition programmeEdition);

    boolean isStudentEnrolledInThisProgrammeEdition (Student student, ProgrammeEdition programmeEdition);
}
