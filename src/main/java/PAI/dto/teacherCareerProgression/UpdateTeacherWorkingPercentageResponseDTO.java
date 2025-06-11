package PAI.dto.teacherCareerProgression;

public record UpdateTeacherWorkingPercentageResponseDTO(
        String teacherCareerProgressionId,
        String date,
        String teacherID,
        String teacherCategoryID,
        int workingPercentage
) {

}
