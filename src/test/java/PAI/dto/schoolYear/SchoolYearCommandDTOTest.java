package PAI.dto.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;

class SchoolYearCommandDTOTest {

    @Test
    void testConstructor() {
        //arrange
        Description description = mock(Description.class);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);

        //act + assert
        SchoolYearCommandDTO res = new SchoolYearCommandDTO(description, startDate, endDate);
    }

    @Test
    void testGetDescription() {
        //arrange
        Description description = mock(Description.class);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);

        //act
        SchoolYearCommandDTO res = new SchoolYearCommandDTO(description, startDate, endDate);

        //assert
        assertEquals(res.getDescription(),description);
    }

    @Test
    void testGetStartDate() {
        //arrange
        Description description = mock(Description.class);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);

        //act
        SchoolYearCommandDTO res = new SchoolYearCommandDTO(description, startDate, endDate);

        //assert
        assertEquals(res.getStartDate(),startDate);
    }

    @Test
    void testGetEndDate() {
        //arrange
        Description description = mock(Description.class);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);

        //act
        SchoolYearCommandDTO res = new SchoolYearCommandDTO(description, startDate, endDate);

        //assert
        assertEquals(res.getEndDate(),endDate);
    }
}