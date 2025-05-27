package PAI.controllerRest;
import PAI.VOs.*;
import PAI.assembler.courseEdition.ICourseEditionAssembler;
import PAI.assembler.studentGrade.IStudentGradeAssembler;
import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.RemoveCourseEditionEnrolmentDTO;
import PAI.dto.courseEdition.CourseEditionRequestDTO;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import PAI.dto.courseEdition.CreateCourseEditionCommand;
import PAI.dto.courseEdition.DefineRucRequestDTO;
import PAI.dto.studentGrade.GradeAStudentCommand;
import PAI.dto.studentGrade.GradeAStudentRequestDTO;
import PAI.dto.studentGrade.GradeAStudentResponseDTO;
import PAI.service.courseEdition.ICreateCourseEditionService;
import PAI.service.courseEdition.IDefineRucService;
import PAI.service.studentGrade.IGradeAStudentService;
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
import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/courseeditions")
public class CourseEditionRestController {

    private final ICourseEditionEnrolmentService courseEditionEnrolmentService;
    private final ICourseEditionEnrolmentAssembler courseEditionEnrolmentAssembler;
    private final ICreateCourseEditionService createCourseEditionService;
    private final ICourseEditionAssembler courseEditionAssembler;
    private final IGradeAStudentService gradeAStudentService;
    private final IStudentGradeAssembler studentGradeAssembler;
    private final IProgrammeEditionAssembler programmeEditionAssembler;
    private final IDefineRucService defineRucService;

public CourseEditionRestController(
        ICourseEditionEnrolmentService courseEditionEnrolmentService,
        ICourseEditionEnrolmentAssembler courseEditionEnrolmentAssembler,
        ICreateCourseEditionService createCourseEditionService,
        ICourseEditionAssembler courseEditionAssembler,
        IGradeAStudentService gradeAStudentService,
        IStudentGradeAssembler studentGradeAssembler,
        IProgrammeEditionAssembler programmeEditionAssembler,
        IDefineRucService defineRucService
) {
    this.courseEditionEnrolmentService = courseEditionEnrolmentService;
    this.courseEditionEnrolmentAssembler = courseEditionEnrolmentAssembler;
    this.createCourseEditionService = createCourseEditionService;
    this.courseEditionAssembler = courseEditionAssembler;
    this.gradeAStudentService = gradeAStudentService;
    this.studentGradeAssembler = studentGradeAssembler;
    this.programmeEditionAssembler = programmeEditionAssembler;
    this.defineRucService = defineRucService;
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

    @PatchMapping("/ruc")
    public ResponseEntity<String> defineRucForCourseEdition (@RequestBody DefineRucRequestDTO defineRucRequestDTO) throws Exception {
        TeacherID teacherID= courseEditionAssembler.createTeacherID(defineRucRequestDTO.teacherID());
        CourseEditionID courseEditionID= courseEditionAssembler.fromDtoToCourseEditionID(defineRucRequestDTO.courseEditionDTO());
        try {
            defineRucService.assignRucToCourseEdition(teacherID, courseEditionID);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("RUC successfully updated");
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

    @GetMapping
    public ResponseEntity<?> findAllCourseEditions () {
        // Retrieves all Domain Course Editions
        Iterable<CourseEdition> allCourseEditions = createCourseEditionService.findAll();

        List<CourseEditionResponseDTO> dtoList = new ArrayList<>();
        // For each Domain CourseEdition, it converts into a ResponseDTO and adds to list
        for (CourseEdition courseEdition : allCourseEditions) {
            dtoList.add(courseEditionAssembler.toResponseDTO(courseEdition));
        }

        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/studentGrades")
    public ResponseEntity<?> gradeAStudent (@RequestBody @Valid GradeAStudentRequestDTO request) {

        try {
            // Convert requestDTO to command
            GradeAStudentCommand command = studentGradeAssembler.toDomain(request);
            // Call Service to Grade a Student
            GradeAStudentResponseDTO response = gradeAStudentService.gradeAStudent(command);
            // Return 201 Status Code (Ok!)
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            // Return 400 Status Code (Bad Request) for business rule violations
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/averagegrade")
    public ResponseEntity<Double> getCourseEditionAverageGrade(
            @RequestParam("programmeAcronym") String programmeAcronym,
            @RequestParam("schoolYearId") String schoolYearId,
            @RequestParam("courseAcronym") String courseAcronym,
            @RequestParam("studyPlanDate") String studyPlanDate
    ) throws Exception {

        UUID schoolYearUUID = UUID.fromString(schoolYearId);
        SchoolYearID schoolYearID = new SchoolYearID(schoolYearUUID);

        ProgrammeID programmeID = new ProgrammeID(
                new NameWithNumbersAndSpecialChars("Placeholder Name"), //Dummies
                new Acronym(programmeAcronym));

        CourseEditionID courseEditionID = new CourseEditionID(
                new ProgrammeEditionID(programmeID, schoolYearID),
                new CourseInStudyPlanID(
                        new CourseID(new Acronym(courseAcronym), new Name("Some Placeholder Name")),
                        new StudyPlanID(programmeID, new Date(studyPlanDate))));

        Double averageGrade = gradeAStudentService.getAverageGrade(courseEditionID);

        if (averageGrade == null) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(averageGrade);
    }
}