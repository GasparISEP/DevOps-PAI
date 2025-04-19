package PAI.persistence.datamodel.accessMethod;

import PAI.persistence.datamodel.ProgrammeIDDataModel;
import jakarta.persistence.Access;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AccessMethodIDDataModelTest {

    @Test
    void shouldCreateAccessMethodIDDataModel() {
        // arrange
        UUID id = mock(UUID.class);
        // act
        AccessMethodIDDataModel accessMethodIDDataModel = new AccessMethodIDDataModel(id);
        // assert
        assertNotNull(accessMethodIDDataModel);
    }

    @Test
    void shouldCreateAccessMethodIDDataModelNoArgumentsConstructor() {
        // arrange
        // act
        AccessMethodIDDataModel accessMethodIDDataModel = new AccessMethodIDDataModel();
        // assert
        assertNotNull(accessMethodIDDataModel);
    }

    @Test
    void shouldNotCreateAccessMethodIDDataModelWithNullId() {
        // act & assert
        assertThrows(IllegalArgumentException.class,
                () -> new AccessMethodIDDataModel(null));
    }

    @Test
    void shouldReturnId() {
        // arrange
        UUID id = mock(UUID.class);
        AccessMethodIDDataModel model = new AccessMethodIDDataModel(id);
        // act
        UUID result = model.getId();
        // assert
        assertEquals(id, result);
    }

    @Test
    void shouldReturnTrueIfComparingTheSameAccessMethodIDDataModel() {
        // arrange
        UUID id = mock(UUID.class);
        AccessMethodIDDataModel accessMethodIDDataModel = new AccessMethodIDDataModel(id);
        // act
        boolean result = accessMethodIDDataModel.equals(accessMethodIDDataModel);
        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfComparingDifferentTypes() {
        // arrange
        UUID id = mock(UUID.class);
        AccessMethodIDDataModel accessMethodIDDataModel = new AccessMethodIDDataModel(id);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        // act
        boolean result = accessMethodIDDataModel.equals(programmeIDDataModel);
        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfComparingToNull() {
        // arrange
        UUID id = mock(UUID.class);
        AccessMethodIDDataModel accessMethodIDDataModel = new AccessMethodIDDataModel(id);
        // act
        boolean result = accessMethodIDDataModel.equals(null);
        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfIfObjectWithSameId() {
        // arrange
        UUID id = mock(UUID.class);
        AccessMethodIDDataModel accessMethodIDDataModel1 = new AccessMethodIDDataModel(id);
        AccessMethodIDDataModel accessMethodIDDataModel2 = new AccessMethodIDDataModel(id);
        // act & assert
        assertEquals(accessMethodIDDataModel1, accessMethodIDDataModel2);
        assertEquals(accessMethodIDDataModel1.hashCode(), accessMethodIDDataModel1.hashCode());
    }

    @Test
    void shouldReturnNotEqualIfComparingObjectWithDifferentId() {
        // arrange
        UUID id1 = mock(UUID.class);
        UUID id2 = mock(UUID.class);
        AccessMethodIDDataModel accessMethodIDDataModel1 = new AccessMethodIDDataModel(id1);
        AccessMethodIDDataModel accessMethodIDDataModel2 = new AccessMethodIDDataModel(id2);
        // act
        boolean result = accessMethodIDDataModel1.equals(accessMethodIDDataModel2);
        // assert
        assertFalse(result);
        assertNotEquals(accessMethodIDDataModel1.hashCode(), accessMethodIDDataModel2.hashCode());
    }

}