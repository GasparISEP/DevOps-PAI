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
import PAI.service.courseInStudyPlan.CourseInStudyPlanServiceImpl;
import PAI.service.courseInStudyPlan.IAddCourseToAProgrammeService;
import PAI.service.courseInStudyPlan.ICourseInStudyPlanService;
import PAI.service.studyPlan.IStudyPlanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static PAI.utils.ValidationUtils.validateNotNull;

@RestController
@RequestMapping("/course-in-study-plan")
public class CourseInStudyPlanRestController {

    private final ICourseInStudyPlanAssembler assembler;
    private final IAddCourseToAProgrammeService service;
    private final IStudyPlanService studyPlanService;
    private final ICourseInStudyPlanService courseInStudyPlanService;

    public CourseInStudyPlanRestController(ICourseInStudyPlanAssembler assembler,
                                           IAddCourseToAProgrammeService service,
                                           IStudyPlanService studyPlanService,
                                           ICourseInStudyPlanService courseInStudyPlanService) {
        this.assembler = validateNotNull(assembler, "CourseInStudyPlanAssembler");
        this.service = validateNotNull(service, "AddCourseToAProgrammeService");
        this.studyPlanService = validateNotNull(studyPlanService, "StudyPlanService");
        this.courseInStudyPlanService = validateNotNull(courseInStudyPlanService, "CourseInStudyPlanService");
    }

    @PostMapping
    public ResponseEntity<CourseInStudyPlanResponseDTO> create(
            @Valid @RequestBody CourseInStudyPlanRequestDTO dtoRequest) throws Exception {
        CourseInStudyPlanCommand command = assembler.toCommand(dtoRequest);
        CourseInStudyPlanServiceDTO courseInStudyPlanServiceDTO = service.addCourseToAProgramme(command);
        CourseInStudyPlanResponseDTO dtoResponse = assembler.toDTO(courseInStudyPlanServiceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }

    @GetMapping("/{acronym}/{name}")
    public ResponseEntity<List<CourseInStudyPlanResponseDTO>> getCoursesInStudyPlanByProgrammeID(
            @PathVariable("acronym") String acronym,
            @PathVariable("name") String name) throws Exception {

        ProgrammeID programmeIdVO = new ProgrammeID(
                new NameWithNumbersAndSpecialChars(name),
                new Acronym(acronym)
        );

        StudyPlanID latestStudyPlanID = studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeIdVO);

        List<CourseInStudyPlanServiceDTO> coursesDTOs = courseInStudyPlanService.getCourseSummariesByStudyPlanID(latestStudyPlanID);

        List<CourseInStudyPlanResponseDTO> responseDTOs = coursesDTOs.stream()
                .map(assembler::toDTO)
                .toList();

        return ResponseEntity.ok(responseDTOs);
    }
}

