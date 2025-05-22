package PAI.dto.course;

import PAI.VOs.Acronym;
import PAI.VOs.CourseID;
import PAI.VOs.Name;

public class CourseDTOCommand {

    private final CourseID courseId;
    private final Name name;
    private final Acronym acronym;

    public CourseDTOCommand (CourseID courseId, Name name, Acronym acronym) {

        this.courseId = courseId;
        this.name = name;
        this.acronym = acronym;
    }

    public CourseID getCourseId () {
        return this.courseId;
    }

    public Name getName () {
        return this.name;
    }

    public Acronym getAcronym () {
        return this.acronym;
    }
}
