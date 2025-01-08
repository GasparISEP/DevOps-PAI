package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepository {
    private List<Teacher> teachers;

    //constructor
    public TeacherRepository(){
        teachers=new ArrayList<>();
    }

    public Teacher createTeacher(String acronym, String name, String email, String nif, String phoneNumber,
                                 Address address, TeacherCategory category, Department department) {
        return new Teacher(acronym, name, email, nif, phoneNumber, address, category, department);
    }
}
