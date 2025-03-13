package PAI.repository;

import PAI.domain.*;
import PAI.factory.IProgrammeEditionEnrollmentFactory;
import PAI.factory.IProgrammeEditionEnrollmentListFactory;



import java.util.*;

public class ProgrammeEditionEnrollmentRepo {

    private Set<ProgrammeEditionEnrollment> _programmeEditionEnrollments;
    private final IProgrammeEditionEnrollmentFactory _iProgrammeEditionEnrollmentFactory;

    public ProgrammeEditionEnrollmentRepo(IProgrammeEditionEnrollmentFactory iProgrammeEditionEnrollmentFactory,
                                          IProgrammeEditionEnrollmentListFactory iProgrammeEditionEnrolmentListFactory) {

        _iProgrammeEditionEnrollmentFactory = iProgrammeEditionEnrollmentFactory;
        _programmeEditionEnrollments = iProgrammeEditionEnrolmentListFactory.newListProgrammeEditionEnrollment();
    }

    public boolean enrollStudentInProgrammeEdition(Student student, ProgrammeEdition programmeEdition) {
            if (programmeEdition == null || student == null) {
                throw new IllegalArgumentException("ProgrammeEdition and Student cannot be null.");
            }

            ProgrammeEditionEnrollment programmeEditionEnroll = _iProgrammeEditionEnrollmentFactory.newProgrammeEditionEnrollment(student, programmeEdition);

            return _programmeEditionEnrollments.add(programmeEditionEnroll);
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
        Set<String> studentUniqueNumbers = new HashSet<>();

        for (ProgrammeEditionEnrollment enrollment : _programmeEditionEnrollments) {
            if (enrollment.isEnrollmentAssociatedToDepartmentAndSchoolYear(department, schoolYear)) {
                String studentUniqueNumber = enrollment.getStudentUniqueNumber();
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

    public List<ProgrammeEdition> findProgrammeEditionsThatStudentIsEnrolled(Student student){
        List<ProgrammeEdition> list = new ArrayList<>();

        for(ProgrammeEditionEnrollment programmeEditionEnrollment : _programmeEditionEnrollments){
            if(programmeEditionEnrollment.findStudentInProgrammeEdition().equals(student)){
                ProgrammeEdition programmeEdition = programmeEditionEnrollment.findProgrammeEditionInEnrollment();
                list.add(programmeEdition);
            }
        }
        return list;
    }
}