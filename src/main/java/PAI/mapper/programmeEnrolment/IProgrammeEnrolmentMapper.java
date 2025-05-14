package PAI.mapper.programmeEnrolment;

import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.persistence.datamodel.programmeEnrolment.ProgrammeEnrolmentDataModel;

public interface IProgrammeEnrolmentMapper {

    ProgrammeEnrolmentDataModel toDataModel(ProgrammeEnrolment student);

    ProgrammeEnrolment toDomain(ProgrammeEnrolmentDataModel programmeEnrolmentDataModel);
}