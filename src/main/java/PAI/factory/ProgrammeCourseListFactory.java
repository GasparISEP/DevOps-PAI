package PAI.factory;

import PAI.domain.Course;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeCourseListFactory implements ProgrammeCourseListFactoryImpl {

    public List<Course> createCourseList() {
        return new ArrayList<>();
    }
}
