package PAI.repository;

import PAI.VOs.AccessMethodID;
import PAI.VOs.Date;
import PAI.VOs.ProgrammeID;
import PAI.VOs.StudentID;
import PAI.domain.*;
import PAI.factory.IProgrammeEnrolmentFactory;
import PAI.factory.IProgrammeEnrolmentListFactory;

import java.util.List;

public class ProgrammeEnrolmentRepository {

    private List<ProgrammeEnrolment> _enrolmentList;
    private IProgrammeEnrolmentFactory _programmeEnrolmentFactory;

    public ProgrammeEnrolmentRepository(IProgrammeEnrolmentFactory programmeEnrolmentFactory, IProgrammeEnrolmentListFactory programmeEnrolmentList){

        if(programmeEnrolmentFactory == null || programmeEnrolmentList == null)
            throw new IllegalArgumentException("Factory cannot be null!");

        _enrolmentList = programmeEnrolmentList.newArrayList();
        _programmeEnrolmentFactory = programmeEnrolmentFactory;
    }

    public boolean enrolStudents(StudentID studentID, AccessMethodID accessMethodID, ProgrammeID programmeID, Date enrolmentDate) throws Exception {

        ProgrammeEnrolment newProgrammeEnrolment = _programmeEnrolmentFactory.createProgrammeEnrolment(studentID, accessMethodID, programmeID, enrolmentDate);

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

    public boolean isStudentEnrolled (Student student, Programme programmeID) {
        for (ProgrammeEnrolment existingEnrolment : _enrolmentList) {
            if (existingEnrolment.hasSameStudent(student) && existingEnrolment.hasSameProgramme(programmeID)) {
                return true;
            }
        }
        return false;
    }
}
