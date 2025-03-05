package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeCourseListFactoryImpl implements ProgrammeCourseListFactory {

    public List<Course> createCourseList() {
        return new ArrayList<>();
    }
}
