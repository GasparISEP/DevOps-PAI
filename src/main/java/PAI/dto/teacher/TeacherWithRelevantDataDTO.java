package PAI.dto.Teacher;


public class TeacherWithRelevantDataDTO {

    private String name;
    private String acronym;
    private String category;
    private int workingPercentage;

    public TeacherWithRelevantDataDTO(String name, String acronym, String category, int workingPercentage){
        this.name = name;
        this.acronym = acronym;
        this.category = category;
        this.workingPercentage = workingPercentage;
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public String getCategory() {
        return category;
    }

    public int getWorkingPercentage() {
        return workingPercentage;
    }
}
