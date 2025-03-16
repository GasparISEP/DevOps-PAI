package PAI.factory;

import PAI.domain.TeacherCareerProgression;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TeacherCareerProgressionListFactoryImplTest {

    @Test
    void shouldCreateTeacherCareerProgressionList() {
        //arrange
        TeacherCareerProgressionListFactoryImpl tcplF = new TeacherCareerProgressionListFactoryImpl();

        //act
        List<TeacherCareerProgression> tcpl = tcplF.createTeacherCareerProgressionList();

        //assert
        assertNotNull(tcpl);
        assertInstanceOf(List.class, tcpl);
    }

    @Test
    void shouldAddToTheFabricatedList () {
        // Arrange
        TeacherCareerProgressionListFactoryImpl tcpListFactory = new TeacherCareerProgressionListFactoryImpl();
        List<TeacherCareerProgression> tcpList = tcpListFactory.createTeacherCareerProgressionList();
        TeacherCareerProgression tcpDouble = mock(TeacherCareerProgression.class);

        // Act
        boolean result = tcpList.add(tcpDouble);

        // Assert
        assertTrue(result);
    }
}