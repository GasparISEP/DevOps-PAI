package PAI.persistence.springdata;

import PAI.VOs.ProgrammeEnrolmentID;
import PAI.domain.ProgrammeEnrolment;
import PAI.mapper.IProgrammeEnrolmentIDMapper;
import PAI.mapper.IProgrammeEnrolmentMapper;
import PAI.persistence.datamodel.ProgrammeEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEnrolmentIDDataModel;

import java.util.Optional;

public class ProgrammeEnrolmentRepositorySpringData {

    private final IProgrammeEnrolmentRepositorySpringData _jpaRepo;
    private final IProgrammeEnrolmentIDMapper _idMapper;
    private final IProgrammeEnrolmentMapper _programmeEnrolmentMapper;

    public ProgrammeEnrolmentRepositorySpringData(
            IProgrammeEnrolmentRepositorySpringData jpaRepo,
            IProgrammeEnrolmentIDMapper idMapper,
            IProgrammeEnrolmentMapper programmeEnrolmentMapper) {

        if (jpaRepo == null) {
            throw new IllegalArgumentException("jpaRepo must not be null");
        }
        if (idMapper == null) {
            throw new IllegalArgumentException("idMapper must not be null");
        }
        if (programmeEnrolmentMapper == null) {
            throw new IllegalArgumentException("programmeEnrolmentMapper must not be null");
        }

        _jpaRepo = jpaRepo;
        _idMapper = idMapper;
        _programmeEnrolmentMapper = programmeEnrolmentMapper;
    }

    public ProgrammeEnrolment save(ProgrammeEnrolment enrolment) {
        ProgrammeEnrolmentDataModel dataModel = _programmeEnrolmentMapper.toDataModel(enrolment);
        if (dataModel == null) {
            throw new IllegalStateException("Mapper returned null for ProgrammeEnrolment");
        }

        ProgrammeEnrolmentDataModel saved = _jpaRepo.save(dataModel);
        return _programmeEnrolmentMapper.toDomain(saved);
    }

    public Optional<ProgrammeEnrolment> findById(ProgrammeEnrolmentID id) {
        ProgrammeEnrolmentIDDataModel idDataModel = _idMapper.domainToDataModel(id);
        return _jpaRepo.findById(idDataModel)
                .map(_programmeEnrolmentMapper::toDomain);
    }
}
