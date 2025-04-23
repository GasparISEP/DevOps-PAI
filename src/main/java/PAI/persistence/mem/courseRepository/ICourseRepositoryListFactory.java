package PAI.persistence.mem.courseRepository;

import PAI.domain.course.Course;

import java.util.List;

public interface ICourseRepositoryListFactory {
    List<Course> createCourseRepositoryList();
}
