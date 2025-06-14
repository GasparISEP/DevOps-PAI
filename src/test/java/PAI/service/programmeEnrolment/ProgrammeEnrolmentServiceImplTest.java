package PAI.service.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.domain.programmeEnrolment.IProgrammeEnrolmentFactory;
import PAI.domain.repositoryInterfaces.programmeEnrolment.IProgrammeEnrolmentRepository;
import PAI.persistence.mem.programmeEnrolment.IProgrammeEnrolmentListFactory;
import PAI.persistence.mem.programmeEnrolment.ProgrammeEnrolmentRepositoryImpl;
import PAI.service.programme.IProgrammeService;
import PAI.service.student.IStudentService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProgrammeEnrolmentServiceImplTest {

    private IProgrammeEnrolmentFactory _peFactoryDouble;
    private IProgrammeEnrolmentRepository _peRepositoryDouble;
    private StudentID _studentIDDouble;
    private AccessMethodID _amIDDouble;
    private ProgrammeID _programmeIDDouble;
    private Date _dateDouble;
    private ProgrammeEnrolment _peDouble;
    private ProgrammeEnrolmentID _peIDDouble;
    private IStudentService _sService;
    private IProgrammeService _progService;

    private void createDoubles() {
        _peFactoryDouble = mock(IProgrammeEnrolmentFactory.class);
        _peRepositoryDouble = mock(IProgrammeEnrolmentRepository.class);
        _studentIDDouble = mock(StudentID.class);
        _amIDDouble = mock(AccessMethodID.class);
        _programmeIDDouble = mock(ProgrammeID.class);
        _dateDouble = mock(Date.class);
        _peDouble = mock(ProgrammeEnrolment.class);
        _peIDDouble = mock(ProgrammeEnrolmentID.class);
        _sService = mock(IStudentService.class);
        _progService = mock(IProgrammeService.class);
    }

    @Test
    void shouldCreateProgrammeEnrolmentServiceIfParametersValid() {
        //Arrange
        createDoubles();

        //Act
        ProgrammeEnrolmentServiceImpl peService = new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble, _sService, _progService);
        //Assert
        assertNotNull(peService);

    }

    @Test
    void shouldThrowExceptionAndNotCreateProgrammeEnrolmentServiceIfFactoryNull() {
        //Arrange
        createDoubles();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentServiceImpl(null, _peRepositoryDouble, _sService, _progService));
    }

    @Test
    void shouldThrowExceptionAndNotCreateProgrammeEnrolmentServiceIfRepositoryNull() {
        //Arrange
        createDoubles();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, null, _sService, _progService));
    }

    @Test
    void shouldThrowExceptionAndNotCreateProgrammeEnrolmentServiceIfStudentServiceNull() {
        //Arrange
        createDoubles();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble, null, _progService));
    }

    @Test
    void shouldThrowExceptionAndNotCreateProgrammeEnrolmentServiceIfProgServiceNull() {
        //Arrange
        createDoubles();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble, _sService, null));
    }

    @Test
    void shouldCreateNewProgrammeEnrolmentAndPersist() throws Exception {
        //Arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService = new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble, _sService, _progService);

        when(_peFactoryDouble.createProgrammeEnrolment(_studentIDDouble, _amIDDouble, _programmeIDDouble, _dateDouble)).thenReturn(_peDouble);
        when(_peDouble.getProgrammeEnrolmentID()).thenReturn(_peIDDouble);
        when(_peRepositoryDouble.containsOfIdentity(_peIDDouble)).thenReturn(false);
        when(_peRepositoryDouble.save(_peDouble)).thenReturn(_peDouble);

        //Act
        ProgrammeEnrolment result = peService.enrolStudentInProgramme(_studentIDDouble, _amIDDouble, _programmeIDDouble, _dateDouble);

        //Assert
        assertEquals(_peDouble, result);    }

    @Test
    void shouldThrowExceptionAndNotCreateProgrammeEnrolmentIfStudentIDNull() {
        //Arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService = new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble, _sService, _progService);

        // Act & Assert:
        Exception ex = assertThrows(Exception.class, () -> peService.enrolStudentInProgramme(null, _amIDDouble, _programmeIDDouble, _dateDouble));
    }

    @Test
    void shouldThrowExceptionAndNotCreateProgrammeEnrolmentIfAccessMethodIDNull() {
        //Arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService = new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble, _sService, _progService);

        //Act + assert
        Exception ex = assertThrows(
                Exception.class,
                () -> peService.enrolStudentInProgramme(
                        _studentIDDouble,
                        null,
                        _programmeIDDouble,
                        _dateDouble
                )
        );
    }

    @Test
    void shouldThrowExceptionAndNotCreateProgrammeEnrolmentIfProgrammeIDNull() {
        //Arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService = new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble, _sService, _progService);

        //Act + Assert
        Exception ex = assertThrows(
                Exception.class,
                () -> peService.enrolStudentInProgramme(
                        _studentIDDouble,
                        _amIDDouble,
                        null,
                        _dateDouble
                )
        );
    }

    @Test
    void shouldThrowExceptionAndNotCreateProgrammeEnrolmentIfDateIDNull() {
        //Arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService = new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble, _sService, _progService);

        //Act + Assert
        Exception ex = assertThrows(
                Exception.class,
                () -> peService.enrolStudentInProgramme(
                        _studentIDDouble,
                        _amIDDouble,
                        _programmeIDDouble,
                        null
                )
        );
    }

    @Test
    void shouldThrowExceptionAndNotPersistProgrammeEnrolment(){
        // Arrange
        createDoubles();

        when(_peFactoryDouble.createProgrammeEnrolment(
                any(StudentID.class),
                any(AccessMethodID.class),
                any(ProgrammeID.class),
                any(Date.class)
        )).thenReturn(_peDouble);
        when(_peDouble.getProgrammeEnrolmentID()).thenReturn(_peIDDouble);
        when(_peRepositoryDouble.containsOfIdentity(_peIDDouble)).thenReturn(true);


        ProgrammeEnrolmentServiceImpl peService = new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble, _sService, _progService);

        // Act & Assert:
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> peService.enrolStudentInProgramme(_studentIDDouble, _amIDDouble, _programmeIDDouble, _dateDouble));
        assertEquals("Programme Enrolment already exists.", ex.getMessage());
    }

    @Test
    void shouldReturnEnrolmentWhenFound() {
        // Arrange
        createDoubles();

        ProgrammeEnrolmentServiceImpl peService =
                new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble, _sService, _progService);


        when(_peRepositoryDouble.findByStudentIDAndProgrammeID(_studentIDDouble, _programmeIDDouble))
                .thenReturn(Optional.of(_peDouble));

        // Act
        ProgrammeEnrolment result =
                peService.findEnrolmentByStudentAndProgramme(_studentIDDouble, _programmeIDDouble);

        // Assert
        assertNotNull(result, "Deveria retornar o object de domínio quando existe");
        assertSame(_peDouble, result, "Deveria ser exatamente a instância mockada");
    }

    @Test
    void shouldReturnNullWhenNotFound() {
        // Arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService =
                new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble, _sService, _progService);


        when(_peRepositoryDouble.findByStudentIDAndProgrammeID(_studentIDDouble, _programmeIDDouble))
                .thenReturn(Optional.empty());

        // Act
        ProgrammeEnrolment result =
                peService.findEnrolmentByStudentAndProgramme(_studentIDDouble, _programmeIDDouble);

        // Assert
        assertNull(result, "Deveria retornar null quando não existe enrolment");
    }

    @Test
    void testGetProgrammesStudentIsEnrolled() {
        // Arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService =
                new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble, _sService, _progService);

        MaxEcts maxEcts = new MaxEcts(12);
        QuantSemesters quantSemesters = new QuantSemesters(2);
        DegreeTypeID degreeTypeID = new DegreeTypeID();
        DepartmentAcronym departmentAcronym = new DepartmentAcronym("OLA");
        DepartmentID departmentID = new DepartmentID(departmentAcronym);
        TeacherAcronym teacherAcronym = new TeacherAcronym("OLA");
        TeacherID teacherID = new TeacherID(teacherAcronym);
        StudentID studentID = new StudentID(1234567);
        Acronym acronym = new Acronym("OLA");
        ProgrammeID programmeID = new ProgrammeID(acronym);
        ProgrammeEnrolmentGeneratedID enrolmentGID = new ProgrammeEnrolmentGeneratedID(UUID.randomUUID());
        NameWithNumbersAndSpecialChars progName = new NameWithNumbersAndSpecialChars("Engineering");
        Name studentName = new Name("John Doe");
        AccessMethodID accessMethodID = new AccessMethodID(UUID.randomUUID());
        Date date = new Date(LocalDate.of(2025,12,12));

        ProgrammeEnrolment enrolment = new ProgrammeEnrolment(
                studentID, accessMethodID, programmeID, date, enrolmentGID
        );

        Programme programme = new Programme(progName,acronym,maxEcts,quantSemesters,degreeTypeID,departmentID,teacherID,programmeID);

        when(_peRepositoryDouble.getProgrammesStudentIsEnrolledIn(studentID)).thenReturn(List.of(enrolment));
        when(_progService.getProgrammesByProgrammeIDs(List.of(programmeID))).thenReturn(List.of(programme));
        when(_sService.getNameByStudentID(studentID)).thenReturn(studentName);

        // Act
        US34ListOfProgrammes actual = peService.getProgrammesStudentIsEnrolled(studentID);

        // Assert
        ProgrammeSummary expectedSummary = new ProgrammeSummary(programmeID, progName, enrolmentGID);
        US34ListOfProgrammes expected = new US34ListOfProgrammes(List.of(expectedSummary), studentName);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnListOfProgrammeIDs() {
        //arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService =
                new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble, _sService, _progService);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeEnrolment enrolment = mock(ProgrammeEnrolment.class);
        when(enrolment.getProgrammeID()).thenReturn(programmeID);

        ArrayList<ProgrammeEnrolment> listDouble = new ArrayList<>();
        listDouble.add(enrolment);

        //act
        List<ProgrammeID> res = peService.getProgrammeIDsByProgrammeEnrolment(listDouble);

        //assert
        assertNotNull(res);
        assertEquals(1, res.size());
        assertEquals(programmeID, res.get(0));
    }

    @Test
    public void testMappingVOsIntoRecord_withMockedData() {
        //arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService =
                new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble, _sService, _progService);

        when(_programmeIDDouble.toString()).thenReturn("PPP");

        Programme mockProgramme = mock(Programme.class);
        NameWithNumbersAndSpecialChars mockProgName = mock(NameWithNumbersAndSpecialChars.class);
        when(mockProgramme.getProgrammeID()).thenReturn(_programmeIDDouble);
        when(mockProgramme.getProgrammeName()).thenReturn(mockProgName);

        ProgrammeEnrolmentGeneratedID mockEnrolmentGID = mock(ProgrammeEnrolmentGeneratedID.class);
        when(_peDouble.getProgrammeID()).thenReturn(_programmeIDDouble);
        when(_peDouble.getProgrammeEnrolmentGeneratedID()).thenReturn(mockEnrolmentGID);

        List<ProgrammeEnrolment> enrolments = List.of(_peDouble);
        List<Programme> programmes = List.of(mockProgramme);

        //act
        List<ProgrammeSummary> summaries = peService.mappingVOsIntoRecord(enrolments, programmes);

        //assert
        assertNotNull(summaries);
        assertEquals(1, summaries.size());

        ProgrammeSummary summary = summaries.get(0);
        assertEquals(_programmeIDDouble, summary.programmeID());
        assertEquals(mockProgName, summary.programmeName());
        assertEquals(mockEnrolmentGID, summary.generatedID());
    }


}