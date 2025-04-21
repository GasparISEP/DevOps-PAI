package PAI.persistence.datamodel.programmeEdition;

import PAI.VOs.*;
import PAI.domain.SchoolYear;
import PAI.domain.programme.Programme;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionIdDataModelTest {

    @Test
    void shouldCreateProgrammeEditionIdDataModelIsolationTest() {
        //arrange
        //act
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = new ProgrammeEditionIdDataModel();
        //assert
        assertNotNull(programmeEditionIdDataModel);
    }

    @Test
    void shouldCreateProgrammeEditionIdDataModelWithStringParameters() {
        // arrange
        String programmeName = "Programme Name";
        String programmeAcronym = "Acronym";
        String schoolYearId = UUID.randomUUID().toString();

        // act
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = new ProgrammeEditionIdDataModel(programmeName, programmeAcronym, schoolYearId);

        // assert
        assertNotNull(programmeEditionIdDataModel);
        assertEquals(programmeName, programmeEditionIdDataModel.getProgrammeName());
        assertEquals(programmeAcronym, programmeEditionIdDataModel.getProgrammeAcronym());
        assertEquals(schoolYearId, programmeEditionIdDataModel.getSchoolYearId());
    }

    @Test
    void shouldCreateProgrammeEditionIdDataModelWithParametersIsolationTest() {
        //arrange
        Programme doubleProgramme = mock(Programme.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);

        NameWithNumbersAndSpecialChars doubleName = mock(NameWithNumbersAndSpecialChars.class);
        when(doubleProgramme.getProgrammeName()).thenReturn(doubleName);
        when(doubleName.toString()).thenReturn("Programme Name");

        Acronym doubleAcronym = mock(Acronym.class);
        when(doubleProgramme.getAcronym()).thenReturn(doubleAcronym);
        when(doubleAcronym.toString()).thenReturn("Acronym");

        SchoolYearID doubleSchoolYearID = mock(SchoolYearID.class);
        when(doubleSchoolYear.identity()).thenReturn(doubleSchoolYearID);
        when(doubleSchoolYearID.toString()).thenReturn(UUID.randomUUID().toString());
        //act
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = new ProgrammeEditionIdDataModel(doubleProgramme, doubleSchoolYear);
        //assert
        assertNotNull(programmeEditionIdDataModel);
    }

    @Test
    void shouldCreateProgrammeEditionIdDataModelWithParameters() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        Programme programme = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID);

        Description description = new Description("School Year 23/24");
        Date startDate = new Date("01-09-2023");
        Date endDate = new Date("31-08-2024");
        SchoolYear schoolYear = new SchoolYear(description, startDate, endDate);
        //act
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = new ProgrammeEditionIdDataModel(programme, schoolYear);
        //arrange
        assertNotNull(programmeEditionIdDataModel);
    }

    @Test
    void shouldReturnProgrammeNameFromProgrammeEditionIdDataModel() {
        //arrange
        Programme doubleProgramme = mock(Programme.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);

        NameWithNumbersAndSpecialChars doubleName = mock(NameWithNumbersAndSpecialChars.class);
        when(doubleProgramme.getProgrammeName()).thenReturn(doubleName);
        when(doubleName.toString()).thenReturn("Programme Name");

        Acronym doubleAcronym = mock(Acronym.class);
        when(doubleProgramme.getAcronym()).thenReturn(doubleAcronym);
        when(doubleAcronym.toString()).thenReturn("Acronym");

        SchoolYearID doubleSchoolYearID = mock(SchoolYearID.class);
        when(doubleSchoolYear.identity()).thenReturn(doubleSchoolYearID);
        when(doubleSchoolYearID.toString()).thenReturn(UUID.randomUUID().toString());

        ProgrammeEditionIdDataModel programmeEditionIdDataModel = new ProgrammeEditionIdDataModel(doubleProgramme, doubleSchoolYear);
        //act
        String result = programmeEditionIdDataModel.getProgrammeName();
        //assert
        assertEquals("Programme Name", result);
    }

    @Test
    void shouldReturnProgrammeAcronymFromProgrammeEditionIdDataModel() {
        //arrange
        Programme doubleProgramme = mock(Programme.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);

        NameWithNumbersAndSpecialChars doubleName = mock(NameWithNumbersAndSpecialChars.class);
        when(doubleProgramme.getProgrammeName()).thenReturn(doubleName);
        when(doubleName.toString()).thenReturn("Programme Name");

        Acronym doubleAcronym = mock(Acronym.class);
        when(doubleProgramme.getAcronym()).thenReturn(doubleAcronym);
        when(doubleAcronym.toString()).thenReturn("Acronym");

        SchoolYearID doubleSchoolYearID = mock(SchoolYearID.class);
        when(doubleSchoolYear.identity()).thenReturn(doubleSchoolYearID);
        when(doubleSchoolYearID.toString()).thenReturn(UUID.randomUUID().toString());

        ProgrammeEditionIdDataModel programmeEditionIdDataModel = new ProgrammeEditionIdDataModel(doubleProgramme, doubleSchoolYear);
        //act
        String result = programmeEditionIdDataModel.getProgrammeAcronym();
        //assert
        assertEquals("Acronym", result);
    }

    @Test
    void shouldReturnProgrammeYearFromProgrammeEditionIdDataModel() {
        //arrange
        Programme doubleProgramme = mock(Programme.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);

        NameWithNumbersAndSpecialChars doubleName = mock(NameWithNumbersAndSpecialChars.class);
        when(doubleProgramme.getProgrammeName()).thenReturn(doubleName);
        when(doubleName.toString()).thenReturn("Programme Name");

        Acronym doubleAcronym = mock(Acronym.class);
        when(doubleProgramme.getAcronym()).thenReturn(doubleAcronym);
        when(doubleAcronym.toString()).thenReturn("Acronym");

        SchoolYearID doubleSchoolYearID = mock(SchoolYearID.class);
        when(doubleSchoolYear.identity()).thenReturn(doubleSchoolYearID);
        when(doubleSchoolYearID.toString()).thenReturn(UUID.randomUUID().toString());

        ProgrammeEditionIdDataModel programmeEditionIdDataModel = new ProgrammeEditionIdDataModel(doubleProgramme, doubleSchoolYear);
        //act
        String result = programmeEditionIdDataModel.getSchoolYearId();
        //assert
        assertEquals(doubleSchoolYearID.toString(), result);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeIsNull() {
        //arrange
        Programme doubleProgramme = null;
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);
        //act + assert
        assertThrows(NullPointerException.class, () -> new ProgrammeEditionIdDataModel(doubleProgramme, doubleSchoolYear));
    }

    @Test
    void shouldThrowExceptionWhenProgrammeNameIsNull() {
        //arrange
        Programme doubleProgramme = mock(Programme.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);

        when(doubleProgramme.getProgrammeName()).thenReturn(null);

        Acronym doubleAcronym = mock(Acronym.class);
        when(doubleProgramme.getAcronym()).thenReturn(doubleAcronym);
        when(doubleAcronym.toString()).thenReturn("Acronym");

        SchoolYearID doubleSchoolYearID = mock(SchoolYearID.class);
        when(doubleSchoolYear.identity()).thenReturn(doubleSchoolYearID);
        when(doubleSchoolYearID.toString()).thenReturn(UUID.randomUUID().toString());
        //act + assert
        assertThrows(NullPointerException.class, () -> new ProgrammeEditionIdDataModel(doubleProgramme, doubleSchoolYear));
    }

    @Test
    void shouldThrowExceptionWhenProgrammeAcronymIsNull() {
        //arrange
        Programme doubleProgramme = mock(Programme.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);

        NameWithNumbersAndSpecialChars doubleName = mock(NameWithNumbersAndSpecialChars.class);
        when(doubleProgramme.getProgrammeName()).thenReturn(doubleName);
        when(doubleName.toString()).thenReturn("Programme Name");

        when(doubleProgramme.getAcronym()).thenReturn(null);

        SchoolYearID doubleSchoolYearID = mock(SchoolYearID.class);
        when(doubleSchoolYear.identity()).thenReturn(doubleSchoolYearID);
        when(doubleSchoolYearID.toString()).thenReturn(UUID.randomUUID().toString());
        //act + assert
        assertThrows(NullPointerException.class, () -> new ProgrammeEditionIdDataModel(doubleProgramme, doubleSchoolYear));
    }
}