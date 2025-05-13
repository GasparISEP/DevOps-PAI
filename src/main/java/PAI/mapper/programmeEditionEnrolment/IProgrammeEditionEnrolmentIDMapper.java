package PAI.mapper.programmeEditionEnrolment;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.persistence.datamodel.programmeEditionEnrolment.ProgrammeEditionEnrolmentIDDataModel;

import java.util.Optional;

public interface IProgrammeEditionEnrolmentIDMapper {

    Optional<ProgrammeEditionEnrolmentID> toDomain(ProgrammeEditionEnrolmentIDDataModel dataModel);

    Optional<ProgrammeEditionEnrolmentIDDataModel> toDataModel(ProgrammeEditionEnrolmentID domainId);
}
