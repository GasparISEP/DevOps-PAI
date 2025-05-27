package PAI.controllerRest;
import PAI.VOs.*;
import PAI.assembler.courseEdition.ICourseEditionAssembler;
import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.RemoveCourseEditionEnrolmentDTO;
import PAI.dto.courseEdition.CourseEditionRequestDTO;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import PAI.dto.courseEdition.CreateCourseEditionCommand;
import PAI.service.courseEdition.ICreateCourseEditionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import PAI.assembler.courseEditionEnrolment.ICourseEditionEnrolmentAssembler;
import PAI.assembler.programmeEdition.IProgrammeEditionAssembler;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;
import PAI.dto.programmeEdition.ProgrammeEditionIdDto;
import PAI.service.courseEditionEnrolment.ICourseEditionEnrolmentService;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/courseeditions")
public class CourseEditionRestController {

    private final ICourseEditionEnrolmentService courseEditionEnrolmentService;
    private final ICourseEditionEnrolmentAssembler courseEditionEnrolmentAssembler;
    private final ICreateCourseEditionService createCourseEditionService;
    private final ICourseEditionAssembler courseEditionAssembler;
    private final IProgrammeEditionAssembler programmeEditionAssembler; 

    public CourseEditionRestController(ICourseEditionEnrolmentService courseEditionEnrolmentService, ICourseEditionEnrolmentAssembler courseEditionEnrolmentAssembler,
                                ICreateCourseEditionService createCourseEditionService, ICourseEditionAssembler courseEditionAssembler, IProgrammeEditionAssembler programmeEditionAssembler) {
        this.courseEditionEnrolmentService = courseEditionEnrolmentService;
        this.courseEditionEnrolmentAssembler = courseEditionEnrolmentAssembler;
        this.createCourseEditionService = createCourseEditionService;
        this.courseEditionAssembler = courseEditionAssembler;
        this.programmeEditionAssembler = programmeEditionAssembler;
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
        try {
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

            if (removed) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully removed the enrolment from course edition");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Student is not enrolled in that course edition"); //406 Not Acceptable
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createCourseEdition(@RequestBody CourseEditionRequestDTO dto) {
        // Using ResponseEntity<?> to allow different response types:
        // - CourseEditionResponseDTO on success
        // - String or other object for error messages
        try {
            CreateCourseEditionCommand command = courseEditionAssembler.toCommand(dto);
            ProgrammeID programmeID = new ProgrammeID(
                    new NameWithNumbersAndSpecialChars(command.programmeName()),
                    new Acronym(command.programmeAcronym()));

            CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(
                    new CourseID(
                            new Acronym(command.courseAcronym()),
                            new Name(command.courseName())),
                    new StudyPlanID(programmeID, new Date(command.studyPlanImplementationDate())));

            ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(
                    programmeID,
                    new SchoolYearID(command.schoolYearID()));

            CourseEdition created = createCourseEditionService.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID);

            if (created == null) {
                return ResponseEntity.badRequest().build();
            }

            CourseEditionResponseDTO responseDTO = courseEditionAssembler.toResponseDTO(created);

            return ResponseEntity
                    .created(URI.create("/courseeditions/" + responseDTO.courseEditionID()))
                    .body(responseDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/programmeditions")
    public ResponseEntity<List<CourseEditionResponseDTO>> getCourseEditionsByProgrammeEditionID(@Valid @RequestBody ProgrammeEditionIdDto programmeEditionIdDto) {
        try {
            ProgrammeEditionID programmeEditionID = programmeEditionAssembler.toProgrammeEditionID(programmeEditionIdDto);
            List<CourseEditionID> courseEditionIDs = courseEditionEnrolmentService.findCourseEditionIDsByProgrammeEdition(programmeEditionID);
            List<CourseEditionResponseDTO> courseEditionResponseDTOs = courseEditionAssembler.toResponseDTOList(courseEditionIDs);
            return ResponseEntity.ok(courseEditionResponseDTOs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}

