package PAI.persistence.mem.student;

import PAI.domain.student.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentListFactoryImpl implements IStudentListFactory {

    public List<Student> newArrayList() {
        return new ArrayList<>();
    }
}
