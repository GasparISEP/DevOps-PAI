package PAI.domain;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.ddd.AggregateRoot;

public class CourseEdition_2 implements AggregateRoot<CourseEditionID> {

    private CourseEditionID _courseEditionID;
    private CourseInStudyPlanID _courseInStudyPlanID;
    private ProgrammeEditionID _programmeEditionID;

    public CourseEdition_2 (CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) throws Exception {
        if (programmeEditionID == null || courseInStudyPlanID == null) {
            throw new IllegalArgumentException ("ProgrammeEdition must be valid");
        }

        if (courseInStudyPlanID == null) {
            throw new IllegalArgumentException("CourseInStudyPlanID must be valid");
        }

        _courseEditionID = new CourseEditionID();
        _courseInStudyPlanID = courseInStudyPlanID;
        _programmeEditionID = programmeEditionID;
    }

    @Override
    public CourseEditionID identity() {
        return _courseEditionID;}

    @Override
    public boolean sameAs(Object objectToCompare) {
        if (!(objectToCompare instanceof CourseEdition_2)) {
            return false;
        }
        CourseEdition_2 courseEditionTest = (CourseEdition_2) objectToCompare;

        return _courseInStudyPlanID.equals(courseEditionTest._courseInStudyPlanID) &&
                _programmeEditionID.equals(courseEditionTest._programmeEditionID);
    }

    public ProgrammeEditionID getProgrammeEditionID() {
        return _programmeEditionID;
    }

    public CourseInStudyPlanID getCourseInStudyPlanID() {
        return _courseInStudyPlanID;
    }

}

