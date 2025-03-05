package PAI.factory;

import PAI.domain.Student;
import PAI.domain.StudentListFactoryInterface;

import java.util.ArrayList;

public class StudentListFactory implements StudentListFactoryInterface {

    public ArrayList<Student> newArrayList() {
        return new ArrayList<>();
    }
}
