package PAI.domain;

import PAI.VOs.Date;
import PAI.VOs.DurationInYears;
import PAI.VOs.ProgrammeID;
import PAI.VOs.StudyPlanID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudyPlan_2Test {

    @Test
    void testStudyPlanCreation() {
        // Arrange: criar mocks para os parâmetros
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);

        // Act: criar a instância de StudyPlan_2
        StudyPlan_2 studyPlan = new StudyPlan_2(programmeID, implementationDate, durationInYears);

        // Assert: verificar se a instância não é nula
        assertNotNull(studyPlan, "A instância de StudyPlan_2 não deve ser nula.");
    }

    @Test
    void testGetStudyPlanID_NotNull() throws Exception {
        // Arrange: criar mocks para os parâmetros do construtor
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);

        // Act: criar a instância e obter o StudyPlanID
        StudyPlan_2 studyPlan = new StudyPlan_2(programmeID, implementationDate, durationInYears);
        StudyPlanID id = studyPlan.getStudyPlanID();

        // Assert: verificar que o StudyPlanID não é nulo
        assertNotNull(id, "O getter deve retornar um StudyPlanID não nulo.");
    }

    @Test
    void testGetStudyPlanID_Consistente() throws Exception {
        // Arrange: criar mocks para os parâmetros
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);

        StudyPlan_2 studyPlan = new StudyPlan_2(programmeID, implementationDate, durationInYears);

        // Act: obter o StudyPlanID em duas chamadas consecutivas
        StudyPlanID id1 = studyPlan.getStudyPlanID();
        StudyPlanID id2 = studyPlan.getStudyPlanID();

        // Assert: as duas chamadas devem retornar o mesmo objeto
        assertSame(id1, id2, "Chamadas consecutivas ao getter devem retornar o mesmo objeto StudyPlanID.");
    }

    @Test
    void testUniqueStudyPlanIDsForDifferentInstances() {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = mock(Date.class);
        DurationInYears durationInYears = mock(DurationInYears.class);

        // Act: criar duas instâncias diferentes de StudyPlan_2
        StudyPlan_2 studyPlan1 = new StudyPlan_2(programmeID, implementationDate, durationInYears);
        StudyPlan_2 studyPlan2 = new StudyPlan_2(programmeID, implementationDate, durationInYears);

        // Assert: os StudyPlanIDs devem ser diferentes
        assertNotEquals(studyPlan1.getStudyPlanID(), studyPlan2.getStudyPlanID(),
                "Cada instância deve ter um StudyPlanID único.");
    }

}
