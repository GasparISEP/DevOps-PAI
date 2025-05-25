package PAI.dto.teacherCareerProgression;

public class TeacherWorkingPercentageUpdateDTO {
    private String date;
    private int workingPercentage;
    private String teacherID;

    public TeacherWorkingPercentageUpdateDTO(String date, int workingPercentage, String teacherID) {
        this.date = date;
        this.workingPercentage = workingPercentage;
        this.teacherID = teacherID;
    }


    public String getDate(){
        return date;
    }

    public int getWorkingPercentage() {
        return workingPercentage;
    }

    public String getTeacherID() {
        return teacherID;
    }
}
