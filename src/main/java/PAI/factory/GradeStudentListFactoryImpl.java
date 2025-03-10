package PAI.factory;

import PAI.domain.GradeStudent;

import java.util.ArrayList;

public class GradeStudentListFactoryImpl implements GradeStudentListFactory {

    public ArrayList<GradeStudent> newArrayList () { return new ArrayList<>();}

}
