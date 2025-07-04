package PAI.controllerRest;

import PAI.VOs.*;
import PAI.assembler.courseEdition.ICourseEditionAssembler;
import PAI.assembler.courseEdition.ICourseEditionHATEOASAssembler;
import PAI.assembler.courseEdition.ICourseEditionRUCHateoasAssembler;
import PAI.assembler.courseEdition.ICreateCourseEditionHateoasAssembler;
import PAI.assembler.courseEdition.IStudentCountAssembler;
import PAI.assembler.courseEditionEnrolment.ICourseEditionEnrolmentAssembler;
import PAI.assembler.courseEditionEnrolment.ICourseEditionEnrolmentHateoasAssembler;
import PAI.assembler.programmeEdition.IProgrammeEditionServiceAssembler;
import PAI.assembler.schoolYear.ISchoolYearAssembler;
import PAI.assembler.studentGrade.IStudentGradeAssembler;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.schoolYear.SchoolYear;
import PAI.domain.studyPlan.StudyPlan;
import PAI.dto.approvalRate.ApprovalRateResponseDTO;
import PAI.dto.courseEdition.*;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;
import PAI.dto.schoolYear.SchoolYearCEDTO;
import PAI.dto.studentGrade.GradeAStudentCommand;
import PAI.dto.studentGrade.GradeAStudentRequestDTO;
import PAI.dto.studentGrade.GradeAStudentResponseDTO;
import PAI.service.courseEdition.ICourseEditionService;
import PAI.service.courseEdition.ICreateCourseEditionService;
import PAI.service.courseEdition.IDefineRucService;
import PAI.service.courseEditionEnrolment.ICourseEditionEnrolmentService;
import PAI.service.schoolYear.ISchoolYearService;
import PAI.service.studentGrade.IGradeAStudentService;
import PAI.service.studyPlan.IStudyPlanService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static PAI.utils.ValidationUtils.validateNotNull;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/course-editions")
public class CourseEditionRestController {

    private final ICourseEditionEnrolmentService courseEditionEnrolmentService;
    private final ICourseEditionEnrolmentAssembler courseEditionEnrolmentAssembler;
    private final ICreateCourseEditionService createCourseEditionService;
    private final ICourseEditionAssembler courseEditionAssembler;
    private final IGradeAStudentService gradeAStudentService;
    private final IStudentGradeAssembler studentGradeAssembler;
    private final IProgrammeEditionServiceAssembler programmeEditionAssembler;
    private final IDefineRucService defineRucService;
    private final ICourseEditionService courseEditionService;
    private final ICourseEditionRUCHateoasAssembler courseEditionRUCHateoasAssembler;
    private final IStudentCountAssembler studentCountAssembler;
    private final ICourseEditionEnrolmentHateoasAssembler courseEditionEnrolmentHateoasAssembler;
    private final ICourseEditionHATEOASAssembler courseEditionHATEOASAssembler;
    private final ICreateCourseEditionHateoasAssembler createCourseEditionHateoasAssembler;
    private final ISchoolYearService schoolYearService;
    private final ISchoolYearAssembler schoolYearAssembler;

    public CourseEditionRestController(
            ICourseEditionEnrolmentService courseEditionEnrolmentService,
            ICourseEditionEnrolmentAssembler courseEditionEnrolmentAssembler,
            ICreateCourseEditionService createCourseEditionService,
            ICourseEditionService courseEditionService,
            ICourseEditionAssembler courseEditionAssembler,
            IGradeAStudentService gradeAStudentService,
            IStudentGradeAssembler studentGradeAssembler,
            IProgrammeEditionServiceAssembler programmeEditionAssembler,
            IDefineRucService defineRucService,
            ICourseEditionRUCHateoasAssembler courseEditionRUCHateoasAssembler,
            ICreateCourseEditionHateoasAssembler createCourseEditionHateoasAssembler,
            IStudentCountAssembler studentCountAssembler,
            ICourseEditionEnrolmentHateoasAssembler courseEditionEnrolmentHateoasAssembler,
            ICourseEditionHATEOASAssembler courseEditionHATEOASAssembler,
            ISchoolYearService schoolYearService,
            ISchoolYearAssembler schoolYearAssembler
    ) {
        this.courseEditionEnrolmentService = validateNotNull(courseEditionEnrolmentService, "CourseEditionEnrolmentService");
        this.courseEditionEnrolmentAssembler = validateNotNull(courseEditionEnrolmentAssembler, "CourseEditionEnrolmentAssembler");
        this.createCourseEditionService = validateNotNull(createCourseEditionService, "CreateCourseEditionService");
        this.courseEditionService = validateNotNull(courseEditionService, "CourseEditionService");
        this.courseEditionAssembler = validateNotNull(courseEditionAssembler, "CourseEditionAssembler");
        this.gradeAStudentService = validateNotNull(gradeAStudentService, "GradeAStudentService");
        this.studentGradeAssembler = validateNotNull(studentGradeAssembler, "StudentGradeAssembler");
        this.programmeEditionAssembler = validateNotNull(programmeEditionAssembler, "ProgrammeEditionServiceAssembler");
        this.defineRucService = validateNotNull(defineRucService, "DefineRucService");
        this.courseEditionRUCHateoasAssembler = validateNotNull(courseEditionRUCHateoasAssembler, "CourseEditionHateoasAssembler");
        this.studentCountAssembler = validateNotNull(studentCountAssembler, "StudentCountAssembler");
        this.courseEditionEnrolmentHateoasAssembler = validateNotNull(courseEditionEnrolmentHateoasAssembler, "CourseEditionEnrolmentHateoasAssembler");
        this.createCourseEditionHateoasAssembler = validateNotNull(createCourseEditionHateoasAssembler, "CreateCourseEditionHateoasAssembler");
        this.courseEditionHATEOASAssembler = courseEditionHATEOASAssembler;
        this.schoolYearService = validateNotNull(schoolYearService, "SchoolYearService");
        this.schoolYearAssembler = validateNotNull(schoolYearAssembler, "SchoolYearAssembler");
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
    public ResponseEntity<String> removeStudentEnrolmentFromACourseEdition (@RequestBody CourseEditionEnrolmentDto courseEditionEnrolmentDto) {
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
    public ResponseEntity<EntityModel<CourseEditionResponseDTO>> createCourseEdition(
            @Valid @RequestBody CourseEditionRequestDTO requestDTO) {

        CreateCourseEditionCommand command = courseEditionAssembler.toCommand(requestDTO);
        CourseEditionServiceResponseDTO serviceResponseDTO = createCourseEditionService.createCourseEditionForRestApi(command);

        CourseEditionResponseDTO responseDTO = courseEditionAssembler.toResponseDTO(serviceResponseDTO);
        EntityModel<CourseEditionResponseDTO> responseModel = createCourseEditionHateoasAssembler.toModel(responseDTO);

        return ResponseEntity
                .created(URI.create("/course-editions/by-id/" + serviceResponseDTO.courseEditionGeneratedID()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseModel);
    }




    @PatchMapping("/{id}/ruc")
public ResponseEntity<?> defineRucForCourseEdition(
        @PathVariable("id") UUID id,
        @RequestBody DefineRucRequestDTO defineRucRequestDTO) throws Exception {

        TeacherID teacherID = courseEditionAssembler.createTeacherID(defineRucRequestDTO.teacherID());
        CourseEditionGeneratedID courseEditionID = courseEditionAssembler.fromDtoToCourseEditionGeneratedID(
                new SelectedCourseEditionGeneratedIdDTO(id));

        boolean success = defineRucService.assignRucToCourseEdition(teacherID, courseEditionID);

        if (!success) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course edition not found");
        }

        DefineRucResponseDTO responseDTO = new DefineRucResponseDTO(
                defineRucRequestDTO.teacherID(),
                id
        );

        return ResponseEntity.ok(courseEditionRUCHateoasAssembler.toModel(responseDTO));

}
    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> getCourseEditionById(@PathVariable("id") UUID uuid) throws Exception {
        try {
            CourseEditionGeneratedID ceGenerationID = new CourseEditionGeneratedID(uuid);
            CourseEditionServiceResponseDTO serviceResponseDTO = createCourseEditionService.findById(ceGenerationID);
            CourseEditionResponseDTO responseDTO = courseEditionAssembler.toResponseDTO(serviceResponseDTO);

            EntityModel<CourseEditionResponseDTO> entityModelResponseDTO = courseEditionHATEOASAssembler.toModel(responseDTO);

            return ResponseEntity.ok(entityModelResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid parameters or course edition not found.");
        }
    }

    @GetMapping("/programmeditions")
    public ResponseEntity<?> getCourseEditionsByProgrammeEditionID(@Valid @RequestBody CourseEditionRequestDTO courseEditionRequestDTO) {
        try {
            ProgrammeEditionID programmeEditionID = courseEditionAssembler.toProgrammeEditionID(courseEditionRequestDTO);
            CourseInStudyPlanID courseInStudyPlanID = courseEditionAssembler.toCourseInStudyPlanID(courseEditionRequestDTO);
            List<CourseEditionID> courseEditionIDs = courseEditionService.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(programmeEditionID, courseInStudyPlanID);
            List<CourseEditionResponseIDDTO> courseEditionResponseIDDTOs = courseEditionAssembler.toResponseIDDTOList(courseEditionIDs);
            return ResponseEntity.ok(courseEditionRUCHateoasAssembler.toCollectionModel(courseEditionResponseIDDTOs));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> findAllCourseEditions () {
        // Retrieves all Domain Course Editions
        Iterable<CourseEditionServiceResponseDTO> allCourseEditions = createCourseEditionService.findAll();

        List<CourseEditionResponseDTO> courseEditionResponseDTOList = courseEditionAssembler
                                                                .toCourseEditionResponseDTOList(allCourseEditions);

        CollectionModel<EntityModel<CourseEditionResponseDTO>> courseEditionResponseDTOEntityModel =
                                courseEditionHATEOASAssembler.toCollectionModel(courseEditionResponseDTOList);

        return new ResponseEntity<>(courseEditionResponseDTOEntityModel, HttpStatus.OK);
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
    public ResponseEntity<?> getCourseEditionAverageGrade(
            @RequestParam("programmeAcronym") @Valid String programmeAcronym,
            @RequestParam("schoolYearId") @Valid String schoolYearId,
            @RequestParam("courseAcronym") @Valid String courseAcronym,
            @RequestParam("courseName") @Valid String courseName,
            @RequestParam("localDate") @Valid String localDate
    ) {
        try {
            CourseEditionID courseEditionID = courseEditionService.buildCourseEditionID(programmeAcronym, schoolYearId, courseAcronym, courseName, localDate);
            Double averageGrade = gradeAStudentService.getAverageGrade(courseEditionID);
            AverageGradeResponseDTO dto = new AverageGradeResponseDTO(averageGrade);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid parameters or error calculating average grade.");
        }
    }

    @GetMapping("/approval-rate")
    public ResponseEntity<?> getCourseEditionApprovalRate(
            @RequestParam("programmeAcronym") @Valid String programmeAcronym,
            @RequestParam("schoolYearId") @Valid String schoolYearId,
            @RequestParam("courseAcronym") @Valid String courseAcronym,
            @RequestParam("courseName") @Valid String courseName,
            @RequestParam("localDate") @Valid String localDate)  {
     try {
         CourseEditionID courseEditionID = courseEditionService.buildCourseEditionID(programmeAcronym, schoolYearId, courseAcronym, courseName, localDate);
         double approvalRate = gradeAStudentService.knowApprovalRate(courseEditionID);
         ApprovalRateResponseDTO dto = new ApprovalRateResponseDTO(approvalRate);
         return ResponseEntity.ok(dto);
     } catch (Exception exception){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}/enrolments/count")
    public ResponseEntity<StudentCountDTO> getNumberOfStudentsInCourseEdition(@PathVariable("id") UUID uuid) throws Exception {

        CourseEditionGeneratedID generatedID = new CourseEditionGeneratedID(uuid);

        CourseEditionID courseEditionID = courseEditionService.findCourseEditionIDByGeneratedID(generatedID);

        int studentCount = courseEditionEnrolmentService.numberOfStudentsEnrolledInCourseEdition(courseEditionID);
        StudentCountDTO studentCountDTO = studentCountAssembler.fromDomainToDTO(studentCount);
        return ResponseEntity.ok(studentCountDTO);
    }

    @GetMapping("/school-years")
    public ResponseEntity<?> getCoursesEditionsByProgrammeIDAndCourseID(
            @RequestParam("programmeAcronym") @Valid String programmeAcronym,
            @RequestParam("courseAcronym") @Valid String courseAcronym,
            @RequestParam("courseName") @Valid String courseName
    ) {
        try {
            ProgrammeID programmeID = new ProgrammeID(new Acronym(programmeAcronym));
            CourseID courseID =  new CourseID(new Acronym(courseAcronym), new Name(courseName));

            Iterable <CourseEdition> courseEditions = courseEditionService.getCourseEditionsByProgrammeIDAndCourseID(programmeID, courseID);
            List<SchoolYearID> schoolYearIDArrayList = courseEditionService.getSchoolYearIDsFromCourseEditions(courseEditions);
            Iterable<SchoolYear> schoolYearArrayList = schoolYearService.getSchoolYearsByIDs(schoolYearIDArrayList);
            Iterable<SchoolYearCEDTO> schoolYearCEDTOS = schoolYearAssembler.toCEDTOs(schoolYearArrayList);

            return ResponseEntity.ok(schoolYearCEDTOS);
        } catch (Exception e) {return ResponseEntity.badRequest().body("Error processing request: " + e.getMessage());}
    }

}