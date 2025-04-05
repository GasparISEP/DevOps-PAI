package PAI.factory;

import PAI.domain.CourseEdition;

import java.util.ArrayList;
import java.util.List;

public class CourseEditionListFactoryImpl implements ICourseEditionListFactory {
    @Override
    public List<CourseEdition> newList() {
        return new ArrayList<>();
    }
}
