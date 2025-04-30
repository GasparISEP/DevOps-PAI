package PAI.persistence.springdata;

import PAI.VOs.*;
import PAI.domain.ProgrammeEnrolment;
import PAI.mapper.IProgrammeEnrolmentIDMapper;
import PAI.mapper.IProgrammeEnrolmentMapper;
import PAI.mapper.IStudentIDMapper;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.persistence.datamodel.ProgrammeEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEnrolmentIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
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
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        // Act + Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEnrolmentRepositorySpringDataImpl(
                    null, idMapperDouble, iProgrammeEnrolmentMapperDouble, iStudentIDMapperDouble, iProgrammeIDMapperDouble
            );
        });
        assertEquals("jpaRepo must not be null", thrown.getMessage());
    }


    @Test
    public void testConstructor_IdMapperNull_ThrowsException() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentMapper programmeEnrolmentMapperDouble = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        // Act + Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEnrolmentRepositorySpringDataImpl(
                    jpaRepoDouble, null, programmeEnrolmentMapperDouble, iStudentIDMapperDouble, iProgrammeIDMapperDouble
            );
        });
        assertEquals("idMapper must not be null", thrown.getMessage());
    }

    @Test
    public void testConstructor_ProgrammeEnrolmentMapperNull_ThrowsException() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        // Act + Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEnrolmentRepositorySpringDataImpl(
                    jpaRepoDouble, idMapperDouble, null, iStudentIDMapperDouble, iProgrammeIDMapperDouble
            );
        });
        assertEquals("programmeEnrolmentMapper must not be null", thrown.getMessage());
    }

    @Test
    public void testConstructor_StudentIDMapperNull_ThrowsException() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper programmeEnrolmentMapperDouble = mock(IProgrammeEnrolmentMapper.class);

        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        // Act + Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEnrolmentRepositorySpringDataImpl(
                    jpaRepoDouble, idMapperDouble, programmeEnrolmentMapperDouble, null, iProgrammeIDMapperDouble
            );
        });
        assertEquals("studentIDMapper must not be null", thrown.getMessage());
    }

    @Test
    public void testConstructor_ProgrammeIDMapperNull_ThrowsException() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper programmeEnrolmentMapperDouble = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);

        // Act + Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEnrolmentRepositorySpringDataImpl(
                    jpaRepoDouble, idMapperDouble, programmeEnrolmentMapperDouble, iStudentIDMapperDouble, null
            );
        });
        assertEquals("programmeIDMapper must not be null", thrown.getMessage());
    }

    @Test
    public void testSave_SuccessfulSave_ReturnsMappedDomainObject() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper mapperDouble = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, mapperDouble, iStudentIDMapperDouble, iProgrammeIDMapperDouble
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
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, programmeEnrolmentMapperDouble, iStudentIDMapperDouble, iProgrammeIDMapperDouble
        );

        ProgrammeEnrolmentID domainIDDouble = mock(ProgrammeEnrolmentID.class);
        ProgrammeEnrolmentIDDataModel dataIDDouble = mock(ProgrammeEnrolmentIDDataModel.class);


        when(idMapperDouble.domainToDataModel(domainIDDouble)).thenReturn(dataIDDouble);


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
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, programmeEnrolmentMapperDouble, iStudentIDMapperDouble, iProgrammeIDMapperDouble
        );

        ProgrammeEnrolmentID domainIDDouble = mock(ProgrammeEnrolmentID.class);
        ProgrammeEnrolmentIDDataModel dataIDDouble = mock(ProgrammeEnrolmentIDDataModel.class);

        when(idMapperDouble.domainToDataModel(domainIDDouble)).thenReturn(dataIDDouble);

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
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, mapperDouble, iStudentIDMapperDouble, iProgrammeIDMapperDouble
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
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, mapperDouble, iStudentIDMapperDouble, iProgrammeIDMapperDouble
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
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, mapperDouble, iStudentIDMapperDouble, iProgrammeIDMapperDouble);

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
        IStudentIDMapper studentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper programmeIDMapperDouble = mock(IProgrammeIDMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, mapperDouble, studentIDMapperDouble, programmeIDMapperDouble
        );

        StudentID studentIDDouble = mock(StudentID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);

        StudentIDDataModel studentIDDataModelDouble = mock(StudentIDDataModel.class);
        ProgrammeIDDataModel programmeIDDataModelDouble = mock(ProgrammeIDDataModel.class);

        when(studentIDMapperDouble.domainToDataModel(studentIDDouble)).thenReturn(studentIDDataModelDouble);
        when(programmeIDMapperDouble.toData(programmeIDDouble)).thenReturn(programmeIDDataModelDouble);

        when(jpaRepoDouble.isStudentEnrolled(studentIDDataModelDouble, programmeIDDataModelDouble)).thenReturn(true);

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
        IStudentIDMapper studentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper programmeIDMapperDouble = mock(IProgrammeIDMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, mapperDouble, studentIDMapperDouble, programmeIDMapperDouble
        );

        StudentID studentIDDouble = mock(StudentID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);

        StudentIDDataModel studentIDDataModelDouble = mock(StudentIDDataModel.class);
        ProgrammeIDDataModel programmeIDDataModelDouble = mock(ProgrammeIDDataModel.class);

        when(studentIDMapperDouble.domainToDataModel(studentIDDouble)).thenReturn(studentIDDataModelDouble);
        when(programmeIDMapperDouble.toData(programmeIDDouble)).thenReturn(programmeIDDataModelDouble);

        when(jpaRepoDouble.isStudentEnrolled(studentIDDataModelDouble, programmeIDDataModelDouble)).thenReturn(false);

        // Act
        boolean result = repository.isStudentEnrolled(studentIDDouble, programmeIDDouble);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testFindAll() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper programmeEnrolmentMapperDouble = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, programmeEnrolmentMapperDouble, iStudentIDMapperDouble, iProgrammeIDMapperDouble
        );

        ProgrammeEnrolmentDataModel dataModel1 = mock(ProgrammeEnrolmentDataModel.class);
        ProgrammeEnrolmentDataModel dataModel2 = mock(ProgrammeEnrolmentDataModel.class);
        ProgrammeEnrolment domainEnrolment1 = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolment domainEnrolment2 = mock(ProgrammeEnrolment.class);


        when(jpaRepoDouble.findAll()).thenReturn(List.of(dataModel1, dataModel2));


        when(programmeEnrolmentMapperDouble.toDomain(dataModel1)).thenReturn(domainEnrolment1);
        when(programmeEnrolmentMapperDouble.toDomain(dataModel2)).thenReturn(domainEnrolment2);

        // Act
        Iterable<ProgrammeEnrolment> result = repository.findAll();

        // Assert
        List<ProgrammeEnrolment> resultList = (List<ProgrammeEnrolment>) result;
        assertEquals(2, resultList.size());
        assertTrue(resultList.contains(domainEnrolment1));
        assertTrue(resultList.contains(domainEnrolment2));
    }

    @Test
    public void testEnrolStudents_ReturnsFalse() throws Exception {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper programmeEnrolmentMapperDouble = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, programmeEnrolmentMapperDouble, iStudentIDMapperDouble, iProgrammeIDMapperDouble
        );

        StudentID studentIDDouble = mock(StudentID.class);
        AccessMethodID accessMethodIDDouble = mock(AccessMethodID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        Date enrolmentDateDouble = mock(Date.class);

        // Act
        boolean result = repository.enrolStudents(studentIDDouble, accessMethodIDDouble, programmeIDDouble, enrolmentDateDouble);

        // Assert
        assertFalse(result);
    }


}
