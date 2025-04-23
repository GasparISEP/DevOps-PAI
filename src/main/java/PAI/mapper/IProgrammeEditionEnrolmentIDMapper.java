package PAI.mapper;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentIDDataModel;

public interface IProgrammeEditionEnrolmentIDMapper {

    ProgrammeEditionEnrolmentID toDomain(ProgrammeEditionEnrolmentIDDataModel programmeEditionEnrolmentIDDataModel) throws Exception;

    ProgrammeEditionEnrolmentIDDataModel toDataModel(ProgrammeEditionEnrolmentID programmeEditionEnrolmentId) throws Exception;

    }
