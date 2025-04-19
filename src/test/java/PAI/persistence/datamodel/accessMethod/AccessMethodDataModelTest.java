package PAI.persistence.datamodel.accessMethod;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AccessMethodDataModelTest {

    @Test
    void shouldCreateAccessMethodDataModelWithParameters() {
        // arrange
        UUID acccessMethodId = mock(UUID.class);
        String name = "M23";
        // act
        AccessMethodDataModel accessMethodDataModel = new AccessMethodDataModel(acccessMethodId, name);
        // assert
        assertNotNull(accessMethodDataModel);
    }

    @Test
    void shouldCreateAccessMethodDataModelWithoutParameters() {
        // arrange
        // act
        AccessMethodDataModel accessMethodDataModel = new AccessMethodDataModel();
        // assert
        assertNotNull(accessMethodDataModel);
    }

    @Test
    void shouldNotCreateAccessMethodDataModelIfIdNull() {
        // arrange
        String name = "M23";
        // act
        // assert
        assertThrows(IllegalArgumentException.class, () -> new AccessMethodDataModel(null, name)
        );
    }

    @Test
    void shouldNotCreateAccessMethodDataModelIfNameNull() {
        // arrange
        UUID id = mock(UUID.class);
        // act
        // assert
        assertThrows(IllegalArgumentException.class, () -> new AccessMethodDataModel(id, null)
        );
    }

    @Test
    void shouldReturnAccessMethodDataModelAccessMethodId() {
        // arrange
        UUID acccessMethodId = mock(UUID.class);
        String name = "M23";
        AccessMethodDataModel accessMethodDataModel = new AccessMethodDataModel(acccessMethodId, name);
        // act
        UUID result = accessMethodDataModel.getAccessMethodID();
        // assert
        assertEquals(acccessMethodId, result);
    }

    @Test
    void shouldReturnAccessMethodDataModelName() {
        // arrange
        UUID acccessMethodId = mock(UUID.class);
        String name = "M23";
        AccessMethodDataModel accessMethodDataModel = new AccessMethodDataModel(acccessMethodId, name);
        // act
        String result = accessMethodDataModel.getName();
        // assert
        assertEquals(name, result);
    }

    @Test
    void shouldReturnAccessMethodDataModelDatabaseId() {
        // arrange
        UUID acccessMethodId = mock(UUID.class);
        String name = "M23";
        AccessMethodDataModel accessMethodDataModel = new AccessMethodDataModel(acccessMethodId, name);

        // act
        String result = accessMethodDataModel.getName();
        // assert
        assertEquals(name, result);
    }

}