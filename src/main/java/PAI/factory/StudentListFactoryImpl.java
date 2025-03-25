package PAI.factory;

import PAI.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentListFactoryImpl implements IStudentListFactory {

    public List<Student> newArrayList() {
        return new ArrayList<>();
    }
}
