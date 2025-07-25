package PAI.persistence.springdata.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.mapper.programmeEnrolment.IProgrammeEnrolmentIDMapper;
import PAI.mapper.programmeEnrolment.IProgrammeEnrolmentMapper;
import PAI.mapper.student.IStudentIDMapper;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.persistence.datamodel.programmeEnrolment.ProgrammeEnrolmentDataModel;
import PAI.persistence.datamodel.programmeEnrolment.ProgrammeEnrolmentIDDataModel;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.domain.repositoryInterfaces.programmeEnrolment.IProgrammeEnrolmentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProgrammeEnrolmentRepositorySpringDataImpl implements IProgrammeEnrolmentRepository {

    private final IProgrammeEnrolmentRepositorySpringData jpaRepo;
    private final IProgrammeEnrolmentIDMapper idMapper;
    private final IProgrammeEnrolmentMapper programmeEnrolmentMapper;
    private final IStudentIDMapper studentIDMapper;
    private final IProgrammeIDMapper programmeIDMapper;

    public ProgrammeEnrolmentRepositorySpringDataImpl(
            IProgrammeEnrolmentRepositorySpringData jpaRepo,
            IProgrammeEnrolmentIDMapper idMapper,
            IProgrammeEnrolmentMapper programmeEnrolmentMapper,
            IStudentIDMapper studentIDMapper,
            IProgrammeIDMapper programmeIDMapper) {

        if (jpaRepo == null) throw new IllegalArgumentException("jpaRepo must not be null");
        if (idMapper == null) throw new IllegalArgumentException("idMapper must not be null");
        if (programmeEnrolmentMapper == null) throw new IllegalArgumentException("programmeEnrolmentMapper must not be null");
        if (studentIDMapper == null) throw new IllegalArgumentException("studentIDMapper must not be null");
        if (programmeIDMapper == null) throw new IllegalArgumentException("programmeIDMapper must not be null");

        this.jpaRepo = jpaRepo;
        this.idMapper = idMapper;
        this.programmeEnrolmentMapper = programmeEnrolmentMapper;
        this.studentIDMapper = studentIDMapper;
        this.programmeIDMapper = programmeIDMapper;
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
    public boolean isStudentEnrolled(StudentID studentID, ProgrammeID programmeID) {
        StudentIDDataModel studentIDDataModel = studentIDMapper.domainToDataModel(studentID);
        ProgrammeIDDataModel programmeIDDataModel = programmeIDMapper.toData(programmeID);

        return jpaRepo.existsByProgrammeEnrolmentIDPeStudentIDAndProgrammeEnrolmentIDPeProgrammeID(studentIDDataModel, programmeIDDataModel);
    }

    @Override
    public List<ProgrammeID> findProgrammesByStudent(StudentID studentID) {
        StudentIDDataModel studentIDDataModel = studentIDMapper.domainToDataModel(studentID);
        List<ProgrammeIDDataModel> dataModels = jpaRepo.findProgrammeIDsByStudentID(studentIDDataModel);
        return dataModels.stream()
                .map(programmeIDMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProgrammeEnrolment> findByStudentIDAndProgrammeID(StudentID studentID, ProgrammeID programmeID) {

        StudentIDDataModel sidDM = studentIDMapper.domainToDataModel(studentID);
        ProgrammeIDDataModel pidDM = programmeIDMapper.toData(programmeID);


        Optional<ProgrammeEnrolmentDataModel> dmOpt =
                jpaRepo.findByProgrammeEnrolmentIDPeStudentIDAndProgrammeEnrolmentIDPeProgrammeID(sidDM, pidDM);

        return dmOpt.map(programmeEnrolmentMapper::toDomain);
    }

    @Override
    public Optional<ProgrammeEnrolment> findByGeneratedID(ProgrammeEnrolmentGeneratedID gid) {
        return jpaRepo.findByProgrammeEnrolmentGID(gid.getProgrammeEnrolmentGID())
                .map(programmeEnrolmentMapper::toDomain);
    }

    @Override
    public List<ProgrammeEnrolment> getProgrammesStudentIsEnrolledIn(StudentID studentID) {
        StudentIDDataModel studentIDDataModel = studentIDMapper.domainToDataModel(studentID);
        List<ProgrammeEnrolmentDataModel> dataModels = jpaRepo.findByProgrammeEnrolmentID_PeStudentID(studentIDDataModel);
        return dataModels.stream()
                .map(programmeEnrolmentMapper::toDomain)
                .collect(Collectors.toList());
    }
}


