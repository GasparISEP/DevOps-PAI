package PAI.dto.schoolYear;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record CurrentSchoolYearResponseDTO(String id,
                                           String description,
                                           @JsonFormat(pattern = "dd-MM-yyyy")
                                           LocalDate startDate,
                                           @JsonFormat(pattern = "dd-MM-yyyy")
                                           LocalDate endDate) {
}
