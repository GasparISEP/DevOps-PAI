package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class SemesterList {
    private List<Semester> semestersList = new ArrayList<>();

    // Constructor
    public SemesterList() {
    }

    // Getter
    protected List<Semester> getSemestersList() {
        return semestersList;
    }

    // Add Semester
    public void addSemester (Semester semester){
        semestersList.add(semester);}
}
