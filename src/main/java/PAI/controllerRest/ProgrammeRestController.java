package PAI.controllerRest;

import PAI.VOs.*;
import PAI.assembler.programme.IProgrammeAssembler;
import PAI.assembler.programme.IProgrammeDirectorAssembler;
import PAI.assembler.programme.IProgrammeHATEOASAssembler;
import PAI.assembler.programmeEnrolment.IProgrammeEnrolmentAssembler;
import PAI.assembler.student.IStudentDTOAssembler;
import PAI.assembler.studyPlan.IStudyPlanAssembler;
import PAI.domain.programme.Programme;
import PAI.dto.Programme.*;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentIdDTO;
import PAI.dto.student.StudentIDDTO;
import PAI.dto.studyPlan.RegisterStudyPlanCommand;
import PAI.dto.studyPlan.StudyPlanResponseDTO;
import PAI.exception.BusinessRuleViolationException;
import PAI.exception.ErrorResponse;
import PAI.mapper.student.IStudentMapper;
import PAI.service.programme.IProgrammeService;
import PAI.service.programmeEnrolment.IProgrammeEnrolmentService;
import PAI.service.studyPlan.IStudyPlanService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/programmes")
public class ProgrammeRestController {

    private final IProgrammeService _programmeService;
    private final IProgrammeAssembler _programmeAssembler;
    private final IProgrammeEnrolmentService _programmeEnrolmentService;
    private final IProgrammeEnrolmentAssembler _programmeEnrolmentAssembler;
    private final IStudyPlanService _studyPlanService;
    private final IStudyPlanAssembler _studyPlanAssembler;
    private final IProgrammeDirectorAssembler _programmeDirectorAssembler;
    private final IProgrammeHATEOASAssembler _programmeHATEOASAssembler;
    private final IStudentDTOAssembler _studentAssembler;

    public ProgrammeRestController (IProgrammeService programmeService, IProgrammeAssembler programmeAssembler,
                                    IProgrammeEnrolmentService programmeEnrolmentService, IStudyPlanService studyPlanService,
                                    IStudyPlanAssembler studyPlanAssembler, IProgrammeDirectorAssembler programmeDirectorAssembler,
                                    IProgrammeHATEOASAssembler programmeHATEOASAssembler, IProgrammeEnrolmentAssembler programmeEnrolmentAssembler,
                                    IStudentDTOAssembler studentAssembler){

        this._programmeService = programmeService;
        this._programmeAssembler = programmeAssembler;
        this._programmeEnrolmentService = programmeEnrolmentService;
        this._studyPlanService = studyPlanService;
        this._studyPlanAssembler = studyPlanAssembler;
        this._programmeDirectorAssembler = programmeDirectorAssembler;
        this._programmeHATEOASAssembler = programmeHATEOASAssembler;
        this._programmeEnrolmentAssembler = programmeEnrolmentAssembler;
        this._studentAssembler = studentAssembler;
    }

    @PostMapping()
    public ResponseEntity<?> registerProgramme (@Valid @RequestBody ProgrammeDTO programmeDTO) throws Exception {

        ProgrammeVOsDTO programmeVOsDto = _programmeAssembler.fromDTOToDomain(programmeDTO);
        Programme programmeCreated = _programmeService.registerProgramme(programmeVOsDto);
        ProgrammeDTO newProgrammeDTO = _programmeAssembler.fromDomainToDTO(programmeCreated);
        EntityModel<ProgrammeDTO> programmeEntityModel = _programmeHATEOASAssembler.toModel(newProgrammeDTO);

        return new ResponseEntity<>(programmeEntityModel, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/studyPlans")
    public ResponseEntity<?> registerStudyPlan(@PathVariable("id") String programmeAcronym, @RequestParam LocalDate studyPlanStartDate) {
        try {
            RegisterStudyPlanCommand studyPlanCommand = _studyPlanAssembler.toCommand(programmeAcronym, studyPlanStartDate);

            StudyPlanResponseDTO responseDTO = _studyPlanAssembler.toResponseDTO(_studyPlanService.createStudyPlan(studyPlanCommand));
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.toString(),"Error Registering Study Plan: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } catch (BusinessRuleViolationException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.toString(),"Error Registering Study Plan: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), "Error Registering Study Plan: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/ids")
    public ResponseEntity<List<ProgrammeIDResponseDTO>> getAllProgrammeIDDTOs (){
        List<ProgrammeIDDTO> programmeIDDTOS = _programmeService.getAllProgrammeIDDTOs();
        if(!programmeIDDTOS.isEmpty()) {
            List<ProgrammeIDResponseDTO> response = new ArrayList<>();
            for (ProgrammeIDDTO programmeIDDTO : programmeIDDTOS) {
                response.add(_programmeAssembler.toResponseDTO(programmeIDDTO));
            }
            return ResponseEntity.ok(response);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/assigndirector")
    public ResponseEntity<Void> assignProgrammeDirector(@RequestBody ProgrammeDirectorRequestDTO dto) {
        if (dto == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            ProgrammeDirectorVOsDTO vosDTO = _programmeDirectorAssembler.fromDTOToDomain(dto);
            ProgrammeID programmeID = new ProgrammeID(vosDTO.getProgrammeAcronym());
            TeacherID teacherID = new TeacherID(vosDTO.getTeacherAcronym());
            _programmeService.changeProgrammeDirector(programmeID, teacherID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/degreeType/{degreeTypeID}")
    public ResponseEntity<List<ProgrammeIDDTO>> getProgrammesByDegreeTypeID(@PathVariable String degreeTypeID) {
        try {
            DegreeTypeID id = new DegreeTypeID(degreeTypeID);
            List<ProgrammeIDDTO> programmeIDDTOs = _programmeService.getProgrammeIDDTOsByDegreeTypeID(id);

            if (programmeIDDTOs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(programmeIDDTOs);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getProgrammeByID (@PathVariable("id") String acronym){

        Acronym acronym1 = new Acronym(acronym);
        ProgrammeID programmeID = new ProgrammeID(acronym1);

        Optional<Programme> programmeDTO = _programmeService.getProgrammeByID(programmeID);

        if (programmeDTO.isPresent()) {
            return ResponseEntity.ok(programmeDTO.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Programme not found");
        }

    }

    @GetMapping
    public ResponseEntity<?> getAllProgrammes() {
        try {
            Iterable<ProgrammeDTO> programmeDTOS = _programmeService.getAllProgrammes();
            return (ResponseEntity.ok(programmeDTOS));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }

    @GetMapping("/{id}/programmes-enrolled-in")
    public ResponseEntity<List<ProgrammeEnrolmentIdDTO>> getAllProgrammesThatTheStudentIsEnrolledIn(@PathVariable("id") String id) {
        try {
            StudentIDDTO dto = new StudentIDDTO(id);
            StudentID studentID = _studentAssembler.toIdDTO(dto);
            List<ProgrammeEnrolmentID> programmeEnrolmentID = _programmeEnrolmentService.listOfProgrammesStudentIsEnrolledIn(studentID);
            return ResponseEntity.ok(_programmeEnrolmentAssembler.toListOfDTOs(programmeEnrolmentID));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
