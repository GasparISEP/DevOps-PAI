package PAI.persistence.springdata.courseEditionEnrolment;

import PAI.VOs.CourseEditionEnrolmentGeneratedID;
import PAI.VOs.CourseEditionEnrolmentID;
import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.mapper.courseEditionEnrolment.ICourseEditionEnrolmentIDMapper;
import PAI.mapper.courseEditionEnrolment.ICourseEditionEnrolmentMapper;
import PAI.mapper.student.IStudentIDMapper;
import PAI.mapper.courseEdition.ICourseEditionIDMapper;
import PAI.persistence.datamodel.courseEditionEnrolment.CourseEditionEnrolmentDataModel;
import PAI.persistence.datamodel.courseEditionEnrolment.CourseEditionEnrolmentGeneratedIDDataModel;
import PAI.persistence.datamodel.courseEditionEnrolment.CourseEditionEnrolmentIDDataModel;
import PAI.domain.repositoryInterfaces.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
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

    public boolean existsByGeneratedID(CourseEditionEnrolmentGeneratedID generatedID) {
        if (generatedID == null) return false;
        CourseEditionEnrolmentGeneratedIDDataModel dataModel = new CourseEditionEnrolmentGeneratedIDDataModel(generatedID.getCourseEditionEnrolmentGeneratedID());
        return iCEERepoSpringData.existsByGeneratedID(dataModel);
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
    public Optional<CourseEditionEnrolment> findByGeneratedID(CourseEditionEnrolmentGeneratedID id) {
        try {
            CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM = new CourseEditionEnrolmentGeneratedIDDataModel(id.getCourseEditionEnrolmentGeneratedID());
            Optional<CourseEditionEnrolmentDataModel> dataModel = iCEERepoSpringData.findByGeneratedID(ceeGeneratedIDDM);
            if (dataModel.isPresent()) {
                return iCEEMapper.toDomain(dataModel.get());
            } else {
                return Optional.empty();
            }
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

        try {
            List<CourseEditionEnrolmentDataModel> dataModels = iCEERepoSpringData.findAll();
            Set<CourseEditionEnrolment> domainObjects = new HashSet<>();

            for (CourseEditionEnrolmentDataModel dataModel : dataModels) {
                iCEEMapper.toDomain(dataModel).ifPresent(domainObjects::add);
            }

            return domainObjects;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving the set", e);
        }
    }

    @Override
    public CourseEditionEnrolment save(CourseEditionEnrolment entity) throws Exception {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null!");
        }

        Optional<CourseEditionEnrolmentIDDataModel> dataModelIdOpt = iCEEIDMapper.toDataModel(entity.identity());
        if (dataModelIdOpt.isEmpty()) {
            throw new IllegalArgumentException("Could not map domain ID to data model ID for saving.");
        }

        CourseEditionEnrolmentIDDataModel dataModelId = dataModelIdOpt.get();
        CourseEditionEnrolmentDataModel ceeDataModel;
        Optional<CourseEditionEnrolmentDataModel> existingDataModelOpt = iCEERepoSpringData.findById(dataModelId);

        if (existingDataModelOpt.isPresent()) {
            ceeDataModel = existingDataModelOpt.get();
            iCEEMapper.updateDataModelFromDomain(entity, ceeDataModel);
        } else {
            ceeDataModel = iCEEMapper.toDataModel(entity)
                    .orElseThrow(() -> new IllegalArgumentException("Could not map domain entity to new data model for saving."));
        }

        CourseEditionEnrolmentDataModel savedDataModel = iCEERepoSpringData.save(ceeDataModel);
        Optional<CourseEditionEnrolment> courseEditionEnrolment = iCEEMapper.toDomain(savedDataModel);

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

    @Override
    public List<CourseEditionEnrolment> findByStudentID(StudentID studentID) {
        StudentIDDataModel studentIDData = iStudentIDMapper.domainToDataModel(studentID);

        List<CourseEditionEnrolmentDataModel> dataModels = iCEERepoSpringData.findById_StudentID(studentIDData);

        return dataModels.stream()
                .map(dataModel -> {
                    try {
                        return iCEEMapper.toDomain(dataModel)
                                .orElseThrow(() -> new RuntimeException("Empty Optional when mapping CourseEditionEnrolment"));
                    } catch (Exception e) {
                        throw new RuntimeException("Mapping failed", e);
                    }
                })
                .toList();
    }

    @Override
    public List<CourseEditionEnrolment> findActiveEnrolmentsByStudentID(StudentID studentID) {
        StudentIDDataModel studentIDData = iStudentIDMapper.domainToDataModel(studentID);
        List<CourseEditionEnrolmentDataModel> dataModels = iCEERepoSpringData.findById_StudentIDAndActiveTrue(studentIDData);

        return dataModels.stream()
                .map(dataModel -> {
                    try {
                        return iCEEMapper.toDomain(dataModel)
                                .orElseThrow(() -> new RuntimeException("Empty Optional when mapping CourseEditionEnrolment"));
                    } catch (Exception e) {
                        throw new RuntimeException("Mapping failed", e);
                    }
                })
                .toList();
    }
}
