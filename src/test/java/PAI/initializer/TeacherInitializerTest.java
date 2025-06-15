package PAI.initializer;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.controller.US13_RegisterTeacherAndRelevantDataController;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.persistence.springdata.teacherCategory.TeacherCategoryRepositorySpringDataImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherInitializerTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void readsCsvLineCorrectly() throws Exception {
        // Arrange
        Path tempFile = Files.createTempFile("teachers", ".csv");

        String header = "Acronym;Name;Email;NIF;Phone;AcademicBackground;Street;PostalCode;Location;Country;Dept;CountryCode;Date;Category;Percentage\n";
        String data = "AAA;Alexandra Castro;AAA@isep.ipp.pt;112233445;911234569;Bachelor;Rua 1;4000-100;Porto;Portugal;AAU;+351;01-02-2022;Professor Auxiliar;25\n";

        Files.writeString(tempFile, header + data);

        // Act
        List<String> lines = Files.readAllLines(tempFile);
        String[] fields = lines.get(1).split(";");

        // Assert
        assertEquals("AAA", fields[0]);
        assertEquals("Alexandra Castro", fields[1]);
        assertEquals("AAA@isep.ipp.pt", fields[2]);
        assertEquals("Professor Auxiliar", fields[13]);
        assertEquals("25", fields[14]);
    }

    @Test
    void shouldExecuteCommandLineRunnerAndLoadTeachers() throws Exception {
        //arrange
        try (FileWriter writer = new FileWriter("src/main/resources/Teacher_Data.csv")) {
            writer.write("Acronym;Name;Email;NIF;Phone;AcademicBackground;Street;PostalCode;Location;Country;Dept;CountryCode;Date;Category;Percentage\n");
            writer.write("AAA;Alexandra Castro;AAA@isep.ipp.pt;112233445;911234569;Bachelor;Rua 1;4000-100;Porto;Portugal;AAU;+351;01-02-2022;Professor Auxiliar;25\n");
        }

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
        initializer.loadDataRegisterTeacher(controller, repo).run();

        // assert
        verify(controller).registerTeacher(
                eq("AAA"), eq("Alexandra Castro"), eq("AAA@isep.ipp.pt"),
                eq("112233445"), eq("911234569"), eq("Bachelor"), eq("Rua 1"),
                eq("4000-100"), eq("Porto"), eq("Portugal"), eq("AAU"), eq("01-02-2022"),
                eq(uuid.toString()), eq(25), eq("+351")
        );
    }
}