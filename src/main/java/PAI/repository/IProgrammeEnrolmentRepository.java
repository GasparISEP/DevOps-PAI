package PAI.repository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.Programme;
import PAI.domain.ProgrammeEnrolment;
import PAI.domain.Student;

import java.util.Optional;

public interface IProgrammeEnrolmentRepository extends IRepository<ProgrammeEnrolmentID, ProgrammeEnrolment> {

    boolean enrolStudents(StudentID studentID, AccessMethodID accessMethodID, ProgrammeID programmeID, Date enrolmentDate) throws Exception;

    boolean isStudentEnrolled(StudentID studentID, ProgrammeID programmeID);

    ProgrammeEnrolment save(ProgrammeEnrolment enrolment);

    Iterable<ProgrammeEnrolment> findAll();

    Optional<ProgrammeEnrolment> ofIdentity(ProgrammeEnrolmentID peID);

    boolean containsOfIdentity(ProgrammeEnrolmentID peID);
}
