package PAI.persistence.mem.schoolYear;

import PAI.domain.schoolYear.SchoolYear;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SchoolYearListFactoryImplTest {

    @Test
    void shouldCreateSchoolYearList() {
        // Arrange
        ISchoolYearListFactory syListFactory = new SchoolYearListFactoryImpl();

        // Act
        List<SchoolYear> schoolYearList = syListFactory.newArrayList();

        // Assert
        assertNotNull(schoolYearList);
    }
}
