package br.cefetmg.address.repository;

import br.cefetmg.address.models.Model;

import java.util.List;

/**
 * Defines the class who manages the persistence of a model
 * @param <M> model to persist
 */
public interface Repository<M extends Model> {
  /**
   * Inserts a new entity to persist
   *
   * @param model the entity to persist
   * @throws RepositoryException in case of failure
   */
  void insert(M model) throws RepositoryException;

  /**
   * Updates the entity in persistence
   *
   * @param model the entity to persist
   * @throws RepositoryException in case of failure
   */
  void update(M model) throws RepositoryException;

  /**
   * Remove an entity from persistence
   *
   * @param id the entity to remove
   * @throws RepositoryException in case of failure
   * @return the removed entity
   */
  M delete(Long id) throws RepositoryException;

  /**
   * Recover an entity from persistence
   *
   * @param id the entity to recover
   * @throws RepositoryException in case of failure
   */
  M getById(Long id) throws RepositoryException;

  /**
   * Recover every entity in persistence
   *
   * @return every entity in persistence
   * @throws RepositoryException in case of failure
   */
  List<M> listAll() throws RepositoryException;

}
