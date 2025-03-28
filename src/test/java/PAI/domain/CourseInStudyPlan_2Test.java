package PAI.domain;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseInStudyPlan_2Test {

    @Test
    void ShouldConstructACourseInStudyPlan_2WithMock() throws Exception {

        //Arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);

        //Act
        CourseInStudyPlan_2 CourseInStudyPlan_2 = new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

        //Assert
        assertNotNull(CourseInStudyPlan_2);
    }

    @Test
    void testEqualsSameCourseID() throws Exception {
        // Arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);

        CourseInStudyPlan_2 course1 = new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);
        CourseInStudyPlan_2 course2 = new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

        // Assert
        assertEquals(course1, course2, "Instâncias com o mesmo CourseID devem ser iguais.");
    }

    @Test
    void testEqualsCourseIDDiferente() throws Exception {
        // Arrange
        CourseID courseID1 = mock(CourseID.class);
        CourseID courseID2 = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);

        CourseInStudyPlan_2 course1 = new CourseInStudyPlan_2(semester, curricularYear, courseID1, studyPlanID);
        CourseInStudyPlan_2 course2 = new CourseInStudyPlan_2(semester, curricularYear, courseID2, studyPlanID);

        // Assert
        assertNotEquals(course1, course2, "Instâncias com CourseID diferentes não devem ser iguais.");
    }

    @Test
    void testEqualsComNull() throws Exception {
        // Arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);

        CourseInStudyPlan_2 course = new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

        // Assert
        assertNotEquals(course, null, "A comparação com null deve retornar false.");
    }

    @Test
    void testEqualsComTipoDiferente() throws Exception {
        // Arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);

        CourseInStudyPlan_2 course = new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

        // Assert
        assertNotEquals(course, "Uma String", "A comparação com um tipo diferente deve retornar false.");
    }

    @Test
    void testEqualsComEleMesmo() throws Exception {
        // Arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);

        CourseInStudyPlan_2 course = new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

        // Assert
        assertEquals(course, course, "Um objeto deve ser igual a si próprio.");
    }

    @Test
    void testGetters() throws Exception {
        // Arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);

        // Act
        CourseInStudyPlan_2 course = new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

        // Assert
        assertSame(courseID, course.getCourseID(), "O getter de CourseID deve retornar o valor correto.");
        assertSame(semester, course.getSemester(), "O getter de Semester deve retornar o valor correto.");
        assertSame(curricularYear, course.getCurricularYear(), "O getter de CurricularYear deve retornar o valor correto.");
        assertSame(studyPlanID, course.getStudyplanID(), "O getter de StudyPlanID deve retornar o valor correto.");
    }

    @Test
    void testGetCourseInStudyPlanID_NotNull() throws Exception {
        // Arrange: criar os mocks para os parâmetros do construtor
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        // Act: criar a instância e obter o identificador
        CourseInStudyPlan_2 instance = new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);
        CourseInStudyPlanID id = instance.getCourseInStudyPlanID();

        // Assert: verificar que o ID não é nulo
        assertNotNull(id, "O getter deve retornar um identificador não nulo");
    }

    @Test
    void testGetCourseInStudyPlanID_Consistent() throws Exception {
        // Arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        CourseInStudyPlan_2 instance = new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

        // Act: obter o identificador em duas chamadas consecutivas
        CourseInStudyPlanID id1 = instance.getCourseInStudyPlanID();
        CourseInStudyPlanID id2 = instance.getCourseInStudyPlanID();

        // Assert: ambas as chamadas devem retornar o mesmo objeto
        assertSame(id1, id2, "Chamadas consecutivas ao getter devem retornar o mesmo objeto");
    }
}
