package PAI.persistence.springdata;

import PAI.VOs.*;
import PAI.domain.ProgrammeEnrolment;
import PAI.mapper.IProgrammeEnrolmentIDMapper;
import PAI.mapper.IProgrammeEnrolmentMapper;
import PAI.persistence.datamodel.ProgrammeEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEnrolmentIDDataModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

class ProgrammeEnrolmentRepositorySpringDataImplTest {

    @Test
    public void testConstructor_JpaRepoNull_ThrowsException() {
        // Arrange
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper iProgrammeEnrolmentMapperDouble = mock(IProgrammeEnrolmentMapper.class);

        // Act + Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEnrolmentRepositorySpringDataImpl(
                    null, idMapperDouble, iProgrammeEnrolmentMapperDouble
            );
        });
        assertEquals("jpaRepo must not be null", thrown.getMessage());
    }


    @Test
    public void testConstructor_IdMapperNull_ThrowsException() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentMapper programmeEnrolmentMapperDouble = mock(IProgrammeEnrolmentMapper.class);

        // Act + Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEnrolmentRepositorySpringDataImpl(
                    jpaRepoDouble, null, programmeEnrolmentMapperDouble
            );
        });
        assertEquals("idMapper must not be null", thrown.getMessage());
    }

    @Test
    public void testConstructor_ProgrammeEnrolmentMapperNull_ThrowsException() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);

        // Act + Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEnrolmentRepositorySpringDataImpl(
                    jpaRepoDouble, idMapperDouble, null
            );
        });
        assertEquals("programmeEnrolmentMapper must not be null", thrown.getMessage());
    }

    @Test
    public void testSave_SuccessfulSave_ReturnsMappedDomainObject() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper mapperDouble = mock(IProgrammeEnrolmentMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, mapperDouble
        );

        ProgrammeEnrolment domainDouble = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolmentDataModel dataModelDouble = mock(ProgrammeEnrolmentDataModel.class);
        ProgrammeEnrolmentDataModel savedDataModelDouble = mock(ProgrammeEnrolmentDataModel.class);
        ProgrammeEnrolment mappedDomain = mock(ProgrammeEnrolment.class);

        when(mapperDouble.toDataModel(domainDouble)).thenReturn(dataModelDouble);
        when(jpaRepoDouble.save(dataModelDouble)).thenReturn(savedDataModelDouble);
        when(mapperDouble.toDomain(savedDataModelDouble)).thenReturn(mappedDomain);

        // Act
        ProgrammeEnrolment result = repository.save(domainDouble);

        // Assert
        assertEquals(mappedDomain, result);
    }

    @Test
    public void testContainsOfIdentity_ExistingEnrolment() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper programmeEnrolmentMapperDouble = mock(IProgrammeEnrolmentMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, programmeEnrolmentMapperDouble
        );

        ProgrammeEnrolmentID domainIDDouble = mock(ProgrammeEnrolmentID.class);
        ProgrammeEnrolmentIDDataModel dataIDDouble = mock(ProgrammeEnrolmentIDDataModel.class);

        // Mocking idMapper to return a valid DataModel for the given ID
        when(idMapperDouble.domainToDataModel(domainIDDouble)).thenReturn(dataIDDouble);

        // Mocking jpaRepo.existsById to return true (indicating the ID exists)
        when(jpaRepoDouble.existsById(dataIDDouble)).thenReturn(true);

        // Act
        boolean result = repository.containsOfIdentity(domainIDDouble);

        // Assert
        assertTrue(result);
    }



    @Test
    public void testContainsOfIdentity_NotFound() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper programmeEnrolmentMapperDouble = mock(IProgrammeEnrolmentMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, programmeEnrolmentMapperDouble
        );

        ProgrammeEnrolmentID domainIDDouble = mock(ProgrammeEnrolmentID.class);
        ProgrammeEnrolmentIDDataModel dataIDDouble = mock(ProgrammeEnrolmentIDDataModel.class);

        // Mocking idMapper to return a valid DataModel for the given ID
        when(idMapperDouble.domainToDataModel(domainIDDouble)).thenReturn(dataIDDouble);

        // Mocking jpaRepo.existsById to return false (indicating the ID does not exist)
        when(jpaRepoDouble.existsById(dataIDDouble)).thenReturn(false);

        // Act
        boolean result = repository.containsOfIdentity(domainIDDouble);

        // Assert
        assertFalse(result);
    }



    @Test
    public void testSave_MapperReturnsNull_ThrowsException() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper mapperDouble = mock(IProgrammeEnrolmentMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, mapperDouble
        );

        ProgrammeEnrolment domainDouble = mock(ProgrammeEnrolment.class);


        when(mapperDouble.toDataModel(domainDouble)).thenReturn(null);

        // Act + Assert
        IllegalStateException thrownException = assertThrows(IllegalStateException.class, () -> repository.save(domainDouble));

        assertEquals("Data model is null", thrownException.getMessage());
    }


    @Test
    public void testFindById_Found() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper mapperDouble = mock(IProgrammeEnrolmentMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, mapperDouble
        );

        ProgrammeEnrolmentID domainIDDouble = mock(ProgrammeEnrolmentID.class);
        ProgrammeEnrolmentIDDataModel dataIDDouble = mock(ProgrammeEnrolmentIDDataModel.class);
        ProgrammeEnrolmentDataModel dataModelDouble = mock(ProgrammeEnrolmentDataModel.class);
        ProgrammeEnrolment expectedDouble = mock(ProgrammeEnrolment.class);

        when(idMapperDouble.domainToDataModel(domainIDDouble)).thenReturn(dataIDDouble);
        when(jpaRepoDouble.findById(dataIDDouble)).thenReturn(Optional.of(dataModelDouble));
        when(mapperDouble.toDomain(dataModelDouble)).thenReturn(expectedDouble);

        // Act
        Optional<ProgrammeEnrolment> result = repository.ofIdentity(domainIDDouble);

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

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, mapperDouble);

        ProgrammeEnrolmentID domainIDDouble = mock(ProgrammeEnrolmentID.class);
        ProgrammeEnrolmentIDDataModel dataIDDouble = mock(ProgrammeEnrolmentIDDataModel.class);

        when(idMapperDouble.domainToDataModel(domainIDDouble)).thenReturn(dataIDDouble);
        when(jpaRepoDouble.findById(dataIDDouble)).thenReturn(Optional.empty());

        // Act
        Optional<ProgrammeEnrolment> result = repository.ofIdentity(domainIDDouble);

        // Assert
        assertTrue(result.isEmpty());
    }


    @Test
    public void testIsStudentEnrolledShouldReturnTrue() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper mapperDouble = mock(IProgrammeEnrolmentMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, mapperDouble
        );

        StudentID studentIDDouble = mock(StudentID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);

        // Mocking the data model
        ProgrammeEnrolmentDataModel dataModelDouble = mock(ProgrammeEnrolmentDataModel.class);
        ProgrammeEnrolment existingEnrolmentDouble = mock(ProgrammeEnrolment.class);

        // Setting up the mapper to return the domain object
        when(mapperDouble.toDomain(dataModelDouble)).thenReturn(existingEnrolmentDouble);
        when(existingEnrolmentDouble.hasSameStudent(studentIDDouble)).thenReturn(true);
        when(existingEnrolmentDouble.hasSameProgramme(programmeIDDouble)).thenReturn(true);

        // Mocking the repository to return a list of data models
        when(jpaRepoDouble.findAll()).thenReturn(List.of(dataModelDouble));

        // Act
        boolean result = repository.isStudentEnrolled(studentIDDouble, programmeIDDouble);

        // Assert
        assertTrue(result);
    }


    @Test
    public void testIsStudentEnrolled_NotEnrolled() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper mapperDouble = mock(IProgrammeEnrolmentMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, mapperDouble
        );

        StudentID studentIDDouble = mock(StudentID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);

        when(jpaRepoDouble.findAll()).thenReturn(List.of());

        // Act
        boolean result = repository.isStudentEnrolled(studentIDDouble, programmeIDDouble);

        // Assert
        assertFalse(result);
    }
}
