package PAI.persistence.springdata.courseInStudyPlan;

import PAI.VOs.CourseInStudyPlanID;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanIDMapper;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanMapper;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.repository.courseInStudyPlanRepository.ICourseInStudyPlanRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseInStudyPlanRepositorySpringDataImpl implements ICourseInStudyPlanRepository {

    private final ICourseInStudyPlanRepositorySpringData iCourseInStudyPlanRepositorySpringData;
    private final ICourseInStudyPlanMapper iCourseInStudyPlanMapper;
    private final ICourseInStudyPlanIDMapper iCourseInStudyPlanIDMapper;

    public CourseInStudyPlanRepositorySpringDataImpl(ICourseInStudyPlanMapper courseInStudyPlanMapper, ICourseInStudyPlanRepositorySpringData courseInStudyPlanRepositorySpringData, ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper) {

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
}