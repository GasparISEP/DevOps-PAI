package PAI.initializer;

import PAI.VOs.Acronym;
import PAI.VOs.Name;
import PAI.domain.course.Course;
import PAI.service.course.ICourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseInitializerTest {

    @Mock
    private ICourseService courseService;

    @InjectMocks
    private CourseInitializer initializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldInitializeAndSaveCoursesFromCsvFile() throws Exception {
        // arrange
        Course course = mock(Course.class);
        when(courseService.createAndSaveCourse(any(Name.class), any(Acronym.class))).thenReturn(course);

        // act
        initializer.init();

        // assert
        verify(courseService).createAndSaveCourse(new Name("Alchemy"), new Acronym("ALCH"));
        verify(courseService).createAndSaveCourse(new Name("Arithmancy"), new Acronym("ARIT"));
        verify(courseService).createAndSaveCourse(new Name("Astronomy I"), new Acronym("AST1"));
        verify(courseService).createAndSaveCourse(new Name("Astronomy II"), new Acronym("AST2"));
        verify(courseService).createAndSaveCourse(new Name("Astronomy III"), new Acronym("AST3"));
        verify(courseService).createAndSaveCourse(new Name("Astronomy IV"), new Acronym("AST4"));
        verify(courseService).createAndSaveCourse(new Name("Care of Magical Creatures"), new Acronym("COMC"));
        verify(courseService).createAndSaveCourse(new Name("Charms I"), new Acronym("CHM1"));
        verify(courseService).createAndSaveCourse(new Name("Charms II"), new Acronym("CHM2"));
        verify(courseService).createAndSaveCourse(new Name("Defence Against the Dark Arts I"), new Acronym("DADA1"));
        verify(courseService).createAndSaveCourse(new Name("Defence Against the Dark Arts II"), new Acronym("DADA2"));
        verify(courseService).createAndSaveCourse(new Name("Defence Against the Dark Arts III"), new Acronym("DADA3"));
        verify(courseService).createAndSaveCourse(new Name("Defence Against the Dark Arts IV"), new Acronym("DADA4"));
        verify(courseService).createAndSaveCourse(new Name("Defence Against the Dark Arts V"), new Acronym("DADA5"));
        verify(courseService).createAndSaveCourse(new Name("Divination"), new Acronym("DIV"));
        verify(courseService).createAndSaveCourse(new Name("Flying"), new Acronym("FLY"));
        verify(courseService).createAndSaveCourse(new Name("Herbology I"), new Acronym("HERB1"));
        verify(courseService).createAndSaveCourse(new Name("Herbology II"), new Acronym("HERB2"));
        verify(courseService).createAndSaveCourse(new Name("History of Magic I"), new Acronym("HIM1"));
        verify(courseService).createAndSaveCourse(new Name("History of Magic II"), new Acronym("HIM2"));
        verify(courseService).createAndSaveCourse(new Name("Muggle Studies"), new Acronym("MUGS"));
        verify(courseService).createAndSaveCourse(new Name("Potions I"), new Acronym("POT1"));
        verify(courseService).createAndSaveCourse(new Name("Potions II"), new Acronym("POT2"));
        verify(courseService).createAndSaveCourse(new Name("Potions III"), new Acronym("POT3"));
        verify(courseService).createAndSaveCourse(new Name("Potions IV"), new Acronym("POT4"));
        verify(courseService).createAndSaveCourse(new Name("Study of Ancient Runes"), new Acronym("SAR"));
        verify(courseService).createAndSaveCourse(new Name("Transfiguration I"), new Acronym("TRN1"));
        verify(courseService).createAndSaveCourse(new Name("Transfiguration II"), new Acronym("TRN2"));
        verify(courseService).createAndSaveCourse(new Name("Transfiguration III"), new Acronym("TRN3"));
        verify(courseService).createAndSaveCourse(new Name("Transfiguration IV"), new Acronym("TRN4"));
        //CAPITALIZED DUPLICATE NAME TEST
        verify(courseService).createAndSaveCourse(new Name("TRANSFIGURATION IV"), new Acronym("TRN5"));
        //ACRONYM DUPLICATE TEST
        verify(courseService).createAndSaveCourse(new Name("Transfiguration V"), new Acronym("TRN4"));
    }

    @Test
    void shouldInitializeAndSaveAllCourses() throws Exception {
        Course course = mock(Course.class);
        when(courseService.createAndSaveCourse(any(Name.class), any(Acronym.class))).thenReturn(course);

        initializer.init();

        verify(courseService, times(32)).createAndSaveCourse(any(Name.class), any(Acronym.class));
    }
}