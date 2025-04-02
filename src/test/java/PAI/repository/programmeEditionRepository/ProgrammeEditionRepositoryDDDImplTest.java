package PAI.repository.programmeEditionRepository;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.IProgrammeEditionDDDFactory;
import PAI.domain.programmeEdition.ProgrammeEditionDDD;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

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
    void shouldReturnNullWhenSave () throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        ProgrammeEditionDDD pE = mock(ProgrammeEditionDDD.class);

        // Act
        ProgrammeEditionDDD result = pER.save(pE);

        // Assert
        assertNull(result);
    }


    // findAll Test
    @Test
    void shouldReturnNullWhenFindAll() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        // Act
        Iterable<ProgrammeEditionDDD> pE = pER.findAll();

        // Assert
        assertNull(pE);
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
}