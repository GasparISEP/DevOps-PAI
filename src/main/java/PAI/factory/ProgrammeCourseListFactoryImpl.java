package PAI.factory;

import PAI.domain.Course;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeCourseListFactoryImpl implements IProgrammeCourseListFactory {

    public List<Course> createCourseList() {
        return new ArrayList<>();
    }
}
