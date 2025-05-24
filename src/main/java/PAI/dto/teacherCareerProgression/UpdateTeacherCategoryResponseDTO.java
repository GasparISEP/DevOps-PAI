package PAI.dto.teacherCareerProgression;

public class UpdateTeacherCategoryResponseDTO {
    private String _date;
    private String _teacherID;
    private String _teacherCategoryID;
    private int _workingPercentage;

    public UpdateTeacherCategoryResponseDTO(){}

    public UpdateTeacherCategoryResponseDTO(String date, String teacherID, String teacherCategoryID, int workingPercentage){
        _date = date;
        _teacherID = teacherID;
        _teacherCategoryID = teacherCategoryID;
        _workingPercentage = workingPercentage;
    }
}
