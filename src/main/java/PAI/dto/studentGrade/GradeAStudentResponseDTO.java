package PAI.dto.studentGrade;

public record GradeAStudentResponseDTO(
        int _studentUniqueNumber,
        double _grade,
        String _date,
        String _courseEditionId,
        String _programmeEditionID,
        String _courseInStudyPlanID,
        String _programmeId,
        String _schoolYearId,
        String _courseId,
        String _studyPlanId
) {}
