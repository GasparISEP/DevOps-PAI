package PAI.persistence.mem.teacher;

import PAI.domain.teacher.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherListFactoryImpl implements ITeacherListFactory {

    public List<Teacher> newList() {
        return new ArrayList<>();
    }
}
