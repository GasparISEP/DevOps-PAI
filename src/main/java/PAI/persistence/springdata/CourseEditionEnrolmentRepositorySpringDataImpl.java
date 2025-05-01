package PAI.persistence.springdata;

import PAI.VOs.CourseEditionEnrolmentID;
import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.mapper.ICourseEditionEnrolmentIDMapper;
import PAI.mapper.ICourseEditionEnrolmentMapper;
import PAI.mapper.IStudentIDMapper;
import PAI.mapper.courseEdition.ICourseEditionIDMapper;
import PAI.persistence.datamodel.CourseEditionEnrolmentDataModel;
import PAI.persistence.datamodel.CourseEditionEnrolmentIDDataModel;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CourseEditionEnrolmentRepositorySpringDataImpl implements ICourseEditionEnrolmentRepository {

    private final ICourseEditionEnrolmentRepositorySpringData iCEERepoSpringData;
    private final ICourseEditionEnrolmentMapper iCEEMapper;
    private final ICourseEditionEnrolmentIDMapper iCEEIDMapper;
    private final IStudentIDMapper iStudentIDMapper;
    private final ICourseEditionIDMapper icourseEditionIDMapper;

    public CourseEditionEnrolmentRepositorySpringDataImpl(
            ICourseEditionEnrolmentRepositorySpringData springDataRepository,
            ICourseEditionEnrolmentMapper mapper, ICourseEditionEnrolmentIDMapper idMapper,
            IStudentIDMapper iStudentIDMapper, ICourseEditionIDMapper iCourseEditionIDMapper
    ) {
        if (springDataRepository == null) throw new IllegalArgumentException("Spring Data Repository cannot be null!");
        this.iCEERepoSpringData = springDataRepository;
        if (mapper == null) throw new IllegalArgumentException("Mapper cannot be null!");
        this.iCEEMapper = mapper;
        if (idMapper == null) throw new IllegalArgumentException("The ID Mapper cannot be null!");
        this.iCEEIDMapper = idMapper;
        if (iStudentIDMapper == null) throw new IllegalArgumentException("The ID Student Mapper cannot be null!");
        this.iStudentIDMapper = iStudentIDMapper;
        if (iCourseEditionIDMapper == null)
            throw new IllegalArgumentException("The ID Course Edition Mapper cannot be null!");
        this.icourseEditionIDMapper = iCourseEditionIDMapper;
    }

    @Override
    public boolean isStudentEnrolledInCourseEdition(StudentID studentId, CourseEditionID courseEditionId) throws Exception {

        StudentIDDataModel studentIDDataModel = iStudentIDMapper.domainToDataModel(studentId);

        CourseEditionIDDataModel courseEditionIDDataModel = icourseEditionIDMapper.toDataModel(courseEditionId);

        return iCEERepoSpringData.existsById_StudentIDAndId_CourseEditionIDAndActiveTrue(studentIDDataModel, courseEditionIDDataModel);
    }

    @Override
    public Optional<CourseEditionEnrolment> findByStudentAndEdition(StudentID studentId, CourseEditionID courseEditionId) {
        try {
            StudentIDDataModel studentIDDataModel = iStudentIDMapper.domainToDataModel(studentId);
            CourseEditionIDDataModel courseEditionIDDataModel = icourseEditionIDMapper.toDataModel(courseEditionId);

            Optional<CourseEditionEnrolmentDataModel> dataModel =
                    iCEERepoSpringData.findById_StudentIDAndId_CourseEditionID(studentIDDataModel, courseEditionIDDataModel);

            if (dataModel.isEmpty()) return Optional.empty();

            return iCEEMapper.toDomain(dataModel.get());

        } catch (Exception e) {
            return Optional.empty();
        }
    }


    @Override
    public int numberOfStudentsEnrolledInCourseEdition(CourseEditionID courseEditionId) throws Exception {
        CourseEditionIDDataModel courseEditionIDDataModel = icourseEditionIDMapper.toDataModel(courseEditionId);
        return (int) iCEERepoSpringData.countById_CourseEditionIDAndActiveTrue(courseEditionIDDataModel);
    }


    @Override
    public void enrolStudentInProgrammeCourseEditions(StudentID studentId, List<CourseEditionID> courseEditions) throws Exception {
        for (CourseEditionID courseEditionId : courseEditions) {
            Optional<CourseEditionEnrolment> existingEnrollment = this.findByStudentAndEdition(studentId, courseEditionId);

            if (existingEnrollment.isPresent()) {
                throw new IllegalStateException("This course edition enrolment is already in the list.");
            }

            CourseEditionEnrolment newEnrolment = new CourseEditionEnrolment(studentId, courseEditionId);
            this.save(newEnrolment);
        }
    }

    @Override
    public Set<CourseEditionEnrolment> getInternalSet() throws Exception {
        return new HashSet<>();
    }

    @Override
    public CourseEditionEnrolment save(CourseEditionEnrolment entity) throws Exception {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null!");
        }

        Optional<CourseEditionEnrolmentDataModel> entitySaved = iCEEMapper.toDataModel(entity);

        if (entitySaved.isEmpty()) {
            throw new IllegalArgumentException("Entity cannot be empty!");
        }

        CourseEditionEnrolmentDataModel ceeDataModel = iCEERepoSpringData.save(entitySaved.get());

        Optional<CourseEditionEnrolment> courseEditionEnrolment = iCEEMapper.toDomain(ceeDataModel);

        if (courseEditionEnrolment.isEmpty()) {
            throw new IllegalArgumentException("Course Edition Enrolment cannot be empty!");
        }

        return courseEditionEnrolment.get();
    }

    @Override
    public Iterable<CourseEditionEnrolment> findAll() {
        return iCEERepoSpringData.findAll().stream()
                .map(courseEditionEnrolmentDataModel -> {
                    try {
                        return iCEEMapper.toDomain(courseEditionEnrolmentDataModel)
                                .orElseThrow(() -> new IllegalArgumentException("Could not map Course Edition Enrolment."));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CourseEditionEnrolment> ofIdentity(CourseEditionEnrolmentID id) {

        Optional<CourseEditionEnrolmentIDDataModel> ceeIDDataModel;
        try {
            ceeIDDataModel = iCEEIDMapper.toDataModel(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (ceeIDDataModel.isEmpty()) {
            return Optional.empty();
        }
        Optional<CourseEditionEnrolmentDataModel> ceeDataModel = iCEERepoSpringData.findById(ceeIDDataModel.get());

        Optional<CourseEditionEnrolment> cee;
        try {
            cee = iCEEMapper.toDomain(ceeDataModel.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return cee;
    }

    @Override
    public boolean containsOfIdentity(CourseEditionEnrolmentID id) {

        Optional<CourseEditionEnrolmentIDDataModel> ceeIDDataModel;
        try {
            ceeIDDataModel = iCEEIDMapper.toDataModel(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (ceeIDDataModel.isEmpty()) {
            return false;
        }

        return iCEERepoSpringData.existsById(ceeIDDataModel.get());
    }
}
