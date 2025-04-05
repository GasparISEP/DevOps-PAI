package PAI.repository;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.domain.ProgrammeEditionEnrolment;
import PAI.domain.Student;

public class ProgrammeEditionEnrolmentDoubleEqualsTrue extends ProgrammeEditionEnrolment {

    public ProgrammeEditionEnrolmentDoubleEqualsTrue(StudentID studentId, ProgrammeEditionID programmeEditionId) {
        super(studentId, programmeEditionId);
    }

    @Override
    public boolean equals (Object obj) {
        return true;
    }

}
