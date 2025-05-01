package PAI.persistence.datamodel;


import PAI.persistence.datamodel.accessMethod.AccessMethodIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeEnrolmentDataModelTest {

    private Object[] createDoublesForTestsWithIsolation() {
        ProgrammeEnrolmentIDDataModel peIDDouble = mock(ProgrammeEnrolmentIDDataModel.class);
        ProgrammeIDDataModel programmeIDDouble = mock(ProgrammeIDDataModel.class);
        StudentIDDataModel studentIDDouble = mock(StudentIDDataModel.class);
        AccessMethodIDDataModel accessMethodIDDouble = mock(AccessMethodIDDataModel.class);
        LocalDate dateDouble = mock(LocalDate.class);
        return new Object[]{peIDDouble, programmeIDDouble, studentIDDouble, accessMethodIDDouble, dateDouble};
    }

    @Test
    public void testEmptyConstructor() {
        //Arrange

        //Act
        ProgrammeEnrolmentDataModel programmeEnrolment = new ProgrammeEnrolmentDataModel();

        //Assert
        assertNotNull(programmeEnrolment);
    }

    @Test
    public void testConstructorWithParameters() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentIDDataModel peIDDouble = (ProgrammeEnrolmentIDDataModel) doubles[0];
        ProgrammeIDDataModel programmeIDDouble = (ProgrammeIDDataModel) doubles[1];
        StudentIDDataModel studentIDDouble = (StudentIDDataModel) doubles[2];
        AccessMethodIDDataModel accessMethodIDDouble = (AccessMethodIDDataModel) doubles[3];
        LocalDate dateDouble = (LocalDate) doubles[4];

        //Act
        ProgrammeEnrolmentDataModel programmeEnrolment = new ProgrammeEnrolmentDataModel(peIDDouble, programmeIDDouble, studentIDDouble, accessMethodIDDouble, dateDouble);

        //Assert
        assertNotNull(programmeEnrolment);
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfProgrammeEnrolmentIDNull() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ProgrammeIDDataModel programmeIDDouble = (ProgrammeIDDataModel) doubles[1];
        StudentIDDataModel studentIDDouble = (StudentIDDataModel) doubles[2];
        AccessMethodIDDataModel accessMethodIDDouble = (AccessMethodIDDataModel) doubles[3];
        LocalDate dateDouble = (LocalDate) doubles[4];

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentDataModel (null, programmeIDDouble, studentIDDouble, accessMethodIDDouble, dateDouble));
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfProgrammeIDNull() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentIDDataModel peIDDouble = (ProgrammeEnrolmentIDDataModel) doubles[0];
        StudentIDDataModel studentIDDouble = (StudentIDDataModel) doubles[2];
        AccessMethodIDDataModel accessMethodIDDouble = (AccessMethodIDDataModel) doubles[3];
        LocalDate dateDouble = (LocalDate) doubles[4];

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentDataModel (peIDDouble, null, studentIDDouble, accessMethodIDDouble, dateDouble));
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfStudentIDNull() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentIDDataModel peIDDouble = (ProgrammeEnrolmentIDDataModel) doubles[0];
        ProgrammeIDDataModel programmeIDDouble = (ProgrammeIDDataModel) doubles[1];
        AccessMethodIDDataModel accessMethodIDDouble = (AccessMethodIDDataModel) doubles[3];
        LocalDate dateDouble = (LocalDate) doubles[4];

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentDataModel (peIDDouble, programmeIDDouble, null, accessMethodIDDouble, dateDouble));
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfAccessMethodIDNull() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentIDDataModel peIDDouble = (ProgrammeEnrolmentIDDataModel) doubles[0];
        ProgrammeIDDataModel programmeIDDouble = (ProgrammeIDDataModel) doubles[1];
        StudentIDDataModel studentIDDouble = (StudentIDDataModel) doubles[2];
        LocalDate dateDouble = (LocalDate) doubles[4];

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentDataModel (peIDDouble, programmeIDDouble, studentIDDouble, null, dateDouble));
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfDateNull() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentIDDataModel peIDDouble = (ProgrammeEnrolmentIDDataModel) doubles[0];
        ProgrammeIDDataModel programmeIDDouble = (ProgrammeIDDataModel) doubles[1];
        StudentIDDataModel studentIDDouble = (StudentIDDataModel) doubles[2];
        AccessMethodIDDataModel accessMethodIDDouble = (AccessMethodIDDataModel) doubles[3];

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentDataModel (peIDDouble, programmeIDDouble, studentIDDouble, accessMethodIDDouble, null));
    }

    @Test
    void shouldReturnProgrammeEnrolmentID() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentIDDataModel peIDDouble = (ProgrammeEnrolmentIDDataModel) doubles[0];
        ProgrammeIDDataModel programmeIDDouble = (ProgrammeIDDataModel) doubles[1];
        StudentIDDataModel studentIDDouble = (StudentIDDataModel) doubles[2];
        AccessMethodIDDataModel accessMethodIDDouble = (AccessMethodIDDataModel) doubles[3];
        LocalDate dateDouble = (LocalDate) doubles[4];

        ProgrammeEnrolmentDataModel programmeEnrolment = new ProgrammeEnrolmentDataModel(peIDDouble, programmeIDDouble, studentIDDouble, accessMethodIDDouble, dateDouble);

        //Act
        ProgrammeEnrolmentIDDataModel result = programmeEnrolment.getProgrammeEnrolmentID();

        //Assert
        assertEquals(peIDDouble, result);
    }

    @Test
    void shouldReturnAccessMethodID() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentIDDataModel peIDDouble = (ProgrammeEnrolmentIDDataModel) doubles[0];
        ProgrammeIDDataModel programmeIDDouble = (ProgrammeIDDataModel) doubles[1];
        StudentIDDataModel studentIDDouble = (StudentIDDataModel) doubles[2];
        AccessMethodIDDataModel accessMethodIDDouble = (AccessMethodIDDataModel) doubles[3];
        LocalDate dateDouble = (LocalDate) doubles[4];

        ProgrammeEnrolmentDataModel programmeEnrolment = new ProgrammeEnrolmentDataModel(peIDDouble, programmeIDDouble, studentIDDouble, accessMethodIDDouble, dateDouble);

        //Act
        AccessMethodIDDataModel result = programmeEnrolment.getAccessMethodID();

        //Assert
        assertEquals(accessMethodIDDouble,result);
    }

    @Test
    void shouldReturnDate() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentIDDataModel peIDDouble = (ProgrammeEnrolmentIDDataModel) doubles[0];
        ProgrammeIDDataModel programmeIDDouble = (ProgrammeIDDataModel) doubles[1];
        StudentIDDataModel studentIDDouble = (StudentIDDataModel) doubles[2];
        AccessMethodIDDataModel accessMethodIDDouble = (AccessMethodIDDataModel) doubles[3];
        LocalDate dateDouble = (LocalDate) doubles[4];

        ProgrammeEnrolmentDataModel programmeEnrolment = new ProgrammeEnrolmentDataModel(peIDDouble, programmeIDDouble, studentIDDouble, accessMethodIDDouble, dateDouble);

        //Act
        LocalDate result = programmeEnrolment.getDate();

        //Assert
        assertEquals(dateDouble,result);
    }
}