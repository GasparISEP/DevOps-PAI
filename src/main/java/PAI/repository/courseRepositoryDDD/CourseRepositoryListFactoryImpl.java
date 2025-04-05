package PAI.repository.courseRepositoryDDD;

import PAI.domain.course.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryListFactoryImpl implements ICourseRepositoryListFactoryDDD {

    @Override
    public List<Course> createCourseRepositoryList() {
        return new ArrayList<Course>();
    }
}
