package PAI.domain;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;

public class CourseDDD implements AggregateRoot<CourseID> {

    private CourseID _courseID;
    private Name _name;
    private Acronym _acronym;
    private CourseQuantityCreditsEcts _quantityCreditsEcts;
    private DurationCourseInCurricularYear _duration;

    public CourseDDD(CourseID id,Name name, Acronym acronym, CourseQuantityCreditsEcts quantityOfEcts, DurationCourseInCurricularYear durationCourseInSemester) throws Exception{
        this._courseID = id;
        this._name = name;
        this._acronym = acronym;
        this._quantityCreditsEcts = quantityOfEcts;
        this._duration = durationCourseInSemester;
    }

    @Override
    public CourseID identity() {
        return _courseID;
    }

    @Override
    public boolean sameAs(Object object) {
        return false;
    }
}
