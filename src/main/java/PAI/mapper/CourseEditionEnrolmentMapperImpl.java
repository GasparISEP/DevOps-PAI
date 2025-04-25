package PAI.mapper;

import PAI.VOs.*;
import PAI.domain.CourseEditionEnrolment;
import PAI.factory.ICourseEditionEnrolmentFactory;
import PAI.persistence.datamodel.CourseEditionEnrolmentDataModel;
import PAI.persistence.datamodel.CourseEditionEnrolmentIDDataModel;

import java.util.Optional;

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

        return Optional.of(
                new CourseEditionEnrolmentDataModel(
                        idDataModel.get(),
                        domain.getEnrolmentDate(),
                        domain.isEnrolmentActive()
                )
        );
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

        return Optional.of(
                factory.createWithEnrolmentDate(
                        studentID,
                        courseEditionID,
                        dataModel.findEnrolmentDate(),
                        dataModel.isActive()
                )
        );
    }
}
