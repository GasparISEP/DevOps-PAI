package PAI.domain.programmeEditionEnrolment;

import PAI.VOs.*;

public interface IProgrammeEditionEnrolmentFactory {

    ProgrammeEditionEnrolment newProgrammeEditionEnrolment(StudentID studentId, ProgrammeEditionID programmeEditionId);

    ProgrammeEditionEnrolment createWithEnrolmentDate(StudentID studentId, ProgrammeEditionID programmeEditionId, Date enrolmentDate, EnrolmentStatus isActive);

    default ProgrammeEditionEnrolment create(
            ProgrammeEditionEnrolmentID id,
            StudentID studentID,
            ProgrammeEditionID programmeEditionID
    ) {
        return newProgrammeEditionEnrolment(studentID, programmeEditionID);
    }

    ProgrammeEditionEnrolment createWithEnrolmentDateFromDataModel(StudentID studentId, ProgrammeEditionID programmeEditionId, Date enrolmentDate, EnrolmentStatus isActive, ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID);
    }
