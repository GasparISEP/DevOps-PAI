package PAI.persistence.springdata;

import PAI.VOs.*;
import PAI.domain.ProgrammeEnrolment;
import PAI.factory.ProgrammeEnrolmentFactoryImpl;
import PAI.mapper.ProgrammeEnrolmentIDMapper;
import PAI.mapper.ProgrammeEnrolmentMapper;
import PAI.persistence.datamodel.ProgrammeEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEnrolmentIDDataModel;
import PAI.repository.IProgrammeEnrolmentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProgrammeEnrolmentRepositorySpringData implements IProgrammeEnrolmentRepository {

    private final IProgrammeEnrolmentRepositorySpringData jpaRepo;
    private final ProgrammeEnrolmentIDMapper idMapper;
    private final ProgrammeEnrolmentMapper programmeEnrolmentMapper;
    private final ProgrammeEnrolmentFactoryImpl programmeEnrolmentFactory;

    public ProgrammeEnrolmentRepositorySpringData(
            IProgrammeEnrolmentRepositorySpringData jpaRepo,
            ProgrammeEnrolmentIDMapper idMapper,
            ProgrammeEnrolmentMapper programmeEnrolmentMapper,
            ProgrammeEnrolmentFactoryImpl programmeEnrolmentFactory) {

        if (jpaRepo == null) throw new IllegalArgumentException("jpaRepo must not be null");
        if (idMapper == null) throw new IllegalArgumentException("idMapper must not be null");
        if (programmeEnrolmentMapper == null) throw new IllegalArgumentException("programmeEnrolmentMapper must not be null");
        if (programmeEnrolmentFactory == null) throw new IllegalArgumentException("programmeEnrolmentFactory must not be null");

        this.jpaRepo = jpaRepo;
        this.idMapper = idMapper;
        this.programmeEnrolmentMapper = programmeEnrolmentMapper;
        this.programmeEnrolmentFactory = programmeEnrolmentFactory;
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

    public boolean isEnrolmentRepeated(ProgrammeEnrolment newEnrolment) {
        return findAll().iterator().hasNext() &&
                ((List<ProgrammeEnrolment>) findAll())
                        .stream()
                        .anyMatch(existing -> existing.hasSameEnrolment(newEnrolment));
    }

    @Override
    public boolean enrolStudents(StudentID studentID, AccessMethodID accessMethodID, ProgrammeID programmeID, Date enrolmentDate) throws Exception {
        ProgrammeEnrolment newProgrammeEnrolment = programmeEnrolmentFactory.createProgrammeEnrolment(studentID, accessMethodID, programmeID, enrolmentDate);

        if (!isEnrolmentRepeated(newProgrammeEnrolment)) {
            save(newProgrammeEnrolment);
            return true;
        } else {
            throw new Exception("Student is already enrolled in the programme.");
        }
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


