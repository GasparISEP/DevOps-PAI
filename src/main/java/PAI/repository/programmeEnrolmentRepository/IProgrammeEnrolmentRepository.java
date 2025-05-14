package PAI.repository.programmeEnrolmentRepository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;

import java.util.Optional;

public interface IProgrammeEnrolmentRepository extends IRepository<ProgrammeEnrolmentID, ProgrammeEnrolment> {

    boolean isStudentEnrolled(StudentID studentID, ProgrammeID programmeID);

    ProgrammeEnrolment save(ProgrammeEnrolment enrolment);

    Iterable<ProgrammeEnrolment> findAll();

    Optional<ProgrammeEnrolment> ofIdentity(ProgrammeEnrolmentID peID);

    boolean containsOfIdentity(ProgrammeEnrolmentID peID);
}
