package PAI.domain;

import java.util.ArrayList;

public class SemestersListOfAProgramme {

    private ArrayList<Semester> listOfSemester = new ArrayList<>();

    public SemestersListOfAProgramme() {}

    public void addSemester(Semester semester) {
        listOfSemester.add(semester);
    }

    public Semester getSemester(int semesterNumber) {
        return listOfSemester.get(semesterNumber);
    }
}