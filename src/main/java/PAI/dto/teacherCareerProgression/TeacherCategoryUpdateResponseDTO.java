package PAI.dto.teacherCareerProgression;

import java.util.UUID;

public class TeacherCategoryUpdateResponseDTO {
    private String _date;
    private String _teacherID;
    private String _teacherCategoryID;
    private int _workingPercentage;

    public TeacherCategoryUpdateResponseDTO(){}

    public TeacherCategoryUpdateResponseDTO(String date, String teacherID, String teacherCategoryID, int workingPercentage){
        _date = date;
        _teacherID = teacherID;
        _teacherCategoryID = teacherCategoryID;
        _workingPercentage = workingPercentage;
    }
}
