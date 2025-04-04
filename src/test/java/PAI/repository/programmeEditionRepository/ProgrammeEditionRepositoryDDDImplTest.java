package PAI.repository.programmeEditionRepository;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.IProgrammeEditionDDDFactory;
import PAI.domain.programmeEdition.ProgrammeEditionDDD;
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
        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);

        // Act
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        // Assert
        assertNotNull(pER);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionListFactoryIsNull() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = null;
        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory));

        // Assert
        assertEquals("Programme Edition ListFactory cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionFactoryIsNull() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        IProgrammeEditionDDDFactory programmeEditionFactory = null;

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
        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
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
        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
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
        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
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
        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);
        ProgrammeEditionDDD pE = mock(ProgrammeEditionDDD.class);
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
        ProgrammeEditionDDD pE1 = mock(ProgrammeEditionDDD.class);
        ProgrammeEditionDDD pE2 = mock(ProgrammeEditionDDD.class);
        ProgrammeEditionDDD pE3 = mock(ProgrammeEditionDDD.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
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
        ProgrammeEditionDDD pE1 = mock(ProgrammeEditionDDD.class);
        ProgrammeEditionDDD pE2 = mock(ProgrammeEditionDDD.class);
        ProgrammeEditionDDD pE3 = mock(ProgrammeEditionDDD.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
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
        ProgrammeEditionDDD pE1 = mock(ProgrammeEditionDDD.class);
        ProgrammeEditionDDD pE2 = mock(ProgrammeEditionDDD.class);
        ProgrammeEditionDDD pE3 = mock(ProgrammeEditionDDD.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
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
        ProgrammeEditionDDD pE1 = mock(ProgrammeEditionDDD.class);
        ProgrammeEditionDDD pE2 = mock(ProgrammeEditionDDD.class);
        ProgrammeEditionDDD pE3 = mock(ProgrammeEditionDDD.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
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
        ProgrammeEditionDDD pE = mock(ProgrammeEditionDDD.class);
        Set<ProgrammeEditionDDD> programmeEditions= mock(Set.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn(programmeEditions);

        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        // Act
        ProgrammeEditionDDD pEToBeSaved = pER.save(pE);

        // Assert
        Mockito.verify(programmeEditions).add(pEToBeSaved);
        assertNotNull(pEToBeSaved);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionIsNull () throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        ProgrammeEditionDDD pE = null;
        Set<ProgrammeEditionDDD> programmeEditions= mock(Set.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn(programmeEditions);

        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
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
        ProgrammeEditionDDD pE1 = mock(ProgrammeEditionDDD.class);
        ProgrammeEditionDDD pE2 = mock(ProgrammeEditionDDD.class);
        ProgrammeEditionDDD pE3 = mock(ProgrammeEditionDDD.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        // Act
        Iterable<ProgrammeEditionDDD> pERCheck = pER.findAll();

        // Assert
        assertNotNull(pERCheck);
        List<ProgrammeEditionDDD> resultList = StreamSupport.stream(pERCheck.spliterator(), false).toList();
        assertEquals(3, resultList.size());
        assertTrue(resultList.containsAll(Set.of(pE1, pE2, pE3)));
    }

    @Test
    void shouldReturnEmptyIterableIfRepositoryIsEmpty() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of()));

        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        // Act
        Iterable<ProgrammeEditionDDD> pERCheck = pER.findAll();

        // Assert
        assertNotNull(pERCheck);
        List<ProgrammeEditionDDD> resultList = StreamSupport.stream(pERCheck.spliterator(), false).toList();
        assertEquals(0, resultList.size());
    }


    //ofIdentity Test
    @Test
    void shouldReturnOptionalWithProgrammeEditionThatContainsTheID() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        ProgrammeEditionDDD pE1 = mock(ProgrammeEditionDDD.class);
        ProgrammeEditionDDD pE2 = mock(ProgrammeEditionDDD.class);
        ProgrammeEditionDDD pE3 = mock(ProgrammeEditionDDD.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);

        when(pE1.identity()).thenReturn(mock(ProgrammeEditionID.class));
        when(pE2.identity()).thenReturn(mock(ProgrammeEditionID.class));
        when(pE3.identity()).thenReturn(pEID);

        // Act
        Optional<ProgrammeEditionDDD> pE = pER.ofIdentity(pEID);

        // Assert
        assertFalse(pE.isEmpty());
        ProgrammeEditionDDD result = pE.get();
        assertEquals(result.identity(), pE3.identity());
    }

    @Test
    void shouldReturnEmptyOptionalIfThereIsNoProgrammeEditionWithTheID() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        ProgrammeEditionDDD pE1 = mock(ProgrammeEditionDDD.class);
        ProgrammeEditionDDD pE2 = mock(ProgrammeEditionDDD.class);
        ProgrammeEditionDDD pE3 = mock(ProgrammeEditionDDD.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);

        when(pE1.identity()).thenReturn(mock(ProgrammeEditionID.class));
        when(pE2.identity()).thenReturn(mock(ProgrammeEditionID.class));
        when(pE3.identity()).thenReturn(mock(ProgrammeEditionID.class));

        // Act
        Optional<ProgrammeEditionDDD> pE = pER.ofIdentity(pEID);

        // Assert
        assertTrue(pE.isEmpty());
    }

    //containsOfIdentity Test
    @Test
    void shouldReturnFalseWhenRepositoryDoesNotContainAProgrammeEditionWithGivenID() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        ProgrammeEditionDDD pE1 = mock(ProgrammeEditionDDD.class);
        ProgrammeEditionDDD pE2 = mock(ProgrammeEditionDDD.class);
        ProgrammeEditionDDD pE3 = mock(ProgrammeEditionDDD.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
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
        ProgrammeEditionDDD pE1 = mock(ProgrammeEditionDDD.class);
        ProgrammeEditionDDD pE2 = mock(ProgrammeEditionDDD.class);
        ProgrammeEditionDDD pE3 = mock(ProgrammeEditionDDD.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
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
        IProgrammeEditionDDDFactory programmeEditionFactoryDouble = mock(IProgrammeEditionDDDFactory.class);

        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        ProgrammeID otherProgrammeIDDouble = mock(ProgrammeID.class);

        ProgrammeEditionDDD edition1Double = mock(ProgrammeEditionDDD.class);
        ProgrammeEditionDDD edition2Double = mock(ProgrammeEditionDDD.class);
        ProgrammeEditionDDD edition3Double = mock(ProgrammeEditionDDD.class);

        when(edition1Double.findProgrammeIDInProgrammeEdition()).thenReturn(programmeIDDouble);
        when(edition2Double.findProgrammeIDInProgrammeEdition()).thenReturn(programmeIDDouble);
        when(edition3Double.findProgrammeIDInProgrammeEdition()).thenReturn(otherProgrammeIDDouble);

        Set<ProgrammeEditionDDD> internalSet = new HashSet<>();
        internalSet.add(edition1Double);
        internalSet.add(edition2Double);
        internalSet.add(edition3Double);

        when(listFactoryDouble.createProgrammeEditionList()).thenReturn(internalSet);

        ProgrammeEditionRepositoryDDDImpl repository = new ProgrammeEditionRepositoryDDDImpl(listFactoryDouble, programmeEditionFactoryDouble);

        // Act
        List<ProgrammeEditionDDD> result = repository.getProgrammeEditionsByProgrammeID(programmeIDDouble);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(edition1Double));
        assertTrue(result.contains(edition2Double));
        assertFalse(result.contains(edition3Double));
    }
}