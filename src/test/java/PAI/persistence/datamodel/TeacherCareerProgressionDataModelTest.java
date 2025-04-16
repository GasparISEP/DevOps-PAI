package PAI.persistence.datamodel;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TeacherCareerProgressionDataModelTest {

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
        UUID teacherCategoryId = mock(UUID.class);
        int workingPercentage = 100;
        LocalDate date = mock(LocalDate.class);
        String teacherId = "teacher-uuid";
        // Act
        new TeacherCareerProgressionDataModel(id, teacherCategoryId, workingPercentage, date, teacherId);

        // Assert
    }
}