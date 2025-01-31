package PAI.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherRepository {
    private List<Teacher> teachers;

    //constructor
    public TeacherRepository(){
        teachers=new ArrayList<>();
    }

    public boolean registerTeacher(String acronym, String name, String email, String nif, String phoneNumber, String academicBackground, String street, String postalCode, String location, String country, String date, TeacherCategory category, int workingPercentage,
                                   Department department) throws IllegalArgumentException {

        Teacher teacher = new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, date, category, workingPercentage, department);

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

    public List<Teacher> getAllTeachers() {
        return new ArrayList<>(teachers);
    }

    public Optional<Teacher> getTeacherByNIF(String NIF) {

        for (Teacher existingTeacher : teachers) {
            if (existingTeacher.hasThisNIF(NIF)) {
                return Optional.of(existingTeacher);
            }
        }
        return Optional.empty();
    }
}
