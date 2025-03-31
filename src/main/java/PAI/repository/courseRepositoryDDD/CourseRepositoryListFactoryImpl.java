package PAI.repository.courseRepositoryDDD;

import PAI.domain.CourseDDD;

import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryListFactoryImpl implements ICourseRepositoryListFactoryDDD {

    @Override
    public List<CourseDDD> createCourseRepositoryList() {
        return new ArrayList<CourseDDD>();
    }
}
