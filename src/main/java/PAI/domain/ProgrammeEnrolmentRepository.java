package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeEnrolmentRepository {

    private List<ProgrammeEnrolment> _enrolmentList;

    public ProgrammeEnrolmentRepository(){
        _enrolmentList = new ArrayList<>();
    }

    public boolean enrolStudents(Student student, AccessMethod accessMethod, Programme programme, String enrolmentDate) throws Exception {

        ProgrammeEnrolment newProgrammeEnrolment = new ProgrammeEnrolment (student, accessMethod, programme, enrolmentDate);

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
            if (existingEnrolment.findStudentInEnrollments().getUniqueNumber() ==
                    student.getUniqueNumber() && existingEnrolment.getProgramme().equals(programme)) {
                return true;
            }
        }
        return false;
    }
}
