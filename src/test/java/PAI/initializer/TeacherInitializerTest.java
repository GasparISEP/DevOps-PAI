package PAI.initializer;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.controller.US13_RegisterTeacherAndRelevantDataController;
import PAI.domain.repositoryInterfaces.teacherCategory.ITeacherCategoryRepository;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.persistence.springdata.teacherCategory.TeacherCategoryRepositorySpringDataImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

class TeacherInitializerTest {

    private String _csvPath;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        _csvPath = "src/main/resources/Teacher_Data.csv";
    }

    @Test
    void shouldExecuteCommandLineRunnerAndLoadTeachers() throws Exception {
        //arrange
        US13_RegisterTeacherAndRelevantDataController controller = mock(US13_RegisterTeacherAndRelevantDataController.class);
        TeacherCategoryRepositorySpringDataImpl repo = mock(TeacherCategoryRepositorySpringDataImpl.class);
        TeacherCategory category = mock(TeacherCategory.class);
        TeacherCategoryID categoryID = mock(TeacherCategoryID.class);

        UUID uuid = UUID.fromString("4b68bc54-1a5f-4d90-af6f-43d65e3a166d");
        when(categoryID.toString()).thenReturn(uuid.toString());
        when(category.identity()).thenReturn(categoryID);
        when(repo.findByName(new Name("Professor Auxiliar"))).thenReturn(Optional.of(category));


        TeacherInitializer initializer = new TeacherInitializer();

        // act
        initializer.loadTeachers(controller, repo, _csvPath);

        // assert
        verify(controller).registerTeacher(
                eq("AAA"), eq("Alexandra Castro"), eq("AAA@isep.ipp.pt"),
                eq("112233445"), eq("911234569"), eq("Bachelor"), eq("Rua 1"),
                eq("4000-100"), eq("Porto"), eq("Portugal"), eq("AAU"), eq("01-02-2022"),
                eq(uuid.toString()), eq(25), eq("+351")
        );
    }
}