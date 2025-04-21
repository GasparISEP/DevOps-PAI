package PAI.mapper;

import PAI.VOs.*;
import PAI.persistence.datamodel.ProgrammeEnrolmentIDDataModel;
import PAI.persistence.datamodel.ProgrammeIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEnrolmentIDMapperTest {

    @Test
    public void shouldReturnDataModelFromDomain() {
        // Arrange
        StudentID studentIDDouble = mock(StudentID.class);
        when(studentIDDouble.getUniqueNumber()).thenReturn(1234567);

        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        NameWithNumbersAndSpecialChars nameDouble = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronymDouble = mock(Acronym.class);
        when(programmeIDDouble.getName()).thenReturn(nameDouble);
        when(programmeIDDouble.getAcronym()).thenReturn(acronymDouble);
        when(nameDouble.getnameWithNumbersAndSpecialChars()).thenReturn("Informatics");
        when(acronymDouble.getAcronym()).thenReturn("INF");

        ProgrammeEnrolmentID programmeEnrolmentIDDouble = mock(ProgrammeEnrolmentID.class);
        when(programmeEnrolmentIDDouble.getStudentID()).thenReturn(studentIDDouble);
        when(programmeEnrolmentIDDouble.getProgrammeID()).thenReturn(programmeIDDouble);

        IProgrammeEnrolmentIDMapper mapper = new ProgrammeEnrolmentIDMapper();

        // Act
        ProgrammeEnrolmentIDDataModel dataModel = mapper.domainToDataModel(programmeEnrolmentIDDouble);

        // Assert
        assertNotNull(dataModel);
        assertEquals(1234567, dataModel.getStudentID().getUniqueNumber());
        assertEquals("Informatics", dataModel.getProgrammeID().getName());
        assertEquals("INF", dataModel.getProgrammeID().getAcronym());
    }

    @Test
    public void shouldReturnDomainFromDataModel() {
        // Arrange
        StudentIDDataModel studentIDDataModelDouble = mock(StudentIDDataModel.class);
        when(studentIDDataModelDouble.getUniqueNumber()).thenReturn(1234567);

        ProgrammeIDDataModel programmeIDDataModelDouble = mock(ProgrammeIDDataModel.class);
        when(programmeIDDataModelDouble.getName()).thenReturn("Informatics");
        when(programmeIDDataModelDouble.getAcronym()).thenReturn("INF");

        ProgrammeEnrolmentIDDataModel programmeEnrolmentIDDataModelDouble = mock(ProgrammeEnrolmentIDDataModel.class);
        when(programmeEnrolmentIDDataModelDouble.getStudentID()).thenReturn(studentIDDataModelDouble);
        when(programmeEnrolmentIDDataModelDouble.getProgrammeID()).thenReturn(programmeIDDataModelDouble);

        IProgrammeEnrolmentIDMapper mapper = new ProgrammeEnrolmentIDMapper();

        // Act
        ProgrammeEnrolmentID domain = mapper.dataModelToDomain(programmeEnrolmentIDDataModelDouble);

        // Assert
        assertNotNull(domain);
        assertEquals(1234567, domain.getStudentID().getUniqueNumber());
        assertEquals("Informatics", domain.getProgrammeID().getName().getnameWithNumbersAndSpecialChars());
        assertEquals("INF", domain.getProgrammeID().getAcronym().getAcronym());
    }

}