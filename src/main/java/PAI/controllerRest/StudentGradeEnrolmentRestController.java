package PAI.controllerRest;

import PAI.assembler.studentGrade.IStudentGradeEnrolmentAssembler;
import PAI.assembler.studentGrade.StudentGradeEnrolmentAssemblerHateoas;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentMinimalDTO;
import PAI.dto.studentGrade.GradeAStudentCommand;
import PAI.dto.studentGrade.GradeAStudentRequestMinimalDTO;
import PAI.dto.studentGrade.GradeAStudentResponseDTO;
import PAI.service.courseEditionEnrolment.ICourseEditionEnrolmentService;
import PAI.service.studentGrade.IGradeAStudentService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentgrades")
public class StudentGradeEnrolmentRestController {

    private final ICourseEditionEnrolmentService courseEditionEnrolmentService;
    private final IStudentGradeEnrolmentAssembler studentGradeEnrolmentAssembler;
    private final IGradeAStudentService gradeAStudentService;
    private final StudentGradeEnrolmentAssemblerHateoas hateoasAssembler;

    public StudentGradeEnrolmentRestController(
            ICourseEditionEnrolmentService courseEditionEnrolmentService,
            IStudentGradeEnrolmentAssembler studentGradeEnrolmentAssembler,
            IGradeAStudentService gradeAStudentService,
            StudentGradeEnrolmentAssemblerHateoas hateoasAssembler
    ) {
        this.courseEditionEnrolmentService = courseEditionEnrolmentService;
        this.studentGradeEnrolmentAssembler = studentGradeEnrolmentAssembler;
        this.gradeAStudentService = gradeAStudentService;
        this.hateoasAssembler = hateoasAssembler;
    }

    @GetMapping("/students/{studentID}/courseeditionenrolments")
    public ResponseEntity<List<CourseEditionEnrolmentMinimalDTO>> getActiveEnrolments(
            @PathVariable("studentID") int studentID) {

        List<CourseEditionEnrolment> enrolments = courseEditionEnrolmentService.findByStudentID(studentID);
        List<CourseEditionEnrolmentMinimalDTO> active = enrolments.stream()
                .filter(CourseEditionEnrolment::isEnrolmentActive)
                .map(studentGradeEnrolmentAssembler::toMinimalDTO)
                .toList();

        return ResponseEntity.ok(active);
    }

    @PostMapping("/register/hateoas")
    public ResponseEntity<EntityModel<GradeAStudentResponseDTO>> registerWithHateoas(
            @RequestBody GradeAStudentRequestMinimalDTO dto) {
        try {
            GradeAStudentCommand command = studentGradeEnrolmentAssembler.toCommand(dto);
            GradeAStudentResponseDTO responseDTO = gradeAStudentService.gradeAStudent(command);
            EntityModel<GradeAStudentResponseDTO> model = hateoasAssembler.toModel(responseDTO);
            return ResponseEntity.ok(model);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atribuir nota ao estudante: " + e.getMessage(), e);
        }
    }
}
