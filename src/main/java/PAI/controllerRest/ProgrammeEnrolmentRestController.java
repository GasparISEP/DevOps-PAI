package PAI.controllerRest;


import PAI.VOs.*;
import PAI.assembler.programmeEdition.IProgrammeEditionControllerAssembler;
import PAI.assembler.programmeEnrolment.IProgrammeEnrolmentAssembler;
import PAI.assembler.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear.ITotalEnrolledStudentsAssembler;
import PAI.dto.programmeEdition.ProgrammeEditionIdDto;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentIdDTO;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsCommand;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsRequest;
import PAI.service.programmeEditionEnrolment.IStudentProgrammeEditionEnrolmentService;
import PAI.service.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear.ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/programme-enrolments")
public class ProgrammeEnrolmentRestController {

    private final ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler;
    private final ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService totalEnrolledStudentsService;
    private final IProgrammeEnrolmentAssembler iprogrammeEnrolmentAssembler;
    private final IStudentProgrammeEditionEnrolmentService iStudentProgrammeEnrolmentService;
    private final IProgrammeEditionControllerAssembler iProgrammeEditionControllerAssembler;


    public ProgrammeEnrolmentRestController(ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler, ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService totalEnrolledStudentsService, IProgrammeEnrolmentAssembler programmeEnrolmentAssembler, IStudentProgrammeEditionEnrolmentService studentProgrammeEnrolmentService, IProgrammeEditionControllerAssembler programmeEditionControllerAssembler) {
        this.totalEnrolledStudentsAssembler = totalEnrolledStudentsAssembler;
        this.totalEnrolledStudentsService = totalEnrolledStudentsService;
        this.iprogrammeEnrolmentAssembler = programmeEnrolmentAssembler;
        this.iStudentProgrammeEnrolmentService = studentProgrammeEnrolmentService;
        this.iProgrammeEditionControllerAssembler = programmeEditionControllerAssembler;

    }

    @PostMapping
    public ResponseEntity<String> testPost(@RequestBody ProgrammeEnrolmentIdDTO dto) {
        return ResponseEntity.ok("Received: " + dto.toString());
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

    @GetMapping("/{programmeEnrolmentGID}/available-programme-editions")
    public ResponseEntity<List<ProgrammeEditionIdDto>> getProgrammeEditionsWhereStudentCanBeEnrolled(
            @PathVariable("programmeEnrolmentGID") UUID programmeEnrolmentGID) {
        try {
            ProgrammeEnrolmentIdDTO dto = new ProgrammeEnrolmentIdDTO(programmeEnrolmentGID);

            ProgrammeEnrolmentGeneratedID programmeEnrolmentGeneratedID = iprogrammeEnrolmentAssembler.toProgrammeEnrolmentGeneratedID(dto);

            LocalDate programmeEnrolmentDate = iStudentProgrammeEnrolmentService.findDateByProgrammeEnrolmentGeneratedID(programmeEnrolmentGeneratedID);

            ProgrammeID programmeID = iStudentProgrammeEnrolmentService.findProgrammeIDByProgrammeEnrolmentGeneratedID(programmeEnrolmentGeneratedID);

            List<ProgrammeEditionID> editions = iStudentProgrammeEnrolmentService.getAvailableProgrammeEditions(programmeID, programmeEnrolmentDate);

            List<ProgrammeEditionIdDto> dtos = editions.stream()
                    .map(iProgrammeEditionControllerAssembler::toIdDto)
                    .toList();

            return ResponseEntity.ok(dtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}