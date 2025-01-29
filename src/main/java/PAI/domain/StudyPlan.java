package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class StudyPlan {

    private List<CourseInStudyPlan> studyPlan;

    public StudyPlan() {

        this.studyPlan = new ArrayList<>();
    }

    public boolean registerCourseInStudyPlan(int semester, int curricularYear, Course course, Programme programme) throws Exception {

        // Verifica se o Course fornecido está na courseList do Programme
        if (!programme.getCourseList().contains(course)) {
            throw new Exception("The course provided is not part of the programme.");
        }

        // Verifica se o curso já foi adicionado no mesmo plano curricular
        for (CourseInStudyPlan existingCourse : studyPlan) {
            if (existingCourse.getCourse().equals(course)) {
                throw new Exception("Cannot register course: Course already registered in Study Plan.");
            }
        }

        // Verifica se o course é anual
        if (course.getDurationInSemester() == 2) {

            if (semester != 1) {
                throw new Exception("Annual courses must be registered in the first semester.");
            }

            int quantityOfSemesters = programme.getQuantityOfSemester();
            int numberOfYears = calculateNumberOfYears(quantityOfSemesters);

            if (quantityOfSemesters % 2 != 0 && curricularYear == numberOfYears) {
                throw new Exception("Annual courses must be registered in a complete year.");
            }


            // Divide os créditos do curso anual em 2
            double halfEcts = course.getQuantityCreditsEcts() / 2.0;

            // Cria o objeto para o primeiro semestre
            CourseInStudyPlan firstSemesterCourse = new CourseInStudyPlan(1, curricularYear,
                    new Course(course.getName(), course.getAcronym(), halfEcts, 1), programme);

            // Verifica se o limite de ECTS foi excedido no 1º semestre
            if (isEctsLimitExceeded(curricularYear, 1, firstSemesterCourse)) {
                throw new IllegalArgumentException("Cannot register course: ECTS limit for this semester exceeded.");
            }

            // Cria o objeto para o segundo semestre
            CourseInStudyPlan secondSemesterCourse = new CourseInStudyPlan(2, curricularYear,
                    new Course(course.getName(), course.getAcronym(), halfEcts, 1), programme);

            // Verifica se o limite de ECTS foi excedido no 2º semestre
            if (isEctsLimitExceeded(curricularYear, 2, secondSemesterCourse)) {
                throw new IllegalArgumentException("Cannot register course: ECTS limit for this semester exceeded.");
            }

            // Adiciona ambos ao plano de estudos
            studyPlan.add(firstSemesterCourse);
            studyPlan.add(secondSemesterCourse);
            return true;
        }

        CourseInStudyPlan courseInStudyPlan = new CourseInStudyPlan(semester, curricularYear, course, programme);

        // Verifica se o limite de ECTS foi excedido
        if (isEctsLimitExceeded(curricularYear, semester, courseInStudyPlan)) {
            throw new IllegalArgumentException("Cannot register course: ECTS limit for this semester exceeded.");
        }

        studyPlan.add(courseInStudyPlan);
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

    private int calculateNumberOfYears(int quantityOfSemesters) {
        int numberOfYears;

        // Calcula o número de anos
        if (quantityOfSemesters % 2 != 0) {
            numberOfYears = (quantityOfSemesters + 1) / 2;
        }
        else {
            numberOfYears = quantityOfSemesters / 2;
        }
        return numberOfYears;
    }
}