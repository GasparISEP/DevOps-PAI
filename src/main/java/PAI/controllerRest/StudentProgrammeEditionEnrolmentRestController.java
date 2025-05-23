package PAI.controllerRest;


import PAI.VOs.*;
import PAI.dto.programmeEditionEnrolment.ProgrammeEditionEnrolmentRequest;
import PAI.dto.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentDTO;
import PAI.service.programmeEditionEnrolment.IStudentProgrammeEditionEnrolmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentProgrammeEditionEnrolmentRestController {

    private final IStudentProgrammeEditionEnrolmentService applicationService;

    public StudentProgrammeEditionEnrolmentRestController(IStudentProgrammeEditionEnrolmentService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/{studentId}/programme-editions")
    public ResponseEntity<List<StudentProgrammeEditionEnrolmentDTO>> getProgrammeEditionsStudentCanEnrollIn(
            @PathVariable String studentId) {

        try {
            StudentID sid = new StudentID(Integer.parseInt(studentId));
            List<StudentProgrammeEditionEnrolmentDTO> result = applicationService.findAvailableProgrammeEditionsForStudent(sid);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/programme-edition-enrolments")
    public ResponseEntity<Void> enrolStudentInProgrammeEdition(@RequestBody ProgrammeEditionEnrolmentRequest request) {
        try {
            StudentID studentID = new StudentID(Integer.parseInt(request.getStudentId()));
            ProgrammeID programmeID = new ProgrammeID(
                    new NameWithNumbersAndSpecialChars(request.getProgrammeName()),
                    new Acronym(request.getProgrammeAcronym())
            );
            SchoolYearID schoolYearID = new SchoolYearID(UUID.fromString(request.getSchoolYearId()));

            applicationService.enrolStudentInProgrammeEdition(studentID, programmeID, schoolYearID);

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}