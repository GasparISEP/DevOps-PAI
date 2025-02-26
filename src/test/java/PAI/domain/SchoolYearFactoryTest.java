package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SchoolYearFactoryTest {

    @Test
    void shouldCreateAccessMethod() throws Exception {
        //arrange
        SchoolYearFactory schoolYearFactory = new SchoolYearFactory();
        //act
        SchoolYear schoolYear = schoolYearFactory.createSchoolYear("23/24", "20-09-2024", "20-06-2025");
        //assert
        assertNotNull(schoolYear);
    }

    @Test
    void shouldNotCreateAccessMethod() throws InstantiationException {
        //arrange
        SchoolYearFactory schoolYearFactory = new SchoolYearFactory();
        //act
        //assert
        assertThrows(Exception.class, () -> schoolYearFactory.createSchoolYear("23/24", "20-09-2024", ""));
    }
}