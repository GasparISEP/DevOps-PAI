package PAI.domain;

public class Enrolment {

    private Student _student;
    private AccessMethod _accessMethod;

    public Enrolment (Student student, AccessMethod accessMethod) {

        _student = student;
        _accessMethod = accessMethod;
    }

    public boolean isSameStudent (Student student) {

        return _student.hasSameUniqueNumber(student);
    }

    //US17
    public Student findStudentInEnrollments(){
        return _student;
    }
}