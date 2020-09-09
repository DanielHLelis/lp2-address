package br.cefetmg.address.persist;

import br.cefetmg.address.models.Model;
import java.util.List;

public interface DBModel <M extends Model> {
    public void insert(M model) throws PersistException;

    public void update(M model) throws PersistException;

    public M delete(Long id) throws PersistException;

    public M getById(Long id) throws PersistException;

    public List<M> listAll() throws PersistException ;
    
}
