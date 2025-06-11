package PAI.controllerRest;

import PAI.assembler.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear.ITotalEnrolledStudentsAssembler;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsCommand;
import PAI.service.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear.ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/programmeEnrolment")
public class ProgrammeEnrolmentRestController {

    private final ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler;
    private final ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService totalEnrolledStudentsService;

    public ProgrammeEnrolmentRestController(ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler, ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService totalEnrolledStudentsService) {
        this.totalEnrolledStudentsAssembler = totalEnrolledStudentsAssembler;
        this.totalEnrolledStudentsService = totalEnrolledStudentsService;
    }

    @GetMapping("/departments/{departmentId}/schoolYears/{schoolYearId}/programme-enrolments/count")
    public ResponseEntity<?> countByDepartmentAndSchoolYear(@PathVariable String departmentId, @PathVariable String schoolYearId) {
        if (departmentId == null)
            return ResponseEntity.badRequest().body("departmentID cannot be null");
        if (schoolYearId == null)
            return ResponseEntity.badRequest().body("schoolYearID cannot be null");
        try {
            TotalEnrolledStudentsCommand command = totalEnrolledStudentsAssembler.fromRequestToCommand(departmentId, schoolYearId);
            int count = totalEnrolledStudentsService.getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(command);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }
}
