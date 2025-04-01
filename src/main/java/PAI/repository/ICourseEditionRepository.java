package PAI.repository;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.ddd.IRepository;
import PAI.domain.CourseEdition_2;

public interface ICourseEditionRepository extends IRepository <CourseEditionID, CourseEdition_2> {

     boolean createAndSaveCourseEdition(CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID);

}
