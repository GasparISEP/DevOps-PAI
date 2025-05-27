package PAI.controllerRest;

import PAI.VOs.*;
import PAI.assembler.courseInStudyPlan.CourseInStudyPlanAssemblerImpl;
import PAI.assembler.courseInStudyPlan.ICourseInStudyPlanAssembler;
import PAI.assembler.courseInStudyPlan.ICourseInStudyPlanBusinessAssembler;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanRequestDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanResponseDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;
import PAI.exception.BusinessRuleViolationException;
import PAI.service.courseEdition.ICreateCourseEditionService;
import PAI.service.courseInStudyPlan.IAddCourseToAProgrammeService;
import PAI.service.courseInStudyPlan.ICourseInStudyPlanService;
import PAI.service.studyPlan.IStudyPlanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course-in-study-plan")
public class CourseInStudyPlanRestController {

    private final ICourseInStudyPlanAssembler assembler;
    private final IAddCourseToAProgrammeService service;
    private final IStudyPlanService studyPlanService;
    private final ICourseInStudyPlanService courseInStudyPlanService;

    public CourseInStudyPlanRestController(ICourseInStudyPlanAssembler assembler, IAddCourseToAProgrammeService service,
                                           IStudyPlanService studyPlanService, ICourseInStudyPlanService courseInStudyPlanService) {
        this.service = service;
        this.assembler = assembler;
        this.studyPlanService = studyPlanService;
        this.courseInStudyPlanService = courseInStudyPlanService;
    }

    @PostMapping
    public ResponseEntity<CourseInStudyPlanResponseDTO> create(
            @Valid @RequestBody CourseInStudyPlanRequestDTO dtoRequest) {
        try {

            CourseInStudyPlanCommand command = assembler.toCommand(dtoRequest);

            CourseInStudyPlanServiceDTO courseInStudyPlanServiceDTO = service.addCourseToAProgramme(command);

            CourseInStudyPlanResponseDTO dtoResponse = assembler.toDTO(courseInStudyPlanServiceDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);

        } catch (IllegalArgumentException | BusinessRuleViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/{programmeID}")
    public ResponseEntity<List<CourseInStudyPlanResponseDTO>> getCoursesInStudyPlanByProgrammeID(
            @PathVariable String name,
            @PathVariable String acronym) throws Exception {

        ProgrammeID programmeIdVO = new ProgrammeID(
                new NameWithNumbersAndSpecialChars(name),
                new Acronym(acronym)
        );

        StudyPlanID latestStudyPlanID = studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeIdVO);

        List<CourseInStudyPlan> courses = courseInStudyPlanService.getCoursesByStudyPlanId(latestStudyPlanID);

        List<CourseInStudyPlanResponseDTO> responseDTOs = courses.stream()
                .map(assembler::toDTOFromEntity)
                .toList();

        return ResponseEntity.ok(responseDTOs);
    }
}

