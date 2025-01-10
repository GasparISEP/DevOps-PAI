package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    // Create ArrayList to store students
    private List<Student> _students = new ArrayList<>();

    public boolean registerStudent(int uniqueNumber, String name, String NIF, String phone, String email, Address address) throws Exception {

        Student newstudent = new Student(uniqueNumber, name, NIF, phone, email, address);

        if (newstudent.isStudentRepeated(_students)) {
            throw new Exception("Duplicate unique number or NIF detected. Student cannot be added.");
        } else {
            _students.add(newstudent); //add the student to the list
            return true;
        }
    }
}