package PAI.dto.schoolYear;

import java.time.LocalDate;

public record CurrentSchoolYearDTO(String id,
                                   String description,
                                   LocalDate startDate,
                                   LocalDate endDate) {
}
