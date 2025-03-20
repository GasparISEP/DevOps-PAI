package PAI.repository;

import PAI.domain.*;
import PAI.factory.ProgrammeEnrolmentFactory;
import PAI.factory.ProgrammeEnrolmentListFactory;

import java.util.List;

public class ProgrammeEnrolmentRepository {

    private List<ProgrammeEnrolment> _enrolmentList;
    private ProgrammeEnrolmentFactory _programmeEnrolmentFactory;

    public ProgrammeEnrolmentRepository(ProgrammeEnrolmentFactory programmeEnrolmentFactory, ProgrammeEnrolmentListFactory programmeEnrolmentList){

        if(programmeEnrolmentFactory == null || programmeEnrolmentList == null)
            throw new IllegalArgumentException("Factory cannot be null!");

        _enrolmentList = programmeEnrolmentList.newArrayList();
        _programmeEnrolmentFactory = programmeEnrolmentFactory;
    }

    public boolean enrolStudents(Student student, AccessMethod accessMethod, Programme programme, String enrolmentDate) throws Exception {

        ProgrammeEnrolment newProgrammeEnrolment = _programmeEnrolmentFactory.createProgrammeEnrolment (student, accessMethod, programme, enrolmentDate);

        //Checks if Enrolment is repeated
        if(!isEnrolmentRepeated(newProgrammeEnrolment)) {
            _enrolmentList.add(newProgrammeEnrolment);
            return true;
        } else
            throw new Exception("Student is already enrolled in the programme.");
    }

    private boolean isEnrolmentRepeated(ProgrammeEnrolment newEnrolment) {
        for (ProgrammeEnrolment enrolment : _enrolmentList) {
            if (enrolment.hasSameEnrolment(newEnrolment)) {
                return true; // Return true if a repeated enrolment is found
            }
        }
        return false; // Return false if no repeated enrolment is found
    }

    public boolean isStudentEnrolled (Student student, Programme programme) {
        for (ProgrammeEnrolment existingEnrolment : _enrolmentList) {
            if (existingEnrolment.hasSameStudent(student) && existingEnrolment.hasSameProgramme(programme)) {
                return true;
            }
        }
        return false;
    }
}
