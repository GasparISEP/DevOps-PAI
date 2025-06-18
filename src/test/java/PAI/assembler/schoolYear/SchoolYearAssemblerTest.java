package PAI.assembler.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.domain.schoolYear.ISchoolYearFactory;
import PAI.domain.schoolYear.SchoolYear;
import PAI.domain.schoolYear.SchoolYearFactoryImpl;
import PAI.dto.schoolYear.CurrentSchoolYearDTO;
import PAI.dto.schoolYear.CurrentSchoolYearResponseDTO;
import PAI.dto.schoolYear.SchoolYearCEDTO;
import PAI.dto.schoolYear.SchoolYearDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SchoolYearAssemblerTest {

    @Test
    void shouldConstructASchoolYearDTOMapper() {
        //arrange
        ISchoolYearFactory syFactory = mock(SchoolYearFactoryImpl.class);

        //act
        SchoolYearAssembler syMapper = new SchoolYearAssembler(syFactory);

        //assert
        assertNotNull(syMapper);
    }

    @Test
    void shouldTransformSchoolYearDTOtoDomain() {
        //arrange
        SchoolYear sy = mock(SchoolYear.class);
        SchoolYearDTO syDTO = mock(SchoolYearDTO.class);

        ISchoolYearFactory syFactory = mock(SchoolYearFactoryImpl.class);
        SchoolYearAssembler syMapper = new SchoolYearAssembler(syFactory);

        when(syFactory.createSchoolYear(
                any(Description.class),
                any(Date.class),
                any(Date.class)
        )).thenReturn(sy);

        when(syDTO.getDescription()).thenReturn("Ola");
        when(syDTO.getEndDate()).thenReturn("25-04-2025");
        when(syDTO.getStartDate()).thenReturn("15-03-2025");

        //act
        SchoolYear res = syMapper.toDomain(syDTO);

        //assert
        assertNotNull(res);
    }

    @Test
    void shouldTransformToDescription() {
        // arrange
        ISchoolYearFactory syFactory = mock(SchoolYearFactoryImpl.class);
        SchoolYearDTO syDTO = mock(SchoolYearDTO.class);
        SchoolYearAssembler syMapperDTO = new SchoolYearAssembler(syFactory);

        when(syDTO.getDescription()).thenReturn("Ola");

        // act
        Description res = syMapperDTO.toDescription(syDTO);

        // assert
        assertNotNull(res);
    }

    @Test
    void shouldTransformToEndDate() {
        // arrange
        ISchoolYearFactory syFactory = mock(SchoolYearFactoryImpl.class);
        SchoolYearDTO syDTO = mock(SchoolYearDTO.class);
        SchoolYearAssembler syMapperDTO = new SchoolYearAssembler(syFactory);

        when(syDTO.getEndDate()).thenReturn("25-04-2025");

        // act
        Date res = syMapperDTO.toEndDate(syDTO);

        // assert
        assertNotNull(res);
    }

    @Test
    void shouldTransformToStartDate() {
        // arrange
        ISchoolYearFactory syFactory = mock(SchoolYearFactoryImpl.class);
        SchoolYearDTO syDTO = mock(SchoolYearDTO.class);
        SchoolYearAssembler syMapperDTO = new SchoolYearAssembler(syFactory);

        when(syDTO.getStartDate()).thenReturn("15-03-2025");

        // act
        Date res = syMapperDTO.toStartDate(syDTO);

        // assert
        assertNotNull(res);
    }

    @Test
    void shouldTransformSchoolYeartoDTO() {
        //arrange
        SchoolYear sy = mock(SchoolYear.class);

        ISchoolYearFactory syFactory = mock(SchoolYearFactoryImpl.class);
        SchoolYearAssembler syMapper = new SchoolYearAssembler(syFactory);
        Description description = mock(Description.class);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);

        when(sy.getDescription()).thenReturn(description);
        when(sy.getEndDate()).thenReturn(startDate);
        when(sy.getStartDate()).thenReturn(endDate);

        when(description.getDescription()).thenReturn("2024-2025");
        when(startDate.getLocalDate()).thenReturn(LocalDate.of(2025, 5, 4));
        when(endDate.getLocalDate()).thenReturn(LocalDate.of(2025, 5, 6));

        //act
        SchoolYearDTO res = syMapper.toDTO(sy);

        //assert
        assertNotNull(res);
    }

    @Test
    void shouldTransformSchoolYearToCEDTO() {
        //arrange
        SchoolYear sy = mock(SchoolYear.class);

        ISchoolYearFactory syFactory = mock(SchoolYearFactoryImpl.class);
        SchoolYearAssembler syMapper = new SchoolYearAssembler(syFactory);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        when(sy.identity()).thenReturn(schoolYearID);
        UUID id = mock(UUID.class);
        when(schoolYearID.getSchoolYearID()).thenReturn(id);
        Description description = mock(Description.class);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);

        when(sy.getDescription()).thenReturn(description);
        when(sy.getEndDate()).thenReturn(startDate);
        when(sy.getStartDate()).thenReturn(endDate);
        when(sy.identity()).thenReturn(schoolYearID);

        when(description.getDescription()).thenReturn("2024-2025");
        when(startDate.getLocalDate()).thenReturn(LocalDate.of(2025, 5, 4));
        when(endDate.getLocalDate()).thenReturn(LocalDate.of(2025, 5, 6));
        when(schoolYearID.getSchoolYearID().toString()).thenReturn("550e8400-e29b-41d4-a716-446655440000");

        //act
        SchoolYearCEDTO res = syMapper.toCEDTO(sy);

        //assert
        assertNotNull(res);
    }

    @Test
    void shouldCreateCurrentSchoolYearDTO() {
        // Arrange
        SchoolYearID schoolyearId = mock(SchoolYearID.class);
        Description description = mock(Description.class);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);
        ISchoolYearFactory syFactory = mock(SchoolYearFactoryImpl.class);

        SchoolYearAssembler schoolYearAssembler = new SchoolYearAssembler(syFactory);
        SchoolYear schoolYear = mock(SchoolYear.class);

        when(schoolyearId.getSchoolYearID()).thenReturn(UUID.randomUUID());
        when(schoolYear.identity()).thenReturn(schoolyearId);
        when(schoolYear.getDescription()).thenReturn(description);
        when(schoolYear.getStartDate()).thenReturn(startDate);
        when(schoolYear.getEndDate()).thenReturn(endDate);

        // act
        CurrentSchoolYearDTO currentSchoolYearDTO = schoolYearAssembler.toCurrentSchoolYearDTO(schoolYear);

        // assert
        assertNotNull(currentSchoolYearDTO);
    }

    @Test
    void shouldThrowIfSchoolYearIsNull() {
        // Arrange
        ISchoolYearFactory syFactory = mock(SchoolYearFactoryImpl.class);
        SchoolYearAssembler schoolYearAssembler = new SchoolYearAssembler(syFactory);
        SchoolYear schoolYear = null;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
           schoolYearAssembler.toCurrentSchoolYearDTO(schoolYear);
        });
    }

    @Test
    void shouldReturnCurrentSchoolYearDTO(){
        // Arrange
        String schoolYearId = UUID.randomUUID().toString();
        String description = "2024-2025";
        LocalDate startDate = LocalDate.of(2025, 5, 4);
        LocalDate endDate = LocalDate.of(2025, 5, 6);
        ISchoolYearFactory syFactory = mock(SchoolYearFactoryImpl.class);

        SchoolYearAssembler schoolYearAssembler = new SchoolYearAssembler(syFactory);
        CurrentSchoolYearDTO currentSchoolYearDTO = mock(CurrentSchoolYearDTO.class);

        when(currentSchoolYearDTO.id()).thenReturn(schoolYearId);
        when(currentSchoolYearDTO.description()).thenReturn(description);
        when(currentSchoolYearDTO.startDate()).thenReturn(startDate);
        when(currentSchoolYearDTO.endDate()).thenReturn(endDate);

        // act
        CurrentSchoolYearResponseDTO result = schoolYearAssembler.toResponseDTO(currentSchoolYearDTO);

        // assert
        assertNotNull(result);
    }

    @Test
    void shouldThrowIfCurrentSchoolYearDTOIsNull() {
        // Arrange
        ISchoolYearFactory syFactory = mock(SchoolYearFactoryImpl.class);
        SchoolYearAssembler schoolYearAssembler = new SchoolYearAssembler(syFactory);
        CurrentSchoolYearDTO currentSchoolYearDTO = null;

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            schoolYearAssembler.toResponseDTO(currentSchoolYearDTO);
        });
    }

    @Test
    void shouldTransformStringIDtoSchoolYearID() {
        //arrange
        ISchoolYearFactory schoolYearFactory = mock(SchoolYearFactoryImpl.class);
        SchoolYearAssembler schoolYearAssembler = new SchoolYearAssembler(schoolYearFactory);
        String id = "550e8400-e29b-41d4-a716-446655440000";

        //act
        SchoolYearID syID = schoolYearAssembler.fromStringToSchoolYearID(id);

        //assert
        assertNotNull(syID);
    }

    @Test
    void shouldNotTransformStringIDtoSchoolYearIDWhenStringIsBlank() {
        //arrange
        ISchoolYearFactory schoolYearFactory = mock(SchoolYearFactoryImpl.class);
        SchoolYearAssembler schoolYearAssembler = new SchoolYearAssembler(schoolYearFactory);
        String id = "";

        //act + assert
        assertThrows(IllegalArgumentException.class , () -> schoolYearAssembler.fromStringToSchoolYearID(id));
    }

    @Test
    void shouldNotTransformStringIDtoSchoolYearIDWhenStringIsNull() {
        //arrange
        ISchoolYearFactory schoolYearFactory = mock(SchoolYearFactoryImpl.class);
        SchoolYearAssembler schoolYearAssembler = new SchoolYearAssembler(schoolYearFactory);

        //act + assert
        assertThrows(IllegalArgumentException.class , () -> schoolYearAssembler.fromStringToSchoolYearID(null));
    }
}