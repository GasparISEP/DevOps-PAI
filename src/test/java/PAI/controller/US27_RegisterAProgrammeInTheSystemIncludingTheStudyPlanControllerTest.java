package PAI.controller;

import PAI.VOs.*;
import PAI.assembler.studyPlan.IStudyPlanAssembler;
import PAI.domain.programme.Programme;

import PAI.dto.studyPlan.RegisterStudyPlanCommand;
import PAI.dto.studyPlan.StudyPlanDTO;
import PAI.dto.Programme.ProgrammeResponseDTO;
import PAI.dto.Programme.ProgrammeVOsDTO;
import PAI.service.studyPlan.IStudyPlanService;
import PAI.service.programme.IProgrammeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanControllerTest {

    private IProgrammeService _programmeServiceDouble;
    private IStudyPlanService _studyPlanServiceDouble;
    private IStudyPlanAssembler _studyPlanAssemblerDouble;
    private DegreeTypeID _degreeTypeIDDouble;
    private DepartmentID _departmentIDDouble;
    private Programme _programmeDouble;
    private TeacherID _teacherIDDouble;
    private String _programmeName;
    private String _programmeAcronym;
    private int _maxEtcs;
    private int _quantityOfSemesters;
    private String _studyPlanStartDate;
    private RegisterStudyPlanCommand _studyPlanCommandDouble;
    private StudyPlanDTO _studyPlanDTODouble;
    private ProgrammeResponseDTO _programmeResponseDTO;
    private US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController _controllerDouble;

    @BeforeEach
    void setup(){
        _programmeServiceDouble = mock(IProgrammeService.class);
        _studyPlanServiceDouble = mock(IStudyPlanService.class);
        _studyPlanAssemblerDouble = mock(IStudyPlanAssembler.class);
    }

    private void createDoubles() throws Exception {
        _degreeTypeIDDouble = mock(DegreeTypeID.class);
        _departmentIDDouble = mock(DepartmentID.class);
        _teacherIDDouble = mock(TeacherID.class);
        _programmeName = "Software Engineering";
        _programmeAcronym = "SE";
        _maxEtcs = 180;
        _quantityOfSemesters = 2;
        _studyPlanStartDate = "2025-09-01";
        _studyPlanCommandDouble = mock(RegisterStudyPlanCommand.class);
        _studyPlanDTODouble = mock(StudyPlanDTO.class);
        _programmeResponseDTO = mock(ProgrammeResponseDTO.class);
        _programmeDouble = mock(Programme.class);
        _controllerDouble = new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(_programmeServiceDouble,
                                                                _studyPlanServiceDouble, _studyPlanAssemblerDouble);
    }

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullProgrammeRepo() throws Exception {
        //arrange

        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(null, _studyPlanServiceDouble, _studyPlanAssemblerDouble));

        assertEquals("Programme Service cannot be null.", exception.getMessage());
    }

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullStudyPlanService() {
        //arrange

        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(_programmeServiceDouble, null, _studyPlanAssemblerDouble));

        assertEquals("Study Plan Service cannot be null.", exception.getMessage());

    }

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullStudyPlanAssembler() {
        //arrange

        //act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(_programmeServiceDouble, _studyPlanServiceDouble, null));

        assertEquals("Study Plan Assembler cannot be null.", exception.getMessage());

    }

    @Test
    void shouldCreateRegisterProgrammeInTheSystemControllerCorrectly() throws Exception {
        //arrange

        //act & Assert
        US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController controller =
                new US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlanController(_programmeServiceDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble);
    }

    @Test
    void shouldRegisterProgrammeAndStudyPlanSuccessfully() throws Exception {
        // Arrange
        createDoubles();
        when(_programmeServiceDouble.registerProgramme(any(ProgrammeVOsDTO.class))).thenReturn(_programmeDouble);
        when(_studyPlanAssemblerDouble.toCommand(_programmeName, _programmeAcronym, LocalDate.parse(_studyPlanStartDate))).thenReturn(_studyPlanCommandDouble);
        when(_studyPlanServiceDouble.createStudyPlan(_studyPlanCommandDouble)).thenReturn(_studyPlanDTODouble);

        // Act
        boolean result = _controllerDouble.registerProgrammeIncludingStudyPlan(_programmeName, _programmeAcronym,
                _maxEtcs, _quantityOfSemesters, _degreeTypeIDDouble, _departmentIDDouble, _teacherIDDouble, _studyPlanStartDate);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldFailWhenProgrammeFailsToRegister() throws Exception {
        // Arrange
        createDoubles();
        when(_programmeServiceDouble.registerProgramme(any(ProgrammeVOsDTO.class))).thenThrow(new Exception("Exception"));
        when(_studyPlanAssemblerDouble.toCommand(_programmeName, _programmeAcronym, LocalDate.parse(_studyPlanStartDate))).thenReturn(_studyPlanCommandDouble);


        // Act
        boolean result = _controllerDouble.registerProgrammeIncludingStudyPlan(_programmeName, _programmeAcronym,
                _maxEtcs, _quantityOfSemesters, _degreeTypeIDDouble, _departmentIDDouble, _teacherIDDouble, _studyPlanStartDate);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldFailWhenStudyPlanFailsToRegister() throws Exception {
        // Arrange
        createDoubles();
        when(_programmeServiceDouble.registerProgramme(any(ProgrammeVOsDTO.class))).thenReturn(_programmeDouble);
        when(_studyPlanAssemblerDouble.toCommand(_programmeName, _programmeAcronym, LocalDate.parse(_studyPlanStartDate))).thenReturn(_studyPlanCommandDouble);
        when(_studyPlanServiceDouble.createStudyPlan(_studyPlanCommandDouble)).thenThrow(new Exception("Exception"));

        // Act
        boolean result = _controllerDouble.registerProgrammeIncludingStudyPlan(_programmeName, _programmeAcronym,
                _maxEtcs, _quantityOfSemesters, _degreeTypeIDDouble, _departmentIDDouble, _teacherIDDouble, _studyPlanStartDate);

        // Assert
        assertFalse(result);
    }
}