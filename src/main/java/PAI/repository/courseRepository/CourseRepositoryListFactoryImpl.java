package PAI.repository.courseRepository;

import PAI.domain.course.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryListFactoryImpl implements ICourseRepositoryListFactory {

    @Override
    public List<Course> createCourseRepositoryList() {
        return new ArrayList<Course>();
    }
}
