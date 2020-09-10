package br.cefetmg.address.repository;

import br.cefetmg.address.models.Model;

import java.util.List;

public interface Repository<M extends Model> {
  void insert(M model) throws RepositoryException;

  void update(M model) throws RepositoryException;

  M delete(Long id) throws RepositoryException;

  M getById(Long id) throws RepositoryException;

  List<M> listAll() throws RepositoryException;

}
