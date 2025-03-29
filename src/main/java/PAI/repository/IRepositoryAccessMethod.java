package PAI.repository;

import PAI.VOs.AccessMethodID;
import PAI.ddd.IRepository;
import PAI.domain.accessMethodDDD.AccessMethodDDD;

public interface IRepositoryAccessMethod extends IRepository<AccessMethodID, AccessMethodDDD> {
}
