package PAI.dto.SchoolYear;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SchoolYearDTOTest {

    @Test
    void shouldConstructSchoolYearDTO() {
    //arrange
    String description = "ola";
    LocalDate startDate = LocalDate.of(2025, 2, 26);
    LocalDate endDate = LocalDate.of(2025, 3, 31);

    //act
    SchoolYearDTO syDTO = new SchoolYearDTO(description, startDate, endDate);

    //assert
    assertNotNull(syDTO);
    }

    @Test
    void shouldGetDescription() {
        //arrange
        String description = "ola";
        LocalDate startDate = LocalDate.of(2025, 2, 26);
        LocalDate endDate = LocalDate.of(2025, 3, 31);
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
        LocalDate startDate = LocalDate.of(2025, 2, 26);
        LocalDate endDate = LocalDate.of(2025, 3, 31);
        SchoolYearDTO syDTO = new SchoolYearDTO(description, startDate, endDate);

        //act
        String get = syDTO.getDescription();

        //assert
        assertNotEquals(descriptions,get);
    }
}