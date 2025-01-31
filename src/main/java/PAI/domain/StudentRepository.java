package PAI.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {

    private Programme programme;
    // Create ArrayList to store students
    private List<Student> _students = new ArrayList<>();

    public boolean registerStudent(int uniqueNumber, String name, String NIF, String phone, String email, Address address) throws Exception {

        Student newStudent = new Student(uniqueNumber, name, NIF, phone, email, address);

        if (newStudent.isStudentRepeated(_students)) {
            throw new Exception("Duplicate unique number or NIF detected. Student cannot be added.");
        } else {
            _students.add(newStudent); //add the student to the list
            return true;
        }
    }

    //US17
    public boolean isStudentEnrolledInProgramme(Student student, Programme programme) {
        return programme.isStudentEnrolled(student);
    }

    //US17
    public Optional<Student> findStudentByUniqueNumber(int uniqueNumber){
        for (Student student : _students)
            if (student.getUniqueNumber() == uniqueNumber) {
                return Optional.of(student);
            }
        return Optional.empty();
    }
}