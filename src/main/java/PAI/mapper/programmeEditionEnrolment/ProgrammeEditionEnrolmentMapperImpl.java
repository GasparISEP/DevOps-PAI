package PAI.mapper.programmeEditionEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.programmeEditionEnrolment.IProgrammeEditionEnrolmentFactory;
import PAI.persistence.datamodel.programmeEditionEnrolment.ProgrammeEditionEnrolmentDataModel;
import PAI.persistence.datamodel.programmeEditionEnrolment.ProgrammeEditionEnrolmentIDDataModel;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProgrammeEditionEnrolmentMapperImpl implements IProgrammeEditionEnrolmentMapper {

    private final IProgrammeEditionEnrolmentIDMapper _programmeEditionEnrolmentIDMapper;
    private final IProgrammeEditionEnrolmentFactory _programmeEditionEnrolmentFactory;

    public ProgrammeEditionEnrolmentMapperImpl(IProgrammeEditionEnrolmentIDMapper programmeEditionEnrolmentIDMapper,
                                               IProgrammeEditionEnrolmentFactory programmeEditionEnrolmentFactory) {
        if (programmeEditionEnrolmentIDMapper == null) {
            throw new IllegalArgumentException("programmeEditionEnrolmentIDMapper cannot be null or blank");
        }

        if (programmeEditionEnrolmentFactory == null) {
            throw new IllegalArgumentException("programmeEditionEnrolmentFactory cannot be null or blank");
        }

        this._programmeEditionEnrolmentIDMapper = programmeEditionEnrolmentIDMapper;
        this._programmeEditionEnrolmentFactory = programmeEditionEnrolmentFactory;
    }

    @Override
    public Optional<ProgrammeEditionEnrolment> toDomain(ProgrammeEditionEnrolmentDataModel dataModel) {

        if(dataModel == null) {
            return Optional.empty();
        }

        Optional<ProgrammeEditionEnrolmentID> pEEID = _programmeEditionEnrolmentIDMapper.toDomain(dataModel.getProgrammeEditionEnrolmentIDDataModel());

        StudentID studentID = pEEID.get().getStudentiD();
        ProgrammeEditionID programmeEditionId = pEEID.get().getProgrammeEditionId();
        Date enrolmentDate = new Date(dataModel.getEnrolmentDate());
        EnrolmentStatus enrolmentStatus = new EnrolmentStatus(dataModel.isActive());
        ProgrammeEditionEnrolmentGeneratedID programmeEditionEnrolmentGeneratedID = new ProgrammeEditionEnrolmentGeneratedID(dataModel.getGeneratedID());
        return Optional.of(_programmeEditionEnrolmentFactory.createWithEnrolmentDateFromDataModel(studentID, programmeEditionId,enrolmentDate, enrolmentStatus, programmeEditionEnrolmentGeneratedID));
    }

    @Override
    public Optional<ProgrammeEditionEnrolmentDataModel> toDataModel(ProgrammeEditionEnrolment domain) {

        if(domain == null) {
            return Optional.empty();
        }

        Optional<ProgrammeEditionEnrolmentIDDataModel> idDataModel = _programmeEditionEnrolmentIDMapper.toDataModel(domain.identity());

        return Optional.of(new ProgrammeEditionEnrolmentDataModel(idDataModel.get(), domain.getEnrolmentDate().getLocalDate(),domain.isEnrolmentActive(), domain.getProgrammeEditionEnrolmentGeneratedID().toUUID()));
    }
}