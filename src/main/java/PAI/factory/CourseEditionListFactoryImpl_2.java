package PAI.factory;

import PAI.domain.CourseEdition_2;

import java.util.ArrayList;
import java.util.List;

public class CourseEditionListFactoryImpl_2 implements ICourseEditionListFactory_2{
    @Override
    public List<CourseEdition_2> newList() {
        return new ArrayList<>();
    }
}
