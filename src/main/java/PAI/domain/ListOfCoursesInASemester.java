package PAI.domain;

import java.util.ArrayList;

public class ListOfCoursesInASemester {

    private ArrayList<Course> _listOfCoursesInTheSemester = new ArrayList<>();

    public ListOfCoursesInASemester() {}

    public ArrayList<Course> getAllCourses() {
        return _listOfCoursesInTheSemester;
    }

    public void addCourseToSemester (Course course) {
        _listOfCoursesInTheSemester.add(course);
    }

    public double sumOfCreditsOfAllCourses() {
        double sum = 0;
        for (Course course : _listOfCoursesInTheSemester) {
            sum += course.getQuantityCreditsEcts();
        }
        return sum;
    }
}