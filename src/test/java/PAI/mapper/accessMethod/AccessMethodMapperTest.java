package PAI.mapper.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.accessMethod.IAccessMethodFactory;
import PAI.persistence.datamodel.accessMethod.AccessMethodDataModel;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccessMethodMapperTest {

    @Test
    void shouldMapAccessMethodToAccessMethodDataModel() {
        // arrange
        IAccessMethodFactory accessMethodFactory = mock(IAccessMethodFactory.class);
        AccessMethod accessMethod = mock(AccessMethod.class);
        AccessMethodID accessMethodId = mock(AccessMethodID.class);
        UUID uuid = mock(UUID.class);
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        AccessMethodMapper accessMethodMapper = new AccessMethodMapper(accessMethodFactory);

        when(accessMethod.identity()).thenReturn(accessMethodId);
        when(accessMethod.identity().getAccessMethodID()).thenReturn(uuid);
        when(accessMethod.getAccessMethodName()).thenReturn(accessMethodName);
        // act
        Optional<AccessMethodDataModel> result = accessMethodMapper.toDataModel(accessMethod);
        // assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldMapAccessMethodDataModelToAccessMethod() {
        // arrange
        IAccessMethodFactory accessMethodFactory = mock(IAccessMethodFactory.class);
        AccessMethodDataModel accessMethodDataModel = mock(AccessMethodDataModel.class);
        AccessMethod mockAccessMethod = mock(AccessMethod.class);
        UUID uuid = mock(UUID.class);
        String name = "M23";
        AccessMethodMapper accessMethodMapper = new AccessMethodMapper(accessMethodFactory);

        when(accessMethodDataModel.getAccessMethodID()).thenReturn(uuid);
        when(accessMethodDataModel.getName()).thenReturn(name);

        ArgumentCaptor<AccessMethodID> idCaptor = ArgumentCaptor.forClass(AccessMethodID.class);
        ArgumentCaptor<NameWithNumbersAndSpecialChars> nameCaptor = ArgumentCaptor.forClass(NameWithNumbersAndSpecialChars.class);
        when(accessMethodFactory.createAccessMethod(idCaptor.capture(), nameCaptor.capture())).thenReturn(mockAccessMethod);

        // act
        Optional<AccessMethod> result = accessMethodMapper.toDomain(accessMethodDataModel);

        // assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldNotMapAccessMethodDataModelToDomainIfInputNull() {
        // arrange
        IAccessMethodFactory accessMethodFactory = mock(IAccessMethodFactory.class);
        AccessMethodMapper accessMethodMapper = new AccessMethodMapper(accessMethodFactory);
        // act
        Optional<AccessMethodDataModel> result = accessMethodMapper.toDataModel(null);
        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotMapAccessMethodToDataModelIfInputNull() {
        // arrange
        IAccessMethodFactory accessMethodFactory = mock(IAccessMethodFactory.class);
        AccessMethodMapper accessMethodMapper = new AccessMethodMapper(accessMethodFactory);
        // act
        Optional<AccessMethod> result = accessMethodMapper.toDomain(null);
        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotCreateAccessMethodMapperIfInputNull() {
        // arrange
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> new AccessMethodMapper(null));
    }

}