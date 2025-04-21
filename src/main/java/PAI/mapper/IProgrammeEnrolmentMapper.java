package PAI.mapper;

import PAI.domain.ProgrammeEnrolment;
import PAI.persistence.datamodel.ProgrammeEnrolmentDataModel;

public interface IProgrammeEnrolmentMapper {

    ProgrammeEnrolmentDataModel toDataModel(ProgrammeEnrolment student);

    ProgrammeEnrolment toDomain(ProgrammeEnrolmentDataModel programmeEnrolmentDataModel);
}