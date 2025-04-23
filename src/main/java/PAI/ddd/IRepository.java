package PAI.ddd;

import java.util.Optional;

public abstract interface IRepository<ID extends DomainId, T extends AggregateRoot<ID> > {
  
  public T save(T entity) throws Exception;
  
  public Iterable<T> findAll();
  
  public Optional<T> ofIdentity(ID id);
  
  public boolean containsOfIdentity(ID id);
}