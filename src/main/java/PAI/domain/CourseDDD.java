package PAI.domain;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;

public class CourseDDD implements AggregateRoot<CourseID> {

    private CourseID _courseID;
    private Name _name;
    private Acronym _acronym;
    private CourseQuantityCreditsEcts _quantityCreditsEcts;
    private DurationCourseInCurricularYear _duration;

    public CourseDDD(CourseID id, Name name, Acronym acronym, CourseQuantityCreditsEcts quantityCreditsEcts, DurationCourseInCurricularYear durationCourseInSemester) throws Exception{
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
        if(durationCourseInSemester == null){
            throw new IllegalArgumentException("Course duration must be valid");
        }

        this._courseID = id;
        this._name = name;
        this._acronym = acronym;
        this._quantityCreditsEcts = quantityCreditsEcts;
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
