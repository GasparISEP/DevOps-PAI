package PAI.repository;

import PAI.domain.*;
import PAI.factory.IProgrammeEditionEnrolmentFactory;
import PAI.factory.IProgrammeEditionEnrolmentListFactory;



import java.util.*;

public class ProgrammeEditionEnrolmentRepository {

    private Set<ProgrammeEditionEnrolment> _programmeEditionEnrolments;
    private final IProgrammeEditionEnrolmentFactory _iProgrammeEditionEnrolmentFactory;

    public ProgrammeEditionEnrolmentRepository(IProgrammeEditionEnrolmentFactory iProgrammeEditionEnrolmentFactory,
                                               IProgrammeEditionEnrolmentListFactory iProgrammeEditionEnrolmentListFactory) {

        _iProgrammeEditionEnrolmentFactory = iProgrammeEditionEnrolmentFactory;
        _programmeEditionEnrolments = iProgrammeEditionEnrolmentListFactory.newListProgrammeEditionEnrollment();
    }

    public boolean enrollStudentInProgrammeEdition(Student student, ProgrammeEdition programmeEdition) {
            if (programmeEdition == null || student == null) {
                throw new IllegalArgumentException("ProgrammeEdition and Student cannot be null.");
            }

            ProgrammeEditionEnrolment programmeEditionEnroll = _iProgrammeEditionEnrolmentFactory.newProgrammeEditionEnrollment(student, programmeEdition);

            return _programmeEditionEnrolments.add(programmeEditionEnroll);
    }

    public boolean isStudentEnrolledInThisProgrammeEdition(Student student, ProgrammeEdition programmeEdition) {
        if(student == null || programmeEdition == null) {
            return false;
        }
        for (ProgrammeEditionEnrolment enrollment : _programmeEditionEnrolments) {
            if (enrollment.hasSameStudent(student) && enrollment.hasSameProgrammeEdition(programmeEdition)) {
                return true;
            }
        }
        return false;
    }


    public int countStudentsInProgrammesFromDepartmentInSchoolYear(Department department, SchoolYear schoolYear) {
        Set<String> studentUniqueNumbers = new HashSet<>();

        for (ProgrammeEditionEnrolment enrollment : _programmeEditionEnrolments) {
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

        for(ProgrammeEditionEnrolment programmeEditionEnrolment : _programmeEditionEnrolments)
            if(programmeEditionEnrolment.findProgrammeEditionInEnrollment().equals(programmeEdition)){
                numberOfStudents++;
            }

        return numberOfStudents;
    }

    public List<ProgrammeEdition> findProgrammeEditionsThatStudentIsEnrolled(Student student){
        List<ProgrammeEdition> list = new ArrayList<>();

        for(ProgrammeEditionEnrolment programmeEditionEnrolment : _programmeEditionEnrolments){
            if(programmeEditionEnrolment.findStudentInProgrammeEdition().equals(student)){
                ProgrammeEdition programmeEdition = programmeEditionEnrolment.findProgrammeEditionInEnrollment();
                list.add(programmeEdition);
            }
        }
        return list;
    }
}