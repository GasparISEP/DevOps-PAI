package PAI.domain.course;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;

public class CourseDDD implements AggregateRoot<CourseID> {

    private CourseID _courseID;
    private Name _name;
    private Acronym _acronym;
    private CourseQuantityCreditsEcts _quantityCreditsEcts;
    private DurationCourseInCurricularYear _duration;

    public CourseDDD(CourseID id, Name name, Acronym acronym, CourseQuantityCreditsEcts quantityCreditsEcts, DurationCourseInCurricularYear durationCourseInCurricularYear){
        if(id == null){
            throw new IllegalArgumentException("Course Id must be valid");
        }
        if(name == null){
            throw new IllegalArgumentException("Course Name must be valid");
        }
        if(acronym == null){
            throw new IllegalArgumentException("Course acronym must be valid");
        }
        if(quantityCreditsEcts == null){
            throw new IllegalArgumentException("Course quantity credits Ects must be valid");
        }
        if(durationCourseInCurricularYear == null){
            throw new IllegalArgumentException("Course duration must be valid");
        }

        this._courseID = id;
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

        if (objectToCompare instanceof CourseDDD) {

            CourseDDD courseDDDTest = (CourseDDD) objectToCompare;

            if (_courseID.equals(courseDDDTest._courseID))
                return true;
        }
        return false;
    }

    @Override
    public boolean sameAs(Object objectToCompare) {
        if (!(objectToCompare instanceof CourseDDD)) {
            return false;
        }
        CourseDDD courseDDDTest = (CourseDDD) objectToCompare;
        return _name.equals(courseDDDTest._name) &&
                _acronym.equals((courseDDDTest._acronym)) &&
                _quantityCreditsEcts.equals(courseDDDTest._quantityCreditsEcts) &&
                _duration.equals(courseDDDTest._duration);
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
}
