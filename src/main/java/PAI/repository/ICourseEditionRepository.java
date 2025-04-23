package PAI.repository;

import PAI.VOs.CourseEditionID;
import PAI.VOs.ProgrammeEditionID;
import PAI.ddd.IRepository;
import PAI.domain.CourseEdition;

import java.util.List;

public interface ICourseEditionRepository extends IRepository <CourseEditionID, CourseEdition> {

     List<CourseEditionID> findCourseEditionsByProgrammeEditionID(ProgrammeEditionID programmeEditionId);
}
