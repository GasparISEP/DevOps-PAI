package PAI.dto.teacherCareerProgression;
import java.time.LocalDate;
import java.util.UUID;

public class TeacherCareerProgressionResponseDTO {
    private String tcpid;
    private String date;
    private String tcid;
    private int workingpercentage;
    private String teacherid;

    public TeacherCareerProgressionResponseDTO(String tcpid, String date, String tcid, int workingpercentage, String teacherid) {
        this.tcpid = tcpid;
        this.date = date;
        this.tcid = tcid;
        this.workingpercentage = workingpercentage;
        this.teacherid = teacherid;
    }

    public String getTcpid() {
        return tcpid;
    }

    public String getDate(){
        return date;
    }

    public String getTcid() {
        return tcid;
    }

    public int getWorkingpercentage() {
        return workingpercentage;
    }

    public String getTeacherid() {
        return teacherid;
    }
}
