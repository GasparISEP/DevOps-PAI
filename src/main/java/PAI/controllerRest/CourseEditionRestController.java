package PAI.controllerRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import PAI.assembler.courseEditionEnrolment.ICourseEditionEnrolmentAssembler;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;
import PAI.service.courseEditionEnrolment.ICourseEditionEnrolmentService;

@RestController
@RequestMapping("/courseeditions")
public class CourseEditionRestController {

    private final ICourseEditionEnrolmentService courseEditionEnrolmentService;
    private final ICourseEditionEnrolmentAssembler courseEditionEnrolmentAssembler;

    public CourseEditionRestController(ICourseEditionEnrolmentService courseEditionEnrolmentService, ICourseEditionEnrolmentAssembler courseEditionEnrolmentAssembler) {
        this.courseEditionEnrolmentService = courseEditionEnrolmentService;
        this.courseEditionEnrolmentAssembler = courseEditionEnrolmentAssembler;
    }

    @PostMapping("/students/enrolments")
    public ResponseEntity<String> enrolStudentInCourseEdition(@RequestBody CourseEditionEnrolmentDto courseEditionEnrolmentDTO) throws Exception {
        try {
            CourseEditionEnrolment courseEditionEnrolment = courseEditionEnrolmentAssembler.toDomain(courseEditionEnrolmentDTO);
            boolean enrolment = courseEditionEnrolmentService.enrolStudentInACourseEdition(courseEditionEnrolment.knowStudent(), courseEditionEnrolment.knowCourseEdition());
            if (enrolment) {
                    return ResponseEntity.status(HttpStatus.CREATED).body("Student enrolled in course edition");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student already enrolled in this course edition");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

