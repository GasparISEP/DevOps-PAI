package PAI.initializer;
import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.service.courseEdition.ICourseEditionService;
import PAI.service.courseEdition.ICreateCourseEditionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

class CourseEditionInitializerTest {

    @Mock
    private ICreateCourseEditionService courseEditionService;

    @InjectMocks
    private CourseEditionInitializer initializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallCreateAndSaveCorrectly() throws Exception {
        // Arrange
        ProgrammeID programmeID = new ProgrammeID(
                new NameWithNumbersAndSpecialChars("Data Science"),
                new Acronym("DSD")
        );
        SchoolYearID schoolYearID = new SchoolYearID(UUID.fromString("22222222-2222-2222-2222-222222222222"));
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);

        Date date = new Date("01-07-2023");
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, date);

        CourseID courseID = new CourseID(new Acronym("ARIT"), new Name("Arithmancy"));
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);

        CourseEdition courseEdition = mock(CourseEdition.class);

        when(courseEditionService.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID))
                .thenReturn(courseEdition);
        // Act
        initializer.init();
        // Assert
        verify(courseEditionService).createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID);
    }

    @Test
    void shouldInitializeAndSaveAllCourseEditions() throws Exception {
        CourseEdition courseEdition = mock(CourseEdition.class);
        when(courseEditionService.createAndSaveCourseEdition(any(CourseInStudyPlanID.class), any(ProgrammeEditionID.class))).thenReturn(courseEdition);

        initializer.init();

        verify(courseEditionService, times(4)).createAndSaveCourseEdition(any(CourseInStudyPlanID.class), any(ProgrammeEditionID.class));
    }
}



