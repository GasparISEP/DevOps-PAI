package PAI.dto.teacherCareerProgression;

public class TeacherCareerProgressionDTO {

    private String tcpID;
    private String date;
    private String tcID;
    private int workingPercentage;
    private String teacherID;

    public TeacherCareerProgressionDTO(String tcpID, String date, String tcID, int workingPercentage, String teacherID) {
        this.tcpID = tcpID;
        this.date = date;
        this.tcID = tcID;
        this.workingPercentage = workingPercentage;
        this.teacherID = teacherID;
    }

    public String getTcpID() {
        return tcpID;
    }

    public String getDate() {
        return date;
    }
    public String getTcID() {
        return tcID;
    }
    public int getWorkingPercentage() {
        return workingPercentage;
    }
    public String getTeacherID() {
        return teacherID;
    }
}
