package PAI.mapper.programmeEditionEnrolment;

import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.persistence.datamodel.programmeEditionEnrolment.ProgrammeEditionEnrolmentDataModel;

import java.util.Optional;

public interface IProgrammeEditionEnrolmentMapper {

   Optional<ProgrammeEditionEnrolment> toDomain(ProgrammeEditionEnrolmentDataModel dataModel);

    Optional<ProgrammeEditionEnrolmentDataModel> toDataModel(ProgrammeEditionEnrolment programmeEditionEnrolment);
}
