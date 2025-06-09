package PAI.controllerRest;

import PAI.VOs.*;
import PAI.assembler.course.ICourseAssembler;
import PAI.assembler.courseEdition.ICourseEditionAssembler;
import PAI.assembler.programmeEdition.IProgrammeEditionControllerAssembler;
import PAI.dto.course.CourseIDDTO;
import PAI.dto.programmeEdition.*;
import PAI.service.programmeEdition.IProgrammeEditionService;
import PAI.service.programmeEnrolment.IAvailableCoursesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/programmeeditions")
public class ProgrammeEditionRestController {

    private final IProgrammeEditionService programmeEditionService;
    private final IProgrammeEditionControllerAssembler programmeEditionControllerAssembler;
    private final IAvailableCoursesService availableCoursesService;
    private final ICourseAssembler courseAssembler;

    public ProgrammeEditionRestController(IProgrammeEditionService programmeEditionService, IProgrammeEditionControllerAssembler programmeEditionControllerAssembler,IAvailableCoursesService availableCoursesService, ICourseAssembler courseAssembler) {
        if (programmeEditionService == null) {
            throw new IllegalArgumentException("ProgrammeEdition service cannot be null");
        }
        if (programmeEditionControllerAssembler == null) {
            throw new IllegalArgumentException("ProgrammeEdition Controller Assembler cannot be null");
        }
        if(availableCoursesService == null){
            throw new IllegalArgumentException("Available courses cannot be null");
        }
        if(courseAssembler == null){
            throw new IllegalArgumentException("Course Assembler cannot be null");
        }

        this.programmeEditionService = programmeEditionService;
        this.programmeEditionControllerAssembler = programmeEditionControllerAssembler;
        this.availableCoursesService = availableCoursesService;
        this.courseAssembler = courseAssembler;
    }

    @GetMapping
    public ResponseEntity<List<CountStudentsDto>> getAllProgrammeEditions() throws Exception {
        List<CountStudentsDto> programmeEditionDtos = programmeEditionService
                .findAllProgrammeEditions()
                .stream()
                .map(programmeEditionControllerAssembler::toCountDTO)
                .toList();
        return ResponseEntity.ok(programmeEditionDtos);
    }

    @GetMapping("/{id}/{schoolYearID}/students")
    public ResponseEntity<Integer> getNumberOfStudents(
            @PathVariable("id") String programmeAcronym,
            @PathVariable("schoolYearID") String schoolYearID) throws Exception {

        CountStudentsDto dto =
                new CountStudentsDto(programmeAcronym, schoolYearID);

        int totalStudents = programmeEditionService.countTotalNumberOfStudentsInAProgrammeEdition(dto);

        return ResponseEntity.ok(totalStudents);
    }

    @GetMapping("/programme/{programmeid}")
    public ResponseEntity<List<ProgrammeEditionServiceDTO>> getProgrammeEditionsByProgrammeID(
            @PathVariable("programmeid") String programmeAcronym) throws Exception {

        ProgrammeID programmeID = new ProgrammeID(
                new Acronym(programmeAcronym)
        );

        List<ProgrammeEditionServiceDTO> dtos = programmeEditionService
                .getProgrammeEditionIDsByProgrammeID(programmeID)
                .stream()
                .map(id -> programmeEditionControllerAssembler.toDTOFromIDs(id.getProgrammeID(), id.getSchoolYearID()))
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PostMapping()
    public ResponseEntity<?> createAProgrammeEditionForTheCurrentSchoolYear(@Valid @RequestBody ProgrammeEditionRequestDTO requestDto) {
        try {
            ProgrammeEditionServiceDTO programmeEditionServiceDTO = programmeEditionControllerAssembler.toDTO(requestDto);
            ProgrammeEditionServiceDTO serviceResult = programmeEditionService.createProgrammeEditionAndSave(programmeEditionServiceDTO);
            ProgrammeEditionResponseDTO response = programmeEditionControllerAssembler.toResponseDTO(serviceResult);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping ("/available-courses")
    public ResponseEntity<List<CourseIDDTO>> getAvailableCourses(@RequestBody ProgrammeEditionIdDto programmeEditionIdDto){
        try {
            Acronym acronym = new Acronym(programmeEditionIdDto.programmeAcronym());
            ProgrammeID programmeID = new ProgrammeID(acronym);
            SchoolYearID schoolYearID = new SchoolYearID(UUID.fromString(programmeEditionIdDto.schoolYearId()));
            ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID,schoolYearID);
            List<CourseID> courseIDS = availableCoursesService.getListOfCourseIdForAGivenProgrammeEdition(programmeEditionID);
            List<CourseIDDTO> responseDTOS = courseAssembler.toDTOList(courseIDS);
            return ResponseEntity.ok(responseDTOS);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}

