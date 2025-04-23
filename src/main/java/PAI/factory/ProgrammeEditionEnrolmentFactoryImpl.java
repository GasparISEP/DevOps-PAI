package PAI.factory;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.domain.ProgrammeEditionEnrolment;
import PAI.domain.Student;

import java.time.LocalDate;

public class ProgrammeEditionEnrolmentFactoryImpl implements IProgrammeEditionEnrolmentFactory {

    public ProgrammeEditionEnrolment newProgrammeEditionEnrolment(
            StudentID studentId, ProgrammeEditionID programmeEditionId) {

        if (studentId == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        if (programmeEditionId == null) {
            throw new IllegalArgumentException("ProgrammeEdition cannot be null.");
        }

        return new ProgrammeEditionEnrolment(studentId, programmeEditionId);
    }

    @Override
    public ProgrammeEditionEnrolment createWithEnrolmentDate(StudentID studentId, ProgrammeEditionID programmeEditionId, LocalDate enrolmentDate) {

        if (studentId == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        if (programmeEditionId == null) {
            throw new IllegalArgumentException("ProgrammeEdition cannot be null.");
        }

        return new ProgrammeEditionEnrolment(studentId, programmeEditionId, enrolmentDate);
    }
}