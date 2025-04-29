package PAI.persistence.springdata;

import PAI.VOs.*;
import PAI.domain.ProgrammeEnrolment;
import PAI.mapper.IProgrammeEnrolmentIDMapper;
import PAI.mapper.IProgrammeEnrolmentMapper;
import PAI.persistence.datamodel.ProgrammeEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEnrolmentIDDataModel;
import PAI.repository.IProgrammeEnrolmentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProgrammeEnrolmentRepositorySpringDataImpl implements IProgrammeEnrolmentRepository {

    private final IProgrammeEnrolmentRepositorySpringData jpaRepo;
    private final IProgrammeEnrolmentIDMapper idMapper;
    private final IProgrammeEnrolmentMapper programmeEnrolmentMapper;

    public ProgrammeEnrolmentRepositorySpringDataImpl(
            IProgrammeEnrolmentRepositorySpringData jpaRepo,
            IProgrammeEnrolmentIDMapper idMapper,
            IProgrammeEnrolmentMapper programmeEnrolmentMapper) {

        if (jpaRepo == null) throw new IllegalArgumentException("jpaRepo must not be null");
        if (idMapper == null) throw new IllegalArgumentException("idMapper must not be null");
        if (programmeEnrolmentMapper == null) throw new IllegalArgumentException("programmeEnrolmentMapper must not be null");

        this.jpaRepo = jpaRepo;
        this.idMapper = idMapper;
        this.programmeEnrolmentMapper = programmeEnrolmentMapper;
    }

    @Override
    public ProgrammeEnrolment save(ProgrammeEnrolment enrolment) {
        ProgrammeEnrolmentDataModel dataModel = programmeEnrolmentMapper.toDataModel(enrolment);
        if (dataModel == null) {
            throw new IllegalStateException("Data model is null");
        }
        ProgrammeEnrolmentDataModel saved = jpaRepo.save(dataModel);
        return programmeEnrolmentMapper.toDomain(saved);
    }

    @Override
    public Optional<ProgrammeEnrolment> ofIdentity(ProgrammeEnrolmentID id) {
        ProgrammeEnrolmentIDDataModel idDataModel = idMapper.domainToDataModel(id);
        return jpaRepo.findById(idDataModel)
                .map(programmeEnrolmentMapper::toDomain);
    }

    @Override
    public boolean containsOfIdentity(ProgrammeEnrolmentID id) {
        ProgrammeEnrolmentIDDataModel idDataModel = idMapper.domainToDataModel(id);
        return jpaRepo.existsById(idDataModel);
    }

    @Override
    public Iterable<ProgrammeEnrolment> findAll() {
        List<ProgrammeEnrolmentDataModel> dataModels = jpaRepo.findAll();
        return dataModels.stream()
                .map(programmeEnrolmentMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean enrolStudents(StudentID studentID, AccessMethodID accessMethodID, ProgrammeID programmeID, Date enrolmentDate) throws Exception {
        return false;
    }

    @Override
    public boolean isStudentEnrolled(StudentID studentID, ProgrammeID programmeID) {
        return ((List<ProgrammeEnrolment>) findAll())
                .stream()
                .anyMatch(enrolment ->
                        enrolment.hasSameStudent(studentID) &&
                                enrolment.hasSameProgramme(programmeID));
    }
}


