package PAI.domain;

import java.util.ArrayList;

public class StudentListFactory implements StudentListFactoryInterface {

    public ArrayList<Student> newArrayList() {
        return new ArrayList<>();
    }
}
