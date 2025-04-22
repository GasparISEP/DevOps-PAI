package PAI.persistence.springdata.accessMethod;

import PAI.persistence.datamodel.accessMethod.AccessMethodDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IAccessMethodRepositorySpringData extends JpaRepository<AccessMethodDataModel, Long> {
    Optional<AccessMethodDataModel> findAccessMethodDataModelByName(String name);
    Optional<AccessMethodDataModel> findAccessMethodDataModelByAccessMethodID(UUID accessMethodID);
}
