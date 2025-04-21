package PAI.mapper.courseInStudyPlanID;

import PAI.VOs.CourseInStudyPlanID;
import PAI.mapper.CourseID.CourseIDMapperImpl;
import PAI.mapper.studyPlanID.StudyPlanIDMapperImpl;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import org.springframework.stereotype.Service;

@Service
public class CourseInStudyPlanIDMapperImpl implements ICourseInStudyPlanIDMapper {

    public CourseInStudyPlanIDDataModel toDataModel(CourseInStudyPlanID courseInStudyPlanID) {

        StudyPlanIDMapperImpl studyPlanIDMapper = new StudyPlanIDMapperImpl();
        CourseIDMapperImpl courseIDMapper = new CourseIDMapperImpl();

        return new CourseInStudyPlanIDDataModel(studyPlanIDMapper.toDataModel(courseInStudyPlanID.getStudyPlanID()),
                courseIDMapper.toDataModel(courseInStudyPlanID.getCourseID()));
    }

    public CourseInStudyPlanID toDomain(CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel) {

        StudyPlanIDMapperImpl StudyPlanMapper = new StudyPlanIDMapperImpl();
        CourseIDMapperImpl courseIDMapper = new CourseIDMapperImpl();

        return new CourseInStudyPlanID(courseIDMapper.toDomain(courseInStudyPlanIDDataModel.getCourseID()),
                StudyPlanMapper.toDomain(courseInStudyPlanIDDataModel.getStudyPlanIDDataModel()));
    }
}