package PAI.factory;

import PAI.domain.Course;
import java.util.ArrayList;
import java.util.List;

public class CourseListFactory implements CourseListFactoryInterface {

    public List<Course> newArrayList() {
        return new ArrayList<>();
    }
}
