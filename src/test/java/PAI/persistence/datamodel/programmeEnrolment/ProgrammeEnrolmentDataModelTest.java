package PAI.persistence.datamodel.programmeEnrolment;


import PAI.persistence.datamodel.accessMethod.AccessMethodIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeEnrolmentDataModelTest {

    private Object[] createDoublesForTestsWithIsolation() {
        ProgrammeEnrolmentIDDataModel peIDDouble = mock(ProgrammeEnrolmentIDDataModel.class);
        ProgrammeIDDataModel programmeIDDouble = mock(ProgrammeIDDataModel.class);
        StudentIDDataModel studentIDDouble = mock(StudentIDDataModel.class);
        AccessMethodIDDataModel accessMethodIDDouble = mock(AccessMethodIDDataModel.class);
        LocalDate dateDouble = mock(LocalDate.class);
        UUID uuid = mock(UUID.class);
        return new Object[]{peIDDouble, programmeIDDouble, studentIDDouble, accessMethodIDDouble, dateDouble, uuid};
    }

    @Test
    public void testEmptyConstructor() {
        // Arrange

        // Act
        ProgrammeEnrolmentDataModel programmeEnrolment = new ProgrammeEnrolmentDataModel();

        // Assert
        assertNotNull(programmeEnrolment);
    }

    @Test
    public void testConstructorWithParameters() {
        // Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentIDDataModel peIDDouble = (ProgrammeEnrolmentIDDataModel) doubles[0];
        ProgrammeIDDataModel programmeIDDouble = (ProgrammeIDDataModel) doubles[1];
        StudentIDDataModel studentIDDouble = (StudentIDDataModel) doubles[2];
        AccessMethodIDDataModel accessMethodIDDouble = (AccessMethodIDDataModel) doubles[3];
        LocalDate dateDouble = (LocalDate) doubles[4];
        UUID uuid = (UUID) doubles[5];

        // Act
        ProgrammeEnrolmentDataModel
                programmeEnrolment =
                new ProgrammeEnrolmentDataModel(peIDDouble, programmeIDDouble, studentIDDouble, accessMethodIDDouble, dateDouble, uuid);

        // Assert
        assertNotNull(programmeEnrolment);
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfProgrammeEnrolmentIDNull() {
        // Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ProgrammeIDDataModel programmeIDDouble = (ProgrammeIDDataModel) doubles[1];
        StudentIDDataModel studentIDDouble = (StudentIDDataModel) doubles[2];
        AccessMethodIDDataModel accessMethodIDDouble = (AccessMethodIDDataModel) doubles[3];
        LocalDate dateDouble = (LocalDate) doubles[4];
        UUID uuid = (UUID) doubles[5];

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentDataModel(null, programmeIDDouble, studentIDDouble, accessMethodIDDouble, dateDouble, uuid));
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfProgrammeIDNull() {
        // Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentIDDataModel peIDDouble = (ProgrammeEnrolmentIDDataModel) doubles[0];
        StudentIDDataModel studentIDDouble = (StudentIDDataModel) doubles[2];
        AccessMethodIDDataModel accessMethodIDDouble = (AccessMethodIDDataModel) doubles[3];
        LocalDate dateDouble = (LocalDate) doubles[4];
        UUID uuid = (UUID) doubles[5];

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentDataModel(peIDDouble, null, studentIDDouble, accessMethodIDDouble, dateDouble, uuid));
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfStudentIDNull() {
        // Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentIDDataModel peIDDouble = (ProgrammeEnrolmentIDDataModel) doubles[0];
        ProgrammeIDDataModel programmeIDDouble = (ProgrammeIDDataModel) doubles[1];
        AccessMethodIDDataModel accessMethodIDDouble = (AccessMethodIDDataModel) doubles[3];
        LocalDate dateDouble = (LocalDate) doubles[4];
        UUID uuid = (UUID) doubles[5];

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentDataModel(peIDDouble, programmeIDDouble, null, accessMethodIDDouble, dateDouble, uuid));
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfAccessMethodIDNull() {
        // Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentIDDataModel peIDDouble = (ProgrammeEnrolmentIDDataModel) doubles[0];
        ProgrammeIDDataModel programmeIDDouble = (ProgrammeIDDataModel) doubles[1];
        StudentIDDataModel studentIDDouble = (StudentIDDataModel) doubles[2];
        LocalDate dateDouble = (LocalDate) doubles[4];
        UUID uuid = (UUID) doubles[5];

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentDataModel(peIDDouble, programmeIDDouble, studentIDDouble, null, dateDouble, uuid));
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfDateNull() {
        // Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentIDDataModel peIDDouble = (ProgrammeEnrolmentIDDataModel) doubles[0];
        ProgrammeIDDataModel programmeIDDouble = (ProgrammeIDDataModel) doubles[1];
        StudentIDDataModel studentIDDouble = (StudentIDDataModel) doubles[2];
        AccessMethodIDDataModel accessMethodIDDouble = (AccessMethodIDDataModel) doubles[3];
        UUID uuid = (UUID) doubles[5];

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentDataModel(peIDDouble, programmeIDDouble, studentIDDouble, accessMethodIDDouble, null, uuid));
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfUUIDNull() {
        // Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentIDDataModel peIDDouble = (ProgrammeEnrolmentIDDataModel) doubles[0];
        ProgrammeIDDataModel programmeIDDouble = (ProgrammeIDDataModel) doubles[1];
        StudentIDDataModel studentIDDouble = (StudentIDDataModel) doubles[2];
        AccessMethodIDDataModel accessMethodIDDouble = (AccessMethodIDDataModel) doubles[3];
        LocalDate dateDouble = (LocalDate) doubles[4];

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentDataModel(peIDDouble, programmeIDDouble, studentIDDouble, accessMethodIDDouble, dateDouble, null));
    }

    @Test
    void shouldReturnProgrammeEnrolmentID() {
        // Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentIDDataModel peIDDouble = (ProgrammeEnrolmentIDDataModel) doubles[0];
        ProgrammeIDDataModel programmeIDDouble = (ProgrammeIDDataModel) doubles[1];
        StudentIDDataModel studentIDDouble = (StudentIDDataModel) doubles[2];
        AccessMethodIDDataModel accessMethodIDDouble = (AccessMethodIDDataModel) doubles[3];
        LocalDate dateDouble = (LocalDate) doubles[4];
        UUID uuid = (UUID) doubles[5];

        ProgrammeEnrolmentDataModel
                programmeEnrolment =
                new ProgrammeEnrolmentDataModel(peIDDouble, programmeIDDouble, studentIDDouble, accessMethodIDDouble, dateDouble, uuid);

        // Act
        ProgrammeEnrolmentIDDataModel result = programmeEnrolment.getProgrammeEnrolmentID();

        // Assert
        assertEquals(peIDDouble, result);
    }

    @Test
    void shouldReturnAccessMethodID() {
        // Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentIDDataModel peIDDouble = (ProgrammeEnrolmentIDDataModel) doubles[0];
        ProgrammeIDDataModel programmeIDDouble = (ProgrammeIDDataModel) doubles[1];
        StudentIDDataModel studentIDDouble = (StudentIDDataModel) doubles[2];
        AccessMethodIDDataModel accessMethodIDDouble = (AccessMethodIDDataModel) doubles[3];
        LocalDate dateDouble = (LocalDate) doubles[4];
        UUID uuid = (UUID) doubles[5];

        ProgrammeEnrolmentDataModel
                programmeEnrolment =
                new ProgrammeEnrolmentDataModel(peIDDouble, programmeIDDouble, studentIDDouble, accessMethodIDDouble, dateDouble, uuid);

        // Act
        AccessMethodIDDataModel result = programmeEnrolment.getAccessMethodID();

        // Assert
        assertEquals(accessMethodIDDouble, result);
    }

    @Test
    void shouldReturnDate() {
        // Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentIDDataModel peIDDouble = (ProgrammeEnrolmentIDDataModel) doubles[0];
        ProgrammeIDDataModel programmeIDDouble = (ProgrammeIDDataModel) doubles[1];
        StudentIDDataModel studentIDDouble = (StudentIDDataModel) doubles[2];
        AccessMethodIDDataModel accessMethodIDDouble = (AccessMethodIDDataModel) doubles[3];
        LocalDate dateDouble = (LocalDate) doubles[4];
        UUID uuid = (UUID) doubles[5];

        ProgrammeEnrolmentDataModel
                programmeEnrolment =
                new ProgrammeEnrolmentDataModel(peIDDouble, programmeIDDouble, studentIDDouble, accessMethodIDDouble, dateDouble, uuid);

        // Act
        LocalDate result = programmeEnrolment.getDate();

        // Assert
        assertEquals(dateDouble, result);
    }

    @Test
    void shouldReturnProgrammeEnrolmentGID() {
        // Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentIDDataModel peIDDouble = (ProgrammeEnrolmentIDDataModel) doubles[0];
        ProgrammeIDDataModel programmeIDDouble = (ProgrammeIDDataModel) doubles[1];
        StudentIDDataModel studentIDDouble = (StudentIDDataModel) doubles[2];
        AccessMethodIDDataModel accessMethodIDDouble = (AccessMethodIDDataModel) doubles[3];
        LocalDate dateDouble = (LocalDate) doubles[4];
        UUID uuid = (UUID) doubles[5];

        ProgrammeEnrolmentDataModel
                programmeEnrolment =
                new ProgrammeEnrolmentDataModel(peIDDouble, programmeIDDouble, studentIDDouble, accessMethodIDDouble, dateDouble, uuid);

        // Act
        UUID result = programmeEnrolment.getProgrammeEnrolmentGID();

        // Assert
        assertEquals(uuid, result);
    }
}