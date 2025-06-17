package PAI.controllerRest;

import PAI.VOs.*;
import PAI.assembler.ProgrammeAndCourses.IProgrammeAndCoursesAssembler;
import PAI.assembler.ProgrammeAndCourses.IProgrammeAndCoursesHateoasAssembler;
import PAI.assembler.programmeEnrolment.IProgrammeEnrolmentAssembler;
import PAI.assembler.courseEditionEnrolment.ICourseEditionEnrolmentAssembler;
import PAI.assembler.programmeEnrolment.IProgrammeEnrolmentHATEOASAssembler;
import PAI.assembler.student.IStudentDTOAssembler;
import PAI.assembler.student.IStudentHateoasAssembler;
import PAI.assembler.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear.ITotalEnrolledStudentsAssembler;
import PAI.domain.courseEdition.CourseEdition;
import PAI.VOs.US35EnrolledCourseDetails;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.domain.student.Student;
import PAI.dto.ProgrammeAndCourses.StudentEnrolmentResultDto;
import PAI.dto.ProgrammeAndCourses.StudentProgrammeEnrolmentRequestDto;
import PAI.dto.courseEditionEnrolment.EnrolledCourseEditionDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentHateoasResponseDto;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentIdDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentResponseDTO;
import PAI.dto.student.StudentDTO;
import PAI.dto.student.StudentResponseDTO;
import PAI.service.programmeEditionEnrolment.IStudentProgrammeEditionEnrolmentService;
import PAI.service.courseEdition.ICourseEditionService;
import PAI.service.courseEditionEnrolment.ICourseEditionEnrolmentService;
import PAI.service.student.IProgrammeAndCoursesEnrolmentService;
import PAI.service.programmeEnrolment.IProgrammeEnrolmentService;
import PAI.service.student.IStudentService;
import PAI.service.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear.ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/students")
public class StudentRestController {

    private final IStudentService service;
    private final IStudentDTOAssembler mapper;
    private final IStudentHateoasAssembler hateoasAssembler;
    private final IProgrammeEnrolmentService programmeEnrolmentService;
    private final IStudentProgrammeEditionEnrolmentService iStudentProgrammeEnrolmentService;
    private final IProgrammeEnrolmentAssembler programmeEnrolmentMapper;
    private final ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler;
    private final ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService totalEnrolledStudentsService;
    private final IProgrammeEnrolmentHATEOASAssembler enrolmentHateoasAssembler;
    private final IProgrammeAndCoursesEnrolmentService programmeAndCoursesEnrolmentService;
    private final IProgrammeAndCoursesAssembler programmeAndCoursesAssembler;
    private final ICourseEditionEnrolmentAssembler courseEditionEnrolmentAssembler;
    private final ICourseEditionService courseEditionService;
    private final ICourseEditionEnrolmentService courseEditionEnrolmentService;
    private final IProgrammeAndCoursesHateoasAssembler programmeAndCoursesHateoasAssembler;

    public StudentRestController(IStudentService service, IStudentDTOAssembler mapper, IStudentHateoasAssembler hateoasAssembler,
                                 IProgrammeEnrolmentService programmeEnrolmentService, IStudentProgrammeEditionEnrolmentService iStudentProgrammeEnrolmentService,
                                 IProgrammeEnrolmentAssembler programmeEnrolmentMapper, ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler,
                                 ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService totalEnrolledStudentsService,
                                 IProgrammeEnrolmentHATEOASAssembler enrolmentHateoasAssembler, IProgrammeAndCoursesEnrolmentService programmeAndCoursesEnrolmentService,
                                 IProgrammeAndCoursesAssembler programmeAndCoursesAssembler, ICourseEditionEnrolmentAssembler courseEditionEnrolmentAssembler,
                                 ICourseEditionService courseEditionService, ICourseEditionEnrolmentService courseEditionEnrolmentService,
                                 IProgrammeAndCoursesHateoasAssembler programmeAndCoursesHateoasAssembler) {

        this.service = service;
        this.mapper = mapper;
        this.hateoasAssembler = hateoasAssembler;
        this.programmeEnrolmentService = programmeEnrolmentService;
        this.iStudentProgrammeEnrolmentService = iStudentProgrammeEnrolmentService;
        this.programmeEnrolmentMapper = programmeEnrolmentMapper;
        this.totalEnrolledStudentsAssembler = totalEnrolledStudentsAssembler;
        this.totalEnrolledStudentsService = totalEnrolledStudentsService;
        this.enrolmentHateoasAssembler = enrolmentHateoasAssembler;
        this.programmeAndCoursesEnrolmentService = programmeAndCoursesEnrolmentService;
        this.programmeAndCoursesAssembler = programmeAndCoursesAssembler;
        this.courseEditionEnrolmentAssembler = courseEditionEnrolmentAssembler;
        this.courseEditionService = courseEditionService;
        this.courseEditionEnrolmentService = courseEditionEnrolmentService;
        this.programmeAndCoursesHateoasAssembler = programmeAndCoursesHateoasAssembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<StudentResponseDTO>> registerAStudent(@Valid @RequestBody StudentDTO studentDTO) {

        try {
            Name name = mapper.toName(studentDTO);
            NIF nif = mapper.toNIF(studentDTO);
            PhoneNumber phoneNumber = mapper.toPhoneNumber(studentDTO);
            Email email = mapper.toEmail(studentDTO);
            Address address = mapper.toAddress(studentDTO);

            Student student = service.registerStudent(
                    name, nif, phoneNumber, email,
                    address.getStreet(), address.getPostalCode(),
                    address.getLocation(), address.getCountry()
            );

            if (student != null) {

                StudentResponseDTO responseDTO = mapper.toStudentResponseDTO(student);
                return ResponseEntity.status(HttpStatus.CREATED).body(hateoasAssembler.toModel(responseDTO));

            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e ) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/last-id")
    public ResponseEntity<Map<String, Integer>> getLastStudentID() {
        int value = service.getLastStudentID();
        Map<String, Integer> response = Collections.singletonMap("lastStudentID", value);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<StudentResponseDTO>>> getAllStudents() {
        List<Student> students = service.getAllStudents();

        List<EntityModel<StudentResponseDTO>> studentModels = students.stream()
                .map(mapper::toStudentResponseDTO)
                .map(hateoasAssembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(studentModels,
                linkTo(methodOn(StudentRestController.class).getAllStudents()).withSelfRel()
        ));
    }

    @PostMapping("/enrollStudent")
    public ResponseEntity<EntityModel<ProgrammeEnrolmentResponseDTO>> enrolStudentInProgramme (
            @RequestBody ProgrammeEnrolmentDTO programmeEnrolmentDTO) {

        if (programmeEnrolmentDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            ProgrammeEnrolment pe = programmeEnrolmentService.enrolStudentInProgramme(
                    programmeEnrolmentMapper.toStudentID(programmeEnrolmentDTO),
                    programmeEnrolmentMapper.toAccessMethodID(programmeEnrolmentDTO),
                    programmeEnrolmentMapper.toProgrammeID(programmeEnrolmentDTO),
                    programmeEnrolmentMapper.toDateVO(programmeEnrolmentDTO)
            );

            if (pe != null) {
                ProgrammeEnrolmentResponseDTO dto = programmeEnrolmentMapper.toProgrammeEnrolmentDTO(pe);

                EntityModel<ProgrammeEnrolmentResponseDTO> model = enrolmentHateoasAssembler.toModel(dto);

                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(model);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/enrollStudent/{programmeEnrolmentGID}")
    public ResponseEntity<ProgrammeEnrolmentHateoasResponseDto> getEnrolmentByStudentAndProgramme(
            @PathVariable("programmeEnrolmentGID") UUID programmeEnrolmentGID
    ) {

        try {
            ProgrammeEnrolmentIdDTO dto = new ProgrammeEnrolmentIdDTO(programmeEnrolmentGID);
            ProgrammeEnrolmentGeneratedID programmeEnrolmentGeneratedID = programmeEnrolmentMapper.toProgrammeEnrolmentGeneratedID(dto);

            StudentID studentID = iStudentProgrammeEnrolmentService.findStudentIDByProgrammeEnrolmentGeneratedID(programmeEnrolmentGeneratedID);
            ProgrammeID programmeID = iStudentProgrammeEnrolmentService.findProgrammeIDByProgrammeEnrolmentGeneratedID(programmeEnrolmentGeneratedID);

            ProgrammeEnrolment pe = programmeEnrolmentService.findEnrolmentByStudentAndProgramme(studentID, programmeID);

            if (pe == null) {
                return ResponseEntity.notFound().build();
            }

            ProgrammeEnrolmentHateoasResponseDto responseDto = iStudentProgrammeEnrolmentService.getProgrammeEnrolmentHateoasInformationDto(pe);

            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{id}/enrolments")
    public ResponseEntity<EntityModel<StudentEnrolmentResultDto>> enrolStudent(@RequestBody StudentProgrammeEnrolmentRequestDto dto) throws Exception {
        StudentID studentID = programmeAndCoursesAssembler.toStudentID(dto);
        ProgrammeEditionID programmeEditionID = programmeAndCoursesAssembler.toProgrammeEditionID(dto);
        List<CourseID> courseIDs = programmeAndCoursesAssembler.toCourseIDs(dto);

        US34Response result = programmeAndCoursesEnrolmentService.enrollStudentInProgrammeAndCourses(studentID, programmeEditionID, courseIDs);
        StudentEnrolmentResultDto response = programmeAndCoursesAssembler.toDto(result);

        EntityModel<StudentEnrolmentResultDto> hateoasResponse = programmeAndCoursesHateoasAssembler.toModel(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(hateoasResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentResponseDTO>> getStudentByID(@PathVariable("id") int id) {
        StudentID studentID = new StudentID(id);

        Optional<Student> optionalStudent = service.findByID(studentID);

        if (optionalStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        StudentResponseDTO dto = mapper.toStudentResponseDTO(optionalStudent.get());
        EntityModel<StudentResponseDTO> model = hateoasAssembler.toModel(dto);

        return ResponseEntity.ok(model);
    }

    @GetMapping("/{id}/enrolled-course-editions")
    public ResponseEntity<List<EnrolledCourseEditionDTO>> findEnrolledCourseEditionsForStudent(
            @PathVariable("id") int studentUniqueNumber) {

        StudentID studentID = new StudentID(studentUniqueNumber);

        List<US35EnrolledCourseDetails> enrolledCourseDetailsList = courseEditionEnrolmentService.findEnrolledCourseEditionsForStudent(studentID);

        List<EnrolledCourseEditionDTO> enrolledDTOs = enrolledCourseDetailsList.stream()
                .map(details -> {
                    CourseEdition courseEdition = details.courseEdition();

                    String courseAcronym = courseEdition.getCourseInStudyPlanID().getCourseID().getCourseAcronymValue();
                    String courseName = courseEdition.getCourseInStudyPlanID().getCourseID().getCourseNameValue();
                    String programmeAcronym = courseEdition.getProgrammeEditionID().getProgrammeID().getAcronym().getValue();
                    int studyPlanStartYear = courseEdition.getCourseInStudyPlanID().getStudyPlanID().getLocalDate().getYear();
                    String courseEditionGeneratedUUID = courseEdition.getCourseEditionGeneratedID().toString();
                    UUID schoolYearId = courseEdition.getProgrammeEditionID().getSchoolYearID().getSchoolYearID();
                    CourseEditionEnrolmentGeneratedID enrolmentGeneratedID = details.enrolmentGeneratedID();

                    return new EnrolledCourseEditionDTO(
                            courseAcronym,
                            courseName,
                            programmeAcronym,
                            studyPlanStartYear,
                            courseEditionGeneratedUUID,
                            schoolYearId,
                            enrolmentGeneratedID.getCourseEditionEnrolmentGeneratedID()
                    );
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(enrolledDTOs);
    }

    @PatchMapping("/{studentId}/course-editions/{courseEditionGeneratedUUID}")
    public ResponseEntity<String> removeStudentEnrolmentFromACourseEdition (
            @PathVariable("studentId") int studentUniqueNumber,
            @PathVariable("courseEditionGeneratedUUID") UUID courseEditionGeneratedUUID) {
        try {
            StudentID studentID = courseEditionEnrolmentAssembler.toStudentID(studentUniqueNumber);
            CourseEditionGeneratedID courseEditionGeneratedID = new CourseEditionGeneratedID(courseEditionGeneratedUUID);

            CourseEditionID courseEditionID = courseEditionService.findCourseEditionByGeneratedID(courseEditionGeneratedID);

            boolean removed = courseEditionEnrolmentService.removeCourseEditionEnrolment(studentID, courseEditionID);

            if (removed) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully removed the enrolment from course edition.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Student is not enrolled in that course edition or enrolment could not be removed."); // 406 Not Acceptable
            }

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid identifier format: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing enrolment removal: " + e.getMessage());
        }
    }
}