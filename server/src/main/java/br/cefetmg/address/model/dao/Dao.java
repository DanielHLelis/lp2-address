package br.cefetmg.address.model.dao;

import java.util.List;

/**
 * Defines the class who manages the persistence of a model
 *
 * @param <M> model to persist
 */
public interface Dao<M> {
  /**
   * Inserts a new entity to persist
   *
   * @param model the entity to persist
   * @throws DaoException in case of failure
   */
  void insert(M model) throws DaoException;

  /**
   * Updates the entity in persistence
   *
   * @param model the entity to persist
   * @throws DaoException in case of failure
   */
  void update(M model) throws DaoException;

  /**
   * Remove an entity from persistence
   *
   * @param id the entity to remove
   * @return the removed entity
   * @throws DaoException in case of failure
   */
  M delete(Long id) throws DaoException;

  /**
   * Recover an entity from persistence
   *
   * @param id the entity to recover
   * @throws DaoException in case of failure
   */
  M getById(Long id) throws DaoException;

  /**
   * Recover every entity in persistence
   *
   * @return every entity in persistence
   * @throws DaoException in case of failure
   */
  List<M> getAll() throws DaoException;

}
