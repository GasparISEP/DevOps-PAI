package PAI.persistence.springdata.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.persistence.datamodel.accessMethod.AccessMethodDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccessMethodRepositorySpringData extends JpaRepository<AccessMethodDataModel, AccessMethodID> {
}
