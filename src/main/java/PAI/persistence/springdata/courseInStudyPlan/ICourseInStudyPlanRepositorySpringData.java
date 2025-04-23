package PAI.persistence.springdata.courseInStudyPlan;

import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseInStudyPlanRepositorySpringData extends JpaRepository<CourseInStudyPlanDataModel, CourseInStudyPlanIDDataModel> {

}