package PAI.dto.schoolYear;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SchoolYearDTOTest {

    @Test
    void shouldConstructSchoolYearDTOWithEmptyController() {
        //arrange + act
        SchoolYearDTO syDTO = new SchoolYearDTO();

        //assert
        assertNotNull(syDTO);
    }

    @Test
    void shouldConstructSchoolYearDTO() {
    //arrange
        String description = "School Year 24/25";
        String startDate = "24-09-2024";
        String endDate = "31-06-2025";

    //act
    SchoolYearDTO syDTO = new SchoolYearDTO(description, startDate, endDate);

    //assert
    assertNotNull(syDTO);
    }

    @Test
    void shouldGetDescription() {
        //arrange
        String description = "School Year 24/25";
        String startDate = "24-09-2024";
        String endDate = "31-06-2025";
        SchoolYearDTO syDTO = new SchoolYearDTO(description, startDate, endDate);

        //act
        String get = syDTO.getDescription();

        //assert
        assertEquals(get,syDTO.getDescription());
    }

    @Test
    void shouldNotGetDescription() {
        //arrange
        String description = "ola";
        String descriptions = "olas";
        String startDate = "24-09-2024";
        String endDate = "31-06-2025";
        SchoolYearDTO syDTO = new SchoolYearDTO(description, startDate, endDate);

        //act
        String get = syDTO.getDescription();

        //assert
        assertNotEquals(descriptions,get);
    }

    @Test
    void shouldGetEndDate() {
        //arrange
        String description = "School Year 24/25";
        String startDate = "24-09-2024";
        String endDate = "31-06-2025";
        SchoolYearDTO syDTO = new SchoolYearDTO(description, startDate, endDate);

        //act
        String get = syDTO.getEndDate();

        //assert
        assertEquals(get,syDTO.getEndDate());
    }

    @Test
    void shouldNotGetEndDate() {
        //arrange
        String description = "School Year 24/25";
        String startDate = "24-09-2024";
        String endDate = "31-06-2025";
        String endDates = "30-08-2025";
        SchoolYearDTO syDTO = new SchoolYearDTO(description, startDate, endDate);

        //act
        String get = syDTO.getEndDate();

        //assert
        assertNotEquals(endDates,get);
    }

    @Test
    void shouldGetStartDate() {
        //arrange
        String description = "School Year 24/25";
        String startDate = "24-09-2024";
        String endDate = "31-06-2025";
        SchoolYearDTO syDTO = new SchoolYearDTO(description, startDate, endDate);

        //act
        String get = syDTO.getStartDate();

        //assert
        assertEquals(get,syDTO.getStartDate());
    }

    @Test
    void shouldNotGetStartDate() {
        //arrange
        String description = "School Year 24/25";
        String startDate = "24-09-2024";
        String endDate = "31-06-2025";
        String startDates = "25-09-2024";
        SchoolYearDTO syDTO = new SchoolYearDTO(description, startDate, endDate);

        //act
        String get = syDTO.getStartDate();

        //assert
        assertNotEquals(startDates,get);
    }
}