package PAI.factory;

import PAI.domain.Teacher;

import java.util.ArrayList;

public class TeacherListFactoryImpl implements TeacherListFactory {

    public ArrayList<Teacher> newArrayList() {
        return new ArrayList<>();
    }
}
