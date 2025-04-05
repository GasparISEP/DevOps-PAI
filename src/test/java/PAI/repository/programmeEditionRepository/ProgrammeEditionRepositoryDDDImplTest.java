package PAI.repository.programmeEditionRepository;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionRepositoryDDDImplTest {

    //Constructor Tests
    @Test
    void shouldCreateProgrammeEditionRepository() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);

        // Act
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        // Assert
        assertNotNull(pER);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionListFactoryIsNull() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = null;
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory));

        // Assert
        assertEquals("Programme Edition ListFactory cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionFactoryIsNull() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        IProgrammeEditionFactory programmeEditionFactory = null;

        // Act
        Exception exception = assertThrows(Exception.class, () -> new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory));

        // Assert
        assertEquals("Programme Edition Factory cannot be null", exception.getMessage());
    }


    // createProgrammeEdition Test
    @Test
    void shouldReturnTrueWhenCreatingAProgrammeEditionThatDoesNotExistInTheRepository() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);

        // Act
        boolean result = pER.createProgrammeEdition(pID, sYID);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenCreatingAProgrammeEditionWithANullProgrammeID() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        ProgrammeID pID = null;
        SchoolYearID sYID = mock(SchoolYearID.class);
        when(programmeEditionFactory.createProgrammeEdition(pID, sYID)).thenThrow(new Exception("ProgrammeID cannot be null"));

        // Act
        boolean result = pER.createProgrammeEdition(pID, sYID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenCreatingAProgrammeEditionWithANullSchoolYear() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = null;
        when(programmeEditionFactory.createProgrammeEdition(pID, sYID)).thenThrow(new Exception("SchoolYearID cannot be null"));

        // Act
        boolean result = pER.createProgrammeEdition(pID, sYID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenCreatingAProgrammeEditionThatAlreadyExistsInTheRepository() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEdition pE = mock(ProgrammeEdition.class);
        when(programmeEditionFactory.createProgrammeEdition(pID, sYID)).thenReturn(pE);
        pER.createProgrammeEdition(pID, sYID);

        // Act
        boolean result = pER.createProgrammeEdition(pID, sYID);

        // Assert
        assertFalse(result);
    }


    // findProgrammeEditionByProgrammeIDAndSchoolYearID Test
    @Test
    void shouldReturnOptionalProgrammeEditionIDWhenFindProgrammeEditionByProgrammeIDAndSchoolYearID() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        ProgrammeEdition pE1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE2 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE3 = mock(ProgrammeEdition.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        SchoolYearID sYID  = mock(SchoolYearID.class);
        ProgrammeID pID = mock(ProgrammeID.class);

        when(pE1.findProgrammeIDInProgrammeEdition()).thenReturn(mock(ProgrammeID.class));
        when(pE1.findSchoolYearIDInProgrammeEdition()).thenReturn(mock(SchoolYearID.class));
        when(pE2.findProgrammeIDInProgrammeEdition()).thenReturn(mock(ProgrammeID.class));
        when(pE2.findSchoolYearIDInProgrammeEdition()).thenReturn(mock(SchoolYearID.class));
        when(pE3.findProgrammeIDInProgrammeEdition()).thenReturn(pID);
        when(pE3.findSchoolYearIDInProgrammeEdition()).thenReturn(sYID);

        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        when(pE3.identity()).thenReturn(pEID);

        // Act
        Optional<ProgrammeEditionID> pEIDTest = pER.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(pID, sYID);

        // Assert
        assertFalse(pEIDTest.isEmpty());
        ProgrammeEditionID result = pEIDTest.get();
        assertEquals(result, pEID);
        assertEquals(result, pE3.identity());
    }

    @Test
    void shouldReturnEmptyOptionalProgrammeEditionIDWhenThereIsNoProgrammeEditionWithProgrammeIDAndSchoolYearIDGiven() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        ProgrammeEdition pE1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE2 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE3 = mock(ProgrammeEdition.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        SchoolYearID sYID  = mock(SchoolYearID.class);
        ProgrammeID pID = mock(ProgrammeID.class);

        when(pE1.findProgrammeIDInProgrammeEdition()).thenReturn(mock(ProgrammeID.class));
        when(pE1.findSchoolYearIDInProgrammeEdition()).thenReturn(mock(SchoolYearID.class));
        when(pE2.findProgrammeIDInProgrammeEdition()).thenReturn(mock(ProgrammeID.class));
        when(pE2.findSchoolYearIDInProgrammeEdition()).thenReturn(mock(SchoolYearID.class));
        when(pE3.findProgrammeIDInProgrammeEdition()).thenReturn(mock(ProgrammeID.class));
        when(pE3.findSchoolYearIDInProgrammeEdition()).thenReturn(mock(SchoolYearID.class));

        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        when(pE3.identity()).thenReturn(pEID);

        // Act
        Optional<ProgrammeEditionID> pEIDTest = pER.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(pID, sYID);

        // Assert
        assertTrue(pEIDTest.isEmpty());
    }

    @Test
    void shouldReturnEmptyOptionalProgrammeEditionIDWhenThereIsNoProgrammeEditionWithSchoolYearIDGiven() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        ProgrammeEdition pE1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE2 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE3 = mock(ProgrammeEdition.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        SchoolYearID sYID  = mock(SchoolYearID.class);
        ProgrammeID pID = mock(ProgrammeID.class);

        when(pE1.findProgrammeIDInProgrammeEdition()).thenReturn(mock(ProgrammeID.class));
        when(pE1.findSchoolYearIDInProgrammeEdition()).thenReturn(mock(SchoolYearID.class));
        when(pE2.findProgrammeIDInProgrammeEdition()).thenReturn(mock(ProgrammeID.class));
        when(pE2.findSchoolYearIDInProgrammeEdition()).thenReturn(mock(SchoolYearID.class));
        when(pE3.findProgrammeIDInProgrammeEdition()).thenReturn(pID);
        when(pE3.findSchoolYearIDInProgrammeEdition()).thenReturn(mock(SchoolYearID.class));

        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        when(pE3.identity()).thenReturn(pEID);

        // Act
        Optional<ProgrammeEditionID> pEIDTest = pER.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(pID, sYID);

        // Assert
        assertTrue(pEIDTest.isEmpty());
    }

    @Test
    void shouldReturnEmptyOptionalProgrammeEditionIDWhenThereIsNoProgrammeEditionWithProgrammeIDGiven() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        ProgrammeEdition pE1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE2 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE3 = mock(ProgrammeEdition.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        SchoolYearID sYID  = mock(SchoolYearID.class);
        ProgrammeID pID = mock(ProgrammeID.class);

        when(pE1.findProgrammeIDInProgrammeEdition()).thenReturn(mock(ProgrammeID.class));
        when(pE1.findSchoolYearIDInProgrammeEdition()).thenReturn(mock(SchoolYearID.class));
        when(pE2.findProgrammeIDInProgrammeEdition()).thenReturn(mock(ProgrammeID.class));
        when(pE2.findSchoolYearIDInProgrammeEdition()).thenReturn(mock(SchoolYearID.class));
        when(pE3.findProgrammeIDInProgrammeEdition()).thenReturn(mock(ProgrammeID.class));
        when(pE3.findSchoolYearIDInProgrammeEdition()).thenReturn(sYID);

        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        when(pE3.identity()).thenReturn(pEID);

        // Act
        Optional<ProgrammeEditionID> pEIDTest = pER.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(pID, sYID);

        // Assert
        assertTrue(pEIDTest.isEmpty());
    }


    // save Test
    @Test
    void shouldReturnProgrammeEditionWhenSave () throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        ProgrammeEdition pE = mock(ProgrammeEdition.class);
        Set<ProgrammeEdition> programmeEditions= mock(Set.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn(programmeEditions);

        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        // Act
        ProgrammeEdition pEToBeSaved = pER.save(pE);

        // Assert
        Mockito.verify(programmeEditions).add(pEToBeSaved);
        assertNotNull(pEToBeSaved);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionIsNull () throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        ProgrammeEdition pE = null;
        Set<ProgrammeEdition> programmeEditions= mock(Set.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn(programmeEditions);

        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        // Act
        Exception exception = assertThrows(Exception.class, () -> pER.save(pE));

        // Assert
        assertEquals("Programme Edition cannot be null", exception.getMessage());
    }


    // findAll Test
    @Test
    void shouldReturnIterableWithProgrammeEditionsContainedInTheRepository() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        ProgrammeEdition pE1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE2 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE3 = mock(ProgrammeEdition.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        // Act
        Iterable<ProgrammeEdition> pERCheck = pER.findAll();

        // Assert
        assertNotNull(pERCheck);
        List<ProgrammeEdition> resultList = StreamSupport.stream(pERCheck.spliterator(), false).toList();
        assertEquals(3, resultList.size());
        assertTrue(resultList.containsAll(Set.of(pE1, pE2, pE3)));
    }

    @Test
    void shouldReturnEmptyIterableIfRepositoryIsEmpty() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of()));

        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        // Act
        Iterable<ProgrammeEdition> pERCheck = pER.findAll();

        // Assert
        assertNotNull(pERCheck);
        List<ProgrammeEdition> resultList = StreamSupport.stream(pERCheck.spliterator(), false).toList();
        assertEquals(0, resultList.size());
    }


    //ofIdentity Test
    @Test
    void shouldReturnOptionalWithProgrammeEditionThatContainsTheID() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        ProgrammeEdition pE1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE2 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE3 = mock(ProgrammeEdition.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);

        when(pE1.identity()).thenReturn(mock(ProgrammeEditionID.class));
        when(pE2.identity()).thenReturn(mock(ProgrammeEditionID.class));
        when(pE3.identity()).thenReturn(pEID);

        // Act
        Optional<ProgrammeEdition> pE = pER.ofIdentity(pEID);

        // Assert
        assertFalse(pE.isEmpty());
        ProgrammeEdition result = pE.get();
        assertEquals(result.identity(), pE3.identity());
    }

    @Test
    void shouldReturnEmptyOptionalIfThereIsNoProgrammeEditionWithTheID() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        ProgrammeEdition pE1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE2 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE3 = mock(ProgrammeEdition.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);

        when(pE1.identity()).thenReturn(mock(ProgrammeEditionID.class));
        when(pE2.identity()).thenReturn(mock(ProgrammeEditionID.class));
        when(pE3.identity()).thenReturn(mock(ProgrammeEditionID.class));

        // Act
        Optional<ProgrammeEdition> pE = pER.ofIdentity(pEID);

        // Assert
        assertTrue(pE.isEmpty());
    }

    //containsOfIdentity Test
    @Test
    void shouldReturnFalseWhenRepositoryDoesNotContainAProgrammeEditionWithGivenID() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        ProgrammeEdition pE1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE2 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE3 = mock(ProgrammeEdition.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);

        when(pE1.identity()).thenReturn(mock(ProgrammeEditionID.class));
        when(pE2.identity()).thenReturn(mock(ProgrammeEditionID.class));
        when(pE3.identity()).thenReturn(mock(ProgrammeEditionID.class));

        // Act
        boolean result = pER.containsOfIdentity(pEID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueWhenRepositoryContainsAProgrammeEditionWithGivenID() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        ProgrammeEdition pE1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE2 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE3 = mock(ProgrammeEdition.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);

        when(pE1.identity()).thenReturn(mock(ProgrammeEditionID.class));
        when(pE2.identity()).thenReturn(pEID);
        when(pE3.identity()).thenReturn(mock(ProgrammeEditionID.class));

        // Act
        boolean result = pER.containsOfIdentity(pEID);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnListOfProgrammeEditionsByProgrammeID() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory listFactoryDouble = mock(IProgrammeEditionDDDListFactory.class);
        IProgrammeEditionFactory programmeEditionFactoryDouble = mock(IProgrammeEditionFactory.class);

        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        ProgrammeID otherProgrammeIDDouble = mock(ProgrammeID.class);

        ProgrammeEdition edition1Double = mock(ProgrammeEdition.class);
        ProgrammeEdition edition2Double = mock(ProgrammeEdition.class);
        ProgrammeEdition edition3Double = mock(ProgrammeEdition.class);

        when(edition1Double.findProgrammeIDInProgrammeEdition()).thenReturn(programmeIDDouble);
        when(edition2Double.findProgrammeIDInProgrammeEdition()).thenReturn(programmeIDDouble);
        when(edition3Double.findProgrammeIDInProgrammeEdition()).thenReturn(otherProgrammeIDDouble);

        Set<ProgrammeEdition> internalSet = new HashSet<>();
        internalSet.add(edition1Double);
        internalSet.add(edition2Double);
        internalSet.add(edition3Double);

        when(listFactoryDouble.createProgrammeEditionList()).thenReturn(internalSet);

        ProgrammeEditionRepositoryDDDImpl repository = new ProgrammeEditionRepositoryDDDImpl(listFactoryDouble, programmeEditionFactoryDouble);

        // Act
        List<ProgrammeEdition> result = repository.getProgrammeEditionsByProgrammeID(programmeIDDouble);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(edition1Double));
        assertTrue(result.contains(edition2Double));
        assertFalse(result.contains(edition3Double));
    }
}