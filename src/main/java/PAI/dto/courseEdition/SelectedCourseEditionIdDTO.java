package PAI.dto.courseEdition;
import java.time.LocalDate;
import java.util.UUID;

public record SelectedCourseEditionIdDTO(
    String programmeName,
    String programmeAcronym,
    UUID schoolYearID,
    String courseAcronym,
    String courseName,
    LocalDate studyPlanImplementationDate
) {}
