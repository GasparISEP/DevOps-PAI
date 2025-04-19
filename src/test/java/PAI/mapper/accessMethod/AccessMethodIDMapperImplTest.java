package PAI.mapper.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.persistence.datamodel.accessMethod.AccessMethodIDDataModel;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccessMethodIDMapperImplTest {

    @Test
    void shouldCreateAccessMethodMapper() {
        // arrange
        // act
        AccessMethodIDMapperImpl accessMethodIDMapper = new AccessMethodIDMapperImpl();
        // assert
        assertNotNull(accessMethodIDMapper);
    }

    @Test
    void shouldMapAccessMethodIDDataModelToAccessMethodID() {
        // arrange
        UUID mockId = mock(UUID.class);
        AccessMethodIDDataModel accessMethodIDDataModel = mock(AccessMethodIDDataModel.class);
        when(accessMethodIDDataModel.getId()).thenReturn(mockId);
        AccessMethodIDMapperImpl accessMethodIDMapper = new AccessMethodIDMapperImpl();
        // act
        Optional<AccessMethodID> accessMethodID = accessMethodIDMapper.toVO(accessMethodIDDataModel);
        // assert
        assertTrue(accessMethodID.isPresent());
    }

    @Test
    void shouldMapAccessMethodIDToAccessMethodIDDataModel() {
        // arrange
        UUID mockId = mock(UUID.class);
        AccessMethodID accessMethodID = mock(AccessMethodID.class);
        when(accessMethodID.getAccessMethodID()).thenReturn(mockId);
        AccessMethodIDMapperImpl accessMethodIDMapper = new AccessMethodIDMapperImpl();
        // act
        Optional<AccessMethodIDDataModel> accessMethodIDDataModel = accessMethodIDMapper.toDataModel(accessMethodID);
        // assert
        assertTrue(accessMethodIDDataModel.isPresent());
    }

    @Test
    void shouldNotMapAccessMethodIDDataModelToAccessMethodIDIfInputNull() {
        // arrange
        AccessMethodIDMapperImpl accessMethodIDMapper = new AccessMethodIDMapperImpl();
        // act
        Optional<AccessMethodID> accessMethodID = accessMethodIDMapper.toVO(null);
        // assert
        assertFalse(accessMethodID.isPresent());
    }

    @Test
    void shouldNotMapAccessMethodIDToAccessMethodIDDataModelIfInputNull() {
        // arrange
        AccessMethodIDMapperImpl accessMethodIDMapper = new AccessMethodIDMapperImpl();
        // act
        Optional<AccessMethodIDDataModel> accessMethodIDDataModel = accessMethodIDMapper.toDataModel(null);
        // assert
        assertFalse(accessMethodIDDataModel.isPresent());
    }


}