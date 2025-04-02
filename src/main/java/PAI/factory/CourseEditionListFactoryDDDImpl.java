package PAI.factory;

import PAI.domain.CourseEditionDDD;

import java.util.ArrayList;
import java.util.List;

public class CourseEditionListFactoryDDDImpl implements ICourseEditionListFactoryDDD {
    @Override
    public List<CourseEditionDDD> newList() {
        return new ArrayList<>();
    }
}
