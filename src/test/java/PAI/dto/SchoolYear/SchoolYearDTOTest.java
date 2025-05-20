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
}