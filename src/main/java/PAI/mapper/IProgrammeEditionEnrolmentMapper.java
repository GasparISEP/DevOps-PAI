package PAI.mapper;

import PAI.domain.ProgrammeEditionEnrolment;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentDataModel;

public interface IProgrammeEditionEnrolmentMapper {

    ProgrammeEditionEnrolment toDomain(ProgrammeEditionEnrolmentDataModel dataModel);

    ProgrammeEditionEnrolmentDataModel toDataModel(ProgrammeEditionEnrolment programmeEditionEnrolment);
}
