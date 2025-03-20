package PAI.repository;

import PAI.domain.ProgrammeEdition;
import PAI.domain.ProgrammeEditionEnrolment;
import PAI.domain.Student;

public class ProgrammeEditionEnrolmentDoubleEqualsTrue extends ProgrammeEditionEnrolment {

    public ProgrammeEditionEnrolmentDoubleEqualsTrue(Student student, ProgrammeEdition programmeEdition) {
        super(student, programmeEdition);
    }

    @Override
    public boolean equals (Object obj) {
        return true;
    }

}
