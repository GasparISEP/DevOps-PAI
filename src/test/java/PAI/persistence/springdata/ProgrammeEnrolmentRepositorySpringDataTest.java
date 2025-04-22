package PAI.persistence.springdata;

import PAI.VOs.ProgrammeEnrolmentID;
import PAI.domain.ProgrammeEnrolment;
import PAI.mapper.IProgrammeEnrolmentIDMapper;
import PAI.mapper.IProgrammeEnrolmentMapper;
import PAI.persistence.datamodel.ProgrammeEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEnrolmentIDDataModel;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEnrolmentRepositorySpringDataTest {

    @Test
    public void testSave_Successful() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper mapperDouble = mock(IProgrammeEnrolmentMapper.class);

        ProgrammeEnrolmentRepositorySpringData repository = new ProgrammeEnrolmentRepositorySpringData(jpaRepoDouble, idMapperDouble, mapperDouble);

        ProgrammeEnrolment domainDouble = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolmentDataModel dataModelDouble = mock(ProgrammeEnrolmentDataModel.class);
        ProgrammeEnrolmentDataModel savedDataModelDouble = mock(ProgrammeEnrolmentDataModel.class);
        ProgrammeEnrolment expectedDouble = mock(ProgrammeEnrolment.class);


        when(mapperDouble.toDataModel(domainDouble)).thenReturn(dataModelDouble);
        when(jpaRepoDouble.save(dataModelDouble)).thenReturn(savedDataModelDouble);
        when(mapperDouble.toDomain(savedDataModelDouble)).thenReturn(expectedDouble);

        // Act
        ProgrammeEnrolment result = repository.save(domainDouble);

        // Assert
        assertEquals(expectedDouble, result);
    }


    @Test
    public void testSave_MapperReturnsNull_ThrowsException() {

        // Arrange

        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper mapperDouble = mock(IProgrammeEnrolmentMapper.class);

        ProgrammeEnrolmentRepositorySpringData repository = new ProgrammeEnrolmentRepositorySpringData(jpaRepoDouble, idMapperDouble, mapperDouble);

        ProgrammeEnrolment domainDouble = mock(ProgrammeEnrolment.class);

        when(mapperDouble.toDataModel(domainDouble)).thenReturn(null);

        // Act + Assert
        assertThrows(IllegalStateException.class, () -> repository.save(domainDouble));
    }

    @Test
    public void testFindById_Found() {

        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper mapperDouble = mock(IProgrammeEnrolmentMapper.class);

        ProgrammeEnrolmentRepositorySpringData repository = new ProgrammeEnrolmentRepositorySpringData(jpaRepoDouble, idMapperDouble, mapperDouble);

        ProgrammeEnrolmentID domainIDDouble = mock(ProgrammeEnrolmentID.class);
        ProgrammeEnrolmentIDDataModel dataIDDouble = mock(ProgrammeEnrolmentIDDataModel.class);
        ProgrammeEnrolmentDataModel dataModelDouble = mock(ProgrammeEnrolmentDataModel.class);
        ProgrammeEnrolment expectedDouble = mock(ProgrammeEnrolment.class);

        when(idMapperDouble.domainToDataModel(domainIDDouble)).thenReturn(dataIDDouble);
        when(jpaRepoDouble.findById(dataIDDouble)).thenReturn(Optional.of(dataModelDouble));
        when(mapperDouble.toDomain(dataModelDouble)).thenReturn(expectedDouble);

        // Act
        Optional<ProgrammeEnrolment> result = repository.findById(domainIDDouble);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedDouble, result.get());
    }

    @Test
    public void testFindById_NotFound() {

        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper mapperDouble = mock(IProgrammeEnrolmentMapper.class);

        ProgrammeEnrolmentRepositorySpringData repository = new ProgrammeEnrolmentRepositorySpringData(jpaRepoDouble, idMapperDouble, mapperDouble);

        ProgrammeEnrolmentID domainIDDouble = mock(ProgrammeEnrolmentID.class);
        ProgrammeEnrolmentIDDataModel dataIDDouble = mock(ProgrammeEnrolmentIDDataModel.class);

        when(idMapperDouble.domainToDataModel(domainIDDouble)).thenReturn(dataIDDouble);
        when(jpaRepoDouble.findById(dataIDDouble)).thenReturn(Optional.empty());

        // Act
        Optional<ProgrammeEnrolment> result = repository.findById(domainIDDouble);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    public void testConstructor_NullArguments_ShouldThrowException() {

        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper mapperDouble = mock(IProgrammeEnrolmentMapper.class);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentRepositorySpringData(null, idMapperDouble, mapperDouble));
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentRepositorySpringData(jpaRepoDouble, null, mapperDouble));
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentRepositorySpringData(jpaRepoDouble, idMapperDouble, null));
    }
}