package PAI.domain.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

class ProgrammeEditionFactoryImplTest {

    @Test
    void shouldCreateProgrammeEdition() throws Exception {
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
        assertEquals("schoolYearID cannot be null", exception.getMessage());
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
            assertEquals("SchoolYearID cannot be null", exception.getMessage());
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
        assertEquals("programmeID cannot be null", exception.getMessage());
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
            assertEquals("programmeID cannot be null", exception.getMessage());
        }
    }
}