package PAI.dto.teacherCareerProgression;

public class TeacherWorkingPercentageUpdateDTO {
    private String date;
    private int workingpercentage;
    private String teacherid;

    public TeacherWorkingPercentageUpdateDTO(String date, int workingpercentage, String teacherid) {
        this.date = date;
        this.workingpercentage = workingpercentage;
        this.teacherid = teacherid;
    }


    public String getDate(){
        return date;
    }

    public int getWorkingpercentage() {
        return workingpercentage;
    }

    public String getTeacherid() {
        return teacherid;
    }
}
