package PAI.factory;

import PAI.domain.CourseEditionDDD;

import java.util.ArrayList;
import java.util.List;

public class CourseEditionListFactoryImpl implements ICourseEditionListFactory {

    public List<CourseEditionDDD> newArrayList(){
        return new ArrayList<>();
    }
}
