package PAI.repository;

import PAI.VOs.*;
import PAI.domain.Student;
import PAI.factory.IStudentListFactory;

import java.util.List;
import java.util.Optional;

    public class StudentRepository implements IStudentRepository {


        private List<Student> _students;

        public StudentRepository(IStudentListFactory studentListFactory) {
            if ( studentListFactory == null) {
                throw new IllegalArgumentException("Invalid factory argument, null values are not allowed!");
            }

            _students = studentListFactory.newArrayList();
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
                if (existingStudent.sameAs(student)) {
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


        @Override
        public boolean containsByStudentIDOrNIF(StudentID studentID, NIF nif) {
            for (Student student : _students) {
                if (student.identity().equals(studentID) || student.getStudentNIF().equals(nif)) {
                    return true;
                }
            }
            return false;
        }
}