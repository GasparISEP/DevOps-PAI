package PAI.repository;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.ddd.IRepository;
import PAI.domain.CourseEditionDDD;

import java.util.List;
import java.util.Optional;

public interface ICourseEditionRepositoryDDD extends IRepository <CourseEditionID, CourseEditionDDD> {

     boolean createAndSaveCourseEdition(CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID);

     List<CourseEditionID> findCourseEditionsByProgrammeEdition(ProgrammeEditionID programmeEditionId);

     Optional<CourseEditionID> findIdByCourseEdition (CourseEditionDDD courseEdition2);
}
