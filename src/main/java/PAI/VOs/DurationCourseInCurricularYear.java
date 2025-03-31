package PAI.VOs;

import PAI.ddd.ValueObject;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DurationCourseInCurricularYear that = (DurationCourseInCurricularYear) o;
        return _duration == that._duration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_duration);
    }
}
