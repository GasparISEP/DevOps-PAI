package PAI.repository.programmeEditionRepository;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.ProgrammeEdition;
import PAI.domain.programmeEdition.IProgrammeEditionDDDFactory;
import PAI.domain.programmeEdition.ProgrammeEditionDDD;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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
    void shouldReturnFalseWhenCreateProgrammeEdition() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);

        // Act
        boolean result = pER.createProgrammeEdition(pID, sYID);

        // Assert
        assertFalse(result);
    }


    // findProgrammeEditionByProgrammeIDAndSchoolYearID Test
    @Test
    void shouldReturnEmptyOptionalWhenfindProgrammeEditionByProgrammeIDAndSchoolYearID() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);

        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);

        // Act
        Optional<ProgrammeEditionDDD> programmeEdition = pER.findProgrammeEditionByProgrammeIDAndSchoolYearID(pID, sYID);

        // Assert
        assertTrue(programmeEdition.isEmpty());
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
    void shouldReturnEmptyOptionalWhenOfIdentity() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);

        // Act
        Optional<ProgrammeEditionDDD> pE = pER.ofIdentity(pEID);

        // Assert
        assertFalse(pE.isPresent());
    }


    //containsOfIdentity Test
    @Test
    void shouldReturnFalseWhencontainsOfIdentity() throws Exception {
        // Arrange
        IProgrammeEditionDDDListFactory programmeEditionListFactory = mock(ProgrammeEditionDDDListFactoryImpl.class);
        IProgrammeEditionDDDFactory programmeEditionFactory = mock(IProgrammeEditionDDDFactory.class);
        ProgrammeEditionRepositoryDDDImpl pER = new ProgrammeEditionRepositoryDDDImpl(programmeEditionListFactory, programmeEditionFactory);
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);

        // Act
        boolean result = pER.containsOfIdentity(pEID);

        // Assert
        assertFalse(result);
    }
}