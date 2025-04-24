package PAI.persistence.springdata;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.domain.ProgrammeEditionEnrolment;
import PAI.mapper.IProgrammeEditionEnrolmentIDMapper;
import PAI.mapper.IProgrammeEditionEnrolmentMapper;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentIDDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeEditionEnrolmentRepositorySpringDataTest {

    private IProgrammeEditionEnrolmentRepositorySpringData repoSpringData;
    private IProgrammeEditionEnrolmentMapper mapper;
    private IProgrammeEditionEnrolmentIDMapper idMapper;

    @BeforeEach
    void setUp() {
        repoSpringData = mock(IProgrammeEditionEnrolmentRepositorySpringData.class);
        mapper = mock(IProgrammeEditionEnrolmentMapper.class);
        idMapper = mock(IProgrammeEditionEnrolmentIDMapper.class);
    }

    @Test
    void testConstructorThrowsIfAnyDependencyIsNull() {
        // Arrange & Act
        // Testing for constructor with null dependencies
        // Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolmentRepositorySpringData(null, mapper, idMapper));
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolmentRepositorySpringData(repoSpringData, null, idMapper));
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolmentRepositorySpringData(repoSpringData, mapper, null));
    }

    @Test
    void testSaveCoversSuccessAndFailure() {
        ProgrammeEditionEnrolment domain = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolmentDataModel model = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolmentDataModel saved = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolment mapped = mock(ProgrammeEditionEnrolment.class);

        ProgrammeEditionEnrolmentRepositorySpringData repository = new ProgrammeEditionEnrolmentRepositorySpringData(repoSpringData, mapper, idMapper);

        // Arrange
        when(mapper.toDataModel(domain)).thenReturn(Optional.of(model));
        when(repoSpringData.save(model)).thenReturn(saved);
        when(mapper.toDomain(saved)).thenReturn(Optional.of(mapped));
        // Act
        ProgrammeEditionEnrolment result = repository.save(domain);
        // Assert
        assertEquals(mapped, result);

        // Arrange
        when(mapper.toDataModel(domain)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(IllegalStateException.class, () -> repository.save(domain));

        // Arrange
        when(mapper.toDataModel(domain)).thenReturn(Optional.of(model));
        when(mapper.toDomain(saved)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(IllegalStateException.class, () -> repository.save(domain));

        // Arrange & Act
        assertThrows(IllegalArgumentException.class, () -> repository.save(null), "ProgrammeEditionEnrolment cannot be null");
    }

    @Test
    void testOfIdentityCoversAllCases() {
        ProgrammeEditionEnrolmentID id = mock(ProgrammeEditionEnrolmentID.class);
        ProgrammeEditionEnrolmentIDDataModel idModel = mock(ProgrammeEditionEnrolmentIDDataModel.class);
        ProgrammeEditionEnrolmentDataModel dataModel = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolment domain = mock(ProgrammeEditionEnrolment.class);

        ProgrammeEditionEnrolmentRepositorySpringData repository = new ProgrammeEditionEnrolmentRepositorySpringData(repoSpringData, mapper, idMapper);

        // Arrange
        when(idMapper.toDataModel(id)).thenReturn(Optional.of(idModel));
        when(repoSpringData.findById(idModel)).thenReturn(Optional.of(dataModel));
        when(mapper.toDomain(dataModel)).thenReturn(Optional.of(domain));
        // Act
        Optional<ProgrammeEditionEnrolment> result = repository.ofIdentity(id);
        // Assert
        assertTrue(result.isPresent());
        assertEquals(domain, result.get());

        // Arrange
        when(mapper.toDomain(dataModel)).thenReturn(Optional.empty());
        // Act & Assert
        assertTrue(repository.ofIdentity(id).isEmpty());

        // Arrange
        when(repoSpringData.findById(idModel)).thenReturn(Optional.empty());
        // Act & Assert
        assertTrue(repository.ofIdentity(id).isEmpty());

        // Arrange
        when(idMapper.toDataModel(id)).thenReturn(Optional.empty());
        // Act & Assert
        assertTrue(repository.ofIdentity(id).isEmpty());
        assertTrue(repository.ofIdentity(null).isEmpty());
    }

    @Test
    void testFindAllCoversValidAndInvalid() {
        ProgrammeEditionEnrolmentDataModel m1 = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolmentDataModel m2 = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolment d1 = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment d2 = mock(ProgrammeEditionEnrolment.class);

        ProgrammeEditionEnrolmentRepositorySpringData repository = new ProgrammeEditionEnrolmentRepositorySpringData(repoSpringData, mapper, idMapper);

        // Arrange
        when(repoSpringData.findAll()).thenReturn(Arrays.asList(m1, m2));
        when(mapper.toDomain(m1)).thenReturn(Optional.of(d1));
        when(mapper.toDomain(m2)).thenReturn(Optional.of(d2));
        // Act
        List<ProgrammeEditionEnrolment> result = repository.findAll();
        // Assert
        assertEquals(List.of(d1, d2), result);

        // Arrange
        when(repoSpringData.findAll()).thenReturn(List.of(m1));
        when(mapper.toDomain(m1)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(IllegalStateException.class, repository::findAll);
    }

    @Test
    void testContainsOfIdentityCoversAll() {
        ProgrammeEditionEnrolmentID id = mock(ProgrammeEditionEnrolmentID.class);
        ProgrammeEditionEnrolmentIDDataModel idModel = mock(ProgrammeEditionEnrolmentIDDataModel.class);

        ProgrammeEditionEnrolmentRepositorySpringData repository = new ProgrammeEditionEnrolmentRepositorySpringData(repoSpringData, mapper, idMapper);

        // Arrange
        when(idMapper.toDataModel(id)).thenReturn(Optional.of(idModel));
        when(repoSpringData.existsById(idModel)).thenReturn(true);
        // Act & Assert
        assertTrue(repository.containsOfIdentity(id));

        // Arrange
        when(repoSpringData.existsById(idModel)).thenReturn(false);
        // Act & Assert
        assertFalse(repository.containsOfIdentity(id));

        // Arrange
        when(idMapper.toDataModel(id)).thenReturn(Optional.empty());
        // Act & Assert
        assertFalse(repository.containsOfIdentity(id));
        assertFalse(repository.containsOfIdentity(null));
    }
}
