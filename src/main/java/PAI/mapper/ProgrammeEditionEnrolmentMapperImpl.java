package PAI.mapper;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.domain.ProgrammeEditionEnrolment;
import PAI.factory.IProgrammeEditionEnrolmentFactory;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentIDDataModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
        LocalDate enrolmentDate = dataModel.getEnrolmentDate();

        return Optional.of(_programmeEditionEnrolmentFactory.createWithEnrolmentDate(studentID, programmeEditionId,enrolmentDate));
    }

    @Override
    public Optional<ProgrammeEditionEnrolmentDataModel> toDataModel(ProgrammeEditionEnrolment domain) {

        if(domain == null) {
            return Optional.empty();
        }

        Optional<ProgrammeEditionEnrolmentIDDataModel> idDataModel = _programmeEditionEnrolmentIDMapper.toDataModel(domain.identity());

        return Optional.of(new ProgrammeEditionEnrolmentDataModel(idDataModel.get(),domain.getEnrolmentDate()));
    }
}
