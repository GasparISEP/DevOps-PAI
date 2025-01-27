package PAI.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgrammeEditionEnrollmentRepo {

    private List<ProgrammeEditionEnrollment> _programmeEditionEnrollments;

    public ProgrammeEditionEnrollmentRepo() {
        _programmeEditionEnrollments = new ArrayList<>();
    }

    public Optional<ProgrammeEditionEnrollment> enrollStudentInProgrammeEdition(Student student, ProgrammeEdition programmeEdition, LocalDate localDate) {
        if (programmeEdition == null || student == null) {
            throw new IllegalArgumentException("ProgrammeEdition and Student cannot be null.");
        }
        ProgrammeEditionEnrollment programmeEditionEnroll = new ProgrammeEditionEnrollment(student, programmeEdition, localDate);
        checkIfThisEnrollmentAlreadyExists(programmeEditionEnroll);
        _programmeEditionEnrollments.add(programmeEditionEnroll);

        return Optional.of(programmeEditionEnroll);
    }

    //US16 e US17 - check if a student is enrolled in any programme edition
    public boolean isStudentEnrolledInThisProgrammeEdition(Student student, ProgrammeEdition programmeEdition) {
        for (ProgrammeEditionEnrollment enrollment : _programmeEditionEnrollments) {
            if (enrollment.findProgrammeEditionInEnrollment().equals(programmeEdition) && enrollment.findStudentInProgrammeEdition().equals(student)) {
                return true;
            }
        }
        return false;
    }


    private void checkIfThisEnrollmentAlreadyExists(ProgrammeEditionEnrollment programmeEditionEnrollment) throws IllegalArgumentException {
        if (_programmeEditionEnrollments.contains(programmeEditionEnrollment)) {
            throw new IllegalArgumentException("This programme edition enrollment is already in the list.");
        }
    }

}