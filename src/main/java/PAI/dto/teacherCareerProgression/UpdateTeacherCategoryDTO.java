package PAI.dto.teacherCareerProgression;

public record UpdateTeacherCategoryDTO(
        String date,
        String teacherID,
        String teacherCategoryID,
        int workingPercentage
) {
}
