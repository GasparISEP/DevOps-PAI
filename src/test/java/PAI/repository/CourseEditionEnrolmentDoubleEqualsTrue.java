package PAI.repository;

import PAI.domain.CourseEdition;
import PAI.domain.CourseEditionEnrolment;
import PAI.domain.Student;

public class CourseEditionEnrolmentDoubleEqualsTrue extends CourseEditionEnrolment {

    public CourseEditionEnrolmentDoubleEqualsTrue(Student student, CourseEdition courseEdition) throws IllegalArgumentException {
        super(student, courseEdition);
    }

    @Override
    public boolean equals (Object obj) {
        return true;
    }
}
