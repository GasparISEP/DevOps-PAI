package PAI.mapper.schoolYearID;


import PAI.VOs.SchoolYearID;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SchoolYearIDMapperImplTest {

    // Tests with isolation
    @Test
    void shouldMapSchoolYearIDToDataModelWithDoubleVariables() {

        //arrange
        ISchoolYearIDMapper mapper = new SchoolYearIDMapperImpl();
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        when(schoolYearID.toString()).thenReturn(UUID.randomUUID().toString());

        //act
        SchoolYearIDDataModel schoolYearIDDataModel = mapper.toDataModel(schoolYearID);

        //assert
        assertNotNull(schoolYearIDDataModel);
    }

    @Test
    void shouldThrowExceptionIfInputSchoolYearIDIsNullWithDoubles() {

        //arrange
        ISchoolYearIDMapper mapper = new SchoolYearIDMapperImpl();
        SchoolYearID schoolYearID = null;

        //act +assert
        assertThrows(IllegalArgumentException.class, () -> {
            SchoolYearIDDataModel schoolYearIDDataModel = mapper.toDataModel(schoolYearID);
        });
    }


    @Test
    void shouldCorrectlyReturnSchoolYearIDWhenUsingDoubles() {

        //arrange
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        when(schoolYearIDDataModel.getId()).thenReturn(UUID.randomUUID().toString());

        ISchoolYearIDMapper mapper = new SchoolYearIDMapperImpl();

        //act
        SchoolYearID schoolYearIDResult = mapper.toDomain(schoolYearIDDataModel);

        //assert
        assertNotNull(schoolYearIDResult);
    }


    @Test
    void shouldThrowExceptionWhenDataModelIsNullWithDoubles() {

        //arrange
        ISchoolYearIDMapper mapper = new SchoolYearIDMapperImpl();

        //act + assert
        assertThrows(Exception.class, () -> {
            SchoolYearID schoolYearID = mapper.toDomain(null);
        });
    }

    // Integration tests
    @Test
    void shouldMapSchoolYearIDToDataModelCorrectly() {
        SchoolYearIDMapperImpl mapper = new SchoolYearIDMapperImpl();
        SchoolYearID schoolYearID = new SchoolYearID();

        SchoolYearIDDataModel model = mapper.toDataModel(schoolYearID);

        assertEquals(schoolYearID.toString(), model.getId());
    }

    @Test
    void shouldMapSchoolYearIDToDataModelWithUUIDCorrectly() {
        SchoolYearIDMapperImpl mapper = new SchoolYearIDMapperImpl();
        String uuid = "9fbd7db8-2ab5-456b-98ac-e372589d57bb";
        SchoolYearID schoolYearID = new SchoolYearID(UUID.fromString(uuid));

        SchoolYearIDDataModel model = mapper.toDataModel(schoolYearID);

        assertEquals(uuid, model.getId());
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearIDIsNull() {
        SchoolYearIDMapperImpl mapper = new SchoolYearIDMapperImpl();
        assertThrows(Exception.class, () -> {
            SchoolYearIDDataModel model = mapper.toDataModel(null);
        });
    }

    @Test
    void shouldCorrectlyReturnSchoolYearIDWhenAccurateDataIsProvided() {
        SchoolYearIDMapperImpl mapper = new SchoolYearIDMapperImpl();

        String uuid = "9fbd7db8-2ab5-456b-98ac-e372589d57bb";
        SchoolYearIDDataModel schoolYearIDDataModel = new SchoolYearIDDataModel(uuid);

        SchoolYearID schoolYearID = mapper.toDomain(schoolYearIDDataModel);

        assertEquals(schoolYearID.toString(), uuid);

    }

    @Test
    void shouldThrowExceptionWhenDataModelIsNull() {
        SchoolYearIDMapperImpl mapper = new SchoolYearIDMapperImpl();

        assertThrows(Exception.class, () -> {
            SchoolYearID schoolYearID = mapper.toDomain(null);
        });
    }

}