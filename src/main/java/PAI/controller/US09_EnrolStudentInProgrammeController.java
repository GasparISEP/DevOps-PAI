package PAI.controller;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.service.programmeEnrolment.IProgrammeEnrolmentService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class US09_EnrolStudentInProgrammeController {

    private final IProgrammeEnrolmentService _enrolmentService;

    public US09_EnrolStudentInProgrammeController(IProgrammeEnrolmentService enrolmentService) {
        validateServiceArguments(enrolmentService);
        this._enrolmentService = enrolmentService;
    }

    private void validateServiceArguments(IProgrammeEnrolmentService enrolmentService) {
        if (enrolmentService == null) {
            throw new IllegalArgumentException("Services cannot be null.");
        }
    }

    public ProgrammeEnrolment enrolStudentInProgramme(int studentNumber, UUID accessMethodIDStr, String programmeName, String acronym, String enrolmentDate) {
        try {
            StudentID studentID = new StudentID(studentNumber);
            AccessMethodID accessMethodID = new AccessMethodID(accessMethodIDStr);
            Acronym acronymVO = new Acronym(acronym);
            ProgrammeID programmeID = new ProgrammeID(acronymVO);
            Date date = new Date(enrolmentDate);

            return _enrolmentService.enrolStudentInProgramme(studentID, accessMethodID, programmeID, date);
        } catch (Exception e) {
            System.err.println("Error enrolling student in programme: " + e.getMessage());
            return null;

        }
    }
}
