package PAI.domain;

import PAI.factory.StudentFactory;
import PAI.factory.StudentListFactory;

import java.util.List;
import java.util.Optional;

public class StudentRepository {

    private StudentFactory _studentFactory;
    private List<Student> _students;

    public StudentRepository(StudentFactory studentFactory, StudentListFactory studentListFactory) {
        _studentFactory = studentFactory;
        _students = studentListFactory.newArrayList();
    }

    public boolean registerStudent(int uniqueNumber, String name, String NIF, String phone, String email, Address address) throws Exception {

        Student newStudent = _studentFactory.newStudent(uniqueNumber, name, NIF, phone, email, address);

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

    public Optional<Student> getStudentByUniqueNumber(int uniqueNumber) {
        for ( Student student : _students) {
            if ( student.hasThisUniqueNumber(uniqueNumber)){
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }
}