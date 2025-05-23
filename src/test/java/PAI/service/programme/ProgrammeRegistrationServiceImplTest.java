package PAI.service.programme;

import PAI.VOs.*;
import PAI.assembler.programme.IProgrammeAssembler;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.department.Department;
import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.programme.Programme;
import PAI.domain.repositoryInterfaces.degreeType.IDegreeTypeRepository;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.teacher.Teacher;
import PAI.dto.Programme.ProgrammeResponseDTO;
import PAI.dto.Programme.ProgrammeVOsDTO;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.stream.Streams;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeRegistrationServiceImplTest {

    static class ProgrammeRegistrationServiceTestData {
        IProgrammeFactory programmeFactoryDouble = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepositoryDouble = mock(IProgrammeRepository.class);
        IDegreeTypeRepository degreeTypeRepositoryDouble = mock(IDegreeTypeRepository.class);
        IDepartmentRepository departmentRepositoryDouble = mock(IDepartmentRepository.class);
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        IProgrammeAssembler programmeAssemblerDouble = mock(IProgrammeAssembler.class);
        ProgrammeVOsDTO programmeVOsDTODouble = mock(ProgrammeVOsDTO.class);
        NameWithNumbersAndSpecialChars programmeNameDouble = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronymDouble = mock(Acronym.class);
        QuantEcts quantityOfEctsDouble = mock(QuantEcts.class);
        QuantSemesters quantityOfSemestersDouble = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeIDDouble = mock(DegreeTypeID.class);
        DegreeType degreeTypeDouble = mock(DegreeType.class);
        DepartmentID departmentIDDouble = mock(DepartmentID.class);
        Department departmentDouble = mock(Department.class);
        TeacherID teacherIDDouble = mock(TeacherID.class);
        Teacher teacherDouble = mock(Teacher.class);
        Programme programmeDouble = mock(Programme.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        Name nameDouble1 = mock(Name.class);
        Name nameDouble2 = mock(Name.class);
        Name nameDouble3 = mock(Name.class);
        ProgrammeResponseDTO programmeResponseDTO = mock(ProgrammeResponseDTO.class);
    }

    @Test
    void shouldCreateProgrammeRegistrationService() {
        //arrange
        ProgrammeRegistrationServiceTestData data = new ProgrammeRegistrationServiceTestData();

        //act
        ProgrammeRegistrationServiceImpl programmeRegistrationService = new ProgrammeRegistrationServiceImpl(data.programmeFactoryDouble, data.programmeRepositoryDouble, data.degreeTypeRepositoryDouble, data.departmentRepositoryDouble, data.teacherRepositoryDouble, data.programmeAssemblerDouble);

        //assert
        assertNotNull(programmeRegistrationService);
    }

    static Stream<Arguments> parametersToCreateProgrammeServiceAreInvalid() {
        return Streams.of(
                Arguments.of(null, mock(IProgrammeRepository.class), mock(IDegreeTypeRepository.class), mock(IDepartmentRepository.class), mock(ITeacherRepository.class), mock(IProgrammeAssembler.class), "Programme Factory cannot be null"),
                Arguments.of(mock(IProgrammeFactory.class), null, mock(IDegreeTypeRepository.class), mock(IDepartmentRepository.class), mock(ITeacherRepository.class), mock(IProgrammeAssembler.class), "Programme Repository cannot be null"),
                Arguments.of(mock(IProgrammeFactory.class), mock(IProgrammeRepository.class), null, mock(IDepartmentRepository.class), mock(ITeacherRepository.class), mock(IProgrammeAssembler.class), "Degree Type Repository cannot be null"),
                Arguments.of(mock(IProgrammeFactory.class), mock(IProgrammeRepository.class), mock(IDegreeTypeRepository.class), null, mock(ITeacherRepository.class), mock(IProgrammeAssembler.class), "Department Repository cannot be null"),
                Arguments.of(mock(IProgrammeFactory.class), mock(IProgrammeRepository.class), mock(IDegreeTypeRepository.class), mock(IDepartmentRepository.class), null, mock(IProgrammeAssembler.class), "Teacher Repository cannot be null"),
                Arguments.of(mock(IProgrammeFactory.class), mock(IProgrammeRepository.class), mock(IDegreeTypeRepository.class), mock(IDepartmentRepository.class), mock(ITeacherRepository.class), null, "Programme Assembler cannot be null")
        );
    }

    @ParameterizedTest
    @MethodSource("parametersToCreateProgrammeServiceAreInvalid")
    void shouldThrowExceptionWhenParametersToCreateProgrammeServiceAreNotValid(IProgrammeFactory programmeFactory, IProgrammeRepository programmeRepository, IDegreeTypeRepository degreeTypeRepository, IDepartmentRepository departmentRepository, ITeacherRepository teacherRepository, IProgrammeAssembler programmeAssembler, String expectedMessage) {
        //arrange

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ProgrammeRegistrationServiceImpl(programmeFactory, programmeRepository, degreeTypeRepository, departmentRepository, teacherRepository, programmeAssembler));

        //assert
        assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    void shouldRegisterProgrammeSuccessfully() throws Exception {
        //arrange
        ProgrammeRegistrationServiceTestData data = new ProgrammeRegistrationServiceTestData();
        ProgrammeRegistrationServiceImpl programmeRegistrationService = new ProgrammeRegistrationServiceImpl(data.programmeFactoryDouble, data.programmeRepositoryDouble, data.degreeTypeRepositoryDouble, data.departmentRepositoryDouble, data.teacherRepositoryDouble, data.programmeAssemblerDouble);

        String degreeTypeName = "Master";
        String departmentName = "Astronomy";
        String teacherName = "AAA";

        when(data.programmeVOsDTODouble.getName()).thenReturn(data.programmeNameDouble);
        when(data.programmeVOsDTODouble.getAcronym()).thenReturn(data.acronymDouble);
        when(data.programmeVOsDTODouble.getQuantEcts()).thenReturn(data.quantityOfEctsDouble);
        when(data.programmeVOsDTODouble.getQuantSemesters()).thenReturn(data.quantityOfSemestersDouble);
        when(data.programmeVOsDTODouble.getDegreeTypeID()).thenReturn(data.degreeTypeIDDouble);
        when(data.programmeVOsDTODouble.getDepartmentID()).thenReturn(data.departmentIDDouble);
        when(data.programmeVOsDTODouble.getTeacherID()).thenReturn(data.teacherIDDouble);

        when(data.programmeFactoryDouble.registerProgramme(data.programmeNameDouble, data.acronymDouble, data.quantityOfEctsDouble, data.quantityOfSemestersDouble, data.degreeTypeIDDouble, data.departmentIDDouble, data.teacherIDDouble)).thenReturn(data.programmeDouble);

        when(data.programmeDouble.identity()).thenReturn(data.programmeIDDouble);
        when(data.programmeRepositoryDouble.containsOfIdentity(data.programmeIDDouble)).thenReturn(false);
        when(data.programmeRepositoryDouble.save(data.programmeDouble)).thenReturn(data.programmeDouble);
        when(data.degreeTypeRepositoryDouble.ofIdentity(data.degreeTypeIDDouble)).thenReturn(Optional.of(data.degreeTypeDouble));
        when(data.degreeTypeDouble.getName()).thenReturn(data.nameDouble1);
        when(data.nameDouble1.getName()).thenReturn(degreeTypeName);
        when(data.programmeDouble.getDepartment()).thenReturn(data.departmentIDDouble);
        when(data.departmentRepositoryDouble.ofIdentity(data.departmentIDDouble)).thenReturn(Optional.of(data.departmentDouble));
        when(data.departmentDouble.getName()).thenReturn(data.nameDouble2);
        when(data.nameDouble2.getName()).thenReturn(departmentName);
        when(data.programmeDouble.getProgrammeDirectorID()).thenReturn(data.teacherIDDouble);
        when(data.teacherRepositoryDouble.ofIdentity(data.teacherIDDouble)).thenReturn(Optional.of(data.teacherDouble));
        when(data.teacherDouble.getName()).thenReturn(data.nameDouble3);
        when(data.nameDouble3.getName()).thenReturn(teacherName);

        when(data.programmeAssemblerDouble.fromDomainToDTO(data.programmeDouble, degreeTypeName, departmentName, teacherName)).thenReturn(data.programmeResponseDTO);

        //act
        ProgrammeResponseDTO result = programmeRegistrationService.registerProgramme(data.programmeVOsDTODouble);

        //assert
        assertSame(data.programmeResponseDTO, result);
    }

    @Test
    void shouldThrowExceptionIfProgrammeIsAlreadyRegistered() {
        //arrange
        ProgrammeRegistrationServiceTestData data = new ProgrammeRegistrationServiceTestData();
        ProgrammeRegistrationServiceImpl programmeRegistrationService = new ProgrammeRegistrationServiceImpl(data.programmeFactoryDouble, data.programmeRepositoryDouble, data.degreeTypeRepositoryDouble, data.departmentRepositoryDouble, data.teacherRepositoryDouble, data.programmeAssemblerDouble);

        when(data.programmeVOsDTODouble.getName()).thenReturn(data.programmeNameDouble);
        when(data.programmeVOsDTODouble.getAcronym()).thenReturn(data.acronymDouble);
        when(data.programmeVOsDTODouble.getQuantEcts()).thenReturn(data.quantityOfEctsDouble);
        when(data.programmeVOsDTODouble.getQuantSemesters()).thenReturn(data.quantityOfSemestersDouble);
        when(data.programmeVOsDTODouble.getDegreeTypeID()).thenReturn(data.degreeTypeIDDouble);
        when(data.programmeVOsDTODouble.getDepartmentID()).thenReturn(data.departmentIDDouble);
        when(data.programmeVOsDTODouble.getTeacherID()).thenReturn(data.teacherIDDouble);

        when(data.programmeFactoryDouble.registerProgramme(data.programmeNameDouble, data.acronymDouble, data.quantityOfEctsDouble, data.quantityOfSemestersDouble, data.degreeTypeIDDouble, data.departmentIDDouble, data.teacherIDDouble)).thenReturn(data.programmeDouble);

        when(data.programmeDouble.identity()).thenReturn(data.programmeIDDouble);
        when(data.programmeRepositoryDouble.containsOfIdentity(data.programmeIDDouble)).thenReturn(true);

        //act
        Exception exception = assertThrows(Exception.class, () -> programmeRegistrationService.registerProgramme(data.programmeVOsDTODouble));

        //assert
        assertEquals(exception.getMessage(), "Programme is already registered");
    }

    @Test
    void shouldThrowExceptionIfItIsPossibleToSaveProgrammeInTheDatabase() throws Exception {
        //arrange
        ProgrammeRegistrationServiceTestData data = new ProgrammeRegistrationServiceTestData();
        ProgrammeRegistrationServiceImpl programmeRegistrationService = new ProgrammeRegistrationServiceImpl(data.programmeFactoryDouble, data.programmeRepositoryDouble, data.degreeTypeRepositoryDouble, data.departmentRepositoryDouble, data.teacherRepositoryDouble, data.programmeAssemblerDouble);

        when(data.programmeVOsDTODouble.getName()).thenReturn(data.programmeNameDouble);
        when(data.programmeVOsDTODouble.getAcronym()).thenReturn(data.acronymDouble);
        when(data.programmeVOsDTODouble.getQuantEcts()).thenReturn(data.quantityOfEctsDouble);
        when(data.programmeVOsDTODouble.getQuantSemesters()).thenReturn(data.quantityOfSemestersDouble);
        when(data.programmeVOsDTODouble.getDegreeTypeID()).thenReturn(data.degreeTypeIDDouble);
        when(data.programmeVOsDTODouble.getDepartmentID()).thenReturn(data.departmentIDDouble);
        when(data.programmeVOsDTODouble.getTeacherID()).thenReturn(data.teacherIDDouble);

        when(data.programmeFactoryDouble.registerProgramme(data.programmeNameDouble, data.acronymDouble, data.quantityOfEctsDouble, data.quantityOfSemestersDouble, data.degreeTypeIDDouble, data.departmentIDDouble, data.teacherIDDouble)).thenReturn(data.programmeDouble);

        when(data.programmeDouble.identity()).thenReturn(data.programmeIDDouble);
        when(data.programmeRepositoryDouble.containsOfIdentity(data.programmeIDDouble)).thenReturn(false);
        when(data.programmeRepositoryDouble.save(data.programmeDouble)).thenThrow(new RuntimeException());

        //act + assert
        assertThrows(RuntimeException.class, () -> programmeRegistrationService.registerProgramme(data.programmeVOsDTODouble));

    }

    @Test
    void shouldThrowExceptionIfDegreeTypeCannotBeFoundByID() throws Exception {
        //arrange
        ProgrammeRegistrationServiceTestData data = new ProgrammeRegistrationServiceTestData();
        ProgrammeRegistrationServiceImpl programmeRegistrationService = new ProgrammeRegistrationServiceImpl(data.programmeFactoryDouble, data.programmeRepositoryDouble, data.degreeTypeRepositoryDouble, data.departmentRepositoryDouble, data.teacherRepositoryDouble, data.programmeAssemblerDouble);

        when(data.programmeVOsDTODouble.getName()).thenReturn(data.programmeNameDouble);
        when(data.programmeVOsDTODouble.getAcronym()).thenReturn(data.acronymDouble);
        when(data.programmeVOsDTODouble.getQuantEcts()).thenReturn(data.quantityOfEctsDouble);
        when(data.programmeVOsDTODouble.getQuantSemesters()).thenReturn(data.quantityOfSemestersDouble);
        when(data.programmeVOsDTODouble.getDegreeTypeID()).thenReturn(data.degreeTypeIDDouble);
        when(data.programmeVOsDTODouble.getDepartmentID()).thenReturn(data.departmentIDDouble);
        when(data.programmeVOsDTODouble.getTeacherID()).thenReturn(data.teacherIDDouble);

        when(data.programmeFactoryDouble.registerProgramme(data.programmeNameDouble, data.acronymDouble, data.quantityOfEctsDouble, data.quantityOfSemestersDouble, data.degreeTypeIDDouble, data.departmentIDDouble, data.teacherIDDouble)).thenReturn(data.programmeDouble);

        when(data.programmeDouble.identity()).thenReturn(data.programmeIDDouble);
        when(data.programmeRepositoryDouble.containsOfIdentity(data.programmeIDDouble)).thenReturn(false);
        when(data.programmeRepositoryDouble.save(data.programmeDouble)).thenReturn(data.programmeDouble);
        when(data.degreeTypeRepositoryDouble.ofIdentity(data.degreeTypeIDDouble)).thenReturn(Optional.empty());

        //act
        Exception exception = assertThrows(EntityNotFoundException.class, () -> programmeRegistrationService.registerProgramme(data.programmeVOsDTODouble));

        //assert
        assertEquals(exception.getMessage(), "Degree Type not found");
    }

    @Test
    void shouldThrowExceptionIfDepartmentCannotBeFoundByID() throws Exception {
        //arrange
        ProgrammeRegistrationServiceTestData data = new ProgrammeRegistrationServiceTestData();
        ProgrammeRegistrationServiceImpl programmeRegistrationService = new ProgrammeRegistrationServiceImpl(data.programmeFactoryDouble, data.programmeRepositoryDouble, data.degreeTypeRepositoryDouble, data.departmentRepositoryDouble, data.teacherRepositoryDouble, data.programmeAssemblerDouble);

        String degreeTypeName = "Master";

        when(data.programmeVOsDTODouble.getName()).thenReturn(data.programmeNameDouble);
        when(data.programmeVOsDTODouble.getAcronym()).thenReturn(data.acronymDouble);
        when(data.programmeVOsDTODouble.getQuantEcts()).thenReturn(data.quantityOfEctsDouble);
        when(data.programmeVOsDTODouble.getQuantSemesters()).thenReturn(data.quantityOfSemestersDouble);
        when(data.programmeVOsDTODouble.getDegreeTypeID()).thenReturn(data.degreeTypeIDDouble);
        when(data.programmeVOsDTODouble.getDepartmentID()).thenReturn(data.departmentIDDouble);
        when(data.programmeVOsDTODouble.getTeacherID()).thenReturn(data.teacherIDDouble);

        when(data.programmeFactoryDouble.registerProgramme(data.programmeNameDouble, data.acronymDouble, data.quantityOfEctsDouble, data.quantityOfSemestersDouble, data.degreeTypeIDDouble, data.departmentIDDouble, data.teacherIDDouble)).thenReturn(data.programmeDouble);

        when(data.programmeDouble.identity()).thenReturn(data.programmeIDDouble);
        when(data.programmeRepositoryDouble.containsOfIdentity(data.programmeIDDouble)).thenReturn(false);
        when(data.programmeRepositoryDouble.save(data.programmeDouble)).thenReturn(data.programmeDouble);
        when(data.degreeTypeRepositoryDouble.ofIdentity(data.degreeTypeIDDouble)).thenReturn(Optional.of(data.degreeTypeDouble));
        when(data.degreeTypeDouble.getName()).thenReturn(data.nameDouble1);
        when(data.nameDouble1.getName()).thenReturn(degreeTypeName);
        when(data.programmeDouble.getDepartment()).thenReturn(data.departmentIDDouble);
        when(data.departmentRepositoryDouble.ofIdentity(data.departmentIDDouble)).thenReturn(Optional.empty());

        //act
        Exception exception = assertThrows(EntityNotFoundException.class, () -> programmeRegistrationService.registerProgramme(data.programmeVOsDTODouble));

        //assert
        assertEquals(exception.getMessage(), "Department not found");
    }

    @Test
    void shouldThrowExceptionIfTeacherCannotBeFoundByID() throws Exception {
        //arrange
        ProgrammeRegistrationServiceTestData data = new ProgrammeRegistrationServiceTestData();
        ProgrammeRegistrationServiceImpl programmeRegistrationService = new ProgrammeRegistrationServiceImpl(data.programmeFactoryDouble, data.programmeRepositoryDouble, data.degreeTypeRepositoryDouble, data.departmentRepositoryDouble, data.teacherRepositoryDouble, data.programmeAssemblerDouble);

        String degreeTypeName = "Master";
        String departmentName = "Astronomy";

        when(data.programmeVOsDTODouble.getName()).thenReturn(data.programmeNameDouble);
        when(data.programmeVOsDTODouble.getAcronym()).thenReturn(data.acronymDouble);
        when(data.programmeVOsDTODouble.getQuantEcts()).thenReturn(data.quantityOfEctsDouble);
        when(data.programmeVOsDTODouble.getQuantSemesters()).thenReturn(data.quantityOfSemestersDouble);
        when(data.programmeVOsDTODouble.getDegreeTypeID()).thenReturn(data.degreeTypeIDDouble);
        when(data.programmeVOsDTODouble.getDepartmentID()).thenReturn(data.departmentIDDouble);
        when(data.programmeVOsDTODouble.getTeacherID()).thenReturn(data.teacherIDDouble);

        when(data.programmeFactoryDouble.registerProgramme(data.programmeNameDouble, data.acronymDouble, data.quantityOfEctsDouble, data.quantityOfSemestersDouble, data.degreeTypeIDDouble, data.departmentIDDouble, data.teacherIDDouble)).thenReturn(data.programmeDouble);

        when(data.programmeDouble.identity()).thenReturn(data.programmeIDDouble);
        when(data.programmeRepositoryDouble.containsOfIdentity(data.programmeIDDouble)).thenReturn(false);
        when(data.programmeRepositoryDouble.save(data.programmeDouble)).thenReturn(data.programmeDouble);
        when(data.degreeTypeRepositoryDouble.ofIdentity(data.degreeTypeIDDouble)).thenReturn(Optional.of(data.degreeTypeDouble));
        when(data.degreeTypeDouble.getName()).thenReturn(data.nameDouble1);
        when(data.nameDouble1.getName()).thenReturn(degreeTypeName);
        when(data.programmeDouble.getDepartment()).thenReturn(data.departmentIDDouble);
        when(data.departmentRepositoryDouble.ofIdentity(data.departmentIDDouble)).thenReturn(Optional.of(data.departmentDouble));
        when(data.departmentDouble.getName()).thenReturn(data.nameDouble2);
        when(data.nameDouble2.getName()).thenReturn(departmentName);
        when(data.programmeDouble.getProgrammeDirectorID()).thenReturn(data.teacherIDDouble);
        when(data.teacherRepositoryDouble.ofIdentity(data.teacherIDDouble)).thenReturn(Optional.empty());

        //act
        Exception exception = assertThrows(EntityNotFoundException.class, () -> programmeRegistrationService.registerProgramme(data.programmeVOsDTODouble));

        //assert
        assertEquals(exception.getMessage(), "Teacher not found");

    }

}