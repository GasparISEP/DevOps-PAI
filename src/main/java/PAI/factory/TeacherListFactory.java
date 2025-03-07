package PAI.factory;

import PAI.domain.Teacher;

import java.util.ArrayList;

public class TeacherListFactory implements TeacherListFactoryInterface {

    public ArrayList<Teacher> newArrayList() {
        return new ArrayList<>();
    }
}
