package PAI.domain.student;

import java.util.ArrayList;
import java.util.List;

public class StudentListFactoryImpl implements IStudentListFactory {

    public List<Student> newArrayList() {
        return new ArrayList<>();
    }
}
