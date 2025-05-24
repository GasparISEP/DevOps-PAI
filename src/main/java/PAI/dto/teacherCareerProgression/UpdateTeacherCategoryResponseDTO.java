package PAI.dto.teacherCareerProgression;

public record UpdateTeacherCategoryResponseDTO(
        String date,
        String teacherID,
        String teacherCategoryID,
        int workingPercentage
) {

}
