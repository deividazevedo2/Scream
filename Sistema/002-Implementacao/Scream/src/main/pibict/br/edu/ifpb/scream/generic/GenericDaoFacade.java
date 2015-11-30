package br.edu.ifpb.scream.generic;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Mauricio
 * @param <T>
 */
public interface GenericDaoFacade<T> extends Serializable {

    int count();

    void create(T entity);

    void edit(T entity);

    T find(Long id);

    List<T> findAll();

    List<T> findRange(int[] range);

    public void remove(T entity);

}
