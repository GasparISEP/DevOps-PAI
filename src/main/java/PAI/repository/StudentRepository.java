package PAI.repository;

import PAI.VOs.StudentID;
import PAI.domain.Address;
import PAI.domain.Student;
import PAI.factory.IStudentFactory;
import PAI.factory.IStudentListFactory;

import java.util.List;
import java.util.Optional;

public class StudentRepository {

    private IStudentFactory _studentFactory;
    private List<Student> _students;

    public StudentRepository(IStudentFactory studentFactory, IStudentListFactory studentListFactory) {
        if(studentFactory == null || studentListFactory == null){
            throw new IllegalArgumentException("Invalid factory argument, null values are not allowed!");
        }

        _studentFactory = studentFactory;
        _students = studentListFactory.newArrayList();
    }

    public boolean registerStudent(StudentID studentID, String name, String NIF, String phone, String email, Address address) throws Exception {

        Student newStudent = _studentFactory.newStudent(studentID, name, NIF, phone, email, address);

        if (isStudentRepeated(newStudent)) {
            throw new Exception("Duplicate ID or NIF detected. Student cannot be added.");
        } else {
            _students.add(newStudent); //add the student to the list
            return true;
        }
    }

    private boolean isStudentRepeated(Student student) {
        for (Student existingStudent : _students) {
            if (existingStudent.isEquals(student) || existingStudent.sameAs(student)) {
                return true;
            }
        }
        return false;
    }

    public Optional<Student> getStudentByID(StudentID studentID) {
        for ( Student existingStudent : _students) {
            if ( existingStudent.identity().equals(studentID)){
                return Optional.of(existingStudent);
            }
        }
        return Optional.empty();
    }
}