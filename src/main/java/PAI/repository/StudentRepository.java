package PAI.repository;

import PAI.VOs.*;
import PAI.domain.Address;
import PAI.domain.Student;
import PAI.domain.StudentGrade;
import PAI.factory.IStudentFactory;
import PAI.factory.IStudentListFactory;

import java.util.List;
import java.util.Optional;

public class StudentRepository implements IStudentRepository {

    private IStudentFactory _studentFactory;
    private List<Student> _students;

    public StudentRepository(IStudentFactory studentFactory, IStudentListFactory studentListFactory) {
        if (studentFactory == null || studentListFactory == null) {
            throw new IllegalArgumentException("Invalid factory argument, null values are not allowed!");
        }

        _studentFactory = studentFactory;
        _students = studentListFactory.newArrayList();
    }

    public boolean registerStudent(StudentID studentID, Name name, NIF NIF, PhoneNumber phone, Email email, Address address, StudentAcademicEmail academicEmail) throws Exception {

        Student newStudent = _studentFactory.newStudent(studentID, name, NIF, phone, email, address, academicEmail);

        if (isStudentRepeated(newStudent)) {
            throw new Exception("Duplicate ID or NIF detected. Student cannot be added.");
        } else {
            save(newStudent); //add the student to the list
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
        for (Student existingStudent : _students) {
            if (existingStudent.identity().equals(studentID)) {
                return Optional.of(existingStudent);
            }
        }
        return Optional.empty();
    }

    public Optional<StudentID> findIdByStudent(Student student) {
        for (Student existingStudent : _students) {
            if (existingStudent.equals(student)) {
                return Optional.of(student.identity());
            }
        }
        return Optional.empty();
    }

    @Override
    public Student save(Student student) {
        _students.add(student);
        return student;
    }

    @Override
    public Iterable<Student> findAll() {
        if (_students.isEmpty()){
            throw new IllegalStateException("Student List is currently empty.");
        }
        return _students;
    }

    @Override
    public Optional<Student> ofIdentity(StudentID studentID) {
        return _students.stream()
                .filter(student -> student.identity().equals(studentID))
                .findAny();
    }

    @Override
    public boolean containsOfIdentity(StudentID studentID) {
        if (ofIdentity(studentID).isPresent())
            return true;

        return false;
    }
}