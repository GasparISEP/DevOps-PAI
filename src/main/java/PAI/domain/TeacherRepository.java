package PAI.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherRepository {
    private List<Teacher> teachers;
    private TeacherFactory _teacherFactory;

    //constructor
    public TeacherRepository(TeacherFactory teacherFactory){

        teachers=new ArrayList<>();
        _teacherFactory = teacherFactory;
    }

    public boolean registerTeacher(String acronym, String name, String email, String nif, String phoneNumber, String academicBackground, String street, String postalCode, String location, String country, String date, TeacherCategory category, int workingPercentage,
                                   Department department) throws IllegalArgumentException {

        Teacher teacher = _teacherFactory.createTeacher(acronym, name, email, nif, phoneNumber,
                academicBackground, street, postalCode, location, country, date,
                category, workingPercentage, department);

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

    // US20 - retrieves all the teachers in the repository
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
