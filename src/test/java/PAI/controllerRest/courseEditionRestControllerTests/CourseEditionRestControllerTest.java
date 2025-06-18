package PAI.controllerRest.courseEditionRestControllerTests;
import PAI.VOs.*;
import PAI.assembler.courseEdition.*;
import PAI.assembler.programmeEdition.IProgrammeEditionServiceAssembler;
import PAI.assembler.schoolYear.ISchoolYearAssembler;
import PAI.assembler.studentGrade.IStudentGradeAssembler;
import PAI.assembler.courseEditionEnrolment.ICourseEditionEnrolmentHateoasAssembler;
import PAI.controllerRest.CourseEditionRestController;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.schoolYear.SchoolYearCEDTO;
import PAI.dto.studentGrade.GradeAStudentCommand;
import PAI.dto.studentGrade.GradeAStudentRequestDTO;
import PAI.dto.studentGrade.GradeAStudentResponseDTO;
import PAI.dto.courseEdition.*;
import PAI.exception.GlobalExceptionHandler;
import PAI.exception.NotFoundException;
import PAI.service.courseEdition.ICourseEditionService;
import PAI.service.courseEdition.ICreateCourseEditionService;
import PAI.service.courseEdition.IDefineRucService;
import PAI.service.schoolYear.ISchoolYearService;
import PAI.service.studentGrade.IGradeAStudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import PAI.assembler.courseEditionEnrolment.ICourseEditionEnrolmentAssembler;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;
import PAI.service.courseEditionEnrolment.ICourseEditionEnrolmentService;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseEditionRestController.class)
class CourseEditionRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ICourseEditionEnrolmentService courseEditionEnrolmentService;

    @MockBean
    private ICourseEditionEnrolmentAssembler courseEditionEnrolmentAssembler;

    @MockBean
    private ICreateCourseEditionHateoasAssembler createCourseEditionHateoasAssembler;

    @MockBean
    private ICreateCourseEditionService createCourseEditionService;

    @MockBean
    private ICourseEditionAssembler courseEditionAssembler;

    @MockBean
    private IDefineRucService defineRucService;

    @MockBean
    private IGradeAStudentService gradeAStudentService;

    @MockBean
    private IStudentGradeAssembler studentGradeAssembler;

    @MockBean
    private IProgrammeEditionServiceAssembler programmeEditionAssembler;

    @MockBean
    private ICourseEditionService courseEditionService;

    @MockBean
    private ICourseEditionRUCHateoasAssembler courseEditionRUCHateoasAssembler;

    @MockBean
    private IStudentCountAssembler studentCountAssembler;

    @MockBean
    private CourseEditionEnrolmentDto validEnrolmentDto;

    @Autowired
    private CourseEditionRestController courseEditionRestController;

    @MockBean
    private ICourseEditionEnrolmentHateoasAssembler courseEditionEnrolmentHateoasAssembler;

    @MockBean
    private ICourseEditionHATEOASAssembler courseEditionHATEOASAssembler;

    @MockBean
    private ISchoolYearService schoolYearService;

    @MockBean
    private ISchoolYearAssembler schoolYearAssembler;

    @BeforeEach
    void setUp() {
        validEnrolmentDto = new CourseEditionEnrolmentDto(
            1100000,
            "LEIC",
            "123e4567-e89b-12d3-a456-426614174000",
            "ESOFT",
            "Engineering Software",
            "01-01-2024"
        );
    }

    @Test
    void whenEnrolStudent_thenReturnsCreated() throws Exception {
        // Arrange
        int studentId = 1100000;
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);
        StudentID mockStudentID = mock(StudentID.class);
        
        when(courseEditionEnrolmentAssembler.toCourseEditionID(any(CourseEditionEnrolmentDto.class)))
            .thenReturn(mockCourseEditionID);
        when(courseEditionEnrolmentAssembler.toStudentID(any(int.class)))
            .thenReturn(mockStudentID);
        when(courseEditionEnrolmentService.enrolStudentInACourseEdition(mockStudentID, mockCourseEditionID))
            .thenReturn(true);
        when(courseEditionEnrolmentHateoasAssembler.toModel(any(CourseEditionEnrolmentDto.class)))
            .thenReturn(EntityModel.of(validEnrolmentDto));

        // Act & Assert
        mockMvc.perform(post("/course-editions/students/{id}/courses-edition-enrolments", studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validEnrolmentDto)))
                .andExpect(status().isCreated());
        }

    @Test
    void whenEnrolStudentAlreadyEnrolled_thenReturnsBadRequest() throws Exception {
        // Arrange
        int studentId = 1100000;
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);
        StudentID mockStudentID = mock(StudentID.class);
        
        when(courseEditionEnrolmentAssembler.toCourseEditionID(any(CourseEditionEnrolmentDto.class)))
            .thenReturn(mockCourseEditionID);
        when(courseEditionEnrolmentAssembler.toStudentID(studentId))
            .thenReturn(mockStudentID);
        when(courseEditionEnrolmentService.enrolStudentInACourseEdition(any(), any()))
            .thenReturn(false);

        // Act & Assert
        mockMvc.perform(post("/course-editions/students/{id}/courses-edition-enrolments", studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validEnrolmentDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Student already enrolled in this course edition"));
    }

    @Test
    void whenEnrolStudentWithInvalidData_thenReturnsBadRequest() throws Exception {
        // Arrange
        int studentId = 0;    
        CourseEditionEnrolmentDto invalidDto = new CourseEditionEnrolmentDto(
            0,
            "",
            "",
            "",
            "",
            "" 
        );

        // Act & Assert
        mockMvc.perform(post("/course-editions/students/{id}/courses-edition-enrolments", studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenEnrolStudentWithException_thenReturnsBadRequest() throws Exception {
        // Arrange
        int studentId = 1100000;
        
        when(courseEditionEnrolmentAssembler.toCourseEditionID(any(CourseEditionEnrolmentDto.class)))
            .thenThrow(new RuntimeException("Test exception"));

        // Act & Assert
        mockMvc.perform(post("/course-editions/students/{id}/courses-edition-enrolments", studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validEnrolmentDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error processing enrollment: Test exception"));
    }

    @Test
    void whenRemoveStudentEnrolmentFromACourseEdition_thenReturnsSuccess() throws Exception {
        //arrange
        when(courseEditionEnrolmentAssembler.toCourseEditionID(any(CourseEditionEnrolmentDto.class))).thenReturn(mock(CourseEditionID.class));
        when(courseEditionEnrolmentAssembler.toStudentID(any(int.class))).thenReturn(mock(StudentID.class));
        when(courseEditionEnrolmentService.removeCourseEditionEnrolment(any(), any())).thenReturn(true);
        //act + assert
        mockMvc.perform(patch("/course-editions/enrolments/students/remove")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validEnrolmentDto)))
                .andExpect(status().isAccepted());

    }

    @Test
    void shouldReturnBadRequestWhenRemoveCourseEditionEnrolment_throwsException() throws Exception {
        //arrange
        when(courseEditionEnrolmentAssembler.toCourseEditionID(any(CourseEditionEnrolmentDto.class))).thenReturn(mock(CourseEditionID.class));
        when(courseEditionEnrolmentAssembler.toStudentID(any(int.class))).thenReturn(mock(StudentID.class));
        when(courseEditionEnrolmentService.removeCourseEditionEnrolment(any(), any())).thenThrow(new RuntimeException("Test exception"));

        //act + assert
        mockMvc.perform(patch("/course-editions/enrolments/students/remove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validEnrolmentDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnNotAcceptableWhenRemoveCourseEditionEnrolmentCannotBeRemoved() throws Exception {
        //arrange
        when(courseEditionEnrolmentAssembler.toCourseEditionID(any(CourseEditionEnrolmentDto.class))).thenReturn(mock(CourseEditionID.class));
        when(courseEditionEnrolmentAssembler.toStudentID(any(int.class))).thenReturn(mock(StudentID.class));
        when(courseEditionEnrolmentService.removeCourseEditionEnrolment(any(), any())).thenReturn(false);
        //act + assert
        mockMvc.perform(patch("/course-editions/enrolments/students/remove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validEnrolmentDto)))
                .andExpect(status().isNotAcceptable());
    }





    @Test
    void whenValidRequest_thenReturnsCreatedCourseEdition() throws Exception {

        // Arrange
        UUID schoolYearID = UUID.randomUUID();
        UUID courseID = UUID.randomUUID();
        UUID generatedID = UUID.randomUUID();

        CourseEditionRequestDTO dto = new CourseEditionRequestDTO(
                "LEI", "LEIC", schoolYearID,
                "SA", "Software Architecture", LocalDate.of(2023, 9, 1)
        );

        CreateCourseEditionCommand command = new CreateCourseEditionCommand(
                new NameWithNumbersAndSpecialChars(dto.programmeName()),
                new Acronym(dto.programmeAcronym()),
                new SchoolYearID(dto.schoolYearID()),
                new Acronym(dto.courseAcronym()),
                new Name(dto.courseName()),
                new Date(dto.studyPlanImplementationDate())
        );

        CourseEditionServiceResponseDTO serviceResponseDTO = new CourseEditionServiceResponseDTO(
                generatedID, "LEIC", schoolYearID,
                "SA", "Software Architecture", LocalDate.now(), courseID.toString(),
                "AAA"
        );

        CourseEditionResponseDTO responseDTO = new CourseEditionResponseDTO(
                generatedID,
                "LEIC",
                schoolYearID,
                "SA",
                "Software Architecture",
                LocalDate.now(),
                courseID.toString(),
                "AAA"
        );

        EntityModel<CourseEditionResponseDTO> responseModel = EntityModel.of(responseDTO);

        when(courseEditionAssembler.toCommand(dto)).thenReturn(command);
        // Usa any() para evitar problemas de equals no command
        when(createCourseEditionService.createCourseEditionForRestApi(any(CreateCourseEditionCommand.class)))
                .thenReturn(serviceResponseDTO);
        when(courseEditionAssembler.toResponseDTO(serviceResponseDTO)).thenReturn(responseDTO);
        when(createCourseEditionHateoasAssembler.toModel(any(CourseEditionResponseDTO.class))).thenReturn(responseModel);

        // Act
        ResponseEntity<EntityModel<CourseEditionResponseDTO>> response = courseEditionRestController.createCourseEdition(dto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().getLocation());
        // Ajusta para comparar com o ID vindo do serviceResponseDTO
        assertEquals("/course-editions/" + serviceResponseDTO.courseEditionID().toString(), response.getHeaders().getLocation().toString());
        assertNotNull(response.getBody());

        EntityModel<CourseEditionResponseDTO> actualBody = response.getBody();

        assertNotNull(actualBody.getContent());
        CourseEditionResponseDTO actualContent = actualBody.getContent();

        assertEquals(responseDTO.courseEditionID(), actualContent.courseEditionID());
        assertEquals(responseDTO.programmeAcronym(), actualContent.programmeAcronym());
        assertEquals(responseDTO.schoolYearID(), actualContent.schoolYearID());
        assertEquals(responseDTO.courseAcronym(), actualContent.courseAcronym());
        assertEquals(responseDTO.courseName(), actualContent.courseName());
        assertEquals(responseDTO.studyPlanImplementationDate(), actualContent.studyPlanImplementationDate());
    }

    @Test
    void whenServiceThrowsIllegalArgumentException_thenReturnsBadRequest() throws Exception {

        // Arrange
        CourseEditionRequestDTO dto = new CourseEditionRequestDTO(
                "LEI", "LEIC", UUID.randomUUID(),
                "SA", "Software Architecture", LocalDate.of(2023, 9, 1)
        );

        CreateCourseEditionCommand command = mock(CreateCourseEditionCommand.class);
        when(courseEditionAssembler.toCommand(any())).thenReturn(command);
        when(createCourseEditionService.createCourseEditionForRestApi(command))
                .thenThrow(new IllegalArgumentException("Invalid request"));

        // Act + Assert
        mockMvc.perform(post("/course-editions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("ARGUMENT_INVALID"))
                .andExpect(jsonPath("$.message").value("Invalid request"));
    }

    @Test
    void whenAssemblerThrowsExceptionThrown_thenReturnsBadRequestWithMessage() throws Exception {

        // Arrange
        mockMvc = MockMvcBuilders.standaloneSetup(courseEditionRestController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        CourseEditionRequestDTO dto = new CourseEditionRequestDTO(
                "LEI", "LEIC", UUID.randomUUID(),
                "SA", "Software Architecture", LocalDate.of(2023, 9, 1)
        );

        when(courseEditionAssembler.toCommand(any())).thenThrow(new IllegalArgumentException("Invalid request"));

        // Act + Assert
        mockMvc.perform(post("/course-editions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("ARGUMENT_INVALID"))
                .andExpect(jsonPath("$.message").value("Invalid request"));
    }


    @Test
    void findAllCourseEditionsShouldReturnAllCourseEditions() {
        // Arrange
        CourseEditionServiceResponseDTO responseDouble1 = new CourseEditionServiceResponseDTO(
                UUID.randomUUID(), "PRG1", UUID.randomUUID(), "Course1",
                "Course Name 1", LocalDate.of(2024, 1, 1), "courseEdition123", "AAA");

        CourseEditionServiceResponseDTO responseDouble2 = new CourseEditionServiceResponseDTO(
                UUID.randomUUID(), "PRG2", UUID.randomUUID(), "Course2",
                "Course Name 2", LocalDate.of(2024, 1, 1), "courseEdition456", "BBB");

        List<CourseEditionServiceResponseDTO> serviceResponseList = List.of(responseDouble1, responseDouble2);

        when(createCourseEditionService.findAll()).thenReturn(serviceResponseList);

        CourseEditionResponseDTO dto1 = mock(CourseEditionResponseDTO.class);
        CourseEditionResponseDTO dto2 = mock(CourseEditionResponseDTO.class);
        List<CourseEditionResponseDTO> dtoList = List.of(dto1, dto2);

        when(courseEditionAssembler.toCourseEditionResponseDTOList(serviceResponseList)).thenReturn(dtoList);

        EntityModel<CourseEditionResponseDTO> entity1 = EntityModel.of(dto1);
        EntityModel<CourseEditionResponseDTO> entity2 = EntityModel.of(dto2);
        CollectionModel<EntityModel<CourseEditionResponseDTO>> collectionModel =
                CollectionModel.of(List.of(entity1, entity2));

        when(courseEditionHATEOASAssembler.toCollectionModel(dtoList)).thenReturn(collectionModel);

        // Act
        ResponseEntity<?> responseEntity = courseEditionRestController.findAllCourseEditions();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        @SuppressWarnings("unchecked")
        CollectionModel<EntityModel<CourseEditionResponseDTO>> body =
                (CollectionModel<EntityModel<CourseEditionResponseDTO>>) responseEntity.getBody();

        assertEquals(2, body.getContent().size());
    }


    @Test
    void whenValidUUID_thenReturnsCourseEditionResponseDTO() throws Exception {
        // Mocks
        ICreateCourseEditionService createCourseEditionService = mock(ICreateCourseEditionService.class);
        ICourseEditionAssembler courseEditionAssembler = mock(ICourseEditionAssembler.class);
        IDefineRucService defineRucService = mock(IDefineRucService.class);
        IGradeAStudentService gradeAStudentService = mock(IGradeAStudentService.class);
        IStudentGradeAssembler studentGradeAssembler = mock(IStudentGradeAssembler.class);
        IProgrammeEditionServiceAssembler programmeEditionAssembler = mock(IProgrammeEditionServiceAssembler.class);
        ICourseEditionRUCHateoasAssembler courseEditionRUCHateoasAssembler = mock(ICourseEditionRUCHateoasAssembler.class);
        IStudentCountAssembler studentCountAssembler = mock(IStudentCountAssembler.class);
        ICourseEditionEnrolmentService courseEditionEnrolmentService = mock(ICourseEditionEnrolmentService.class);
        ICourseEditionEnrolmentAssembler courseEditionEnrolmentAssembler = mock(ICourseEditionEnrolmentAssembler.class);
        ICreateCourseEditionHateoasAssembler createCourseEditionHateoasAssembler = mock(ICreateCourseEditionHateoasAssembler.class);
        ICourseEditionService courseEditionService = mock(ICourseEditionService.class);
        ICourseEditionEnrolmentHateoasAssembler courseEditionEnrolmentHateoasAssembler = mock(ICourseEditionEnrolmentHateoasAssembler.class);
        ICourseEditionHATEOASAssembler courseEditionHATEOASAssembler = mock(ICourseEditionHATEOASAssembler.class);
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        ISchoolYearAssembler schoolYearAssembler = mock(ISchoolYearAssembler.class);

        // Controller
        CourseEditionRestController controller = new CourseEditionRestController(
                courseEditionEnrolmentService,
                courseEditionEnrolmentAssembler,
                createCourseEditionService,
                courseEditionService,
                courseEditionAssembler,
                gradeAStudentService,
                studentGradeAssembler,
                programmeEditionAssembler,
                defineRucService,
                courseEditionRUCHateoasAssembler,
                createCourseEditionHateoasAssembler,
                studentCountAssembler,
                courseEditionEnrolmentHateoasAssembler,
                courseEditionHATEOASAssembler,
                schoolYearService,
                schoolYearAssembler
        );

        UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CourseEditionGeneratedID generatedID = new CourseEditionGeneratedID(uuid);

        // Objetos de retorno
        CourseEditionServiceResponseDTO serviceResponseDTO = mock(CourseEditionServiceResponseDTO.class);
        CourseEditionResponseDTO responseDTO = mock(CourseEditionResponseDTO.class);
        EntityModel<CourseEditionResponseDTO> entityModel = EntityModel.of(responseDTO);

        // Comportamento dos mocks
        when(createCourseEditionService.findById(generatedID)).thenReturn(serviceResponseDTO);
        when(courseEditionAssembler.toResponseDTO(serviceResponseDTO)).thenReturn(responseDTO);
        when(courseEditionHATEOASAssembler.toModel(responseDTO)).thenReturn(entityModel);

        ResponseEntity<?> response = controller.getCourseEditionById(uuid);

        // Verificações
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(entityModel, response.getBody());
    }



    @Test
    void whenInvalidUUID_thenReturnsBadRequest() throws Exception {
        // Arrange
        UUID validUUID = UUID.randomUUID();

        when(createCourseEditionService.findById(any(CourseEditionGeneratedID.class)))
                .thenThrow(new RuntimeException("Not found or invalid"));

        // Act & Assert
        mockMvc.perform(get("/course-editions/by-id/{id}", validUUID.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid parameters or course edition not found."));
    }




    @Test
    void gradeAStudentMethodShouldGradeAStudentSuccessfully_AndReturn201() throws Exception {
        // Arrange
        GradeAStudentRequestDTO requestDTO = new GradeAStudentRequestDTO(
                1234567, 18, "12-04-2025", "Engenharia Informática", "EI",
                "6a7c6ce1-850d-428e-92e1-a8c9de5e3c21", "DSOFT",
                "Desenvolvimento de Software", "12-04-2025");
        GradeAStudentCommand command = mock(GradeAStudentCommand.class);
        GradeAStudentResponseDTO responseDTO = new GradeAStudentResponseDTO(1234567, 18, "12-04-2025", "courseEdition123", "programmeEdition123", "courseInStudyPlan123", "programme123", "schoolYear123", "course123", "studyPlan123");

        when(studentGradeAssembler.toDomain(requestDTO)).thenReturn(command);
        when(gradeAStudentService.gradeAStudent(command)).thenReturn(responseDTO);

        // Act + Assert
        mockMvc.perform(post("/course-editions/studentgrades/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
            {
                "grade": 18,
                "date": "12-04-2025",
                "studentUniqueNumber": 1234567,
                "programmeName": "Engenharia Informática",
                "programmeAcronym": "EI",
                "schoolYearId": "6a7c6ce1-850d-428e-92e1-a8c9de5e3c21",
                "courseAcronym": "DSOFT",
                "courseName": "Desenvolvimento de Software",
                "studyPlanImplementationDate": "12-04-2025"
            }
        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$._studentUniqueNumber").value(1234567))
                .andExpect(jsonPath("$._grade").value(18))
                .andExpect(jsonPath("$._date").value("12-04-2025"));
    }

    @Test
    void whenDefineRucSuccessfullyThenReturnsOkWithHateoas() throws Exception {
        // Arrange
        UUID uuid = UUID.randomUUID();
        DefineRucRequestDTO requestDTO = new DefineRucRequestDTO("AAB"); // Pass null or adjust constructor to only require teacherID if needed

        TeacherID teacherID = mock(TeacherID.class);
        CourseEditionGeneratedID courseEditionID = mock(CourseEditionGeneratedID.class);

        when(courseEditionAssembler.createTeacherID("AAB")).thenReturn(teacherID);
        when(courseEditionAssembler.fromDtoToCourseEditionGeneratedID(new SelectedCourseEditionGeneratedIdDTO(uuid))).thenReturn(courseEditionID);
        when(defineRucService.assignRucToCourseEdition(teacherID, courseEditionID)).thenReturn(true);

        when(courseEditionRUCHateoasAssembler.toModel(any(DefineRucResponseDTO.class)))
                .thenAnswer(invocation -> {
                    DefineRucResponseDTO dto = invocation.getArgument(0);
                    EntityModel<DefineRucResponseDTO> model = EntityModel.of(dto);
                    model.add(Link.of("http://localhost/course-editions/" + dto.courseEditionGeneratedID() + "/ruc").withRel("define-ruc"));
                    return model;
                });

        // Act & Assert
        mockMvc.perform(patch("/course-editions/{id}/ruc", uuid.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.teacherID").value("AAB"))
                .andExpect(jsonPath("$.courseEditionGeneratedID").value(uuid.toString()))
                .andExpect(jsonPath("$._links.define-ruc.href").value("http://localhost/course-editions/" + uuid.toString() + "/ruc"));
    }

    @Test
    void whenDefineRucFailsThenReturnsInternalServerError() throws Exception {
        // Arrange
        UUID uuid = UUID.randomUUID();
        SelectedCourseEditionGeneratedIdDTO courseEditionDTO = new SelectedCourseEditionGeneratedIdDTO(uuid);

        DefineRucRequestDTO requestDTO = new DefineRucRequestDTO("GOM"); // or omit courseEditionID if possible

        TeacherID teacherID = mock(TeacherID.class);
        CourseEditionGeneratedID courseEditionID = mock(CourseEditionGeneratedID.class);

        when(courseEditionAssembler.createTeacherID("GOM")).thenReturn(teacherID);
        when(courseEditionAssembler.fromDtoToCourseEditionGeneratedID(courseEditionDTO)).thenReturn(courseEditionID);

        // Simulate an exception thrown by the service
        doThrow(new RuntimeException("DB error"))
                .when(defineRucService).assignRucToCourseEdition(teacherID, courseEditionID);

        // Act & Assert
        mockMvc.perform(patch("/course-editions/{id}/ruc", uuid.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("DB error"))
                .andExpect(jsonPath("$.code").value("INTERNAL_ERROR"));
    }


    @Test
    void whenAssignRucThrowsIllegalArgumentExceptionThenReturnsBadRequestWithArgumentInvalidCode() throws Exception {
        // Arrange
        UUID uuid = UUID.randomUUID();
        SelectedCourseEditionGeneratedIdDTO courseEditionDTO = new SelectedCourseEditionGeneratedIdDTO(uuid);
        DefineRucRequestDTO requestDTO = new DefineRucRequestDTO("GOM");

        TeacherID teacherID = mock(TeacherID.class);
        CourseEditionGeneratedID courseEditionID = mock(CourseEditionGeneratedID.class);

        when(courseEditionAssembler.createTeacherID("GOM")).thenReturn(teacherID);
        when(courseEditionAssembler.fromDtoToCourseEditionGeneratedID(courseEditionDTO)).thenReturn(courseEditionID);
        when(defineRucService.assignRucToCourseEdition(teacherID, courseEditionID))
                .thenThrow(new IllegalArgumentException("Invalid teacher or course edition"));

        // Act & Assert
        mockMvc.perform(patch("/course-editions/{id}/ruc", uuid.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid teacher or course edition"))
                .andExpect(jsonPath("$.code").value("ARGUMENT_INVALID"));
    }

    @Test
    void getCourseEditionAverageGrade_Success() throws Exception {
        String programmeAcronym = "LEI";
        String VALID_SCHOOL_YEAR_UUID = "123e4567-e89b-12d3-a456-426614174000";
        String courseAcronym = "ESOFT";
        String studyPlanDate = "01-01-2024";
        Double expectedAverageGrade = 15.5;

        when(gradeAStudentService.getAverageGrade(any(CourseEditionID.class))).thenReturn(expectedAverageGrade);

        mockMvc.perform(get("/course-editions/averagegrade")
                        .param("programmeAcronym", programmeAcronym)
                        .param("schoolYearId", VALID_SCHOOL_YEAR_UUID)
                        .param("courseAcronym", courseAcronym)
                        .param("studyPlanDate", studyPlanDate))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(expectedAverageGrade));
    }

    @Test
    void getCourseEditionAverageGrade_ServiceReturnsNull_ShouldReturnOkWithNullBody() throws Exception {
        String programmeAcronym = "LEI";
        String schoolYearId = "123e4567-e89b-12d3-a456-426614174000";
        String courseAcronym = "ESOFT";
        String studyPlanDate = "01-01-2024";

        when(gradeAStudentService.getAverageGrade(any(PAI.VOs.CourseEditionID.class))).thenReturn(null);

        mockMvc.perform(get("/course-editions/averagegrade")
                        .param("programmeAcronym", programmeAcronym)
                        .param("schoolYearId", schoolYearId)
                        .param("courseAcronym", courseAcronym)
                        .param("studyPlanDate", studyPlanDate))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void successfullyGetCourseEditionApprovalRate() throws Exception {
        String programmeAcronym = "LEI";
        String schoolYearId = "123e4567-e89b-12d3-a456-426614174000";
        String courseAcronym = "ESOFT";
        String courseName = "Maths";
        String localDate = "01-01-2024";
        double expectedApprovalRate = 85.5;

        when(gradeAStudentService.knowApprovalRate(any(CourseEditionID.class)))
                .thenReturn(expectedApprovalRate);

        mockMvc.perform(get("/course-editions/approval-rate")
                        .param("programmeAcronym", programmeAcronym)
                        .param("schoolYearId", schoolYearId)
                        .param("courseAcronym", courseAcronym)
                        .param("courseName", courseName)
                        .param("localDate", localDate))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.approvalRate").value(expectedApprovalRate));
    }

    @Test
    void shouldReturnZeroIfCourseEditionApprovalRateIsZero() throws Exception {
        String programmeAcronym = "LEI";
        String schoolYearId = "123e4567-e89b-12d3-a456-426614174000";
        String courseAcronym = "ESOFT";
        String courseName = "Maths";
        String localDate = "01-01-2024";
        double expectedApprovalRate = 0.0;

        when(gradeAStudentService.knowApprovalRate(any(CourseEditionID.class)))
                .thenReturn(expectedApprovalRate);

        mockMvc.perform(get("/course-editions/approval-rate")
                        .param("programmeAcronym", programmeAcronym)
                        .param("schoolYearId", schoolYearId)
                        .param("courseAcronym", courseAcronym)
                        .param("courseName", courseName)
                        .param("localDate", localDate))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.approvalRate").value(expectedApprovalRate));
    }

    @Test
    void shouldReturnBadRequestWhenInvalidSchoolYearId() throws Exception {
        mockMvc.perform(get("/course-editions/approval-rate")
                        .param("programmeAcronym", "LEI")
                        .param("schoolYearId", "invalid-uuid")
                        .param("courseAcronym", "ESOFT")
                        .param("courseName", "Maths")
                        .param("localDate", "01-01-2024"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getNumberOfStudentsInCourseEditionShouldReturnStudentCount_Successfully () throws Exception {
        // Arrange
        UUID uuid = UUID.randomUUID();  // UUID that goes in the URL
        CourseEditionGeneratedID generatedID = new CourseEditionGeneratedID(uuid);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        int expectedStudentCount = 5;

        when(courseEditionService.findCourseEditionIDByGeneratedID(generatedID)).thenReturn(courseEditionIDDouble);
        when(courseEditionEnrolmentService.numberOfStudentsEnrolledInCourseEdition(courseEditionIDDouble)).thenReturn(expectedStudentCount);

        StudentCountDTO studentCountDTO = mock(StudentCountDTO.class);
        when(studentCountDTO.studentCount()).thenReturn(expectedStudentCount);
        when(studentCountAssembler.fromDomainToDTO(expectedStudentCount)).thenReturn(studentCountDTO);

        // Act + Assert
        mockMvc.perform(get("/course-editions/{id}/enrolments/count", uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentCount").value(expectedStudentCount));
    }

    @Test
    void testGetNumberOfStudentsInCourseEdition_ExceptionThrown() throws Exception {
        // Arrange
        UUID uuid = UUID.randomUUID();  // UUID that goes in the URL
        CourseEditionGeneratedID generatedID = new CourseEditionGeneratedID(uuid);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        when(courseEditionService.findCourseEditionIDByGeneratedID(generatedID))
                .thenThrow(new NotFoundException("CourseEdition not found with Universally Unique ID:" + generatedID));

        // Act + Assert
        mockMvc.perform(get("/course-editions/{id}/enrolments/count", uuid))
                .andExpect(status().isNotFound());  // GlobalExceptionHandler will catch the NotFoundException and return the respective status
    }

    @Test
    void testGetNumberOfStudentsInCourseEdition_InvalidUUID_ReturnsBadRequest() throws Exception {
        String invalidUuid = "invalid-uuid-format";

        // Act & Assert
        mockMvc.perform(get("/course-editions/{id}/enrolments/count", invalidUuid))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetNumberOfStudentsInCourseEdition_ServiceException_ReturnsInternalServerError () throws Exception {
        // Arrange
        UUID uuid = UUID.randomUUID();
        CourseEditionGeneratedID generatedID = new CourseEditionGeneratedID(uuid);

        when(courseEditionService.findCourseEditionIDByGeneratedID(generatedID)).thenThrow(new RuntimeException("Database is currently down."));

        // Act + Assert
        mockMvc.perform(get("/course-editions/{id}/enrolments/count", uuid))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getCourseEditionsByProgrammeEditionID_Success() throws Exception {
        // Arrange
        CourseEditionRequestDTO requestDTO = new CourseEditionRequestDTO(
            "Software Engineering",     
            "LEIC",                   
            UUID.randomUUID(),          
            "ESOFT",                   
            "Engineering Software",     
            LocalDate.now()             
        );

        ProgrammeEditionID mockProgrammeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID mockCourseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);
        CourseEditionResponseIDDTO mockResponseIDDTO = new CourseEditionResponseIDDTO(

                "LEIC",
            UUID.randomUUID(),
            "ESOFT",
            "Engineering Software",
            LocalDate.now(),
                "courseEdition123"
        );

        CourseEditionResponseDTO mockResponseDTO = new CourseEditionResponseDTO(
                UUID.randomUUID(),
                "LEIC",
                UUID.randomUUID(),
                "ESOFT",
                "Engineering Software",
                LocalDate.now(),
                "courseEdition123",
                "AAA"
        );

        when(courseEditionAssembler.toProgrammeEditionID(requestDTO)).thenReturn(mockProgrammeEditionID);
        when(courseEditionAssembler.toCourseInStudyPlanID(requestDTO)).thenReturn(mockCourseInStudyPlanID);
        when(courseEditionService.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(mockProgrammeEditionID, mockCourseInStudyPlanID))
            .thenReturn(List.of(mockCourseEditionID));
        when(courseEditionAssembler.toResponseIDDTOList(List.of(mockCourseEditionID))).thenReturn(List.of(mockResponseIDDTO));
        
     //   EntityModel<CourseEditionResponseDTO> entityModel = EntityModel.of(mockResponseDTO);
       // CollectionModel<EntityModel<CourseEditionResponseDTO>> collectionModel = CollectionModel.of(List.of(entityModel));
        when(courseEditionRUCHateoasAssembler.toCollectionModel(List.of(mockResponseIDDTO)))
                .thenReturn(CollectionModel.of(List.of(EntityModel.of(mockResponseIDDTO))));

        // Act & Assert
        mockMvc.perform(get("/course-editions/programmeditions")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    void getCourseEditionsByProgrammeEditionID_EmptyResult() throws Exception {
        // Arrange
        CourseEditionRequestDTO requestDTO = new CourseEditionRequestDTO(
            "Software Engineering",
            "LEIC",
            UUID.randomUUID(),
            "ESOFT",
            "Engineering Software",
            LocalDate.now()
        );

        ProgrammeEditionID mockProgrammeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID mockCourseInStudyPlanID = mock(CourseInStudyPlanID.class);

        when(courseEditionAssembler.toProgrammeEditionID(requestDTO)).thenReturn(mockProgrammeEditionID);
        when(courseEditionAssembler.toCourseInStudyPlanID(requestDTO)).thenReturn(mockCourseInStudyPlanID);
        when(courseEditionService.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(mockProgrammeEditionID, mockCourseInStudyPlanID))
            .thenReturn(List.of());
        when(courseEditionAssembler.toResponseIDDTOList(List.of())).thenReturn(List.of());
        
        CollectionModel<EntityModel<CourseEditionResponseIDDTO>> emptyCollectionModel = CollectionModel.empty();
        when(courseEditionRUCHateoasAssembler.toCollectionModel(List.of()))
            .thenReturn(emptyCollectionModel);

        // Act & Assert
        mockMvc.perform(get("/course-editions/programmeditions")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDTO)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();
    }

    @Test
    void getCourseEditionsByProgrammeEditionID_InvalidRequest() throws Exception {
        // Arrange
        CourseEditionRequestDTO invalidRequestDTO = new CourseEditionRequestDTO(
            "",                        
            "",                        
            null,                       
            "",                        
            "",                         
            null                        
        );

        // Act & Assert
        mockMvc.perform(get("/course-editions/programmeditions")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCourseEditionsByProgrammeEditionID_ServiceThrowsException() throws Exception {
        // Arrange
        CourseEditionRequestDTO requestDTO = new CourseEditionRequestDTO(
            "Software Engineering",
            "LEIC",
            UUID.randomUUID(),
            "ESOFT",
            "Engineering Software",
            LocalDate.now()
        );

        ProgrammeEditionID mockProgrammeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID mockCourseInStudyPlanID = mock(CourseInStudyPlanID.class);

        when(courseEditionAssembler.toProgrammeEditionID(requestDTO)).thenReturn(mockProgrammeEditionID);
        when(courseEditionAssembler.toCourseInStudyPlanID(requestDTO)).thenReturn(mockCourseInStudyPlanID);
        when(courseEditionService.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(mockProgrammeEditionID, mockCourseInStudyPlanID))
            .thenThrow(new RuntimeException("Test exception"));

        // Act & Assert
        mockMvc.perform(get("/course-editions/programmeditions")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCoursesEditionsByProgrammeIDAndCourseID_returnsOkWithDTOs() throws Exception {
        // Arrange
        String programmeAcronym = "LEIC";
        String courseAcronym = "ESOFT";
        String courseName = "Engineering Software";
        CourseEdition ce = mock(CourseEdition.class);
        SchoolYearID syid = mock(SchoolYearID.class);
        SchoolYear schoolYear = mock(SchoolYear.class);
        SchoolYearCEDTO schoolYearCEDTO = mock(SchoolYearCEDTO.class);
        List<CourseEdition> ceList = List.of(ce);
        List<SchoolYearID> syidList = List.of(syid);
        List<SchoolYear> syList = List.of(schoolYear);
        List<SchoolYearCEDTO> dtoList = List.of(schoolYearCEDTO);
        when(courseEditionService.getCourseEditionsByProgrammeIDAndCourseID(any(), any())).thenReturn(ceList);
        when(courseEditionService.getSchoolYearIDsFromCourseEditions(ceList)).thenReturn(syidList);
        when(schoolYearService.getSchoolYearsByIDs(syidList)).thenReturn(syList);
        when(schoolYearAssembler.toCEDTOs(syList)).thenReturn(dtoList);

        // Act & Assert
        mockMvc.perform(get("/course-editions/school-years")
                .param("programmeAcronym", programmeAcronym)
                .param("courseAcronym", courseAcronym)
                .param("courseName", courseName))
                .andExpect(status().isOk());
    }

    @Test
    void getCoursesEditionsByProgrammeIDAndCourseID_returnsBadRequestOnException() throws Exception {
        // Arrange
        when(courseEditionService.getCourseEditionsByProgrammeIDAndCourseID(any(), any())).thenThrow(new RuntimeException("fail"));

        // Act & Assert
        mockMvc.perform(get("/course-editions/school-years")
                .param("programmeAcronym", "LEIC")
                .param("courseAcronym", "ESOFT")
                .param("courseName", "Engineering Software"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCoursesEditionsByProgrammeIDAndCourseID_returnsBadRequestOnMissingParams() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/course-editions/school-years")
                .param("programmeAcronym", "LEIC")
                .param("courseAcronym", "ESOFT")) // missing courseName
                .andExpect(status().isBadRequest());
    }

}
