package PAI.controllerRest;
import PAI.VOs.*;
import PAI.assembler.courseEdition.ICourseEditionAssembler;
import PAI.assembler.courseEdition.ICourseEditionHateoasAssembler;
import PAI.assembler.courseEdition.IStudentCountAssembler;
import PAI.assembler.studentGrade.IStudentGradeAssembler;
import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.courseEdition.*;
import PAI.dto.studentGrade.GradeAStudentCommand;
import PAI.dto.studentGrade.GradeAStudentRequestDTO;
import PAI.dto.studentGrade.GradeAStudentResponseDTO;
import PAI.service.courseEdition.ICourseEditionService;
import PAI.service.courseEdition.ICreateCourseEditionService;
import PAI.service.courseEdition.IDefineRucService;
import PAI.service.studentGrade.IGradeAStudentService;
import jakarta.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import PAI.assembler.courseEditionEnrolment.ICourseEditionEnrolmentAssembler;
import PAI.assembler.courseEditionEnrolment.ICourseEditionEnrolmentHateoasAssembler;
import PAI.assembler.programmeEdition.IProgrammeEditionAssembler;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;
import PAI.service.courseEditionEnrolment.ICourseEditionEnrolmentService;

import java.net.URI;
import java.util.List;
import java.util.Objects;
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
    private final ICourseEditionService courseEditionService;
    private final ICourseEditionHateoasAssembler courseEditionHateoasAssembler;
    private final IStudentCountAssembler studentCountAssembler;
    private final ICourseEditionEnrolmentHateoasAssembler courseEditionEnrolmentHateoasAssembler;

public CourseEditionRestController(
        ICourseEditionEnrolmentService courseEditionEnrolmentService,
        ICourseEditionEnrolmentAssembler courseEditionEnrolmentAssembler,
        ICreateCourseEditionService createCourseEditionService,
        ICourseEditionService courseEditionService,
        ICourseEditionAssembler courseEditionAssembler,
        IGradeAStudentService gradeAStudentService,
        IStudentGradeAssembler studentGradeAssembler,
        IProgrammeEditionAssembler programmeEditionAssembler,
        IDefineRucService defineRucService, ICourseEditionHateoasAssembler courseEditionHateoasAssembler,
        IStudentCountAssembler studentCountAssembler, ICourseEditionEnrolmentHateoasAssembler courseEditionEnrolmentHateoasAssembler
) {
    this.courseEditionEnrolmentService = courseEditionEnrolmentService;
    this.courseEditionEnrolmentAssembler = courseEditionEnrolmentAssembler;
    this.createCourseEditionService = createCourseEditionService;
    this.courseEditionService = courseEditionService;
    this.courseEditionAssembler = courseEditionAssembler;
    this.gradeAStudentService = gradeAStudentService;
    this.studentGradeAssembler = studentGradeAssembler;
    this.programmeEditionAssembler = programmeEditionAssembler;
    this.defineRucService = defineRucService;
    this.courseEditionHateoasAssembler = courseEditionHateoasAssembler;
    this.studentCountAssembler = studentCountAssembler;
    this.courseEditionEnrolmentHateoasAssembler = Objects.requireNonNull(courseEditionEnrolmentHateoasAssembler);
}

    @PostMapping("/students/{id}/courses-edition-enrolments")
    public ResponseEntity<?> enrolStudentInCourseEdition(@PathVariable("id") int studentUniqueNumber, @Valid @RequestBody CourseEditionEnrolmentDto courseEditionEnrolmentDTO) {
        try {
            CourseEditionID courseEditionID = courseEditionEnrolmentAssembler.toCourseEditionID(courseEditionEnrolmentDTO);
            StudentID studentID = courseEditionEnrolmentAssembler.toStudentID(studentUniqueNumber);
            boolean enrolment = courseEditionEnrolmentService.enrolStudentInACourseEdition(studentID, courseEditionID);
            if (enrolment) {
                EntityModel<CourseEditionEnrolmentDto> courseEditionEnrolmentHateoas = courseEditionEnrolmentHateoasAssembler.toModel(courseEditionEnrolmentDTO);
                return ResponseEntity.status(HttpStatus.CREATED).body(courseEditionEnrolmentHateoas);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student already enrolled in this course edition");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error processing enrollment: " + e.getMessage());
        }
    }

    @PatchMapping("/enrolments/students/remove")
    public ResponseEntity<String> removeStudentEnrolmentFromACourseEdition (@RequestBody CourseEditionEnrolmentDto courseEditionEnrolmentDto) throws Exception {
        try {
            CourseEditionID courseEditionID = courseEditionEnrolmentAssembler.toCourseEditionID(courseEditionEnrolmentDto);
            StudentID studentID = courseEditionEnrolmentAssembler.toStudentID(courseEditionEnrolmentDto.studentUniqueNumber());

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
        try {
            CreateCourseEditionCommand command = courseEditionAssembler.toCommand(dto);

            ProgrammeID programmeID = new ProgrammeID(command.programmeAcronym());

            CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(
                    new CourseID(command.courseAcronym(), command.courseName()),
                    new StudyPlanID(programmeID, new Date(String.valueOf(command.studyPlanImplementationDate())))
            );

            ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(
                    programmeID,
                    command.schoolYearID()
            );

            CourseEditionResponseDTO responseDTO =
                    createCourseEditionService.createCourseEditionAndReturnDTO(courseInStudyPlanID, programmeEditionID);

            if (responseDTO == null) {
                return ResponseEntity.badRequest().build();
            }

            return ResponseEntity
                    .created(URI.create("/courseeditions/" + responseDTO.courseEditionID()))
                    .body(responseDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PatchMapping("/ruc")
    public ResponseEntity<?> defineRucForCourseEdition(@RequestBody DefineRucRequestDTO defineRucRequestDTO) throws Exception {
        TeacherID teacherID = courseEditionAssembler.createTeacherID(defineRucRequestDTO.teacherID());
        CourseEditionID courseEditionID = courseEditionAssembler.fromDtoToCourseEditionID(defineRucRequestDTO.courseEditionDTO());

        try {
            boolean success = defineRucService.assignRucToCourseEdition(teacherID, courseEditionID);

            if (!success) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course edition not found");
            }

            DefineRucResponseDTO responseDTO = new DefineRucResponseDTO(
                    defineRucRequestDTO.teacherID(),
                    defineRucRequestDTO.courseEditionDTO()
            );

            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(courseEditionHateoasAssembler.toModel(responseDTO));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }



    @GetMapping("/programmeditions")
    public ResponseEntity<?> getCourseEditionsByProgrammeEditionID(@Valid @RequestBody CourseEditionRequestDTO courseEditionRequestDTO) {
        try {
            ProgrammeEditionID programmeEditionID = courseEditionAssembler.toProgrammeEditionID(courseEditionRequestDTO);
            CourseInStudyPlanID courseInStudyPlanID = courseEditionAssembler.toCourseInStudyPlanID(courseEditionRequestDTO);
            List<CourseEditionID> courseEditionIDs = courseEditionService.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(programmeEditionID, courseInStudyPlanID);
            List<CourseEditionResponseDTO> courseEditionResponseDTOs = courseEditionAssembler.toResponseDTOList(courseEditionIDs);
            CollectionModel<EntityModel<CourseEditionResponseDTO>> courseEditionResponseDTOsWithHypermedia = courseEditionHateoasAssembler.toCollectionModel(courseEditionResponseDTOs);
            return ResponseEntity.ok(courseEditionResponseDTOsWithHypermedia);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing request: " + e.getMessage());
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

        System.out.println("DEBUG: Found " + dtoList.size() + " course editions in database");
        
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/debug/count")
    public ResponseEntity<String> getCourseEditionCount() {
        Iterable<CourseEdition> allCourseEditions = createCourseEditionService.findAll();
        int count = 0;
        for (CourseEdition courseEdition : allCourseEditions) {
            count++;
            System.out.println("CourseEdition found: " + courseEdition.identity());
        }
        return ResponseEntity.ok("Total CourseEditions in database: " + count);
    }

    @PostMapping("/studentgrades/register")
    public ResponseEntity<?> gradeAStudent (@RequestBody @Valid GradeAStudentRequestDTO request) throws Exception {

        // Convert requestDTO to command
        GradeAStudentCommand command = studentGradeAssembler.toDomain(request);
        // Call Service to Grade a Student
        GradeAStudentResponseDTO response = gradeAStudentService.gradeAStudent(command);
        // Return 201 Status Code (Ok!)
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/averagegrade")
    public ResponseEntity<Double> getCourseEditionAverageGrade(
            @RequestParam("programmeAcronym") @Valid String programmeAcronym,
            @RequestParam("schoolYearId") @Valid String schoolYearId,
            @RequestParam("courseAcronym") @Valid String courseAcronym,
            @RequestParam("studyPlanDate") @Valid String studyPlanDate
    ) throws Exception {

        UUID schoolYearUUID = UUID.fromString(schoolYearId);
        SchoolYearID schoolYearID = new SchoolYearID(schoolYearUUID);

        ProgrammeID programmeID = new ProgrammeID(//Dummies
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

    @GetMapping("/approvalpercentage")
    public ResponseEntity<Double> getCourseEditionApprovalRate(
            @RequestParam("programmeAcronym") @Valid String programmeAcronym,
            @RequestParam("schoolYearId") @Valid String schoolYearId,
            @RequestParam("courseAcronym") @Valid String courseAcronym,
            @RequestParam("studyPlanDate") @Valid String studyPlanDate) throws Exception {

        UUID schoolYearUUID = UUID.fromString(schoolYearId);
        SchoolYearID schoolYearID = new SchoolYearID(schoolYearUUID);

        ProgrammeID programmeID = new ProgrammeID(
                new Acronym(programmeAcronym));

        CourseEditionID courseEditionID = new CourseEditionID(
                new ProgrammeEditionID(programmeID, schoolYearID),
                new CourseInStudyPlanID(
                        new CourseID(new Acronym(courseAcronym), new Name("Placeholder Name")),
                        new StudyPlanID(programmeID, new Date(studyPlanDate))));

        double approvalRate = gradeAStudentService.knowApprovalRate(courseEditionID);

        return ResponseEntity.ok(approvalRate);
    }

    @PostMapping("/studentscount")
    public ResponseEntity<StudentCountDTO> getNumberOfStudentsInCourseEdition(@RequestBody @Valid SelectedCourseEditionIdDTO dto) {
        try {
            CourseEditionID courseEditionID = courseEditionAssembler.fromDtoToCourseEditionID(dto);
            int studentCount = courseEditionEnrolmentService.numberOfStudentsEnrolledInCourseEdition(courseEditionID);
            StudentCountDTO studentCountDTO = studentCountAssembler.fromDomainToDTO(studentCount);
            return ResponseEntity.ok(studentCountDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}