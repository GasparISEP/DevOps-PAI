package PAI.controllerRest;


import PAI.VOs.StudentID;
import PAI.dto.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentDTO;
import PAI.service.programmeEditionEnrolment.IStudentProgrammeEditionEnrolmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}