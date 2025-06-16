package PAI.VOs;

public record AvailableCourseInfo(
CourseID courseID,
CourseQuantityCreditsEcts qtyEcts,
CurricularYear curricularYear,
Semester semester
) {
}
