package PAI.repository;

import PAI.domain.Address;
import PAI.domain.Student;
import PAI.factory.StudentFactory;
import PAI.factory.StudentFactoryImpl;
import PAI.factory.StudentListFactory;
import PAI.factory.StudentListFactoryImpl;

import java.util.List;
import java.util.Optional;

public class StudentRepository {

    private StudentFactory _studentFactoryImpl;
    private List<Student> _students;

    public StudentRepository(StudentFactory studentFactory, StudentListFactory studentListFactory) {
        _studentFactoryImpl = studentFactory;
        _students = studentListFactory.newArrayList();
    }

    public boolean registerStudent(String uniqueNumber, String name, String NIF, String phone, String email, Address address) throws Exception {

        Student newStudent = _studentFactoryImpl.newStudent(uniqueNumber, name, NIF, phone, email, address);

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

    public Optional<Student> getStudentByUniqueNumber(String uniqueNumber) {
        for ( Student student : _students) {
            if ( student.hasThisUniqueNumber(uniqueNumber)){
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }
}