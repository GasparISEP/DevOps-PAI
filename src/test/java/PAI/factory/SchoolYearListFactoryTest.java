package PAI.factory;

import PAI.domain.SchoolYear;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SchoolYearListFactoryTest {

    @Test
    void shouldCreateSchoolYearList() {
        // Arrange
        SchoolYearListFactory syListFactory = new SchoolYearListFactory();

        // Act
        List<SchoolYear> schoolYearList = syListFactory.newArrayList();

        // Assert
        assertNotNull(schoolYearList);
        assertInstanceOf(List.class, schoolYearList);
    }
}
