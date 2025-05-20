package PAI.dto.SchoolYear;

import PAI.VOs.*;
import PAI.domain.schoolYear.ISchoolYearFactory;
import PAI.domain.schoolYear.SchoolYear;
import PAI.domain.schoolYear.SchoolYearFactoryImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SchoolYearDTOMapperTest {

    @Test
    void shouldConstructASchoolYearDTOMapper() {
        //arrange
        ISchoolYearFactory syFactory = mock(SchoolYearFactoryImpl.class);

        //act
        SchoolYearDTOMapper syMapper = new SchoolYearDTOMapper(syFactory);

        //assert
        assertNotNull(syMapper);
    }

    @Test
    void shouldTransformSchoolYearDTOtoDomain() {
        //arrange
        SchoolYear sy = mock(SchoolYear.class);
        SchoolYearDTO syDTO = mock(SchoolYearDTO.class);

        ISchoolYearFactory syFactory = mock(SchoolYearFactoryImpl.class);
        SchoolYearDTOMapper syMapper = new SchoolYearDTOMapper(syFactory);

        when(syFactory.createSchoolYear(
                any(Description.class),
                any(Date.class),
                any(Date.class)
        )).thenReturn(sy);

        when(syDTO.getDescription()).thenReturn("Ola");
        when(syDTO.getEndDate()).thenReturn(LocalDate.of(2025,2,26));
        when(syDTO.getStartDate()).thenReturn(LocalDate.of(2025,3,15));

        //act
        SchoolYear res = syMapper.toDomain(syDTO);

        //assert
        assertNotNull(res);
    }
}