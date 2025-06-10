package PAI.dto.teacherCareerProgression;

public record UpdateTeacherCategoryDTO(
        String teacherCareerProgressionId,
        String date,
        String teacherID,
        String teacherCategoryID,
        int workingPercentage
) {
}
