package PAI.mapper.programmeEnrolment;

import PAI.VOs.*;
import PAI.mapper.student.IStudentIDMapper;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.persistence.datamodel.programmeEnrolment.ProgrammeEnrolmentIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEnrolmentIDMapperImplTest {

    @Test
    public void shouldReturnNullWhenDomainIsNull() {
        // Arrange
        IStudentIDMapper studentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper programmeIDMapperDouble = mock(IProgrammeIDMapper.class);
        ProgrammeEnrolmentIDMapperImpl mapper = new ProgrammeEnrolmentIDMapperImpl(studentIDMapperDouble, programmeIDMapperDouble);

        // Act
        ProgrammeEnrolmentIDDataModel result = mapper.domainToDataModel(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void shouldReturnNullWhenDataModelIsNull() {
        // Arrange
        IStudentIDMapper studentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper programmeIDMapperDouble = mock(IProgrammeIDMapper.class);
        ProgrammeEnrolmentIDMapperImpl mapper = new ProgrammeEnrolmentIDMapperImpl(studentIDMapperDouble, programmeIDMapperDouble);

        // Act
        ProgrammeEnrolmentID result = mapper.dataModelToDomain(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void shouldReturnDataModelFromDomain() {
        // Arrange
        StudentID studentIDDouble = mock(StudentID.class);
        when(studentIDDouble.getUniqueNumber()).thenReturn(1234567);

        StudentIDDataModel studentIDDataModelDouble = mock(StudentIDDataModel.class);
        when(studentIDDataModelDouble.getUniqueNumber()).thenReturn(1234567);

        IStudentIDMapper studentIDMapperDouble = mock(IStudentIDMapper.class);
        when(studentIDMapperDouble.domainToDataModel(studentIDDouble)).thenReturn(studentIDDataModelDouble);

        NameWithNumbersAndSpecialChars nameDouble = mock(NameWithNumbersAndSpecialChars.class);
        when(nameDouble.getNameWithNumbersAndSpecialChars()).thenReturn("Informatics");

        Acronym acronymDouble = mock(Acronym.class);
        when(acronymDouble.getAcronym()).thenReturn("INF");

        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        when(programmeIDDouble.getAcronym()).thenReturn(acronymDouble);

        ProgrammeIDDataModel programmeIDDataModelDouble = mock(ProgrammeIDDataModel.class);
        when(programmeIDDataModelDouble.getAcronym()).thenReturn("INF");

        IProgrammeIDMapper programmeIDMapperDouble = mock(IProgrammeIDMapper.class);
        when(programmeIDMapperDouble.toData(programmeIDDouble)).thenReturn(programmeIDDataModelDouble);

        ProgrammeEnrolmentID programmeEnrolmentIDDouble = mock(ProgrammeEnrolmentID.class);
        when(programmeEnrolmentIDDouble.getStudentID()).thenReturn(studentIDDouble);
        when(programmeEnrolmentIDDouble.getProgrammeID()).thenReturn(programmeIDDouble);

        IProgrammeEnrolmentIDMapper mapper = new ProgrammeEnrolmentIDMapperImpl(studentIDMapperDouble, programmeIDMapperDouble);

        // Act
        ProgrammeEnrolmentIDDataModel dataModel = mapper.domainToDataModel(programmeEnrolmentIDDouble);

        // Assert
        assertNotNull(dataModel);
        assertEquals(1234567, dataModel.getStudentID().getUniqueNumber());
        assertEquals("INF", dataModel.getProgrammeID().getAcronym());
    }

    @Test
    public void shouldReturnDomainFromDataModel() {
        // Arrange
        StudentIDDataModel studentIDDataModelDouble = mock(StudentIDDataModel.class);
        when(studentIDDataModelDouble.getUniqueNumber()).thenReturn(1234567);

        StudentID studentIDDouble = mock(StudentID.class);
        when(studentIDDouble.getUniqueNumber()).thenReturn(1234567);

        IStudentIDMapper studentIDMapperDouble = mock(IStudentIDMapper.class);
        when(studentIDMapperDouble.dataModelToDomain(studentIDDataModelDouble)).thenReturn(studentIDDouble);

        ProgrammeIDDataModel programmeIDDataModelDouble = mock(ProgrammeIDDataModel.class);
        when(programmeIDDataModelDouble.getAcronym()).thenReturn("INF");

        NameWithNumbersAndSpecialChars nameDouble = mock(NameWithNumbersAndSpecialChars.class);
        when(nameDouble.getNameWithNumbersAndSpecialChars()).thenReturn("Informatics");

        Acronym acronymDouble = mock(Acronym.class);
        when(acronymDouble.getAcronym()).thenReturn("INF");

        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        when(programmeIDDouble.getAcronym()).thenReturn(acronymDouble);

        IProgrammeIDMapper programmeIDMapperDouble = mock(IProgrammeIDMapper.class);
        when(programmeIDMapperDouble.toDomain(programmeIDDataModelDouble)).thenReturn(programmeIDDouble);

        ProgrammeEnrolmentIDDataModel programmeEnrolmentIDDataModelDouble = mock(ProgrammeEnrolmentIDDataModel.class);
        when(programmeEnrolmentIDDataModelDouble.getStudentID()).thenReturn(studentIDDataModelDouble);
        when(programmeEnrolmentIDDataModelDouble.getProgrammeID()).thenReturn(programmeIDDataModelDouble);

        IProgrammeEnrolmentIDMapper mapper = new ProgrammeEnrolmentIDMapperImpl(studentIDMapperDouble, programmeIDMapperDouble);

        // Act
        ProgrammeEnrolmentID domain = mapper.dataModelToDomain(programmeEnrolmentIDDataModelDouble);

        // Assert
        assertNotNull(domain);
        assertEquals(1234567, domain.getStudentID().getUniqueNumber());
        assertEquals("INF", domain.getProgrammeID().getAcronym().getAcronym());
    }

}