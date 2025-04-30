package PAI.persistence.springdata.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanIDMapper;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanMapper;
import PAI.mapper.studyPlan.IStudyPlanIDMapper;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import PAI.repository.courseInStudyPlanRepository.ICourseInStudyPlanRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseInStudyPlanRepositorySpringDataImpl implements ICourseInStudyPlanRepository {

    private final ICourseInStudyPlanRepositorySpringData iCourseInStudyPlanRepositorySpringData;
    private final ICourseInStudyPlanMapper iCourseInStudyPlanMapper;
    private final ICourseInStudyPlanIDMapper iCourseInStudyPlanIDMapper;
    private final IStudyPlanIDMapper iStudyPlanIDMapper;

    public CourseInStudyPlanRepositorySpringDataImpl(ICourseInStudyPlanMapper courseInStudyPlanMapper, ICourseInStudyPlanRepositorySpringData courseInStudyPlanRepositorySpringData, ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper, IStudyPlanIDMapper iStudyPlanIDMapper) {

        if (courseInStudyPlanMapper == null) {
            throw new IllegalArgumentException("iCourseInStudyPlanMapper cannot be null");
        }
        this.iCourseInStudyPlanMapper = courseInStudyPlanMapper;

        if (courseInStudyPlanRepositorySpringData == null) {
            throw new IllegalArgumentException("iCourseInStudyPlanRepositorySpringData cannot be null");
        }
        this.iCourseInStudyPlanRepositorySpringData = courseInStudyPlanRepositorySpringData;

        if (courseInStudyPlanIDMapper == null) {
            throw new IllegalArgumentException("iCourseInStudyPlanIDMapper cannot be null");
        }
        this.iCourseInStudyPlanIDMapper = courseInStudyPlanIDMapper;

        if (iStudyPlanIDMapper == null) {
            throw new IllegalArgumentException("iStudyPlanIDMapper cannot be null");
        }
        this.iStudyPlanIDMapper = iStudyPlanIDMapper;
    }

    @Override
    public CourseInStudyPlan save(CourseInStudyPlan courseInStudyPlan) throws Exception {

        if (courseInStudyPlan == null) {
            throw new IllegalArgumentException("Course In Study Plan cannot be null.");
        }

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
        CourseInStudyPlanIDDataModel idDataModel =
                iCourseInStudyPlanIDMapper.toDataModel(id);
        return iCourseInStudyPlanRepositorySpringData.existsById(idDataModel);
    }

    @Override
    public double getTotalCreditsEctsInStudyPlanSoFar(StudyPlanID studyPlanID, Semester semester, CurricularYear curricularYear, DurationCourseInCurricularYear duration) {
        if (studyPlanID == null || semester == null || curricularYear == null || duration == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        StudyPlanIDDataModel studyPlanIDDataModel = iStudyPlanIDMapper.toDataModel(studyPlanID);

        return iCourseInStudyPlanRepositorySpringData.sumCombinedCredits(studyPlanIDDataModel, semester.toInt(), curricularYear.toInt());
    }
}