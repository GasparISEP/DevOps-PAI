package PAI.repository;

import PAI.domain.Course;
import PAI.domain.CourseFactory;
import PAI.domain.CourseInStudyPlan;
import PAI.domain.Programme;
import PAI.factory.CourseInStudyPlanFactory;

import java.util.ArrayList;
import java.util.List;

public class StudyPlan {

    private CourseInStudyPlanFactory _courseInStudyPlanFactory;
    private CourseFactory _courseFactory;
    private List<CourseInStudyPlan> studyPlan;

    public StudyPlan() {

        this.studyPlan = new ArrayList<>();
        this._courseInStudyPlanFactory = new CourseInStudyPlanFactory();
        this._courseFactory = new CourseFactory();
    }

    public boolean addCourseToStudyPlan(int semester, int curricularYear, Course course, Programme programme) throws Exception {

        if (isCourseOrProgrammeInvalid(course, programme)) {
            throw new Exception("Invalid course or programme.");
        }

        if (isCourseInStudyPlan(course)) {
            throw new Exception("Cannot register course: Course already registered in Study Plan.");
        }

        //Verificar se o course é anual
        if (course.getDurationInSemester() == 2) {
            return addAnnualCourse(semester, curricularYear, course, programme);
        }

        CourseInStudyPlan courseInStudyPlan = _courseInStudyPlanFactory.newCourseInStudyPlan(semester, curricularYear, course, programme);

        // Verifica se o limite de ECTS foi excedido
        if (isEctsLimitExceeded(curricularYear, semester, courseInStudyPlan)) {
            throw new IllegalArgumentException("Cannot register course: ECTS limit for this semester exceeded.");
        }

        studyPlan.add(courseInStudyPlan);
        return true;
    }

    private boolean isCourseOrProgrammeInvalid(Course course, Programme programme) {
        if (course == null || programme == null) {
            return true;
        }
        return !programme.getCourseList().contains(course);
    }

    private boolean isCourseInStudyPlan(Course course) {
        for (CourseInStudyPlan existingCourse : studyPlan) {
            if (existingCourse.getCourse().equals(course)) {
                return true;
            }
        }
        return false;
    }

    private boolean addAnnualCourse(int semester, int curricularYear, Course course, Programme programme) throws Exception {
        if (semester != 1) {
            throw new Exception("Annual courses must be registered in the first semester.");
        }

        int quantityOfSemesters = programme.getQuantityOfSemester();
        int numberOfYears = programme.calculateNumberOfYears(quantityOfSemesters);

        if (quantityOfSemesters % 2 != 0 && curricularYear == numberOfYears) {
            throw new Exception("Annual courses must be registered in a complete year.");
        }

        double halfEcts = course.getQuantityCreditsEcts() / 2.0;

        CourseInStudyPlan firstSemesterCourse = _courseInStudyPlanFactory.newCourseInStudyPlan(1, curricularYear,
                _courseFactory.createCourse(course.getName(), course.getAcronym(), halfEcts, 1), programme);

        if (isEctsLimitExceeded(curricularYear, 1, firstSemesterCourse)) {
            throw new IllegalArgumentException("Cannot register course: ECTS limit for this semester exceeded.");
        }

        CourseInStudyPlan secondSemesterCourse = _courseInStudyPlanFactory.newCourseInStudyPlan(2, curricularYear,
                _courseFactory.createCourse(course.getName(), course.getAcronym(), halfEcts, 1), programme);

        if (isEctsLimitExceeded(curricularYear, 2, secondSemesterCourse)) {
            throw new IllegalArgumentException("Cannot register course: ECTS limit for this semester exceeded.");
        }

        studyPlan.add(firstSemesterCourse);
        studyPlan.add(secondSemesterCourse);
        return true;
    }



    private boolean isEctsLimitExceeded(int curricularYear, int semester, CourseInStudyPlan courseInStudyPlan) {
        double totalEcts = 0;

        // Itera sobre os cursos no StudyPlan para somar os ECTS do mesmo semestre e ano
        for (CourseInStudyPlan existingCourse : studyPlan) {
            if (existingCourse.getCurricularYear() == curricularYear && existingCourse.getSemester() == semester) {
                totalEcts += existingCourse.getCourse().getQuantityCreditsEcts();
            }
        }

        // Adiciona os ECTS do novo curso
        totalEcts += courseInStudyPlan.getCourse().getQuantityCreditsEcts();

        // Obtém o limite de ECTS do programa associado ao curso
        double ectsLimit = courseInStudyPlan.getProgramme().getQuantityOfEcts();

        // Verifica se excede o limite
        return totalEcts > ectsLimit;
    }
}