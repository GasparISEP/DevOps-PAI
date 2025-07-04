package PAI.service.courseEdition;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;

import java.util.List;
import java.util.Optional;

public interface ICourseEditionService {

    CourseEdition createAndSaveCourseEdition (CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID);

    Iterable<CourseEdition> findAll();

    List<CourseEditionID> findCourseEditionsByProgrammeEditionID(ProgrammeEditionID programmeEditionId);

    Optional<CourseEdition> ofIdentity(CourseEditionID courseEditionID);

    boolean containsOfIdentity(CourseEditionID courseEditionID);

    List<CourseEditionID> findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(ProgrammeEditionID programmeEditionID, CourseInStudyPlanID courseInStudyPlanID) throws Exception;

    List <CourseEdition> getCourseEditionsByProgrammeIDAndCourseID(ProgrammeID programmeID, CourseID courseID);

    CourseEditionID findCourseEditionIDByGeneratedID (CourseEditionGeneratedID generatedID) throws Exception;

    List<SchoolYearID> getSchoolYearIDsFromCourseEditions(Iterable<CourseEdition> courseEditions);

    CourseEditionID buildCourseEditionID(String programmeAcronym, String schoolYearId, String courseAcronym, String courseName, String localDate);

}
