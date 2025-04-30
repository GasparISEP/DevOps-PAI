package PAI.persistence.datamodel.studyPlan;

import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudyPlanIDDataModelTest {

    @Test
    void shouldReturnStudyPlanIDDataModel() {
        //arrange
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        LocalDate implementationDate = mock(LocalDate.class);
        //act
        StudyPlanIDDataModel studyPlanIDDataModel = new StudyPlanIDDataModel(programmeIDDataModel, implementationDate);
        //assert
        assertNotNull(studyPlanIDDataModel);
    }

    @Test
    void shouldReturnProgrammeID() {
        //arrange
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        LocalDate implementationDate = mock(LocalDate.class);
        //act
        StudyPlanIDDataModel studyPlanIDDataModel = new StudyPlanIDDataModel(programmeIDDataModel, implementationDate);
        //assert
        assertEquals(programmeIDDataModel, studyPlanIDDataModel.getProgrammeID());
    }

    @Test
    void shouldReturnImplementationDate() {
        //arrenge
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        LocalDate implementationDate = mock(LocalDate.class);
        //act
        StudyPlanIDDataModel studyPlanIDDataModel = new StudyPlanIDDataModel(programmeIDDataModel, implementationDate);
        //assert
        assertEquals(implementationDate, studyPlanIDDataModel.getImplementationDate());
    }

    @Test
    void shouldSuccessfullyCreateStudyPlanIDDataModelWithEmptyConstructor() {
        //arrenge + act
        StudyPlanIDDataModel studyPlanIDDataModel = new StudyPlanIDDataModel();
        //assert
        assertNotNull(studyPlanIDDataModel);

    }

    @Test
    void equalsSameInstanceReturnsTrue() {
        ProgrammeIDDataModel programmeID = mock(ProgrammeIDDataModel.class);
        LocalDate date = LocalDate.of(2020, 1, 1);
        StudyPlanIDDataModel dm = new StudyPlanIDDataModel(programmeID, date);
        assertTrue(dm.equals(dm));
    }

    @Test
    void equalsNullReturnsFalse() {
        ProgrammeIDDataModel programmeID = mock(ProgrammeIDDataModel.class);
        LocalDate date = LocalDate.of(2020, 1, 1);
        StudyPlanIDDataModel dm = new StudyPlanIDDataModel(programmeID, date);
        assertFalse(dm.equals(null));
    }

    @Test
    void equalsDifferentClassReturnsFalse() {
        ProgrammeIDDataModel programmeID = mock(ProgrammeIDDataModel.class);
        LocalDate date = LocalDate.of(2020, 1, 1);
        StudyPlanIDDataModel dm = new StudyPlanIDDataModel(programmeID, date);
        assertFalse(dm.equals(new Object()));
    }

    @Test
    void equalsEqualDataModelsReturnsTrue() {
        ProgrammeIDDataModel programmeID = mock(ProgrammeIDDataModel.class);
        LocalDate date = LocalDate.of(2020, 1, 1);
        StudyPlanIDDataModel dm1 = new StudyPlanIDDataModel(programmeID, date);
        StudyPlanIDDataModel dm2 = new StudyPlanIDDataModel(programmeID, date);
        assertTrue(dm1.equals(dm2));
        assertEquals(dm1.hashCode(), dm2.hashCode());
    }

    @Test
    void equalsDifferentProgrammeIDReturnsFalse() {
        ProgrammeIDDataModel pid1 = mock(ProgrammeIDDataModel.class);
        ProgrammeIDDataModel pid2 = mock(ProgrammeIDDataModel.class);
        LocalDate date = LocalDate.of(2020, 1, 1);
        StudyPlanIDDataModel dm1 = new StudyPlanIDDataModel(pid1, date);
        StudyPlanIDDataModel dm2 = new StudyPlanIDDataModel(pid2, date);
        assertFalse(dm1.equals(dm2));
    }

    @Test
    void equalsDifferentImplementationDateReturnsFalse() {
        ProgrammeIDDataModel programmeID = mock(ProgrammeIDDataModel.class);
        StudyPlanIDDataModel dm1 = new StudyPlanIDDataModel(programmeID, LocalDate.of(2020, 1, 1));
        StudyPlanIDDataModel dm2 = new StudyPlanIDDataModel(programmeID, LocalDate.of(2021, 1, 1));
        assertFalse(dm1.equals(dm2));
    }

    @Test
    void hashCodeConsistentWithEquals() {
        ProgrammeIDDataModel programmeID = mock(ProgrammeIDDataModel.class);
        LocalDate date = LocalDate.of(2020, 1, 1);
        StudyPlanIDDataModel dm1 = new StudyPlanIDDataModel(programmeID, date);
        StudyPlanIDDataModel dm2 = new StudyPlanIDDataModel(programmeID, date);
        assertEquals(dm1, dm2);
        assertEquals(dm1.hashCode(), dm2.hashCode());
    }

    @Test
    void DifferentHashCode() {
        ProgrammeIDDataModel programmeID = new ProgrammeIDDataModel("PED", "DEP");
        LocalDate date = LocalDate.of(2020, 1, 1);

        ProgrammeIDDataModel programmeID2 = new ProgrammeIDDataModel("DEP", "PED");
        LocalDate date2 = LocalDate.of(2023, 3, 3);

        StudyPlanIDDataModel dm1 = new StudyPlanIDDataModel(programmeID, date);
        StudyPlanIDDataModel dm2 = new StudyPlanIDDataModel(programmeID2, date2);

        assertNotEquals(dm1.hashCode(), dm2.hashCode());
    }

    @Test
    void hashCodeMustBeDifferentFromSubtractedComponents() {
        ProgrammeIDDataModel pid = new ProgrammeIDDataModel("ENG", "ENG");
        LocalDate date = LocalDate.of(2024, 4, 29);

        StudyPlanIDDataModel id = new StudyPlanIDDataModel(pid, date);

        int expected = pid.hashCode() + date.hashCode();
        int alternative = pid.hashCode() - date.hashCode();

        assertNotEquals(expected, alternative, "hashCode() não pode ser igual a soma invertida");
        assertEquals(expected, id.hashCode(), "hashCode() deve usar soma dos componentes");
    }

}