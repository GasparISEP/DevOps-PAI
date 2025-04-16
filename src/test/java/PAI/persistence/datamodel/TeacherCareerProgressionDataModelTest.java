package PAI.persistence.datamodel;


import PAI.domain.TeacherCareerProgression;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TeacherCareerProgressionDataModelTest {

    private TeacherCareerProgressionDataModel createTeacherCareerProgressionDataModel() {
        TeacherCareerProgressionIDDataModel id = mock(TeacherCareerProgressionIDDataModel.class);
        UUID teacherCategoryId = UUID.randomUUID();
        int workingPercentage = 100;
        LocalDate date = LocalDate.of(2024, 10, 5);
        String teacherId = "ABC";

        return new TeacherCareerProgressionDataModel(id, teacherCategoryId, workingPercentage, date, teacherId);
    }

    @Test
    void shouldCreateTeacherCareerProgressionDataModelWithoutInputs(){
        // Arrange

        // Act
        new TeacherCareerProgressionDataModel();

        // Assert
    }

    @Test
    void shouldCreateTeacherCareerProgressionDataModelWithValidInputs(){
        // Arrange
        TeacherCareerProgressionIDDataModel id = mock(TeacherCareerProgressionIDDataModel.class);
        UUID teacherCategoryId = UUID.randomUUID();
        int workingPercentage = 100;
        LocalDate date = LocalDate.of(2024, 10, 5);
        String teacherId = "ABC";

        // Act
        new TeacherCareerProgressionDataModel(id, teacherCategoryId, workingPercentage, date, teacherId);

        // Assert
    }

    @Test
    void shouldReturnTeacherCareerProgressionIDWhenCallGetID(){
        // Arrange
        TeacherCareerProgressionDataModel TCP1 = createTeacherCareerProgressionDataModel();
        // Act
        TeacherCareerProgressionIDDataModel result = TCP1.getId();
        // Assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnUUIDWhenCallgetTeacherCategoryId(){
        // Arrange
        TeacherCareerProgressionDataModel TCP1 = createTeacherCareerProgressionDataModel();
        // Act
        UUID result = TCP1.getTeacherCategoryId();
        // Assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnIntWhenCallgetWorkingPercentage(){
        // Arrange
        int expectedWorkingPercentage = 100;
        TeacherCareerProgressionDataModel TCP1 = createTeacherCareerProgressionDataModel();
        // Act
        int result = TCP1.getWorkingPercentage();
        // Assert
        assertEquals(expectedWorkingPercentage, result);
    }

    @Test
    void shouldReturnLocalDateWhenCallgetDate(){
        // Arrange
        LocalDate expectedDate = LocalDate.of(2024, 10, 5);
        TeacherCareerProgressionDataModel TCP1 = createTeacherCareerProgressionDataModel();
        // Act
        LocalDate result = TCP1.getDate();
        // Assert
        assertEquals(expectedDate, result);
    }

    @Test
    void shouldReturnStringWhenCallgetTeacherId(){
        // Arrange
        String expectedTeacherId = "ABC";
        TeacherCareerProgressionDataModel TCP1 = createTeacherCareerProgressionDataModel();
        // Act
        String result = TCP1.getTeacherId();
        // Assert
        assertEquals(expectedTeacherId, result);
    }
}