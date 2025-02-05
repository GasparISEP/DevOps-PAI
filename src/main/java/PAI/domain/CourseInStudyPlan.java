package PAI.domain;

import java.util.List;

public class CourseInStudyPlan {

    private Course _course;
    private int _semester;
    private int _curricularYear;
    private Programme _programme;

    public CourseInStudyPlan(int semester, int curricularYear, Course course, Programme programme) throws Exception {

        int quantityOfSemesters = programme.getQuantityOfSemester();
        int numberOfYears = programme.calculateNumberOfYears(quantityOfSemesters);

        if (isSemesterInvalid(semester)) {
            throw new Exception("Invalid semester.");
        }

        if (isCurricularYearInvalid(curricularYear, numberOfYears)) {
            throw new Exception("Invalid curricular year.");
        }

        if (quantityOfSemesters % 2 != 0 && semester == 2 && curricularYear == numberOfYears) {
            throw new Exception("Course cannot be added to second semester of last year.");
        }

        this._course = course;
        this._semester = semester;
        this._curricularYear = curricularYear;
        this._programme = programme;
    }

    @Override
    public boolean equals(Object ObjectToCompare) {
        // Verifica se o objeto a comparar é o mesmo
        if (this == ObjectToCompare) {
            return true;
        }

        // Verifica se o objeto não é uma instância de CourseInStudyPlan
        if (!(ObjectToCompare instanceof CourseInStudyPlan)) {
            return false;
        }

        // Faz o cast do objeto para CourseInStudyPlan
        CourseInStudyPlan courseToBeCompared = (CourseInStudyPlan) ObjectToCompare;

        // Compara os atributos relevantes
        return this._course.equals(courseToBeCompared._course);
    }

    public Course getCourse() {
        return _course;
    }

    public int getSemester() {
        return _semester;
    }

    public int getCurricularYear() {
        return _curricularYear;
    }

    public Programme getProgramme() {
        return _programme;
    }

    private boolean isSemesterInvalid(int semester) {
        return semester < 1 || semester > 2;
    }

    private boolean isCurricularYearInvalid(int curricularYear, int numberOfYears) {
        return (curricularYear < 1 || curricularYear > numberOfYears);
    }
}