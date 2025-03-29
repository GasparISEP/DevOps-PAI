package PAI.repository;

import PAI.VOs.AccessMethodID;
import PAI.ddd.IRepository;
import PAI.domain.accessMethod.AccessMethodDDD;

public interface IRepositoryAccessMethod extends IRepository<AccessMethodID, AccessMethodDDD> {
}
