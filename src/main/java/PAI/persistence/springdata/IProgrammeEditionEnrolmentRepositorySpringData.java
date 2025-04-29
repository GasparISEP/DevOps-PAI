package PAI.persistence.springdata;

import PAI.VOs.ProgrammeEditionID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProgrammeEditionEnrolmentRepositorySpringData extends JpaRepository<ProgrammeEditionEnrolmentDataModel, ProgrammeEditionEnrolmentIDDataModel> {
    List<ProgrammeEditionEnrolmentDataModel> findAllBy_id_ProgrammeEditionIdDataModel(ProgrammeEditionIdDataModel programmeEditionIdDataModel);
}
