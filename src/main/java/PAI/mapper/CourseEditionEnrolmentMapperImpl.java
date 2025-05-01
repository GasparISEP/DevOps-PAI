package PAI.mapper;

import PAI.VOs.*;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentFactory;
import PAI.persistence.datamodel.CourseEditionEnrolmentDataModel;
import PAI.persistence.datamodel.CourseEditionEnrolmentIDDataModel;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CourseEditionEnrolmentMapperImpl implements ICourseEditionEnrolmentMapper {

    private final ICourseEditionEnrolmentIDMapper idMapper;
    private final ICourseEditionEnrolmentFactory factory;

    public CourseEditionEnrolmentMapperImpl(ICourseEditionEnrolmentIDMapper idMapper, ICourseEditionEnrolmentFactory factory) {
        if (idMapper == null || factory == null) {
            throw new IllegalArgumentException("ID mapper and factory cannot be null");
        }
        this.idMapper = idMapper;
        this.factory = factory;
    }

    @Override
    public Optional<CourseEditionEnrolmentDataModel> toDataModel(CourseEditionEnrolment domain) throws Exception {
        if (domain == null)
            return Optional.empty();

        Optional<CourseEditionEnrolmentIDDataModel> idDataModel = idMapper.toDataModel(domain.identity());
        if (idDataModel.isEmpty()) return Optional.empty();

        return Optional.of(new CourseEditionEnrolmentDataModel(idDataModel.get(),domain.getEnrolmentDate().getLocalDate(),domain.isEnrolmentActive()));
    }

    @Override
    public Optional<CourseEditionEnrolment> toDomain(CourseEditionEnrolmentDataModel dataModel) throws Exception {
        if (dataModel == null) return Optional.empty();

        Optional<CourseEditionEnrolmentID> domainID = idMapper.toDomain(dataModel.findId());
        if (domainID.isEmpty()) return Optional.empty();

        StudentID studentID = domainID.get().getStudentID();
        CourseEditionID courseEditionID = domainID.get().getCourseEditionID();

        if (studentID == null || courseEditionID == null || dataModel.findEnrolmentDate() == null) {
            return Optional.empty();
        }
        Date enrolmentDate = new Date(dataModel.findEnrolmentDate());
        EnrolmentStatus enrolmentStatus = new EnrolmentStatus(dataModel.isActive());
        return Optional.of(factory.createWithEnrolmentDate(studentID, courseEditionID, enrolmentDate, enrolmentStatus));
    }
}
