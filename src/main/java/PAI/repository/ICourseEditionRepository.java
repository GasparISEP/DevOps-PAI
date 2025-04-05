package PAI.repository;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.ddd.IRepository;
import PAI.domain.CourseEdition;

import java.util.List;
import java.util.Optional;

public interface ICourseEditionRepository extends IRepository <CourseEditionID, CourseEdition> {

     boolean createAndSaveCourseEdition(CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID);

     List<CourseEditionID> findCourseEditionsByProgrammeEdition(ProgrammeEditionID programmeEditionId);

     Optional<CourseEditionID> findIdByCourseEdition (CourseEdition courseEdition2);
}
