package PAI.controllerRest;
import PAI.VOs.*;
import PAI.dto.RemoveCourseEditionEnrolmentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/enrolments/students/remove")
    public ResponseEntity<String> removeStudentEnrolmentFromACourseEdition (@RequestBody RemoveCourseEditionEnrolmentDTO removeCourseEditionEnrolmentDTO) throws Exception {
            //For mapper-------------------
            CourseEditionID courseEditionID =
                    new CourseEditionID(new ProgrammeEditionID(
                        new ProgrammeID(new NameWithNumbersAndSpecialChars(removeCourseEditionEnrolmentDTO.programmeName()), new Acronym(removeCourseEditionEnrolmentDTO.programmeAcronym())),
                        new SchoolYearID(removeCourseEditionEnrolmentDTO.schoolYearId())
            ),
                    new CourseInStudyPlanID(
                            new CourseID(new Acronym(removeCourseEditionEnrolmentDTO.courseAcronym()), new Name(removeCourseEditionEnrolmentDTO.courseName())),
                            new StudyPlanID(new ProgrammeID(new NameWithNumbersAndSpecialChars(removeCourseEditionEnrolmentDTO.studyPlanProgrammeName()), new Acronym(removeCourseEditionEnrolmentDTO.studyPlanProgrammeAcronym())),
                                    new Date(removeCourseEditionEnrolmentDTO.studyPlanProgrammeDate()))
                    ));

            StudentID studentID = new StudentID(removeCourseEditionEnrolmentDTO.studentID());
            //--------------

            boolean removed = courseEditionEnrolmentService.removeCourseEditionEnrolment(studentID, courseEditionID);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully removed the enrolment from course edition");
    }


}

