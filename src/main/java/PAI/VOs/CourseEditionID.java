package PAI.VOs;
import PAI.ddd.DomainId;

public class CourseEditionID implements DomainId {

    private final ProgrammeEditionID _programmeEditionID;
    private final CourseInStudyPlanID _courseInStudyPlanID;


    public CourseEditionID(ProgrammeEditionID programmeEditionID, CourseInStudyPlanID courseInStudyPlanID) {

        if (programmeEditionID == null) {
            throw new IllegalArgumentException ("ProgrammeEdition must be valid");
        }

        if (courseInStudyPlanID == null) {
            throw new IllegalArgumentException("CourseInStudyPlanID must be valid");
        }

        _programmeEditionID = programmeEditionID;
        _courseInStudyPlanID = courseInStudyPlanID;
    }

    @Override
    public String toString() {
        String courseEditionID = STR."CourseEditionID{_programmeEditionID=\{_programmeEditionID}, _courseInStudyPlanID=\{_courseInStudyPlanID}}";
        return courseEditionID;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass())
            return false;
        CourseEditionID courseEditionIDTest = (CourseEditionID) object;
        return _programmeEditionID.equals(courseEditionIDTest._programmeEditionID)
                && _courseInStudyPlanID.equals(courseEditionIDTest._courseInStudyPlanID);
    }

}