package PAI.domain.programmeEditionEnrolment;

import PAI.VOs.*;
import org.springframework.stereotype.Component;

@Component
public class ProgrammeEditionEnrolmentFactoryImpl implements IProgrammeEditionEnrolmentFactory {

    public ProgrammeEditionEnrolment newProgrammeEditionEnrolment(
            StudentID studentId, ProgrammeEditionID programmeEditionId) {

        if (studentId == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        if (programmeEditionId == null) {
            throw new IllegalArgumentException("ProgrammeEdition cannot be null.");
        }

        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = new ProgrammeEditionEnrolmentGeneratedID();

        return new ProgrammeEditionEnrolment(studentId, programmeEditionId, programmeEditionEnrolmentGeneratedID);
    }

    @Override
    public ProgrammeEditionEnrolment createWithEnrolmentDate(StudentID studentId, ProgrammeEditionID programmeEditionId, Date enrolmentDate, EnrolmentStatus isActive) {

        if (studentId == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        if (programmeEditionId == null) {
            throw new IllegalArgumentException("ProgrammeEdition cannot be null.");
        }

        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = new ProgrammeEditionEnrolmentGeneratedID();

        return new ProgrammeEditionEnrolment(studentId, programmeEditionId, enrolmentDate, isActive, programmeEditionEnrolmentGeneratedID);
    }

    @Override
    public ProgrammeEditionEnrolment createWithEnrolmentDateFromDataModel(StudentID studentId, ProgrammeEditionID programmeEditionId, Date enrolmentDate, EnrolmentStatus isActive, ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID) {

        if (studentId == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        if (programmeEditionId == null) {
            throw new IllegalArgumentException("ProgrammeEdition cannot be null.");
        }

        return new ProgrammeEditionEnrolment(studentId, programmeEditionId, enrolmentDate, isActive, programmeEditionEnrolmentGeneratedID);
    }
}