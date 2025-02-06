package PAI.domain;

public class GradeStudent {

        private double _grade;
        private String _date;
        private Student _student;
        private CourseEdition _courseEdition;

        public GradeStudent(double grade, String date, Student student, CourseEdition courseEdition) {
            if (!isGradeValid(grade)) throw new IllegalArgumentException("Grade should be between 0 and 20");
            _grade = grade;

            if (!isDateValid(date)) throw new IllegalArgumentException("Date is not valid");
            _date = date;

            if (student == null) throw new IllegalArgumentException("Student cannot be null");
            _student = student;

            if (courseEdition == null) throw new IllegalArgumentException("Course Edition cannot be null");
            _courseEdition = courseEdition;
        }

        private boolean isGradeValid (double grade){
            return grade >= 0 && grade <=20;
        }

        private boolean isDateValid (String date){
            return date !=null && date.matches("\\d{2}/\\d{2}/\\d{4}") ;
        }

        public double knowGrade() {
            return _grade;
        }

        public boolean hasThisCourseEdition(CourseEdition courseEdition) {
            return _courseEdition.equals(courseEdition);
        }

        public CourseEdition KnowCourseEdition() {
            return _courseEdition;
        }
    }


