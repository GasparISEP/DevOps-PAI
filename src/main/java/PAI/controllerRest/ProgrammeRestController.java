package PAI.controllerRest;

import PAI.VOs.*;
import PAI.assembler.programme.IProgrammeAssembler;
import PAI.assembler.programme.IProgrammeDirectorAssembler;
import PAI.assembler.programme.IProgrammeHATEOASAssembler;
import PAI.assembler.programmeEnrolment.IProgrammeEnrolmentAssembler;
import PAI.assembler.programmeEnrolment.IUS34ProgrammeEnrolmentAssembler;
import PAI.assembler.student.IStudentDTOAssembler;
import PAI.assembler.studyPlan.IStudyPlanAssembler;
import PAI.assembler.studyPlan.IStudyPlanHATEOASAssembler;
import PAI.domain.programme.Programme;
import PAI.domain.studyPlan.StudyPlan;
import PAI.dto.Programme.*;
import PAI.dto.programmeEnrolment.US34ListOfProgrammesDTO;
import PAI.dto.student.StudentIDDTO;
import PAI.dto.studyPlan.RegisterStudyPlanCommand;
import PAI.dto.studyPlan.StudyPlanDTO;
import PAI.dto.studyPlan.StudyPlanResponseDTO;
import PAI.dto.teacher.TeacherIdDTO;
import PAI.exception.BusinessRuleViolationException;
import PAI.exception.ErrorResponse;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/programmes")
public class ProgrammeRestController {

    private final IProgrammeService _programmeService;
    private final IProgrammeAssembler _programmeAssembler;
    private final IProgrammeEnrolmentService _programmeEnrolmentService;
    private final IProgrammeEnrolmentAssembler _programmeEnrolmentAssembler;
    private final IStudyPlanService _studyPlanService;
    private final IStudyPlanAssembler _studyPlanAssembler;
    private final IStudyPlanHATEOASAssembler _studyPlanHateoasAssembler;
    private final IProgrammeDirectorAssembler _programmeDirectorAssembler;
    private final IProgrammeHATEOASAssembler _programmeHATEOASAssembler;
    private final IStudentDTOAssembler _studentAssembler;
    private final IUS34ProgrammeEnrolmentAssembler _us34Assembler;

    public ProgrammeRestController (IProgrammeService programmeService, IProgrammeAssembler programmeAssembler,
                                    IProgrammeEnrolmentService programmeEnrolmentService, IStudyPlanService studyPlanService,
                                    IStudyPlanAssembler studyPlanAssembler, IProgrammeDirectorAssembler programmeDirectorAssembler,
                                    IProgrammeHATEOASAssembler programmeHATEOASAssembler, IProgrammeEnrolmentAssembler programmeEnrolmentAssembler,
                                    IStudentDTOAssembler studentAssembler, IUS34ProgrammeEnrolmentAssembler us34Assembler,
                                    IStudyPlanHATEOASAssembler studyPlanHateoasAssembler){

        this._programmeService = programmeService;
        this._programmeAssembler = programmeAssembler;
        this._programmeEnrolmentService = programmeEnrolmentService;
        this._studyPlanService = studyPlanService;
        this._studyPlanAssembler = studyPlanAssembler;
        this._studyPlanHateoasAssembler = studyPlanHateoasAssembler;
        this._programmeDirectorAssembler = programmeDirectorAssembler;
        this._programmeHATEOASAssembler = programmeHATEOASAssembler;
        this._programmeEnrolmentAssembler = programmeEnrolmentAssembler;
        this._studentAssembler = studentAssembler;
        this._us34Assembler = us34Assembler;
    }

    @PostMapping()
    public ResponseEntity<EntityModel<ProgrammeIDDTO>> registerProgramme (@Valid @RequestBody ProgrammeDTO programmeDTO) throws Exception {

        ProgrammeVOsDTO programmeVOsDto = _programmeAssembler.fromDTOToDomain(programmeDTO);
        Programme programmeCreated = _programmeService.registerProgramme(programmeVOsDto);
        ProgrammeID programmeID = programmeCreated.identity();
        ProgrammeIDDTO programmeIDDTO = _programmeAssembler.toDTO(programmeID);
        EntityModel<ProgrammeIDDTO> programmeEntityModel = _programmeHATEOASAssembler.toModel(programmeIDDTO);

        return new ResponseEntity<>(programmeEntityModel, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/studyPlans")
    public ResponseEntity<?> registerStudyPlan(@PathVariable("id") String programmeAcronym, @RequestParam(name = "startDate") LocalDate studyPlanStartDate) {
        try {
            RegisterStudyPlanCommand studyPlanCommand = _studyPlanAssembler.toCommand(programmeAcronym, studyPlanStartDate);

            StudyPlanResponseDTO responseDTO = _studyPlanAssembler.toResponseDTO(_studyPlanService.createStudyPlan(studyPlanCommand));

            EntityModel<StudyPlanResponseDTO> studyPlanEntityModel = _studyPlanHateoasAssembler.toModel(responseDTO);

            return new ResponseEntity<>(studyPlanEntityModel, HttpStatus.CREATED);

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
        if (programmeIDDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ProgrammeIDResponseDTO> response = programmeIDDTOS.stream()
                .map(_programmeAssembler::toResponseDTO)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/director")
    public ResponseEntity<?> getProgrammeDirector(@PathVariable("id") String acronym) {
        try {
            Acronym programmeAcronym = new Acronym(acronym);
            ProgrammeID programmeID = new ProgrammeID(programmeAcronym);

            Optional<TeacherID> directorIDOpt = _programmeService.getProgrammeDirectorByProgrammeID(programmeID);

            if (directorIDOpt.isPresent()) {
                TeacherID directorID = directorIDOpt.get();

                TeacherIdDTO directorDTO = new TeacherIdDTO(directorID.getTeacherAcronym().getAcronym());

                return ResponseEntity.ok(directorDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Programme director not found");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid programme ID");
        }
    }

    @PatchMapping("/{programmeId}/director")
    public ResponseEntity<Void> assignProgrammeDirector(
            @PathVariable("programmeId") String programmeId,
            @Valid @RequestBody ProgrammeDirectorRequestDTO dto) {

        try {
            ProgrammeID programmeID = new ProgrammeID(new Acronym(programmeId));
            TeacherID teacherID = new TeacherID(new TeacherAcronym(dto.teacher().acronym()));

            _programmeService.changeProgrammeDirector(programmeID, teacherID);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
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

        Optional<ProgrammeDTO> programmeDTO = _programmeService.getProgrammeByID(programmeID);

        return programmeDTO.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Programme not found"));

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
    public ResponseEntity<US34ListOfProgrammesDTO> getAllProgrammesThatTheStudentIsEnrolledIn(@PathVariable("id") String id) {
        try {
            StudentIDDTO dto = new StudentIDDTO(id);
            StudentID studentID = _studentAssembler.toIdDTO(dto);
            US34ListOfProgrammes programmeEnrolment = _programmeEnrolmentService.getProgrammesStudentIsEnrolled(studentID);
            US34ListOfProgrammesDTO dtoResult = _us34Assembler.toDto(programmeEnrolment);
            return ResponseEntity.ok(dtoResult);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/studyPlans/{uuid}")
    public ResponseEntity<?> getStudyPlanByGeneratedID(@PathVariable("uuid") UUID studyPlanUuid) {
        try {
            StudyPlanGeneratedID spGeneratedID = new StudyPlanGeneratedID(studyPlanUuid);

            Optional<StudyPlan> studyPlanOpt = _studyPlanService.findByGeneratedUUID(spGeneratedID);

            if(studyPlanOpt.isPresent()) {
                StudyPlan studyPlan = studyPlanOpt.get();
                StudyPlanDTO studyPlanDTO = _studyPlanAssembler.toDTO(studyPlan);
                StudyPlanResponseDTO studyPlanResponseDTO = _studyPlanAssembler.toResponseDTO(studyPlanDTO);

                return ResponseEntity.ok(studyPlanResponseDTO);
            }
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Study Plan not found");

        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), "Error retrieving Study Plan for " + e.getMessage());
            return new ResponseEntity<> (errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/studyPlans")
    public ResponseEntity<?> getStudyPlansForProgramme(@PathVariable("id") String programmeId) {
        try {
            Acronym programmeAcronym = new Acronym(programmeId);
            ProgrammeID programmeID = new ProgrammeID(programmeAcronym);

            List<StudyPlan> studyPlanList = _studyPlanService.getStudyPlansByProgrammeID(programmeID);

            List<StudyPlanResponseDTO> responseDTOs = studyPlanList.stream()
                    .map(_studyPlanAssembler::toDTO)
                    .map(_studyPlanAssembler::toResponseDTO)
                    .toList();
            return ResponseEntity.ok(responseDTOs);

        } catch (EntityNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.toString(), "Programme not found: " + e.getMessage());
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } catch (BusinessRuleViolationException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.toString(), "Programme not found: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), "Error retrieving Study Plans: " + e.getMessage());
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
