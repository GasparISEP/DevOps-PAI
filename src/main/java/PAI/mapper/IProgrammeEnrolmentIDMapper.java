package PAI.mapper;

import PAI.VOs.ProgrammeEnrolmentID;
import PAI.persistence.datamodel.ProgrammeEnrolmentIDDataModel;

public interface IProgrammeEnrolmentIDMapper {

    ProgrammeEnrolmentIDDataModel domainToDataModel (ProgrammeEnrolmentID programmeEnrolmentID);

    ProgrammeEnrolmentID dataModelToDomain (ProgrammeEnrolmentIDDataModel programmeEnrolmentIDDataModel);
}
