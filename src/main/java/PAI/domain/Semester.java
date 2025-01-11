package PAI.domain;

import java.util.ArrayList;

public class Semester {

    private ArrayList<Course> listOfCoursesInThesemester = new ArrayList<>();

    public Semester() {}

    public ArrayList<Course> getAllCourses() {
        return listOfCoursesInThesemester;
    }

    public void addCourseToSemester (Course course) {
        listOfCoursesInThesemester.add(course);
    }

    public double sumOfCreditsOfAllCourses() {
        double sum = 0;
        for (Course course : listOfCoursesInThesemester) {
            sum += course.getQuantityCreditsEcts();
        }
        return sum;
    }
}