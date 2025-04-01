package PAI.repository;

import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.domain.CourseEdition;
import PAI.domain.CourseEditionEnrolment;
import PAI.domain.Student;

public class CourseEditionEnrolmentDoubleEqualsTrue extends CourseEditionEnrolment {

    public CourseEditionEnrolmentDoubleEqualsTrue(StudentID studentId, CourseEditionID courseEditionId) throws IllegalArgumentException {
        super(studentId, courseEditionId);
    }

    @Override
    public boolean equals (Object obj) {
        return true;
    }
}
