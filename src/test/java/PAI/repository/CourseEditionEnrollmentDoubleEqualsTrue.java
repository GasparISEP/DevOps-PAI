package PAI.repository;

import PAI.domain.CourseEdition;
import PAI.domain.CourseEditionEnrollment;
import PAI.domain.Student;

public class CourseEditionEnrollmentDoubleEqualsTrue extends CourseEditionEnrollment {

    public CourseEditionEnrollmentDoubleEqualsTrue(Student student, CourseEdition courseEdition) throws IllegalArgumentException {
        super(student, courseEdition);
    }

    @Override
    public boolean equals (Object obj) {
        return true;
    }
}
