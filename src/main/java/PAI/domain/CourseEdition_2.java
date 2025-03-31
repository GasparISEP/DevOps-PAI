package PAI.domain;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.ddd.AggregateRoot;

public class CourseEdition_2 implements AggregateRoot<CourseEditionID> {

    private CourseEditionID _courseEditionID;
    private CourseInStudyPlanID _courseInStudyPlanID;
    private ProgrammeEditionID _programmeEditionID;

    public CourseEdition_2 (CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {

        if (programmeEditionID == null) {
            throw new IllegalArgumentException ("ProgrammeEdition must be valid");
        }

        if (courseInStudyPlanID == null) {
            throw new IllegalArgumentException("CourseInStudyPlanID must be valid");
        }

        _courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);
        _courseInStudyPlanID = courseInStudyPlanID;
        _programmeEditionID = programmeEditionID;
    }


    //Auxiliary constructor for tests, in order to define a specific CourseEditionID
    public CourseEdition_2(CourseEditionID courseEditionID, CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
        if (courseEditionID == null) {
            throw new IllegalArgumentException("CourseInStudyPlanID must be valid");
        }
        if (courseInStudyPlanID == null) {
            throw new IllegalArgumentException("CourseInStudyPlanID must be valid");
        }
        if (programmeEditionID == null) {
            throw new IllegalArgumentException("ProgrammeEdition must be valid");
        }
        this._courseEditionID = courseEditionID;
        this._courseInStudyPlanID = courseInStudyPlanID;
        this._programmeEditionID = programmeEditionID;
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

    @Override
    public boolean equals(Object objectToCompare) {

        if (this == objectToCompare)
            return true;

        if (objectToCompare instanceof CourseEdition_2) {

            CourseEdition_2 courseEdition = (CourseEdition_2) objectToCompare;

            if (_courseEditionID.equals(courseEdition._courseEditionID))
                return true;
        }
        return false;
    }
}

