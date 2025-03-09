package PAI.factory;

import PAI.domain.GradeStudent;

import java.util.ArrayList;

public class GradeStudentListFactory implements GradeStudentListFactoryImpl {

    public ArrayList<GradeStudent> newArrayList () { return new ArrayList<>();}

}
