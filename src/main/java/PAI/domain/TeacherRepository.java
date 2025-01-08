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
    public void registerTeacher(Teacher teacher) throws IllegalArgumentException {
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher cannot be null.");
        }

        for (Teacher existingTeacher : teachers) {
            if (teacher.hasSameAcronym(existingTeacher)) {
                throw new IllegalArgumentException("A teacher with the same acronym already exists.");
            } else if (teacher.hasSameNif(existingTeacher)) {
                throw new IllegalArgumentException("A teacher with the same NIF already exists.");
            }
        }
        teachers.add(teacher);
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }
}
