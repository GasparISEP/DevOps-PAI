package PAI.repository;

import PAI.domain.*;
import PAI.factory.ProgrammeEditionEnrollmentFactory;


import java.util.*;

public class ProgrammeEditionEnrollmentRepo {

    private Set<ProgrammeEditionEnrollment> _programmeEditionEnrollments;

    private final ProgrammeEditionEnrollmentFactory _programmeEditionEnrollmentFactory;

    public ProgrammeEditionEnrollmentRepo(ProgrammeEditionEnrollmentFactory programmeEditionEnrollmentFactory,ProgrammeEditionEnrolmentListFactory programmeEditionEnrolmentListFactory) {
        _programmeEditionEnrollmentFactory = programmeEditionEnrollmentFactory;
        _programmeEditionEnrollments = programmeEditionEnrolmentListFactory.newListProgrammeEditionEnrollment();

    }

    public boolean enrollStudentInProgrammeEdition(Student student, ProgrammeEdition programmeEdition) {
        try {
            if (programmeEdition == null || student == null) {
                throw new IllegalArgumentException("ProgrammeEdition and Student cannot be null.");
            }

            ProgrammeEditionEnrollment programmeEditionEnroll = _programmeEditionEnrollmentFactory
                    .newProgrammeEditionEnrollment(student, programmeEdition);

            return _programmeEditionEnrollments.add(programmeEditionEnroll);
        } catch (Exception e) {
            return false;
        }
    }


    public boolean isStudentEnrolledInThisProgrammeEdition(Student student, ProgrammeEdition programmeEdition) {
        for (ProgrammeEditionEnrollment enrollment : _programmeEditionEnrollments) {
            if (enrollment.getStudentUniqueNumber() == student.getUniqueNumber() && enrollment.findProgrammeEditionInEnrollment() == programmeEdition) {
                return true;
            }
        }
        return false;
    }

    //US26- number of students enrolled in all programmes associated to a department, in a given school year
    public int countStudentsInProgrammesFromDepartmentInSchoolYear(Department department, SchoolYear schoolYear) {
        Set<Integer> studentUniqueNumbers = new HashSet<>();

        for (ProgrammeEditionEnrollment enrollment : _programmeEditionEnrollments) {
            if (enrollment.isEnrollmentAssociatedToDepartmentAndSchoolYear(department, schoolYear)) {
                Integer studentUniqueNumber = enrollment.getStudentUniqueNumber();
                studentUniqueNumbers.add(studentUniqueNumber);
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