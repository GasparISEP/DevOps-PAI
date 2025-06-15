package PAI.initializer;

import PAI.VOs.*;
import PAI.service.courseInStudyPlan.ICourseInStudyPlanService;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseInStudyPlanInitializerTest {

    private CourseInStudyPlanInitializer initializer;
    private ICourseInStudyPlanService serviceMock;
    private Path tempFile;

    @BeforeEach
    void setup() throws Exception {
        initializer = new CourseInStudyPlanInitializer();
        serviceMock = mock(ICourseInStudyPlanService.class);
        tempFile = Files.createTempFile("course-in-study-plan", ".csv");
    }

    @AfterEach
    void cleanup() throws Exception {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void shouldCallServiceWithCorrectArgumentsWhenLoadingValidCSV() throws Exception {
        // Arrange
        // CSV header and 1 valid line with 10 columns (comma separated)
        String csvContent = "duration,curricularYear,ects,date,semester,ignored1,courseAcronym,courseName,programmeAcronym,ignored2\n" +
                            "1,2,6.0,2024-09-01,1,x,CS101,Introduction to CS,CS,x\n" +
                            "2,1,3.0,2024-09-01,2,x,MATH101,Calculus I,MATH,x\n";
        Files.write(tempFile, csvContent.getBytes());

        // Act
        initializer.loadCourseInStudyPlan(serviceMock, tempFile.toString());

        // Assert
        ArgumentCaptor<Semester> semesterCaptor = ArgumentCaptor.forClass(Semester.class);
        ArgumentCaptor<CurricularYear> curricularYearCaptor = ArgumentCaptor.forClass(CurricularYear.class);
        ArgumentCaptor<CourseID> courseIDCaptor = ArgumentCaptor.forClass(CourseID.class);
        ArgumentCaptor<StudyPlanID> studyPlanIDCaptor = ArgumentCaptor.forClass(StudyPlanID.class);
        ArgumentCaptor<DurationCourseInCurricularYear> durationCaptor = ArgumentCaptor.forClass(DurationCourseInCurricularYear.class);
        ArgumentCaptor<CourseQuantityCreditsEcts> ectsCaptor = ArgumentCaptor.forClass(CourseQuantityCreditsEcts.class);

        verify(serviceMock, times(2)).createCourseInStudyPlan(
                semesterCaptor.capture(),
                curricularYearCaptor.capture(),
                courseIDCaptor.capture(),
                studyPlanIDCaptor.capture(),
                durationCaptor.capture(),
                ectsCaptor.capture());


        // First call assertions
        Assertions.assertEquals(1, semesterCaptor.getAllValues().get(0).toInt());
        Assertions.assertEquals(2, curricularYearCaptor.getAllValues().get(0).toInt());
        Assertions.assertEquals(1, durationCaptor.getAllValues().get(0).getDuration());
        Assertions.assertEquals(6.0, ectsCaptor.getAllValues().get(0).getQuantity());
        Assertions.assertEquals("CS101", courseIDCaptor.getAllValues().get(0).getAcronym().getAcronym());
        Assertions.assertEquals("Introduction to CS", courseIDCaptor.getAllValues().get(0).getName().getName());
        Assertions.assertEquals("CS", studyPlanIDCaptor.getAllValues().get(0).getProgrammeID().getAcronym().getAcronym());
        Assertions.assertEquals(LocalDate.parse("2024-09-01"), studyPlanIDCaptor.getAllValues().get(0).getDate().getLocalDate());

        // Second call assertions
        Assertions.assertEquals(2, semesterCaptor.getAllValues().get(1).toInt());
        Assertions.assertEquals(1, curricularYearCaptor.getAllValues().get(1).toInt());
        Assertions.assertEquals(2, durationCaptor.getAllValues().get(1).getDuration());
        Assertions.assertEquals(3.0, ectsCaptor.getAllValues().get(1).getQuantity());
        Assertions.assertEquals("MATH101", courseIDCaptor.getAllValues().get(1).getAcronym().getAcronym());
        Assertions.assertEquals("Calculus I", courseIDCaptor.getAllValues().get(1).getName().getName());
        Assertions.assertEquals("MATH", studyPlanIDCaptor.getAllValues().get(1).getProgrammeID().getAcronym().getAcronym());
        Assertions.assertEquals(LocalDate.parse("2024-09-01"), studyPlanIDCaptor.getAllValues().get(1).getDate().getLocalDate());
    }

    @Test
    void shouldSkipInvalidOrEmptyLines() throws Exception {
        // Arrange
        String csvContent = "duration,curricularYear,ects,date,semester,ignored1,courseAcronym,courseName,programmeAcronym,ignored2\n" +
                            "1,2,6.0,2024-09-01,1,x,CS101,Introduction to CS,CS,x\n" +
                            "invalid,line,missing,fields\n" +
                            "\n" +
                            "2,1,3.0,2024-09-01,2,x,MATH101,Calculus I,MATH,x\n";
        Files.write(tempFile, csvContent.getBytes());

        // Act
        initializer.loadCourseInStudyPlan(serviceMock, tempFile.toString());

        // Assert
        verify(serviceMock, times(2)).createCourseInStudyPlan(any(), any(), any(), any(), any(), any());
    }

    @Test
    void shouldNotThrowWhenFileDoesNotExist() {
        // Act & Assert
        assertDoesNotThrow(() -> {
            initializer.loadCourseInStudyPlan(serviceMock, "non-existent-file.csv");
        });
    }
}
