package PAI.repository;

import PAI.domain.ProgrammeEdition;
import PAI.domain.ProgrammeEditionEnrollment;
import PAI.domain.Student;

public class ProgrammeEditionEnrollmentDoubleEqualsTrue extends ProgrammeEditionEnrollment {

    public ProgrammeEditionEnrollmentDoubleEqualsTrue(Student student, ProgrammeEdition programmeEdition) {
        super(student, programmeEdition);
    }

    @Override
    public boolean equals (Object obj) {
        return true;
    }

}
