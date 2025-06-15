package PAI.persistence.springdata.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanIDMapper;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanMapper;
import PAI.mapper.studyPlan.IStudyPlanIDMapper;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import PAI.domain.repositoryInterfaces.courseInStudyPlan.ICourseInStudyPlanRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static PAI.utils.ValidationUtils.validateNotNull;

@Repository
public class CourseInStudyPlanRepositorySpringDataImpl implements ICourseInStudyPlanRepository {

    private final ICourseInStudyPlanRepositorySpringData iCourseInStudyPlanRepositorySpringData;
    private final ICourseInStudyPlanMapper iCourseInStudyPlanMapper;
    private final ICourseInStudyPlanIDMapper iCourseInStudyPlanIDMapper;
    private final IStudyPlanIDMapper iStudyPlanIDMapper;

    public CourseInStudyPlanRepositorySpringDataImpl(ICourseInStudyPlanMapper courseInStudyPlanMapper, ICourseInStudyPlanRepositorySpringData courseInStudyPlanRepositorySpringData, ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper, IStudyPlanIDMapper iStudyPlanIDMapper) {
        this.iCourseInStudyPlanMapper = validateNotNull(courseInStudyPlanMapper, "CourseInStudyPlanMapper");
        this.iCourseInStudyPlanRepositorySpringData = validateNotNull(courseInStudyPlanRepositorySpringData, "CourseInStudyPlanRepositorySpringData");
        this.iCourseInStudyPlanIDMapper = validateNotNull(courseInStudyPlanIDMapper, "CourseInStudyPlanIDMapper");
        this.iStudyPlanIDMapper = validateNotNull(iStudyPlanIDMapper, "StudyPlanIDMapper");
    }

    @Override
    public CourseInStudyPlan save(CourseInStudyPlan courseInStudyPlan) throws Exception {
        validateNotNull(courseInStudyPlan, "Course In Study Plan");

        CourseInStudyPlanDataModel dataModel = iCourseInStudyPlanMapper.toDataModel(courseInStudyPlan);

        iCourseInStudyPlanRepositorySpringData.save(dataModel);

        return iCourseInStudyPlanMapper.toDomain(dataModel);

    }

    @Override
    public Iterable<CourseInStudyPlan> findAll() {
        List<CourseInStudyPlan> allCoursesInStudyPlan = new ArrayList<>();

        iCourseInStudyPlanRepositorySpringData.findAll().forEach(dataModel -> {
            if(dataModel != null) {

                try {
            CourseInStudyPlan courseInStudyPlan = iCourseInStudyPlanMapper.toDomain(dataModel);
                    allCoursesInStudyPlan.add(courseInStudyPlan);

                } catch (Exception e) {
                throw new RuntimeException(e);
                }
            }
        });
        return allCoursesInStudyPlan;
    }

    @Override
    public Optional<CourseInStudyPlan> ofIdentity(CourseInStudyPlanID id) {
        if (id == null) {
            return Optional.empty();
        }

        CourseInStudyPlanIDDataModel idDM = iCourseInStudyPlanIDMapper.toDataModel(id);

        Optional<CourseInStudyPlanDataModel> opt = iCourseInStudyPlanRepositorySpringData.findById(idDM);

        return opt.map(dm -> {
            try {
                return iCourseInStudyPlanMapper.toDomain(dm);
            } catch (Exception e) {
                throw new RuntimeException("Error mapping CourseInStudyPlanDataModel to domain", e);
            }
        });
    }

    @Override
    public boolean containsOfIdentity(CourseInStudyPlanID id) {
        CourseInStudyPlanIDDataModel idDataModel = iCourseInStudyPlanIDMapper.toDataModel(id);

        return iCourseInStudyPlanRepositorySpringData.existsById(idDataModel);
    }

    @Override
    public double getTotalCreditsEctsInStudyPlanSoFar(StudyPlanID studyPlanID, Semester semester, CurricularYear curricularYear, DurationCourseInCurricularYear duration) {
        validateNotNull(studyPlanID, "StudyPlanID");
        validateNotNull(semester, "Semester");
        validateNotNull(curricularYear, "CurricularYear");
        validateNotNull(duration, "DurationCourseInCurricularYear");

        StudyPlanIDDataModel studyPlanIDDataModel = iStudyPlanIDMapper.toDataModel(studyPlanID);

        Double credits = iCourseInStudyPlanRepositorySpringData.sumCombinedCredits(
                studyPlanIDDataModel,
                curricularYear.toInt(),
                semester.toInt()
        );

        return credits != null ? credits : 0.0;
    }
}