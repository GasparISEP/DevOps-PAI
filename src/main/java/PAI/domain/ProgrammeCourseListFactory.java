package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeCourseListFactory implements ProgrammeCourseListFactoryImpl {

    public List<Course> createCourseList() {
        return new ArrayList<>();
    }
}
