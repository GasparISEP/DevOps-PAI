package PAI.dto.teacherCareerProgression;

public record UpdateTeacherCategoryResponseDTO(
        String teacherCareerProgressionId,
        String date,
        String teacherID,
        String teacherCategoryID,
        int workingPercentage
) {

}
