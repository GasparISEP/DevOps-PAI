package PAI.domain;

public class Semester {
    private int _qtdSemesters;

    // Constructor
    public Semester(int qtdSemesters) throws Exception{
        if(!isSemesterValid(qtdSemesters)){ throw new IllegalArgumentException("Quantity of Semesters is invalid");}
        _qtdSemesters = qtdSemesters;

    }

    private boolean isSemesterValid (int qtdSemesters){
        return qtdSemesters > 0;
    }
}

