package PAI.dto.courseEdition;

public record AverageGradeRequestDTO(
        String programmeId,
        String courseID,
        String courseName,
        String schoolYearID
) {}