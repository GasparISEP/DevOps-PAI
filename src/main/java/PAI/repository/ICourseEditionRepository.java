package PAI.repository;

import PAI.VOs.CourseEditionID;
import PAI.VOs.ProgrammeEditionID;
import PAI.ddd.IRepository;
import PAI.domain.CourseEdition;

import java.util.List;
import java.util.Optional;

public interface ICourseEditionRepository extends IRepository <CourseEditionID, CourseEdition> {

     List<CourseEditionID> findCourseEditionsByProgrammeEditionID(ProgrammeEditionID programmeEditionId);

     Optional<CourseEditionID> findIdByCourseEdition (CourseEdition courseEdition2);

     ProgrammeEditionID findWhichProgrammeEditionBelongsToACourseEdition(CourseEdition courseEdition) throws Exception;
}
