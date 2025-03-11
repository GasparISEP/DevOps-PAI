package PAI.factory;

import PAI.domain.GradeStudent;

import java.util.ArrayList;
import java.util.List;

public class GradeStudentListFactoryImpl implements GradeStudentListFactory {

    public List<GradeStudent> newArrayList () {
        return new ArrayList<>();}
}
