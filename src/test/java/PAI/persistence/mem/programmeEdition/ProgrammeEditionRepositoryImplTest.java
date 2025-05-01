package PAI.persistence.mem.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
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

class ProgrammeEditionRepositoryImplTest {

    //Constructor Tests
    @Test
    void shouldCreateProgrammeEditionRepository() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionListFactory = mock(ProgrammeEditionListFactoryImpl.class);

        // Act
        ProgrammeEditionRepositoryImpl pER = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);

        // Assert
        assertNotNull(pER);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionListFactoryIsNull() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionListFactory = null;

        // Act
        Exception exception = assertThrows(Exception.class, () -> new ProgrammeEditionRepositoryImpl(programmeEditionListFactory));

        // Assert
        assertEquals("Programme Edition ListFactory cannot be null", exception.getMessage());
    }

    // findProgrammeEditionByProgrammeIDAndSchoolYearID Test
    @Test
    void shouldReturnOptionalProgrammeEditionIDWhenFindProgrammeEditionByProgrammeIDAndSchoolYearID() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionListFactory = mock(ProgrammeEditionListFactoryImpl.class);
        ProgrammeEdition pE1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE2 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE3 = mock(ProgrammeEdition.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        ProgrammeEditionRepositoryImpl pER = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);

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
        IProgrammeEditionListFactory programmeEditionListFactory = mock(ProgrammeEditionListFactoryImpl.class);
        ProgrammeEdition pE1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE2 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE3 = mock(ProgrammeEdition.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        ProgrammeEditionRepositoryImpl pER = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);

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
        IProgrammeEditionListFactory programmeEditionListFactory = mock(ProgrammeEditionListFactoryImpl.class);
        ProgrammeEdition pE1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE2 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE3 = mock(ProgrammeEdition.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        ProgrammeEditionRepositoryImpl pER = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);

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
        IProgrammeEditionListFactory programmeEditionListFactory = mock(ProgrammeEditionListFactoryImpl.class);
        ProgrammeEdition pE1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE2 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE3 = mock(ProgrammeEdition.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        ProgrammeEditionRepositoryImpl pER = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);

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
        IProgrammeEditionListFactory programmeEditionListFactory = mock(ProgrammeEditionListFactoryImpl.class);
        ProgrammeEdition pE = mock(ProgrammeEdition.class);
        Set<ProgrammeEdition> programmeEditions= mock(Set.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn(programmeEditions);

        ProgrammeEditionRepositoryImpl pER = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);

        // Act
        ProgrammeEdition pEToBeSaved = pER.save(pE);

        // Assert
        Mockito.verify(programmeEditions).add(pEToBeSaved);
        assertNotNull(pEToBeSaved);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionIsNull () throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionListFactory = mock(ProgrammeEditionListFactoryImpl.class);
        ProgrammeEdition pE = null;
        Set<ProgrammeEdition> programmeEditions= mock(Set.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn(programmeEditions);

        ProgrammeEditionRepositoryImpl pER = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);

        // Act
        Exception exception = assertThrows(Exception.class, () -> pER.save(pE));

        // Assert
        assertEquals("Programme Edition cannot be null", exception.getMessage());
    }


    // findAll Test
    @Test
    void shouldReturnIterableWithProgrammeEditionsContainedInTheRepository() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionListFactory = mock(ProgrammeEditionListFactoryImpl.class);
        ProgrammeEdition pE1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE2 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE3 = mock(ProgrammeEdition.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        ProgrammeEditionRepositoryImpl pER = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);

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
        IProgrammeEditionListFactory programmeEditionListFactory = mock(ProgrammeEditionListFactoryImpl.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of()));

        ProgrammeEditionRepositoryImpl pER = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);

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
        IProgrammeEditionListFactory programmeEditionListFactory = mock(ProgrammeEditionListFactoryImpl.class);
        ProgrammeEdition pE1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE2 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE3 = mock(ProgrammeEdition.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        ProgrammeEditionRepositoryImpl pER = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);

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
        IProgrammeEditionListFactory programmeEditionListFactory = mock(ProgrammeEditionListFactoryImpl.class);
        ProgrammeEdition pE1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE2 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE3 = mock(ProgrammeEdition.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        ProgrammeEditionRepositoryImpl pER = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);

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
        IProgrammeEditionListFactory programmeEditionListFactory = mock(ProgrammeEditionListFactoryImpl.class);
        ProgrammeEdition pE1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE2 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE3 = mock(ProgrammeEdition.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        ProgrammeEditionRepositoryImpl pER = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);

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
        IProgrammeEditionListFactory programmeEditionListFactory = mock(ProgrammeEditionListFactoryImpl.class);
        ProgrammeEdition pE1 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE2 = mock(ProgrammeEdition.class);
        ProgrammeEdition pE3 = mock(ProgrammeEdition.class);
        when(programmeEditionListFactory.createProgrammeEditionList()).thenReturn((Set.of(pE1, pE2, pE3)));

        ProgrammeEditionRepositoryImpl pER = new ProgrammeEditionRepositoryImpl(programmeEditionListFactory);

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
        IProgrammeEditionListFactory listFactoryDouble = mock(IProgrammeEditionListFactory.class);

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

        ProgrammeEditionRepositoryImpl repository = new ProgrammeEditionRepositoryImpl(listFactoryDouble);

        // Act
        List<ProgrammeEdition> result = repository.getProgrammeEditionsByProgrammeID(programmeIDDouble);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(edition1Double));
        assertTrue(result.contains(edition2Double));
        assertFalse(result.contains(edition3Double));
    }

    @Test
    void shouldReturnSchoolYearIDByProgrammeEdition() throws Exception {
        //arrange
        // Arrange
        IProgrammeEditionListFactory listFactoryDouble = mock(IProgrammeEditionListFactory.class);
        ProgrammeEditionRepositoryImpl repository = new ProgrammeEditionRepositoryImpl(listFactoryDouble);

        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        SchoolYearID schoolYearIDDouble = mock(SchoolYearID.class);
        when(programmeEditionDouble.findSchoolYearIDInProgrammeEdition()).thenReturn(schoolYearIDDouble);

        SchoolYearID result = repository.getSchoolYearIDByProgrammeEdition(programmeEditionDouble);

        assertEquals(schoolYearIDDouble,result);
    }
}