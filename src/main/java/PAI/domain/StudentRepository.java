package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private Programme programme;
    // Create ArrayList to store students
    private List<Student> _students = new ArrayList<>();

    public boolean registerStudent(int uniqueNumber, String name, String NIF, String phone, String email, Address address) throws Exception {

        Student newStudent = new Student(uniqueNumber, name, NIF, phone, email, address);

        if (isStudentRepeated(newStudent)) {
            throw new Exception("Duplicate unique number or NIF detected. Student cannot be added.");
        } else {
            _students.add(newStudent); //add the student to the list
            return true;
        }
    }

    private boolean isStudentRepeated(Student student) {
        for (Student existingStudent : _students) {
            if (existingStudent.hasSameUniqueNumber(student) || existingStudent.hasSameNIF(student)) {
                return true;
            }
        }
        return false;
    }

    public boolean isStudentEnrolledInProgramme(Student student, Programme programme) {
        return programme.isStudentEnrolled(student);
    }

}