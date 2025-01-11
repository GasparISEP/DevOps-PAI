package PAI.domain;

import java.util.ArrayList;

public class SemestersListOfAProgramme {

    private ArrayList<ListOfCoursesInASemester> _semestersListOfAProgramme = new ArrayList<>();

    public SemestersListOfAProgramme() {}

    public void addSemester(ListOfCoursesInASemester semester) {
        _semestersListOfAProgramme.add(semester);
    }

    public ListOfCoursesInASemester getSemester(int semesterNumber) {
        return _semestersListOfAProgramme.get(semesterNumber);
    }
}