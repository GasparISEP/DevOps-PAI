package PAI.factory;

import PAI.domain.TeacherCareerProgression;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TeacherCareerProgressionListFactoryTest {

    @Test
    void shouldCreateTeacherCareerProgressionList() {
        //arrange
        TeacherCareerProgressionListFactory tcplF = new TeacherCareerProgressionListFactory();

        //act
        List<TeacherCareerProgression> tcpl = tcplF.createTeacherCareerProgressionList();

        //assert
        assertNotNull(tcpl);
        assertInstanceOf(List.class, tcpl);
    }

    @Test
    void shouldAddToTheFabricatedList () {
        // Arrange
        TeacherCareerProgressionListFactory tcpListFactory = new TeacherCareerProgressionListFactory();
        List<TeacherCareerProgression> tcpList = tcpListFactory.createTeacherCareerProgressionList();
        TeacherCareerProgression tcpDouble = mock(TeacherCareerProgression.class);

        // Act
        boolean result = tcpList.add(tcpDouble);

        // Assert
        assertTrue(result);
    }
}