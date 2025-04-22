package PAI.domain.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.mapper.programmeEdition.ProgrammeEditionMapperImpl;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProgrammeEditionFactoryImplTest {

    @Test
    void shouldCreateProgrammeEditionWithoutProgrammeEditionID() throws Exception {
        // Arrange
        ProgrammeID pID = mock(ProgrammeID.class);
        SchoolYearID sYID = mock(SchoolYearID.class);

        try (MockedConstruction<ProgrammeEditionID> programmeEditionIDMocked = mockConstruction(ProgrammeEditionID.class);
             MockedConstruction<ProgrammeEdition> programmeEditionDDDMocked = mockConstruction(ProgrammeEdition.class, (mock, context) -> {
                 when(mock.identity()).thenReturn((ProgrammeEditionID) context.arguments().get(0));
                 when(mock.findProgrammeIDInProgrammeEdition()).thenReturn((ProgrammeID) context.arguments().get(1));
                 when(mock.findSchoolYearIDInProgrammeEdition()).thenReturn((SchoolYearID) context.arguments().get(2));
             })) {

            IProgrammeEditionFactory factory = new ProgrammeEditionFactoryImpl();

            // Act
            ProgrammeEdition pE = factory.createProgrammeEdition(pID, sYID);

            // Assert
            assertNotNull(pE);
            ProgrammeEditionID createdProgrammeEditionID = programmeEditionIDMocked.constructed().get(0);
            assertEquals(createdProgrammeEditionID, pE.identity());
            assertEquals(pID, pE.findProgrammeIDInProgrammeEdition());
            assertEquals(sYID, pE.findSchoolYearIDInProgrammeEdition());
        }
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearIDIsNull() {
        // Arrange
        ProgrammeID pID = mock(ProgrammeID.class);
        IProgrammeEditionFactory factory = new ProgrammeEditionFactoryImpl();

        // Act
        Exception exception = assertThrows(Exception.class, () -> {factory.createProgrammeEdition(pID, null);});

        // Assert
        assertEquals("School Year ID cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearIDIsNull2() {
        // Arrange
        ProgrammeID pID = mock(ProgrammeID.class);

        try (MockedConstruction<ProgrammeEditionID> ignored = mockConstruction(ProgrammeEditionID.class)) {
            IProgrammeEditionFactory factory = new ProgrammeEditionFactoryImpl();

            // Act
            Exception exception = assertThrows(Exception.class, () -> factory.createProgrammeEdition(pID, null));

            // Assert
            assertEquals("School Year ID cannot be null", exception.getMessage());
        }
    }

    @Test
    void shouldThrowExceptionWhenProgrammeIDIsNull() {
        // Arrange
        SchoolYearID sYID = mock(SchoolYearID.class);
        IProgrammeEditionFactory factory = new ProgrammeEditionFactoryImpl();

        // Act
        Exception exception = assertThrows(Exception.class, () -> {factory.createProgrammeEdition(null, sYID);});

        // Assert
        assertEquals("Programme ID cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenProgrammeIDIsNull2() {
        // Arrange
        SchoolYearID sYID = mock(SchoolYearID.class);

        try (MockedConstruction<SchoolYearID> ignored = mockConstruction(SchoolYearID.class)) {
            IProgrammeEditionFactory factory = new ProgrammeEditionFactoryImpl();

            // Act
            Exception exception = assertThrows(Exception.class, () -> factory.createProgrammeEdition(null, sYID));

            // Assert
            assertEquals("Programme ID cannot be null", exception.getMessage());
        }
    }

    @Test
    void shouldNotCreateProgrammeEditionWithProgrammeIDNull2() throws Exception {
        // Arrange
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> programmeEditionFactory.createProgrammeEdition(null, schoolYearID));
    }

    @Test
    void shouldCreateProgrammeEditionWithProgrammeEditionID() throws Exception {
        // Arrange
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        // Act
        ProgrammeEdition programmeEdition = programmeEditionFactory.createProgrammeEdition(programmeEditionID, programmeID, schoolYearID);
        // Assert
        assertNotNull(programmeEdition);
        assertEquals(programmeEditionID, programmeEdition.identity());
        assertEquals(programmeID, programmeEdition.findProgrammeIDInProgrammeEdition());
        assertEquals(schoolYearID, programmeEdition.findSchoolYearIDInProgrammeEdition());

    }

    @Test
    void shouldNotCreateProgrammeEditionWithProgrammeEditionIDNull() throws Exception {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> programmeEditionFactory.createProgrammeEdition(null, programmeID, schoolYearID));
    }

    @Test
    void shouldNotCreateProgrammeEditionWithProgrammeIDNull() throws Exception {
        // Arrange
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> programmeEditionFactory.createProgrammeEdition(programmeEditionID, null, schoolYearID));
    }

    @Test
    void shouldNotCreateProgrammeEditionWithSchoolYearIDNull() throws Exception {
        // Arrange
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> programmeEditionFactory.createProgrammeEdition(programmeEditionID, programmeID, null));
    }
}