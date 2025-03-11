package PAI.factory;

import PAI.domain.CourseEdition;

import java.util.ArrayList;
import java.util.List;

public class CourseEditionListFactoryImpl implements CourseEditionListFactory {

    public List<CourseEdition> newArrayList(){
        return new ArrayList<>();
    }
}
