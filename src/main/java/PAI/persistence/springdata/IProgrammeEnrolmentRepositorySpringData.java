package PAI.persistence.springdata;

import PAI.persistence.datamodel.ProgrammeEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEnrolmentIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProgrammeEnrolmentRepositorySpringData extends JpaRepository <ProgrammeEnrolmentDataModel, ProgrammeEnrolmentIDDataModel> {
}
