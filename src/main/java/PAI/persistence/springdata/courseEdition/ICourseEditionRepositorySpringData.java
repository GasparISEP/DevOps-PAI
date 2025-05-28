package PAI.persistence.springdata.courseEdition;

import PAI.VOs.CourseInStudyPlanID;
import PAI.persistence.datamodel.courseEdition.CourseEditionDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICourseEditionRepositorySpringData extends JpaRepository<CourseEditionDataModel, CourseEditionIDDataModel> {

    @Query("SELECT ce FROM CourseEditionDataModel ce WHERE ce._courseEditionIDDataModel._programmeEditionIdDataModel = :programmeEditionId")
    List<CourseEditionIDDataModel> findCourseEditionIDByProgrammeEditionIDDataModel(
            @Param("programmeEditionId") ProgrammeEditionIdDataModel programmeEditionId);

            @Query("SELECT ce FROM CourseEditionDataModel ce WHERE ce._courseEditionIDDataModel._programmeEditionIdDataModel = :programmeEditionId AND ce._courseEditionIDDataModel._courseInStudyPlanIDDataModel = :courseInStudyPlanId")
            List<CourseEditionDataModel> findCourseEditionsByProgrammeEditionIDDataModelAndCourseInStudyPlanIDDataModel(
                @Param("programmeEditionId") ProgrammeEditionIdDataModel programmeEditionId,
                @Param("courseInStudyPlanId") CourseInStudyPlanIDDataModel courseInStudyPlanId);
            }
