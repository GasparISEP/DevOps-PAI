package PAI.controllerRest;

import PAI.VOs.*;
import PAI.assembler.programme.IProgrammeAssembler;
import PAI.assembler.programme.IProgrammeDirectorAssembler;
import PAI.assembler.studyPlan.IStudyPlanAssembler;
import PAI.domain.programme.Programme;
import PAI.domain.teacher.Teacher;
import PAI.dto.Programme.*;
import PAI.dto.studyPlan.RegisterStudyPlanCommand;
import PAI.dto.studyPlan.StudyPlanResponseDTO;
import PAI.exception.BusinessRuleViolationException;
import PAI.exception.ErrorResponse;
import PAI.service.programme.IProgrammeService;
import PAI.service.studyPlan.IStudyPlanService;
import PAI.service.teacher.ITeacherService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
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
    private final IStudyPlanService _studyPlanService;
    private final IStudyPlanAssembler _studyPlanAssembler;
    private final IProgrammeDirectorAssembler _programmeDirectorAssembler;
    private final ITeacherService _teacherService;

    public ProgrammeRestController (IProgrammeService programmeService, IProgrammeAssembler programmeAssembler,
                                    IStudyPlanService studyPlanService, IStudyPlanAssembler studyPlanAssembler,
                                    IProgrammeDirectorAssembler programmeDirectorAssembler, ITeacherService teacherService){

        if (programmeService == null) {
            throw new IllegalArgumentException("ProgrammeService cannot be null.");
        }
        if (programmeAssembler == null) {
            throw new IllegalArgumentException("ProgrammeAssembler cannot be null.");
        }
        if (studyPlanService == null) {
            throw new IllegalArgumentException("StudyPlanService cannot be null.");
        }
        if (studyPlanAssembler == null) {
            throw new IllegalArgumentException("StudyPlanAssembler cannot be null.");
        }
        if (programmeDirectorAssembler == null) throw new IllegalArgumentException("ProgrammeDirectorAssembler cannot be null.");
        if (teacherService == null) throw new IllegalArgumentException("TeacherService cannot be null.");

        this._programmeService = programmeService;
        this._programmeAssembler = programmeAssembler;
        this._studyPlanService = studyPlanService;
        this._studyPlanAssembler = studyPlanAssembler;
        this._programmeDirectorAssembler = programmeDirectorAssembler;
        this._teacherService = teacherService;
    }

    @PostMapping()
    public ResponseEntity<?> registerProgramme (@Valid @RequestBody ProgrammeDTO programmeDTO) throws Exception {

        ProgrammeVOsDTO programmeVOsDto = _programmeAssembler.fromDTOToDomain(programmeDTO);
        Programme programmeCreated = _programmeService.registerProgramme(programmeVOsDto);
        ProgrammeDTO newProgrammeDTO = _programmeAssembler.fromDomainToDTO(programmeCreated);

        return new ResponseEntity<>(newProgrammeDTO, HttpStatus.CREATED);
    }

    @PostMapping("/{programme-name}/{programme-acronym}/studyPlans")
    public ResponseEntity<?> registerStudyPlan(@PathVariable String programmeName, @PathVariable String programmeAcronym, @RequestParam LocalDate studyPlanStartDate) {
        try {
            RegisterStudyPlanCommand studyPlanCommand = _studyPlanAssembler.toCommand(programmeName, programmeAcronym, studyPlanStartDate);

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

    @GetMapping("/assign-director")
    public ResponseEntity<ProgrammeDirectorResponseDTO> getProgrammeDirectorInfo() {
        try {
            List<Programme> programmes = (List<Programme>) _programmeService.findAll();
            List<Teacher> teachers = (List<Teacher>) _teacherService.getAllTeachers();
            ProgrammeDirectorResponseDTO response = _programmeDirectorAssembler.fromDomainToDTO(programmes, teachers);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/assign-director")
    public ResponseEntity<Void> assignProgrammeDirector(@RequestBody ProgrammeDirectorRequestDTO dto) {
        if (dto == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            ProgrammeDirectorVOsDTO vosDTO = _programmeDirectorAssembler.fromDTOToDomain(dto);
            ProgrammeID programmeID = new ProgrammeID(vosDTO.getProgrammeName(), vosDTO.getProgrammeAcronym());
            TeacherID teacherID = new TeacherID(vosDTO.getTeacherAcronym());
            _programmeService.changeProgrammeDirector(programmeID, teacherID);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{degreeTypeID}")
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

    @GetMapping("/{name}/{acronym}")
    public ResponseEntity<Object> getProgrammeByID (@PathVariable("name") String name,
                                                    @PathVariable("acronym") String acronym){

        Acronym acronym1 = new Acronym(acronym);
        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars(name);
        ProgrammeID programmeID = new ProgrammeID(name1, acronym1);

        Optional<Programme> programmeDTO = _programmeService.getProgrammeByID(programmeID);

        if (programmeDTO.isPresent()) {
            return ResponseEntity.ok(programmeDTO.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Programme not found");
        }

    }

}
