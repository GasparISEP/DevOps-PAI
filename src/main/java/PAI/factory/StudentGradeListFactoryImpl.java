package PAI.factory;

import PAI.domain.StudentGrade;

import java.util.ArrayList;
import java.util.List;

public class StudentGradeListFactoryImpl implements StudentGradeListFactory {

    public List<StudentGrade> newArrayList () {
        return new ArrayList<>();}
}
