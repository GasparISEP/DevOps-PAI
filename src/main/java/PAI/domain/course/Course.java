package PAI.domain.course;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;

public class Course implements AggregateRoot<CourseID> {

    private final CourseID _courseID;
    private final Name _name;
    private final Acronym _acronym;
    private final CourseQuantityCreditsEcts _quantityCreditsEcts;
    private final DurationCourseInCurricularYear _duration;

    public Course(CourseID courseID, Name name, Acronym acronym, CourseQuantityCreditsEcts quantityCreditsEcts, DurationCourseInCurricularYear durationCourseInCurricularYear){
        if(courseID == null){
            throw new IllegalArgumentException("Course Id must be valid");
        }
        if(name == null){
            throw new IllegalArgumentException("Course Name must be valid");
        }
        if(acronym == null){
            throw new IllegalArgumentException("Course Acronym must be valid");
        }
        if(quantityCreditsEcts == null){
            throw new IllegalArgumentException("Course Quantity of Credits Ects must be valid");
        }
        if(durationCourseInCurricularYear == null){
            throw new IllegalArgumentException("Course Duration must be valid");
        }

        this._courseID = courseID;
        this._name = name;
        this._acronym = acronym;
        this._quantityCreditsEcts = quantityCreditsEcts;
        this._duration = durationCourseInCurricularYear;
    }

    @Override
    public CourseID identity() {
        return _courseID;
    }

    @Override
    public boolean equals(Object objectToCompare) {

        if (this == objectToCompare)
            return true;

        if (objectToCompare instanceof Course) {

            Course courseTest = (Course) objectToCompare;

            if (_courseID.equals(courseTest._courseID))
                return true;
        }
        return false;
    }

    @Override
    public boolean sameAs(Object objectToCompare) {
        if (!(objectToCompare instanceof Course)) {
            return false;
        }
        Course courseTest = (Course) objectToCompare;
        return _name.equals(courseTest._name) &&
                _acronym.equals((courseTest._acronym)) &&
                _quantityCreditsEcts.equals(courseTest._quantityCreditsEcts) &&
                _duration.equals(courseTest._duration);
    }

    public Name getName() {
        return _name;
    }

    public Acronym getAcronym() {
        return _acronym;
    }

    public CourseQuantityCreditsEcts getCourseQuantityCreditsEcts() {
        return _quantityCreditsEcts;
    }

    public DurationCourseInCurricularYear getDurationCourseInCurricularYear() {
        return _duration;
    }
}
