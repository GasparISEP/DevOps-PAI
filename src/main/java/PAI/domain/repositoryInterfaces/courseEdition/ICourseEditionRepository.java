package PAI.domain.repositoryInterfaces.courseEdition;

import PAI.VOs.CourseEditionGeneratedID;
import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.ddd.IRepository;
import PAI.domain.courseEdition.CourseEdition;

import java.util.List;
import java.util.Optional;

public interface ICourseEditionRepository extends IRepository <CourseEditionID, CourseEdition> {
     List<CourseEditionID> findCourseEditionsByProgrammeEditionID(ProgrammeEditionID programmeEditionId);
     List<CourseEditionID> findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(ProgrammeEditionID programmeEditionId, CourseInStudyPlanID courseInStudyPlanId) throws Exception;
     Optional<CourseEdition> findCourseEditionByGeneratedId(CourseEditionGeneratedID id) throws Exception;
}
