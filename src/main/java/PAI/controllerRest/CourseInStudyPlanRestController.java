package PAI.controllerRest;

import PAI.assembler.courseInStudyPlan.CourseInStudyPlanAssemblerImpl;
import PAI.assembler.courseInStudyPlan.ICourseInStudyPlanBusinessAssembler;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanRequestDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanResponseDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;
import PAI.exception.BusinessRuleViolationException;
import PAI.service.courseInStudyPlan.IAddCourseToAProgrammeService;
import PAI.service.courseInStudyPlan.ICourseInStudyPlanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course-in-study-plan")
public class CourseInStudyPlanRestController {

    private final CourseInStudyPlanAssemblerImpl assembler;
    private final IAddCourseToAProgrammeService service;

    public CourseInStudyPlanRestController(CourseInStudyPlanAssemblerImpl assembler, IAddCourseToAProgrammeService service) {
        this.service = service;
        this.assembler = assembler;
    }

    @PostMapping
    public ResponseEntity<CourseInStudyPlanResponseDTO> create(
            @Valid @RequestBody CourseInStudyPlanRequestDTO dtoRequest) {
        try {

            // Convert DTORequest → Command
            CourseInStudyPlanCommand command = assembler.toCommand(dtoRequest);

            // Service executa a lógica de domínio e retorna a entidade
            CourseInStudyPlanServiceDTO courseInStudyPlanServiceDTO = service.addCourseToAProgramme(command);

            // Convert ServiceDTO → ResponseDTO
            CourseInStudyPlanResponseDTO dtoResponse = assembler.toDTO(courseInStudyPlanServiceDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);

        } catch (IllegalArgumentException | BusinessRuleViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

