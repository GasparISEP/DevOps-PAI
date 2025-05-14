package PAI.domain.repositoryInterfaces.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.ProgrammeEditionID;
import PAI.ddd.IRepository;
import PAI.domain.courseEdition.CourseEdition;

import java.util.List;

public interface ICourseEditionRepository extends IRepository <CourseEditionID, CourseEdition> {

     List<CourseEditionID> findCourseEditionsByProgrammeEditionID(ProgrammeEditionID programmeEditionId);
}
