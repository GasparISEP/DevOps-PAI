package PAI.factory;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.domain.ProgrammeEditionEnrolment;
import PAI.domain.Student;

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
}