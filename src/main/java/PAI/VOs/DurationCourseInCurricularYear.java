package PAI.VOs;

import PAI.ddd.ValueObject;

public class DurationCourseInCurricularYear implements ValueObject {

    private final int _duration;

    public DurationCourseInCurricularYear(int duration) {
        if(!isDurationCurricularYearValid(duration)) {
            throw new IllegalArgumentException("The duration of the current year is invalid.");
        }
        this._duration = duration;
    }

    private boolean isDurationCurricularYearValid(int duration) {
        return duration > 0 && duration < 3;
    }

}
