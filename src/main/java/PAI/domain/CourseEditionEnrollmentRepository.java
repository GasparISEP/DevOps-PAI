package PAI.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class CourseEditionEnrollmentRepository {

    private ArrayList<CourseEditionEnrollment> _courseEditionEnrollments;

    //constructor
    public CourseEditionEnrollmentRepository() {

        _courseEditionEnrollments = new ArrayList<>();
    }

    public Optional<CourseEditionEnrollment> enrollStudentInACourseEdition(Student student, CourseEdition courseEdition, LocalDate enrollmentDate) {

        CourseEditionEnrollment cee1 = new CourseEditionEnrollment(student, courseEdition, enrollmentDate);

        checkIfThisEnrollmentAlreadyExists(cee1);

        _courseEditionEnrollments.add(cee1);

        return Optional.of(cee1);
    }

    //check if this enrollment already exists in this repository
    private void checkIfThisEnrollmentAlreadyExists (CourseEditionEnrollment courseEditionEnrollment) throws IllegalArgumentException {

        if (_courseEditionEnrollments.contains(courseEditionEnrollment)) {
            throw new IllegalArgumentException("This course edition enrollment is already in the list.");
        }
    }

    public boolean isStudentEnrolledInCourseEdition(Student student, CourseEdition courseEdition) {
        return _courseEditionEnrollments.stream()
                .anyMatch(enrollmentStudentCE -> enrollmentStudentCE.knowStudent().equals(student)
                && enrollmentStudentCE.knowCourseEdition().equals(courseEdition));
    }

}
