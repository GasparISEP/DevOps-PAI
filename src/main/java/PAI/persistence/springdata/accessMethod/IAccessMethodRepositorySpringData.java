package PAI.persistence.springdata.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.persistence.datamodel.accessMethod.AccessMethodDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IAccessMethodRepositorySpringData extends JpaRepository<AccessMethodDataModel, AccessMethodID> {
    Optional<AccessMethodDataModel> findAccessMethodDataModelByName(String name);
    Optional<AccessMethodDataModel> findAccessMethodDataModelByAccessMethodID(UUID accessMethodID);
}
