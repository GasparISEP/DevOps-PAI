package PAI.controllerRest;

import PAI.VOs.*;
import PAI.assembler.course.ICourseAssembler;
import PAI.assembler.programmeEdition.IProgrammeEditionControllerAssembler;
import PAI.assembler.programmeEdition.IProgrammeEditionHateoasAssembler;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.course.CourseIDDTO;
import PAI.dto.programmeEdition.*;
import PAI.dto.programmeEdition.RequestServiceDto;
import PAI.dto.programmeEdition.ProgrammeEditionResponseServiceDTO;
import PAI.dto.programmeEdition.ProgrammeEditionRequestDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseDTO;
import PAI.service.programmeEdition.IProgrammeEditionService;
import PAI.service.programmeEnrolment.IAvailableCoursesService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static PAI.utils.ValidationUtils.validateNotNull;

@RestController
@RequestMapping("/programme-editions")
public class ProgrammeEditionRestController {

    private final IProgrammeEditionService programmeEditionService;
    private final IProgrammeEditionControllerAssembler programmeEditionControllerAssembler;
    private final IAvailableCoursesService availableCoursesService;
    private final ICourseAssembler courseAssembler;
    private final IProgrammeEditionHateoasAssembler hateoasAssembler;

    public ProgrammeEditionRestController(IProgrammeEditionService programmeEditionService, IProgrammeEditionControllerAssembler programmeEditionControllerAssembler,
                                          IAvailableCoursesService availableCoursesService, ICourseAssembler courseAssembler,IProgrammeEditionHateoasAssembler hateoasAssembler) {

        this.programmeEditionService = validateNotNull(programmeEditionService,"ProgrammeEditionService");
        this.programmeEditionControllerAssembler = validateNotNull(programmeEditionControllerAssembler, "ProgrammeEditionControllerAssembler");
        this.availableCoursesService = validateNotNull(availableCoursesService, "AvailableCoursesService");
        this.courseAssembler = validateNotNull(courseAssembler, "CourseAssembler");
        this.hateoasAssembler = validateNotNull(hateoasAssembler, "ProgrammeEditionHateoasAssembler");
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<ProgrammeEditionResponseDTO>>> getAllProgrammeEditions() {
        List<ProgrammeEditionResponseDTO> responseDTOs = programmeEditionService
                .findAllProgrammeEditions()
                .stream()
                .map(programmeEditionControllerAssembler::toResponseDTOFromServiceDTO)
                .toList();

        List<EntityModel<ProgrammeEditionResponseDTO>> models = responseDTOs.stream()
                .map(hateoasAssembler::toModel)
                .toList();

        return ResponseEntity.ok(models);
    }

    @GetMapping("/{id}/{schoolYearID}/students")
    public ResponseEntity<Integer> getNumberOfStudents(
            @PathVariable("id") String programmeAcronym,
            @PathVariable("schoolYearID") String schoolYearID)   {

        RequestServiceDto dto =
                new RequestServiceDto(programmeAcronym, schoolYearID);

        int totalStudents = programmeEditionService.countTotalNumberOfStudentsInAProgrammeEdition(dto);

        return ResponseEntity.ok(totalStudents);
    }

    @GetMapping("/programme/{programmeid}")
    public ResponseEntity<List<EntityModel<ProgrammeEditionResponseDTO>>> getProgrammeEditionsByProgrammeID(
            @PathVariable("programmeid") String programmeAcronym) {

        ProgrammeEditionRequestServiceDTO requestDTO = new ProgrammeEditionRequestServiceDTO(
                new ProgrammeIDDTO(programmeAcronym)
        );

        List<ProgrammeEditionResponseServiceDTO> serviceDTOs =
                programmeEditionService.getProgrammeEditionIDsByProgrammeID(requestDTO);

        List<ProgrammeEditionResponseDTO> dtos = serviceDTOs.stream()
                .map(programmeEditionControllerAssembler::toResponseDTOFromServiceDTO)
                .toList();

        List<EntityModel<ProgrammeEditionResponseDTO>> models = dtos.stream()
                .map(hateoasAssembler::toModel)
                .toList();

        return ResponseEntity.ok(models);
    }

    @PostMapping()
    public ResponseEntity<?> createAProgrammeEditionForTheCurrentSchoolYear(@Valid @RequestBody ProgrammeEditionRequestDTO requestDto) {
        try {
            ProgrammeEditionRequestServiceDTO programmeEditionRequestServiceDTO = programmeEditionControllerAssembler.toServiceDTOFromRequestDTO(requestDto);
            ProgrammeEditionResponseServiceDTO serviceResult = programmeEditionService.createProgrammeEditionAndSave(programmeEditionRequestServiceDTO);
            ProgrammeEditionResponseDTO response = programmeEditionControllerAssembler.toResponseDTOFromServiceDTO(serviceResult);
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

