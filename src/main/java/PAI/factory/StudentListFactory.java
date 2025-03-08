package PAI.factory;

import PAI.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentListFactory implements StudentListFactoryInterface {

    public List<Student> newArrayList() {
        return new ArrayList<>();
    }
}
