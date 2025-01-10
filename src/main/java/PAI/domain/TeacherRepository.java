package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepository {
    private List<Teacher> teachers;

    //constructor
    public TeacherRepository(){
        teachers=new ArrayList<>();
    }

    public boolean registerTeacher(String acronym, String name, String email, String nif, String phoneNumber,
                                   Address address, TeacherCategory category, Department department) throws IllegalArgumentException {

        Teacher teacher = new Teacher(acronym, name, email, nif, phoneNumber, address, category, department);

        compareTeacherAcronymAndNifInList(teacher);
        teachers.add(teacher);
        return true;
    }

    private void compareTeacherAcronymAndNifInList(Teacher teacher) {
        for (Teacher existingTeacher : teachers) {
            if (teacher.hasSameAcronym(existingTeacher)) {
                throw new IllegalArgumentException("A teacher with the same acronym already exists.");
            } else if (teacher.hasSameNif(existingTeacher)) {
                throw new IllegalArgumentException("A teacher with the same NIF already exists.");
            }
        }
    }

}
