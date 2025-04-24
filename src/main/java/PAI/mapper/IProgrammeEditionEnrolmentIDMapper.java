package PAI.mapper;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentIDDataModel;

import java.util.Optional;

public interface IProgrammeEditionEnrolmentIDMapper {

    Optional<ProgrammeEditionEnrolmentID> toDomain(ProgrammeEditionEnrolmentIDDataModel dataModel);

    Optional<ProgrammeEditionEnrolmentIDDataModel> toDataModel(ProgrammeEditionEnrolmentID domainId);
}
