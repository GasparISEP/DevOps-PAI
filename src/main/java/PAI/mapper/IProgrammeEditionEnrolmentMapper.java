package PAI.mapper;

import PAI.domain.ProgrammeEditionEnrolment;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentDataModel;

import java.util.Optional;

public interface IProgrammeEditionEnrolmentMapper {

   Optional<ProgrammeEditionEnrolment> toDomain(ProgrammeEditionEnrolmentDataModel dataModel);

    Optional<ProgrammeEditionEnrolmentDataModel> toDataModel(ProgrammeEditionEnrolment programmeEditionEnrolment);
}
