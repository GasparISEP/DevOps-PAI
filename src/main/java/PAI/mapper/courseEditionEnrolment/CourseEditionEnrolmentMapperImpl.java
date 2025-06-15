package PAI.mapper.courseEditionEnrolment;

import PAI.VOs.*;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentFactory;
import PAI.persistence.datamodel.courseEditionEnrolment.CourseEditionEnrolmentDataModel;
import PAI.persistence.datamodel.courseEditionEnrolment.CourseEditionEnrolmentGeneratedIDDataModel;
import PAI.persistence.datamodel.courseEditionEnrolment.CourseEditionEnrolmentIDDataModel;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CourseEditionEnrolmentMapperImpl implements ICourseEditionEnrolmentMapper {

    private final ICourseEditionEnrolmentGeneratedIDMapper _generatedIDMapper;
    private final ICourseEditionEnrolmentIDMapper _idMapper;
    private final ICourseEditionEnrolmentFactory _factory;

    public CourseEditionEnrolmentMapperImpl(ICourseEditionEnrolmentGeneratedIDMapper generatedIDMapper, ICourseEditionEnrolmentIDMapper idMapper, ICourseEditionEnrolmentFactory factory) {
        if (generatedIDMapper == null || idMapper == null || factory == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        this._generatedIDMapper = generatedIDMapper;
        this._idMapper = idMapper;
        this._factory = factory;
    }

    @Override
    public Optional<CourseEditionEnrolmentDataModel> toDataModel(CourseEditionEnrolment domain) throws Exception {
        if (domain == null)
            return Optional.empty();

        Optional<CourseEditionEnrolmentIDDataModel> idDataModel = _idMapper.toDataModel(domain.identity());
        if (idDataModel.isEmpty()) return Optional.empty();

        CourseEditionEnrolmentGeneratedIDDataModel generatedIDDataModel = _generatedIDMapper.toDataModel(domain.getGeneratedID());

        return Optional.of(new CourseEditionEnrolmentDataModel(generatedIDDataModel, idDataModel.get(), domain.getEnrolmentDate().getLocalDate(),domain.isEnrolmentActive()));
    }

    @Override
    public Optional<CourseEditionEnrolment> toDomain(CourseEditionEnrolmentDataModel dataModel) throws Exception {
        if (dataModel == null) return Optional.empty();

        Optional<CourseEditionEnrolmentID> domainID = _idMapper.toDomain(dataModel.findId());
        if (domainID.isEmpty()) return Optional.empty();

        StudentID studentID = domainID.get().getStudentID();
        CourseEditionID courseEditionID = domainID.get().getCourseEditionID();

        CourseEditionEnrolmentGeneratedID generatedID = _generatedIDMapper.toDomain(dataModel.findGeneratedID());

        if (studentID == null || courseEditionID == null || dataModel.findEnrolmentDate() == null) {
            return Optional.empty();
        }
        Date enrolmentDate = new Date(dataModel.findEnrolmentDate());
        EnrolmentStatus enrolmentStatus = new EnrolmentStatus(dataModel.isActive());
        return Optional.of(_factory.createWithEnrolmentDateAndUUID(generatedID, studentID, courseEditionID, enrolmentDate, enrolmentStatus));
    }

    @Override
    public void updateDataModelFromDomain(CourseEditionEnrolment domain, CourseEditionEnrolmentDataModel dataModel) {
        if (domain == null) {
            throw new IllegalArgumentException("Domaincannot be null for update.");
        }
        else if (dataModel == null) {
            throw new IllegalArgumentException("DataModel cannot be null for update.");
        }
        dataModel.setActive(domain.isEnrolmentActive());
    }
}
