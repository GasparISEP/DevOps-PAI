package PAI.persistence.springdata.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethod;
import PAI.mapper.accessMethod.IAccessMethodMapper;
import PAI.persistence.datamodel.accessMethod.AccessMethodDataModel;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccessMethodRepositorySpringDataImplTest {


    @Test
    void shouldCreateAccessMethodRepositorySpringDataImpl() {
        // arrange
        IAccessMethodRepositorySpringData iAccessMethodRepositorySpringData = mock(IAccessMethodRepositorySpringData.class);
        IAccessMethodMapper iAccessMethodMapper = mock(IAccessMethodMapper.class);
        // act
        AccessMethodRepositorySpringDataImpl result = new AccessMethodRepositorySpringDataImpl(iAccessMethodRepositorySpringData, iAccessMethodMapper);
        // assert
        assertNotNull(result);
    }

    @Test
    void shouldNotCreateAccessMethodRepositorySpringDataImplIfRepositoryInputIsNull() {
        // arrange
        IAccessMethodMapper iAccessMethodMapper = mock(IAccessMethodMapper.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new AccessMethodRepositorySpringDataImpl(null, iAccessMethodMapper);
        });
    }

    @Test
    void shouldNotCreateAccessMethodRepositorySpringDataImplIfMapperInputIsNull() {
        // arrange
        IAccessMethodRepositorySpringData iAccessMethodRepositorySpringData = mock(IAccessMethodRepositorySpringData.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new AccessMethodRepositorySpringDataImpl(iAccessMethodRepositorySpringData , null);
        });
    }

    @Test
    void shouldReturnOptionalPresentWhenSearchAccessMethodNameAlreadyInRepository() {
        // arrange
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        String accessMethodNameAsString = "accessMethodName";
        IAccessMethodRepositorySpringData iAccessMethodRepositorySpringData = mock(IAccessMethodRepositorySpringData.class);
        IAccessMethodMapper iAccessMethodMapper = mock(IAccessMethodMapper.class);
        AccessMethodRepositorySpringDataImpl accessMethodRepositorySpringDataImpl = new AccessMethodRepositorySpringDataImpl(iAccessMethodRepositorySpringData, iAccessMethodMapper);
        AccessMethodDataModel accessMethodDataModel = mock(AccessMethodDataModel.class);
        AccessMethod accessMethod = mock(AccessMethod.class);

        when(accessMethodName.toString()).thenReturn(accessMethodNameAsString);
        when(iAccessMethodRepositorySpringData.findAccessMethodDataModelByName(accessMethodNameAsString)).thenReturn(Optional.of(accessMethodDataModel));
        when(iAccessMethodMapper.toDomain(accessMethodDataModel)).thenReturn(Optional.of(accessMethod));
        // act
        Optional<AccessMethod> result = accessMethodRepositorySpringDataImpl.getAccessMethodByName(accessMethodName);
        // assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldReturnOptionalEmptyWhenSearchForAccessMethodNameNotInRepository() {
        // arrange
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        String accessMethodNameAsString = "accessMethodName";
        IAccessMethodRepositorySpringData iAccessMethodRepositorySpringData = mock(IAccessMethodRepositorySpringData.class);
        IAccessMethodMapper iAccessMethodMapper = mock(IAccessMethodMapper.class);
        AccessMethodRepositorySpringDataImpl accessMethodRepositorySpringDataImpl = new AccessMethodRepositorySpringDataImpl(iAccessMethodRepositorySpringData, iAccessMethodMapper);

        when(accessMethodName.toString()).thenReturn(accessMethodNameAsString);
        when(iAccessMethodRepositorySpringData.findAccessMethodDataModelByName(accessMethodNameAsString)).thenReturn(Optional.empty());
        // act
        Optional<AccessMethod> result = accessMethodRepositorySpringDataImpl.getAccessMethodByName(accessMethodName);
        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnOptionalEmptyIfFailsToMapToDomain() {
        // arrange
        NameWithNumbersAndSpecialChars accessMethodName = mock(NameWithNumbersAndSpecialChars.class);
        String accessMethodNameAsString = "accessMethodName";
        IAccessMethodRepositorySpringData iAccessMethodRepositorySpringData= mock(IAccessMethodRepositorySpringData.class);
        IAccessMethodMapper iAccessMethodMapper = mock(IAccessMethodMapper.class);
        AccessMethodRepositorySpringDataImpl accessMethodRepositorySpringData = new AccessMethodRepositorySpringDataImpl(iAccessMethodRepositorySpringData, iAccessMethodMapper);
        AccessMethod accessMethod = mock(AccessMethod.class);
        AccessMethodDataModel accessMethodDataModel = mock(AccessMethodDataModel.class);

        when(accessMethodName.toString()).thenReturn(accessMethodNameAsString);
        when(iAccessMethodMapper.toDataModel(accessMethod)).thenReturn(Optional.of(accessMethodDataModel));
        when(iAccessMethodRepositorySpringData.findAccessMethodDataModelByName(accessMethodNameAsString)).thenReturn(Optional.of(accessMethodDataModel));
        when(iAccessMethodMapper.toDomain(accessMethodDataModel)).thenReturn(Optional.empty());

        // act
        AccessMethod result = accessMethodRepositorySpringData.save(accessMethod);
        // assert
        assertEquals(null, result);
    }

    @Test
    void shouldReturnOptionalAccessMethodWhenSaveSucceeds() {
        // arrange
        IAccessMethodRepositorySpringData iAccessMethodRepositorySpringData= mock(IAccessMethodRepositorySpringData.class);
        IAccessMethodMapper iAccessMethodMapper = mock(IAccessMethodMapper.class);
        AccessMethodRepositorySpringDataImpl accessMethodRepositorySpringData = new AccessMethodRepositorySpringDataImpl(iAccessMethodRepositorySpringData, iAccessMethodMapper);
        AccessMethod accessMethod = mock(AccessMethod.class);
        AccessMethodDataModel accessMethodDataModel = mock(AccessMethodDataModel.class);

        when(iAccessMethodMapper.toDataModel(accessMethod)).thenReturn(Optional.of(accessMethodDataModel));
        when(iAccessMethodRepositorySpringData.save(accessMethodDataModel)).thenReturn(accessMethodDataModel);
        when(iAccessMethodMapper.toDomain(accessMethodDataModel)).thenReturn(Optional.of(accessMethod));

        // act
        Optional<AccessMethod> result = accessMethodRepositorySpringData.saveAccessMethod(accessMethod);

        // assert
        assertTrue(result.isPresent());
        assertEquals(accessMethod, result.get());
    }

    @Test
    void shouldReturnEmptyOptionalWhenSaveFails() {
        // arrange
        IAccessMethodRepositorySpringData iAccessMethodRepositorySpringData = mock(IAccessMethodRepositorySpringData.class);
        IAccessMethodMapper iAccessMethodMapper = mock(IAccessMethodMapper.class);
        AccessMethodRepositorySpringDataImpl accessMethodRepositorySpringData = new AccessMethodRepositorySpringDataImpl(iAccessMethodRepositorySpringData, iAccessMethodMapper);
        AccessMethod accessMethod = mock(AccessMethod.class);

        when(iAccessMethodMapper.toDataModel(accessMethod)).thenReturn(Optional.empty());

        // act
        Optional<AccessMethod> result = accessMethodRepositorySpringData.saveAccessMethod(accessMethod);

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnAllAccessMethodsInRepository(){
        // arrange
        IAccessMethodRepositorySpringData iAccessMethodRepositorySpringData = mock(IAccessMethodRepositorySpringData.class);
        IAccessMethodMapper iAccessMethodMapper = mock(IAccessMethodMapper.class);
        AccessMethodRepositorySpringDataImpl accessMethodRepositorySpringData = new AccessMethodRepositorySpringDataImpl(iAccessMethodRepositorySpringData, iAccessMethodMapper);
        AccessMethodDataModel accessMethodDataModel = mock(AccessMethodDataModel.class);
        AccessMethod accessMethod = mock(AccessMethod.class);

        when(iAccessMethodRepositorySpringData.findAll()).thenReturn(List.of(accessMethodDataModel));
        when(iAccessMethodMapper.toDomain(accessMethodDataModel)).thenReturn(Optional.of(accessMethod));

        // act
        Iterable<AccessMethod> result = accessMethodRepositorySpringData.findAll();
        // assert
        assertNotNull(result);
        Iterator<AccessMethod> iterator = result.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(accessMethod, iterator.next());
    }

    @Test
    void shouldReturnOptionalAccessMethodWhenSearchedByIDAndIDPresentInRepository(){
        // arrange
        IAccessMethodRepositorySpringData iAccessMethodRepositorySpringData = mock(IAccessMethodRepositorySpringData.class);
        IAccessMethodMapper iAccessMethodMapper = mock(IAccessMethodMapper.class);
        AccessMethodRepositorySpringDataImpl accessMethodRepositorySpringData = new AccessMethodRepositorySpringDataImpl(iAccessMethodRepositorySpringData, iAccessMethodMapper);
        AccessMethodID accessMethodID = mock(AccessMethodID.class);
        AccessMethodDataModel accessMethodDataModel = mock(AccessMethodDataModel.class);
        AccessMethod accessMethod = mock(AccessMethod.class);
        UUID uuid = mock(UUID.class);
        when(accessMethodID.getAccessMethodID()).thenReturn(uuid);
        when(iAccessMethodRepositorySpringData.findAccessMethodDataModelByAccessMethodID(uuid)).thenReturn(Optional.of(accessMethodDataModel));
        when(iAccessMethodMapper.toDomain(accessMethodDataModel)).thenReturn(Optional.of(accessMethod));
        // act
        Optional<AccessMethod> result = accessMethodRepositorySpringData.ofIdentity(accessMethodID);
        // assert
        assertTrue(result.isPresent());
        assertEquals(accessMethod, result.get());
    }

    @Test
    void shouldReturnOptionalEmptyIfSearchedByIDAndIDNotPresentInRepository(){
        // arrange
        IAccessMethodRepositorySpringData iAccessMethodRepositorySpringData = mock(IAccessMethodRepositorySpringData.class);
        IAccessMethodMapper iAccessMethodMapper = mock(IAccessMethodMapper.class);
        AccessMethodRepositorySpringDataImpl accessMethodRepositorySpringData = new AccessMethodRepositorySpringDataImpl(iAccessMethodRepositorySpringData, iAccessMethodMapper);
        AccessMethodID accessMethodID = mock(AccessMethodID.class);

        UUID uuid = mock(UUID.class);
        when(accessMethodID.getAccessMethodID()).thenReturn(uuid);
        when(iAccessMethodRepositorySpringData.findAccessMethodDataModelByAccessMethodID(uuid)).thenReturn(Optional.empty());
        // act
        Optional<AccessMethod> result = accessMethodRepositorySpringData.ofIdentity(accessMethodID);
        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnTrueIfAccessMethodIdInRepository(){
        // arrange
        IAccessMethodRepositorySpringData iAccessMethodRepositorySpringData = mock(IAccessMethodRepositorySpringData.class);
        IAccessMethodMapper iAccessMethodMapper = mock(IAccessMethodMapper.class);
        AccessMethodRepositorySpringDataImpl accessMethodRepositorySpringData = new AccessMethodRepositorySpringDataImpl(iAccessMethodRepositorySpringData, iAccessMethodMapper);
        AccessMethodID accessMethodID = mock(AccessMethodID.class);
        AccessMethodDataModel accessMethodDataModel = mock(AccessMethodDataModel.class);
        UUID uuid = mock(UUID.class);
        when(accessMethodID.getAccessMethodID()).thenReturn(uuid);
        when(iAccessMethodRepositorySpringData.findAccessMethodDataModelByAccessMethodID(uuid)).thenReturn(Optional.of(accessMethodDataModel));
        // act
        boolean result = accessMethodRepositorySpringData.containsOfIdentity(accessMethodID);
        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfAccessMethodIdNotInRepository(){
        // arrange
        IAccessMethodRepositorySpringData iAccessMethodRepositorySpringData = mock(IAccessMethodRepositorySpringData.class);
        IAccessMethodMapper iAccessMethodMapper = mock(IAccessMethodMapper.class);
        AccessMethodRepositorySpringDataImpl accessMethodRepositorySpringData = new AccessMethodRepositorySpringDataImpl(iAccessMethodRepositorySpringData, iAccessMethodMapper);
        AccessMethodID accessMethodID = mock(AccessMethodID.class);
        UUID uuid = mock(UUID.class);
        when(accessMethodID.getAccessMethodID()).thenReturn(uuid);
        when(iAccessMethodRepositorySpringData.findAccessMethodDataModelByAccessMethodID(uuid)).thenReturn(Optional.empty());
        // act
        boolean result = accessMethodRepositorySpringData.containsOfIdentity(accessMethodID);
        // assert
        assertFalse(result);
    }
}