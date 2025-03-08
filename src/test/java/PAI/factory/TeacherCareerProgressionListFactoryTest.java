package PAI.factory;

import PAI.domain.TeacherCareerProgression;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
}