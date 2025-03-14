package PAI.repository;

import PAI.domain.*;
import PAI.factory.IProgrammeEditionEnrollmentFactory;
import PAI.factory.IProgrammeEditionEnrollmentListFactory;



import java.util.*;

public class ProgrammeEditionEnrollmentRepository {

    private Set<ProgrammeEditionEnrollment> _programmeEditionEnrollments;
    private final IProgrammeEditionEnrollmentFactory _iProgrammeEditionEnrollmentFactory;

    public ProgrammeEditionEnrollmentRepository(IProgrammeEditionEnrollmentFactory iProgrammeEditionEnrollmentFactory,
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
        if(student == null || programmeEdition == null) {
            return false;
        }
        for (ProgrammeEditionEnrollment enrollment : _programmeEditionEnrollments) {
            if (enrollment.hasSameStudent(student) && enrollment.hasSameProgrammeEdition(programmeEdition)) {
                return true;
            }
        }
        return false;
    }


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