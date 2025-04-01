package PAI.repository;

import PAI.domain.CourseEdition;
import PAI.domain.CourseEditionEnrolment;
import PAI.domain.CourseEdition_2;
import PAI.domain.Student;

public class CourseEditionEnrolmentDoubleEqualsTrue extends CourseEditionEnrolment {

    public CourseEditionEnrolmentDoubleEqualsTrue(Student student, CourseEdition_2 courseEdition) throws IllegalArgumentException {
        super(student, courseEdition);
    }

    @Override
    public boolean equals (Object obj) {
        return true;
    }
}
