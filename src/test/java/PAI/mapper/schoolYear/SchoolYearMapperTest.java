package PAI.mapper.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.domain.SchoolYear;
import PAI.factory.ISchoolYearFactory;
import PAI.factory.SchoolYearFactoryImpl;
import PAI.mapper.SchoolYear.ISchoolYearMapper;
import PAI.mapper.SchoolYear.SchoolYearMapper;
import PAI.persistence.datamodel.schoolYear.SchoolYearDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SchoolYearMapperTest {

    // Tests with isolation
    @Test
    void shouldMapSchoolYearToDataModelWithDoubleVariables() {

        //arrange
        ISchoolYearMapper mapper = new SchoolYearMapper();
        SchoolYear schoolYear = mock(SchoolYear.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        Description description = mock(Description.class);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);
        when(schoolYear.identity()).thenReturn(schoolYearID);
        when(schoolYear.getDescription()).thenReturn(description);
        when(schoolYear.getStartDate()).thenReturn(startDate);
        when(schoolYear.getEndDate()).thenReturn(endDate);

        //act
        SchoolYearDataModel schoolYearDataModel = mapper.toDataModel(schoolYear);

        //assert
        assertNotNull(schoolYearDataModel);
    }

    @Test
    void shouldThrowExceptionIfInputSchoolYearIsNullWithDoubles() {

        //arrange
        ISchoolYearMapper mapper = new SchoolYearMapper();
        SchoolYear schoolYear = null;

        //act +assert
        assertThrows(IllegalArgumentException.class, () -> {
            SchoolYearDataModel schoolYearDataModel = mapper.toDataModel(schoolYear);
        });
    }


    @Test
    void shouldCorrectlyReturnSchoolYearWhenUsingDoubles() {

        //arrange
        SchoolYearDataModel schoolYearDataModel = mock(SchoolYearDataModel.class);

        SchoolYear schoolYear = mock(SchoolYear.class);
        ISchoolYearFactory schoolYearFactory = mock(SchoolYearFactoryImpl.class);
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        when(schoolYearIDDataModel.getId()).thenReturn(UUID.randomUUID().toString());
        when(schoolYearDataModel.getId()).thenReturn(schoolYearIDDataModel);
        when(schoolYearDataModel.getDescription()).thenReturn("2024/2025");
        when(schoolYearDataModel.getStartDate()).thenReturn(LocalDate.of(2024, 1, 1));
        when(schoolYearDataModel.getEndDate()).thenReturn(LocalDate.of(2024, 12, 31));
        when(schoolYearFactory.createSchoolYear(any(), any(), any(), any())).thenReturn(schoolYear);

        ISchoolYearMapper mapper = new SchoolYearMapper();
        //act
        SchoolYear schoolYearResult = mapper.toDomain(schoolYearDataModel, schoolYearFactory);

        //assert
        assertNotNull(schoolYearResult);
    }


    @Test
    void shouldThrowExceptionWhenDataModelIsNullWithDoubles() {

        //arrange
        SchoolYearMapper mapper = new SchoolYearMapper();
        ISchoolYearFactory schoolYearFactory = mock(SchoolYearFactoryImpl.class);

        //act + assert
        assertThrows(Exception.class, () -> {
            SchoolYear schoolYear = mapper.toDomain(null, schoolYearFactory);
        });
    }

    @Test
    void shouldThrowExceptionWhenFactoryIsNullWithDoubles() {

        //arrange
        SchoolYearMapper mapper = new SchoolYearMapper();
        SchoolYearDataModel schoolYearDataModel = mock(SchoolYearDataModel.class);

        //act + assert
        assertThrows(Exception.class, () -> {
            SchoolYear schoolYear = mapper.toDomain(schoolYearDataModel, null);
        });
    }

    // Integration tests
    @Test
    void shouldMapSchoolYearToDataModelCorrectly() {
        SchoolYearMapper mapper = new SchoolYearMapper();
        String description = "2024/2025";
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 1, 1);
        SchoolYear schoolYear = new SchoolYear(new Description(description)
                , new Date(startDate),
                new Date(endDate));

        SchoolYearDataModel model = mapper.toDataModel(schoolYear);
        assertNotNull(model.getId());
        assertEquals(model.getDescription(), description);
        assertEquals(model.getStartDate(), startDate);
        assertEquals(model.getEndDate(), endDate);
    }

    @Test
    void shouldMapSchoolYearToDataModelWithUUIDCorrectly() {
        SchoolYearMapper mapper = new SchoolYearMapper();
        UUID uuid = UUID.randomUUID();
        String description = "2024/2025";
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 1, 1);
        SchoolYear schoolYear = new SchoolYear(uuid, new Description(description)
                , new Date(startDate),
                new Date(endDate));

        SchoolYearDataModel model = mapper.toDataModel(schoolYear);
        assertEquals(model.getId().getId(), uuid.toString());
        assertEquals(model.getDescription(), description);
        assertEquals(model.getStartDate(), startDate);
        assertEquals(model.getEndDate(), endDate);
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearIsNull() {
        SchoolYearMapper mapper = new SchoolYearMapper();
        assertThrows(Exception.class, () -> {
            SchoolYearDataModel model = mapper.toDataModel(null);
        });
    }

    @Test
    void shouldCorrectlyReturnSchoolYearWhenAccurateDataIsProvided() {
        SchoolYearMapper mapper = new SchoolYearMapper();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        SchoolYearIDDataModel schoolYearIDDataModel = new SchoolYearIDDataModel("9fbd7db8-2ab5-456b-98ac-e372589d57bb");
        String description = "2024/2025";
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 1, 1);

        SchoolYearDataModel schoolYearDataModel = new SchoolYearDataModel(schoolYearIDDataModel, description, startDate, endDate);

        SchoolYear schoolYear = mapper.toDomain(schoolYearDataModel, schoolYearFactory);

        assertEquals(schoolYear.identity().getSchoolYearID().toString(), schoolYearIDDataModel.getId());
        assertEquals(schoolYear.getDescription().getDescription(), description);
        assertEquals(schoolYear.getStartDate().getLocalDate(), startDate);
        assertEquals(schoolYear.getEndDate().getLocalDate(), endDate);
    }

    @Test
    void shouldThrowExceptionWhenDataModelIsNull() {
        SchoolYearMapper mapper = new SchoolYearMapper();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();

        assertThrows(Exception.class, () -> {
            SchoolYear schoolYear = mapper.toDomain(null, schoolYearFactory);
        });
    }

    @Test
    void shouldThrowExceptionWhenFactoryIsNull() {
        SchoolYearMapper mapper = new SchoolYearMapper();
        SchoolYearIDDataModel schoolYearIDDataModel = new SchoolYearIDDataModel("9fbd7db8-2ab5-456b-98ac-e372589d57bb");
        String description = "2024/2025";
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 1, 1);

        SchoolYearDataModel schoolYearDataModel = new SchoolYearDataModel(schoolYearIDDataModel, description, startDate, endDate);

        assertThrows(Exception.class, () -> {
            SchoolYear schoolYear = mapper.toDomain(schoolYearDataModel, null);
        });
    }
}
