package PAI.dto.schoolYear;

import PAI.VOs.*;
import PAI.domain.schoolYear.ISchoolYearFactory;
import PAI.domain.schoolYear.SchoolYear;
import PAI.domain.schoolYear.SchoolYearFactoryImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SchoolYearMapperDTOTest {

    @Test
    void shouldConstructASchoolYearDTOMapper() {
        //arrange
        ISchoolYearFactory syFactory = mock(SchoolYearFactoryImpl.class);

        //act
        SchoolYearMapperDTO syMapper = new SchoolYearMapperDTO(syFactory);

        //assert
        assertNotNull(syMapper);
    }

    @Test
    void shouldTransformSchoolYearDTOtoDomain() {
        //arrange
        SchoolYear sy = mock(SchoolYear.class);
        SchoolYearDTO syDTO = mock(SchoolYearDTO.class);

        ISchoolYearFactory syFactory = mock(SchoolYearFactoryImpl.class);
        SchoolYearMapperDTO syMapper = new SchoolYearMapperDTO(syFactory);

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
        SchoolYearMapperDTO syMapperDTO = new SchoolYearMapperDTO(syFactory);

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
        SchoolYearMapperDTO syMapperDTO = new SchoolYearMapperDTO(syFactory);

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
        SchoolYearMapperDTO syMapperDTO = new SchoolYearMapperDTO(syFactory);

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
        SchoolYearMapperDTO syMapper = new SchoolYearMapperDTO(syFactory);
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
}