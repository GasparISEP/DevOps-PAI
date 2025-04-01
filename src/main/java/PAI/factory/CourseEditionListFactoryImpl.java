package PAI.factory;

import PAI.domain.CourseEdition_2;

import java.util.ArrayList;
import java.util.List;

public class CourseEditionListFactoryImpl implements ICourseEditionListFactory {

    public List<CourseEdition_2> newArrayList(){
        return new ArrayList<>();
    }
}
