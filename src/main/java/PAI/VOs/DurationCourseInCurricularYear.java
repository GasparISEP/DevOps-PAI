package PAI.VOs;

import PAI.ddd.ValueObject;

public class DurationCourseInCurricularYear implements ValueObject {

    private final int duration;

    public DurationCourseInCurricularYear(int duration) {
        this.duration = duration;
    }

    public boolean isDurationSemesterValid() {
        return duration > 0 && duration < 3;
    }

}
