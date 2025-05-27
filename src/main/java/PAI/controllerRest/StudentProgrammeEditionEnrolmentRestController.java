package PAI.controllerRest;


import PAI.VOs.*;
import PAI.assembler.programmeEditionEnrolment.IProgrammeEditionEnrolmentAssembler;
import PAI.dto.programmeEditionEnrolment.ProgrammeEditionEnrolmentDetailDto;
import PAI.dto.programmeEditionEnrolment.ProgrammeEditionEnrolmentRequest;
import PAI.dto.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentDTO;
import PAI.service.programmeEditionEnrolment.IProgrammeEditionEnrolmentService;
import PAI.service.programmeEditionEnrolment.IStudentProgrammeEditionEnrolmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentProgrammeEditionEnrolmentRestController {

    private final IStudentProgrammeEditionEnrolmentService applicationService;
    private final IProgrammeEditionEnrolmentAssembler programmeEditionEnrolmentAssembler;
    private final IProgrammeEditionEnrolmentService programmeEditionEnrolmentService;

    public StudentProgrammeEditionEnrolmentRestController(IStudentProgrammeEditionEnrolmentService applicationService, IProgrammeEditionEnrolmentAssembler programmeEditionEnrolmentAssembler, IProgrammeEditionEnrolmentService programmeEditionEnrolmentService) {
        if (applicationService == null) {
            throw new IllegalArgumentException("Application service cannot be null");
        }
        if (programmeEditionEnrolmentAssembler == null) {
            throw new IllegalArgumentException("Programme edition enrolment assembler cannot be null");
        }
        if (programmeEditionEnrolmentService == null) {
            throw new IllegalArgumentException("Programme edition enrolment service cannot be null");
        }
        this.applicationService = applicationService;
        this.programmeEditionEnrolmentAssembler = programmeEditionEnrolmentAssembler;
        this.programmeEditionEnrolmentService = programmeEditionEnrolmentService;
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

    @GetMapping("/programme-edition-enrolments/students") 
    public ResponseEntity<List<ProgrammeEditionEnrolmentDetailDto>> getProgrammeEditionEnrollmentsByStudentID(@RequestParam("studentId") int studentId) {
        try{
            StudentID studentID = new StudentID(studentId);
            List<ProgrammeEditionID> programmeEditionIDs = programmeEditionEnrolmentService.getProgrammeEditionEnrolmentsByStudentID(studentID);
            List<ProgrammeEditionEnrolmentDetailDto> programmeEditionEnrolments = programmeEditionEnrolmentAssembler.toDtoList(programmeEditionIDs, studentID);
            return ResponseEntity.ok(programmeEditionEnrolments);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}