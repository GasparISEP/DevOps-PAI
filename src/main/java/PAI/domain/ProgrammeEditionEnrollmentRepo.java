package PAI.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgrammeEditionEnrollmentRepo {

    private List<ProgrammeEditionEnrollment> _programmeEditionEnrollments;

    private final ProgrammeEditionEnrollmentFactory _programmeEditionEnrollmentFactory;

    public ProgrammeEditionEnrollmentRepo(ProgrammeEditionEnrollmentFactory programmeEditionEnrollmentFactory) {
        _programmeEditionEnrollmentFactory = programmeEditionEnrollmentFactory;
        _programmeEditionEnrollments = new ArrayList<>();
    }

    public Optional<ProgrammeEditionEnrollment> enrollStudentInProgrammeEdition(Student student, ProgrammeEdition programmeEdition, LocalDate localDate) {
        if (programmeEdition == null || student == null) {
            throw new IllegalArgumentException("ProgrammeEdition and Student cannot be null.");
        }
        ProgrammeEditionEnrollment programmeEditionEnroll = _programmeEditionEnrollmentFactory.newProgrammeEditionEnrollment(student, programmeEdition, localDate);
        checkIfThisEnrollmentAlreadyExists(programmeEditionEnroll);
        _programmeEditionEnrollments.add(programmeEditionEnroll);

        return Optional.of(programmeEditionEnroll);
    }

    public boolean isStudentEnrolledInThisProgrammeEdition(Student student, ProgrammeEdition programmeEdition) {
        for (ProgrammeEditionEnrollment enrollment : _programmeEditionEnrollments) {
            if (enrollment.getStudentUniqueNumber() == student.getUniqueNumber() && enrollment.findProgrammeEditionInEnrollment() == programmeEdition) {
                return true;
            }
        }
        return false;
    }

    private void checkIfThisEnrollmentAlreadyExists(ProgrammeEditionEnrollment programmeEditionEnrollment) throws IllegalArgumentException {
        for (ProgrammeEditionEnrollment existingEnrollment : _programmeEditionEnrollments) {
            if (existingEnrollment.equals(programmeEditionEnrollment)) {
                throw new IllegalArgumentException("This programme edition enrollment is already in the list.");
            }
        }
    }

    //US26- number of students enrolled in all programmes associated to a department, in a given school year
    public int countStudentsInProgrammesFromDepartmentInSchoolYear(Department department, SchoolYear schoolYear) {
        List<Integer> studentUniqueNumbers = new ArrayList<>();

        for (ProgrammeEditionEnrollment enrollment : _programmeEditionEnrollments) {
            Integer studentUniqueNumber = enrollment.getStudentUniqueNumber();

            if (!studentUniqueNumbers.contains(studentUniqueNumber)) {
                if (enrollment.isEnrollmentAssociatedToDepartmentAndSchoolYear(department, schoolYear)) {
                    studentUniqueNumbers.add(studentUniqueNumber);
                }
            }
        }
        return studentUniqueNumbers.size();
    }

    //US21 - Get The Number Of Students Enrolled In A Programme Edition
    public int getTheNumberOfStudentsEnrolledInAProgrammeEdition(ProgrammeEdition programmeEdition){
        int numberOfStudents = 0;

        for(ProgrammeEditionEnrollment programmeEditionEnrollment : _programmeEditionEnrollments)
            if(programmeEditionEnrollment.findProgrammeEditionInEnrollment().equals(programmeEdition)){
                numberOfStudents++;
            }

        return numberOfStudents;
    }
}