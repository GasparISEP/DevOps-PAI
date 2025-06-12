package PAI.domain.repositoryInterfaces.programmeEnrolment;

import PAI.VOs.ProgrammeEnrolmentGeneratedID;
import PAI.VOs.ProgrammeEnrolmentID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.StudentID;
import PAI.ddd.IRepository;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;

import java.util.List;
import java.util.Optional;

public interface IProgrammeEnrolmentRepository extends IRepository<ProgrammeEnrolmentID, ProgrammeEnrolment> {

    boolean isStudentEnrolled(StudentID studentID, ProgrammeID programmeID);

    ProgrammeEnrolment save(ProgrammeEnrolment enrolment);

    Iterable<ProgrammeEnrolment> findAll();

    Optional<ProgrammeEnrolment> ofIdentity(ProgrammeEnrolmentID peID);

    boolean containsOfIdentity(ProgrammeEnrolmentID peID);

    List<ProgrammeID> findProgrammesByStudent(StudentID studentID);

    Optional<ProgrammeEnrolment> findByStudentIDAndProgrammeID(StudentID studentID, ProgrammeID programmeID);

    Optional<ProgrammeEnrolment> findByGeneratedID(ProgrammeEnrolmentGeneratedID gid);

    List<ProgrammeEnrolment> listOfProgrammesStudentIsEnrolledIn(StudentID studentID);
}
