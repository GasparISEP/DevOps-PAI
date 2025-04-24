package PAI.persistence.springdata;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.domain.ProgrammeEditionEnrolment;
import PAI.mapper.IProgrammeEditionEnrolmentIDMapper;
import PAI.mapper.IProgrammeEditionEnrolmentMapper;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentIDDataModel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProgrammeEditionEnrolmentRepositorySpringData {

    private final IProgrammeEditionEnrolmentRepositorySpringData _peeRepositorySpringData;
    private final IProgrammeEditionEnrolmentMapper _peeMapper;
    private final IProgrammeEditionEnrolmentIDMapper _peeIDMapper;

    public ProgrammeEditionEnrolmentRepositorySpringData(
            IProgrammeEditionEnrolmentRepositorySpringData peeRepositorySpringData,
            IProgrammeEditionEnrolmentMapper peeMapper,
            IProgrammeEditionEnrolmentIDMapper peeIDMapper) {

        if (peeRepositorySpringData == null)
            throw new IllegalArgumentException("ProgrammeEditionEnrolmentRepositorySpringData cannot be null");
        if (peeMapper == null)
            throw new IllegalArgumentException("ProgrammeEditionEnrolmentMapper cannot be null");
        if (peeIDMapper == null)
            throw new IllegalArgumentException("ProgrammeEditionEnrolmentIDMapper cannot be null");

        this._peeRepositorySpringData = peeRepositorySpringData;
        this._peeMapper = peeMapper;
        this._peeIDMapper = peeIDMapper;
    }

    public ProgrammeEditionEnrolment save(ProgrammeEditionEnrolment enrolment) {
        if (enrolment == null) {
            throw new IllegalArgumentException("ProgrammeEditionEnrolment cannot be null");
        }

        ProgrammeEditionEnrolmentDataModel peeDataModel = this._peeMapper.toDataModel(enrolment)
                .orElseThrow(() -> new IllegalStateException("Cannot map ProgrammeEditionEnrolment to data model"));

        ProgrammeEditionEnrolmentDataModel saved = this._peeRepositorySpringData.save(peeDataModel);

        return this._peeMapper.toDomain(saved)
                .orElseThrow(() -> new IllegalStateException("Failed to map saved entity back to domain"));
    }

    public Optional<ProgrammeEditionEnrolment> ofIdentity(ProgrammeEditionEnrolmentID peeID) {
        if (peeID == null) return Optional.empty();

        Optional<ProgrammeEditionEnrolmentIDDataModel> optionalDataModelID = this._peeIDMapper.toDataModel(peeID);

        if (optionalDataModelID.isEmpty()) return Optional.empty();

        Optional<ProgrammeEditionEnrolmentDataModel> optionalDataModel =
                this._peeRepositorySpringData.findById(optionalDataModelID.get());

        if (optionalDataModel.isEmpty()) return Optional.empty();

        Optional<ProgrammeEditionEnrolment> optionalDomain =
                this._peeMapper.toDomain(optionalDataModel.get());

        return optionalDomain;
    }


    // For each item in the list, turn it into a domain and give me a new list at the end
    public List<ProgrammeEditionEnrolment> findAll() {
        return this._peeRepositorySpringData.findAll().stream()
                .map(peeDataModel -> this._peeMapper.toDomain(peeDataModel)
                        .orElseThrow(() -> new IllegalStateException("Could not map ProgrammeEditionEnrolmentDataModel to domain")))
                .collect(Collectors.toList());
    }

    public boolean containsOfIdentity(ProgrammeEditionEnrolmentID peeID) {
        if (peeID == null)
            return false;

        return this._peeIDMapper.toDataModel(peeID)
                .map(this._peeRepositorySpringData::existsById)
                .orElse(false);
    }
}

