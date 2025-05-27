package PAI.dto.teacherCareerProgression;

public record UpdateTeacherWorkingPercentageResponseDTO(
        String date,
        String teacherID,
        String teacherCategoryID,
        int workingPercentage
) {

}
